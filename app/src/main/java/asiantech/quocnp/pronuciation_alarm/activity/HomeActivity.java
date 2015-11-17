package asiantech.quocnp.pronuciation_alarm.activity;

import android.support.v4.app.FragmentTransaction;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentByTag;

import asiantech.quocnp.pronuciation_alarm.R;
import asiantech.quocnp.pronuciation_alarm.fragment.MainFragment;
import asiantech.quocnp.pronuciation_alarm.fragment.MainFragment_;

@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();
    //This is Fragment Main
    @FragmentByTag("MainFragment")
    MainFragment mMainFragment;

    @Override
    void afterView() {
        //=================================
        if (mMainFragment == null) {
            mMainFragment = MainFragment_.builder().build();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame_layout, mMainFragment, "MainFragment").commit();
    }
}
