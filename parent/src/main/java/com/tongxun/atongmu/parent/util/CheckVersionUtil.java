package com.tongxun.atongmu.parent.util;

/**
 * Created by Anro on 2017/7/29.
 */

public class CheckVersionUtil {
    private static CheckVersionUtil instance=null;

    private CheckVersionUtil() {
    }

    public static CheckVersionUtil getInstance(){
        if(instance==null){
            instance=new CheckVersionUtil();
        }
        return instance;
    }
}
