package com.tongxun.atongmu.parent.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.tongxun.atongmu.parent.application.ParentApplication;

/**
 * Created by Anro on 2017/6/26.
 */

public class SharePreferenceUtil {

    public static String isFirstIn="isFirstIn";

    public static String isRemember="isRemember";

    public static String TOKENID="tokenId";
    //用户手机号
    public static String USERPHONE="username";
    //用户密码
    public static final String PASSWORD="password";

    public static String GROUPID="GROUPID";//群ID
    public static String IMNICKNAME="IMNICKNAME";//群ID
    public static String IMUSERNAME="IMUSERNAME";//IM 账号
    public static String IMPASSWORD="IMPASSWORD";//IM 密码

    private static final String SHAREDPREFERENCES_NAME="atom_parent_pref";

    private static SharedPreferences sharedPreferences;

    public static SharedPreferences getPreferences(){
        if(sharedPreferences==null){
            sharedPreferences= ParentApplication.getContext().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public static SharedPreferences.Editor getEditor(){
        return getPreferences().edit();
    }



}
