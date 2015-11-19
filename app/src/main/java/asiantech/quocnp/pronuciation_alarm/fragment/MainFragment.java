package asiantech.quocnp.pronuciation_alarm.fragment;


import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import asiantech.quocnp.pronuciation_alarm.R;


/**
 * @author quocnp
 *         A simple {@link BaseFragment} subclass.
 */
@SuppressWarnings("ALL")
@EFragment(R.layout.fragment_main)
public class MainFragment extends BaseFragment {
    //Toolbar of root (main)
    @ViewById(R.id.toolbar)
    Toolbar mToolbar;
    @ViewById(R.id.fabSearch)
    FloatingActionButton mFab;


    //FloatingActionButton for Search list
    @Click(R.id.fabSearch)
    protected void doSearch(View view) {
        Snackbar.make(view, "Nothing", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    //click demo
    @Click(R.id.btnDemo)
    protected void clickDemo() {
        addChildFragment(ChildFragment_.builder().build());
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
