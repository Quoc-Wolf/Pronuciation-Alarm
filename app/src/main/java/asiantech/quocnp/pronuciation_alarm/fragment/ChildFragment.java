package asiantech.quocnp.pronuciation_alarm.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.speech.RecognizerIntent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import asiantech.quocnp.pronuciation_alarm.R;
import asiantech.quocnp.pronuciation_alarm.models.database.Vocabulary;
import asiantech.quocnp.pronuciation_alarm.untils.SettingStore;

/**
 * A simple {@link BaseFragment} subclass.
 */

@SuppressWarnings("ALL")
@EFragment(R.layout.fragment_child)
public class ChildFragment extends BaseFragment {
    private static final String TAG = ChildFragment.class.getSimpleName();
    private static final int REQUEST_CODE_RESULT = 777;

    @ViewById(R.id.tvNameWord)
    TextView mTvNameWord;
    @ViewById(R.id.tvPronunciationWord)
    TextView mTvPronunciationWord;
    @ViewById(R.id.imgOfWord)
    ImageView mImgOfWord;
    List<Vocabulary> mVocabularies;
    Context mContext;
    //count size of list vocabularies
    private int mCount = 0;
    private Handler mHandler;

    private Intent mIntent;


    /**
     * This is constructor Child Fragment
     */
    public ChildFragment() {
        // Required empty public constructor
    }

    @Click(R.id.imgViewSpeed)
    protected void setDoSpeed() {
        speak();
    }

    //Demo click next
    @Click(R.id.btnNext)
    protected void clickNext() {
        setNext();
    }

    @AfterViews
    protected void afterView() {
        //create get Json data
        initDataVocabulary();
        setNext();
        //handle popchild Fragment
        handlerBackPopChilFragment();
    }

    /**
     * demo next word
     */
    private void setNext() {
        mVocabularies = initDataVocabulary();
        if (mCount < mVocabularies.size()) {
            //get string and img from json
            mTvNameWord.setText(mVocabularies.get(mCount).getNameWord());
            mTvPronunciationWord.setText(mVocabularies.get(mCount).getPronunciationWord());
            //img
            String nameIcon = mVocabularies.get(mCount).getIconWord();
            int resourceId = getResources().getIdentifier(nameIcon, "drawable", getActivity().getPackageName());
            mImgOfWord.setImageResource(resourceId);
            mCount++;
        } else {
            //back Frangment Main
            mHandler.sendMessage(new Message());
        }
    }

    private void handlerBackPopChilFragment() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                popChildFragment();
                SettingStore.setIdFragment(getActivity(), 0);
            }
        };
    }

    /**
     * list data city area weather
     *
     * @return arr vocabularies
     */
    private List<Vocabulary> initDataVocabulary() {
        List<Vocabulary> vocabularies = new ArrayList<>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        JSONObject jsonObj;
        try {
            JSONArray jsonResponse = new JSONArray(loadJSONFromAsset());
            int length = jsonResponse.length();
            for (int i = 0; i < length; i++) {
                jsonObj = jsonResponse.getJSONObject(i);
                vocabularies.add(gson.fromJson(jsonObj.toString(), Vocabulary.class));
            }

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return vocabularies;

    }

    /**
     * load json from asset
     *
     * @return string json
     */
    private String loadJSONFromAsset() {
        String json;
        try {

            InputStream inputStream = getActivity().getAssets().open(getResources().getString(R.string.file_name_json));

            int size = inputStream.available();

            byte[] buffer = new byte[size];

            inputStream.read(buffer);

            inputStream.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
        return json;

    }

    public void speak() {
        mIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass()
                .getPackage().getName());
        mIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
                Locale.ENGLISH.toString());
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.ENGLISH.toString());
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        mIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getActivity()
                .getPackageName());
        mIntent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE,
                "true");
        mIntent.putExtra("android.speech.extra.EXTRA_ADDITIONAL_LANGUAGES",
                new String[]{"en"});
        mIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        startActivityForResult(mIntent, REQUEST_CODE_RESULT);
    }

    // Su kien nhan lai ket qua
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_RESULT)
            if (resultCode == getActivity().RESULT_OK) {
                final ArrayList<String> textMatchList = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                if (!textMatchList.isEmpty()) {
                    if (textMatchList.get(0).contains("search")) {
                        String searchQuery = textMatchList.get(0).replace(
                                "search", " ");
                        Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
                        search.putExtra(SearchManager.QUERY, searchQuery);
                        startActivity(search);
                    } else {
                        // Hien thi ket qua
                        Log.i("FOX", "text--" + mTvNameWord.getText() + "//speed--" + textMatchList.get(0));
                        if (mTvNameWord.getText().toString().equalsIgnoreCase(textMatchList.get(0))) {
                            getMainActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getMainActivity(), textMatchList.get(0), Toast.LENGTH_SHORT).show();
                                    setNext();
                                }
                            });

                        } else {
                            getMainActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getMainActivity(), textMatchList.get(0), Toast.LENGTH_SHORT).show();
                                    showDialogWrong(textMatchList.get(0));
                                }
                            });
                        }
                    }
                }
                // Cac truong hop loi
            } else if (resultCode == RecognizerIntent.RESULT_AUDIO_ERROR) {
                // showToastMessage("Audio Error");
            } else if (resultCode == RecognizerIntent.RESULT_CLIENT_ERROR) {
                // showToastMessage("Client Error");
            } else if (resultCode == RecognizerIntent.RESULT_NETWORK_ERROR) {
                // showToastMessage("Network Error");
            } else if (resultCode == RecognizerIntent.RESULT_NO_MATCH) {
                // showToastMessage("No Match");
            } else if (resultCode == RecognizerIntent.RESULT_SERVER_ERROR) {
                // showToastMessage("Server Error");
            }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * this is method show dialog check ansewer when you speed this word
     * User AlertDialog
     */
    private void showDialogWrong(String textResult) {
        new AlertDialog.Builder(getMainActivity())
                .setTitle("Result...")
                .setMessage("You are wrong! \n" + "This is a word you say:" + textResult)
                .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        speak();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                }).create().show();
    }

}
