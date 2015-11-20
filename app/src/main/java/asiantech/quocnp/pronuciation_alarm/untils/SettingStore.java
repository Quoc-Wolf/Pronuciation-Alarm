package asiantech.quocnp.pronuciation_alarm.untils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by Binc on 24/09/2015.
 */
public final class SettingStore {
    private static final String ID_FRAGMENT = "ID_FRAGMENT";

    /**
     * contructor
     */
    private SettingStore() {
    }

    /**
     * this method set id
     *
     * @param context    activity
     * @param idFragment id alarm
     */
    public static void setIdFragment(Context context, long idFragment) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ID_FRAGMENT,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(ID_FRAGMENT, idFragment);
        editor.commit();
    }

    /**
     * this method is get id alarm
     *
     * @param context activity
     * @return id
     */
    public static long getIdFragment(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ID_FRAGMENT,
                Context.MODE_PRIVATE);
        return sharedPreferences.getLong(ID_FRAGMENT, -1);
    }
}
