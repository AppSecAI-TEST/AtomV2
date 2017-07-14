package com.tongxun.atongmu.parent.application;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.baidu.mapapi.SDKInitializer;
import com.mob.MobSDK;
import com.squareup.leakcanary.LeakCanary;
import com.tongxun.atongmu.parent.util.DemoHelper;
import com.videogo.openapi.EZOpenSDK;

import org.litepal.LitePalApplication;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Anro on 2017/6/19.
 */

public class ParentApplication extends LitePalApplication {

    private static Context mContext;

    /**
     * 莹石平台视频播放的AppKey
     */
    public static String AppKey = "b9ab738994884c929ea9b46613443969";

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

        EZOpenSDK.enableP2P(true);
        /**
         * APP_KEY请替换成自己申请的
         */
        EZOpenSDK.initLib(this, AppKey, "");


        DemoHelper.getInstance().init(this);

        SDKInitializer.initialize(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    public static Context getContext(){
        return mContext;
    }

    public static EZOpenSDK getOpenSDK() {
        return EZOpenSDK.getInstance();
    }
}
