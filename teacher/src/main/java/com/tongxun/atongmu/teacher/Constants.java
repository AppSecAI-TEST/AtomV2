package com.tongxun.atongmu.teacher;

/**
 * Created by Anro on 2017/8/2.
 */

public class Constants {
    //默认头像
    public static String DEFAULTICON = "http://www.atongmu.net:10010/backwork/userfiles/images/touxiang.png";

    //测试地址
    public static final String TEST_URL = "http://192.168.0.22:10010/backwork/rest/kig/";

    public static final String BASEURL = "https://www.atongmu.net:8443/backwork/rest/";

    public static final String KIG_BASEURL = BASEURL + "tea/";
    //获取通知页面红点
    public static String restGetTeacherNewPush_v3 = KIG_BASEURL + "restGetTeacherNewPush_v3";
    //获取最新的20条通知
    public static String restGeTeacherNoticeListNew_v2 = KIG_BASEURL + "restGeTeacherNoticeListNew_v2";
    //获取最新的20条活动
    public static String restGetTeacherActivityListNew_v2 = KIG_BASEURL + "restGetTeacherActivityListNew_v2";
    //获取最新的20条新闻
    public static String restGetTeacherNewList_v2 = KIG_BASEURL + "restGetTeacherNewList_v2";
    //获取更多20条通知
    public static String restGeTeacherNoticeListPreDate_v2 = KIG_BASEURL + "restGeTeacherNoticeListPreDate_v2";
    //获取更多20条新闻
    public static String restGetTeacherNewListPreDate_v2 = KIG_BASEURL + "restGetTeacherNewListPreDate_v2";
    //获取更多20条活动
    public static String restGetTeacherActivityPreListDate_v2 = KIG_BASEURL + "restGetTeacherActivityPreListDate_v2";
    //更新通知为已读
    public static String restSetTeacherNoticeRead = KIG_BASEURL + "restSetTeacherNoticeRead";
    //更新活动为已读
    public static String restSetTeacherActivityRead = KIG_BASEURL + "restSetTeacherActivityRead";
    //更新新闻为已读
    public static String restSetTeacherNewRead = KIG_BASEURL + "restSetTeacherNewRead";
    //获得通知未阅读
    public static String restGetNoticeDetail_v2 = KIG_BASEURL + "restGetNoticeDetail_v2";
}
