package com.call.logs.app;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ProcessLifecycleOwner;

import com.call.logs.BuildConfig;
import com.call.logs.database.SQLDatabase;
import com.call.logs.utils.SharedPreferenceUtils;

import timber.log.Timber;

public class AppController extends Application implements LifecycleObserver {

    public static AppController mInstance;
    public static final int APP_FORE_GROUNDED = 2;
    public static final int APP_BACK_GROUNDED = 0;

    public AppController() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initSQLDatabase();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    private void initSQLDatabase() {
        SQLDatabase database = new SQLDatabase(this);
        database.getWritableDatabase();
        database.getReadableDatabase();
        database.close();
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public static String getAppPackageName() {
        return BuildConfig.APPLICATION_ID;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onAppBackgrounded() {
        SharedPreferenceUtils.saveApplicationStatus(AppController.getInstance(), AppController.APP_BACK_GROUNDED);
        Timber.tag("Lifecycle.Event").d("APP_BACK_GROUNDED");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onAppForegrounded() {
        SharedPreferenceUtils.saveApplicationStatus(AppController.getInstance(), AppController.APP_FORE_GROUNDED);
        Timber.tag("Lifecycle.Event").d("APP_FORE_GROUNDED");
    }

    public static String getApplicationStatus() {
        return SharedPreferenceUtils.getApplicationStatus(AppController.getInstance());
    }

    public static boolean isAppForeGrounded() {
        return getApplicationStatus().equalsIgnoreCase("APP_FORE_GROUNDED");
    }

}
