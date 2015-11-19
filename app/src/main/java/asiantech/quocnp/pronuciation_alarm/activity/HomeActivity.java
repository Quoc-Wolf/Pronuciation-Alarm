package asiantech.quocnp.pronuciation_alarm.activity;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.widget.Toast;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentByTag;

import asiantech.quocnp.pronuciation_alarm.R;
import asiantech.quocnp.pronuciation_alarm.fragment.MainFragment;
import asiantech.quocnp.pronuciation_alarm.fragment.MainFragment_;
import asiantech.quocnp.pronuciation_alarm.untils.SettingStore;
import lombok.Getter;

@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity {
    private final static int TIME_DELAY_EXIT_APP = 2000;

    private static final String TAG = HomeActivity.class.getSimpleName();
    //This is Fragment Main
    @FragmentByTag("MainFragment")
    MainFragment mMainFragment;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    void afterView() {
        if (mMainFragment == null) {
            mMainFragment = MainFragment_.builder().build();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame_layout, mMainFragment, "MainFragment").commit();
        SettingStore.setIdFragment(this, currentFragment.MAIN_FRAGMENT.getValueEnum());
    }

    //check when click button back of Devices
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (SettingStore.getIdFragment(this) == currentFragment.MAIN_FRAGMENT.getValueEnum()) {
                onBackPressed();
            } else {
                showDialogExit();
            }
        }
        return true;
    }


    /**
     * This is method check twice click button back of Devices and exit app
     */

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, TIME_DELAY_EXIT_APP);
    }

    /**
     * This is show dialog confirm exit screen child fragment
     */
    public void showDialogExit() {
        new AlertDialog.Builder(this)
                .setMessage("You are doing test? and you want to back Home now ?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mMainFragment.popChildFragment();
                        SettingStore.setIdFragment(HomeActivity.this, currentFragment.MAIN_FRAGMENT.getValueEnum());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                }).create().show();
    }

    /**
     * This is method enum set current Fragment
     */
    public enum currentFragment {
        MAIN_FRAGMENT(0),
        CHILD_FRAGMENT(1);

        @Getter
        int valueEnum;

        // Enum constructor
        currentFragment(int value) {
            valueEnum = value;
        }
    }

}
