package asiantech.quocnp.pronuciation_alarm.fragment;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import asiantech.quocnp.pronuciation_alarm.R;

/**
 * A simple {@link BaseFragment} subclass.
 */
@EFragment(R.layout.fragment_child)
public class ChildFragment extends BaseFragment {

    //Demo click back
    @Click(R.id.btnBack)
    void doClickBack(){
        popChildFragment();
    }

    /**
     * This is constructor Child Fragment
     */
    public ChildFragment() {
        // Required empty public constructor
    }

}
