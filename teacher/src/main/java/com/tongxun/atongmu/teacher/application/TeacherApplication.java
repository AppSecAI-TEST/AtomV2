package com.tongxun.atongmu.teacher.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by Anro on 2017/8/2.
 */

public class TeacherApplication extends Application {
    private static Context mContext;
    private static TeacherApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        instance=this;
    }

    private static TeacherApplication getInstance(){
        return instance;
    }

    public static Context getContext() {
        return mContext;
    }

}
