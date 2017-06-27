package com.tongxun.atongmu.parent.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anro on 2017/6/26.
 */

public  class ActivityControl {

    private static List<Activity> mlist=new ArrayList<>();

    public static void addActivity(Activity activity){
        mlist.add(activity);
    }

    public static void removeActivity(Activity activity){
        mlist.remove(activity);
    }

    public static void finishAll(){
        for(Activity activity:mlist){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }





}
