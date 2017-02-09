package com.sparity.jenkinsdemo;

import android.app.Application;
import android.util.Log;

import com.appboy.AppboyLifecycleCallbackListener;
import com.appboy.support.AppboyLogger;

/**
 * Created by Pavan on 1/18/2017.
 */

public class JenkinsApplication extends Application {

    static JenkinsApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        AppboyLogger.LogLevel = Log.VERBOSE;
        registerActivityLifecycleCallbacks(new AppboyLifecycleCallbackListener());
    }

    public static synchronized JenkinsApplication getAppContext() {
        return mInstance;
    }
}
