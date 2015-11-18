package asiantech.quocnp.pronuciation_alarm.fragment;

import android.app.Activity;

import asiantech.quocnp.pronuciation_alarm.activity.HomeActivity;
import asiantech.quocnp.pronuciation_alarm.core.fragment.Fragment;


/**
 * @author quoc
 *         Created by quoc on 18/08/2015.
 */
public class BaseFragment extends Fragment {
    /**
     * Get MainActivity to work
     *
     * @return Activity
     */
    protected HomeActivity getMainActivity() {
        Activity activity = getActivity();
        if (activity != null && HomeActivity.class.isInstance(activity)) {
            return (HomeActivity) activity;
        }
        return null;
    }

}
