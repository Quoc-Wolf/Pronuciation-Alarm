package asiantech.quocnp.pronuciation_alarm.fragment;


import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import asiantech.quocnp.pronuciation_alarm.R;
import asiantech.quocnp.pronuciation_alarm.activity.HomeActivity;
import asiantech.quocnp.pronuciation_alarm.untils.SettingStore;


/**
 * @author quocnp
 *         A simple {@link BaseFragment} subclass.
 */
@SuppressWarnings("ALL")
@EFragment(R.layout.fragment_main)
public class MainFragment extends BaseFragment {
    private static final int TIME_DELAY_FOR_NEXT_CLICK = 500;
    //Toolbar of root (main)
    @ViewById(R.id.toolbar)
    Toolbar mToolbar;
    @ViewById(R.id.fabSearch)
    FloatingActionButton mFab;
    @ViewById(R.id.btnDemo)
    Button mBtnDemo;
    //check no click double
    private boolean isCheckDoubleClick;

    //FloatingActionButton for Search list
    @Click(R.id.fabSearch)
    protected void doSearch(View view) {
        Snackbar.make(view, "Nothing", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    //click demo
    @Click(R.id.btnDemo)
    protected void clickDemo() {
        if (!isCheckDoubleClick) {
            SettingStore.setIdFragment(getActivity(), HomeActivity.currentFragment.CHILD_FRAGMENT.getValueEnum());
            addChildFragment(ChildFragment_.builder().build());
            isCheckDoubleClick = true;
            mBtnDemo.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isCheckDoubleClick = false;
                }
            }, TIME_DELAY_FOR_NEXT_CLICK);
        }
    }

    @AfterViews
    public void afterView() {
        setupToolbar();
    }

    /**
     * This is method setup Toolbar
     */
    private void setupToolbar() {
        if (mToolbar != null) {
            getMainActivity().setSupportActionBar(mToolbar);
            getMainActivity().getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}
