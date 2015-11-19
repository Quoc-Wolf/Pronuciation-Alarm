package asiantech.quocnp.pronuciation_alarm.fragment;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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

import asiantech.quocnp.pronuciation_alarm.R;
import asiantech.quocnp.pronuciation_alarm.models.database.Vocabulary;

/**
 * A simple {@link BaseFragment} subclass.
 */

@SuppressWarnings("ALL")
@EFragment(R.layout.fragment_child)
public class ChildFragment extends BaseFragment {
    private static final String TAG = ChildFragment.class.getSimpleName();

    @ViewById(R.id.tvNameWord)
    TextView mTvNameWord;
    @ViewById(R.id.tvPronunciationWord)
    TextView mTvPronunciationWord;
    @ViewById(R.id.imgOfWord)
    ImageView mImgOfWord;
    List<Vocabulary> mVocabularies;
    //count size of list vocabularies
    private int mCount = 0;

    /**
     * This is constructor Child Fragment
     */
    public ChildFragment() {
        // Required empty public constructor
    }

    //Demo click next
    @Click(R.id.btnNext)
    protected void clickNext() {
        setNext();
    }

    @AfterViews
    protected void afterView() {
        initDataVocabulary();
        setNext();
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
            popChildFragment();
        }
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
}
