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
    private static ParentApplication instance;

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
        mContext = getApplicationContext();
        instance=this;
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        MobSDK.init(this);

        //百度地图
        SDKInitializer.initialize(this);

        //环信
        DemoHelper.getInstance().init(this);


    }

    public static void initEzOpenSDK() {
        EZOpenSDK.enableP2P(true);
        /**
         * APP_KEY请替换成自己申请的
         * 萤石SDK 需要手机状态的权限 没有做6.0权限获取适配
         */
        EZOpenSDK.initLib(getInstance(), AppKey, "");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    private static ParentApplication getInstance(){
        return instance;
    }

    public static Context getContext() {
        return mContext;
    }

    public static EZOpenSDK getOpenSDK() {
        return EZOpenSDK.getInstance();
    }
}
