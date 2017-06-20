package com.tongxun.atongmu.parent.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;




/**
 * Created by Anro on 2017/6/19.
 */

public class ParentApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
