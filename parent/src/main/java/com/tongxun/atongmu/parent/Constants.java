package com.tongxun.atongmu.parent;

/**
 * Created by Anro on 2017/6/21.
 */

public class Constants {
    public static final String BASEURL="https://www.atongmu.net:8443/backwork/rest/";
    public static final String KIG_BASEURL=BASEURL+"kig/";
    //重置密码
    public static final String restParentResetPWD=BASEURL+"restParentResetPWD";
    //登录
    public static final String restParentLoginList=BASEURL+"restParentLoginList";
    //发送短信
    public static final String restSendSMS=KIG_BASEURL+"restSendSMS";
    //获取最新的20条通知
    public static final String restGetParentNoticeListNew_v2=KIG_BASEURL+"restGetParentNoticeListNew_v2";
    //获得最新的20条活动
    public static final String restGetParentActivityListNew_v2=KIG_BASEURL+"restGetParentActivityListNew_v2";
    //获得最新的20条新闻
    public static final String restGetParentTopNew_v2=KIG_BASEURL+"restGetParentTopNew_v2";

}
