package asiantech.quocnp.pronuciation_alarm.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import asiantech.quocnp.pronuciation_alarm.activity.HomeActivity;
import asiantech.quocnp.pronuciation_alarm.core.fragment.Fragment;


/**
 * @author quoc
 *         Created by quoc on 18/08/2015.
 */
public class BaseFragment extends Fragment {
    /**
     * Show dialog with OK button
     *
     * @param msg             message to display
     * @param onClickListener listener for OK button
     */
    protected void showDialog(String msg, DialogInterface.OnClickListener onClickListener) {

        if (null == getActivity()) {
            return;
        }
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, onClickListener)
                .setCancelable(false)
                .create();
        alertDialog.show();
    }

    /**
     * Show dialog with OK and cancel button
     *
     * @param msg
     * @param okClickListener
     * @param cancelClickListener
     */
    protected void showDialog(String msg,
                              DialogInterface.OnClickListener okClickListener,
                              DialogInterface.OnClickListener cancelClickListener) {

        if (null == getActivity()) {
            return;
        }
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, okClickListener)
                .setNegativeButton(android.R.string.cancel, cancelClickListener)
                .setCancelable(false)
                .create();
        alertDialog.show();
    }

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
