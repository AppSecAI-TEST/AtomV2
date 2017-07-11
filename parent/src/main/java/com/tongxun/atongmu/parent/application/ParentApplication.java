package com.tongxun.atongmu.parent.application;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.hyphenate.easeui.EaseUI;
import com.mob.MobSDK;
import com.squareup.leakcanary.LeakCanary;

import org.litepal.LitePalApplication;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Anro on 2017/6/19.
 */

public class ParentApplication extends LitePalApplication {

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
        MobSDK.init(this);

        EaseUI.getInstance().init(this,null);
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
