package com.tongxun.atongmu.parent.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.squareup.leakcanary.LeakCanary;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Anro on 2017/6/19.
 */

public class ParentApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        mContext=getApplicationContext();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    public static Context getContext(){
        return mContext;
    }
}
