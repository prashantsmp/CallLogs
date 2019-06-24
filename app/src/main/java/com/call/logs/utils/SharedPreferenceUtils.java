package com.call.logs.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.call.logs.app.AppController;

import timber.log.Timber;

public class SharedPreferenceUtils {

    private static final String REGION_PREFERENCES = AppController.getAppPackageName();
    private static final String PREF_SCHEDULE_JOB = "jobSchedule";
    private static final String PREF_APP_STATUS = "appStatus";


    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(SharedPreferenceUtils.REGION_PREFERENCES, Context.MODE_PRIVATE);
    }

    private static String getPreferenceString(Context context, String preferenceKey) {
        try {
            final SharedPreferences prefs = getPreferences(context);
            return prefs.getString(preferenceKey, "");
        } catch (Exception e) {
            Timber.e("getPref: %s", String.valueOf(e));
        }
        return null;
    }

    private static void setPreferenceString(Context context, String preferenceKey, String preferenceValue) {
        final SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(preferenceKey, preferenceValue);
        editor.apply();
    }

    private static int getPreferenceInteger(Context context, String preferenceKey) {
        try {
            final SharedPreferences prefs = getPreferences(context);
            return prefs.getInt(preferenceKey, -1);
        } catch (Exception e) {
            Timber.e("getPref: %s", String.valueOf(e));
        }
        return -1;
    }

    private static void setPreferenceInteger(Context context, String preferenceKey, int preferenceValue) {
        final SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(preferenceKey, preferenceValue);
        editor.apply();
    }

    private static void setPreferenceBoolean(Context context, String preferenceKey, boolean preferenceValue) {
        final SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(preferenceKey, preferenceValue);
        editor.apply();
    }


    private static boolean getPreferenceBoolean(Context context, String preferenceKey) {
        try {
            final SharedPreferences prefs = getPreferences(context);
            return prefs.getBoolean(preferenceKey, false);
        } catch (Exception e) {
            Timber.e("getPref: %s", String.valueOf(e));
        }
        return false;
    }

    public static void scheduleJobForFirstTime(Context context, boolean isFirstTime) {
        if (context != null) {
            setPreferenceBoolean(context, PREF_SCHEDULE_JOB, isFirstTime);
            Timber.tag("scheduleJob ").i("Started for the first time");
        }
    }

    public static boolean isJobScheduledAlready(Context context) {
        if (context != null) {
            return getPreferenceBoolean(context, PREF_SCHEDULE_JOB);
        } else {
            return false;
        }
    }

    public static void saveApplicationStatus(Context context, int appStatus) {
        if (context != null) {
            setPreferenceInteger(context, PREF_APP_STATUS, appStatus);
        }
    }

    public static String getApplicationStatus(Context context) {
        if (context != null) {
            if (getPreferenceInteger(context, PREF_APP_STATUS) == AppController.APP_FORE_GROUNDED) {
                return "APP_FORE_GROUNDED";
            } else if (getPreferenceInteger(context, PREF_APP_STATUS) == AppController.APP_BACK_GROUNDED) {
                return "APP_BACK_GROUNDED";
            } else {
                return "FAILED_TO_GET_APP_STATUS";
            }
        } else {
            return "FAILED_TO_GET_APP_STATUS";
        }
    }

}
