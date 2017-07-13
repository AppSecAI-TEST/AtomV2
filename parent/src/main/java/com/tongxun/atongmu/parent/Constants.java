package com.tongxun.atongmu.parent;

/**
 * Created by Anro on 2017/6/21.
 */

public class Constants {

    public static int PERMISSION_CAMERA_CODE=100;
    public static int PERMISSION_AUDIO_CODE=101;
    public static int PERMISSION_PHOTO_CODE=102;
    public static int PERMISSION_VIDEO_CODE=103;
    public static int PERMISSION_PHONE_CODE=104;

    public static int CAMERA_RESULT=0x100;


    //默认头像
    public static String DEFAULTICON="http://www.atongmu.net:10010/backwork/userfiles/images/touxiang.png";

    //测试地址
    public static final String TEST_URL="http://192.168.0.33:10010/backwork/rest/kig/";

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
    //获取更多通知20条
    public static final String restGetParentNoticeListPreDate_v2=KIG_BASEURL+"restGetParentNoticeListPreDate_v2";
    //获得最新的20条活动
    public static final String restGetParentActivityListNew_v2=KIG_BASEURL+"restGetParentActivityListNew_v2";
    //获取更多活动20条
    public static final String restGetParentActivityPreListDate_v2=KIG_BASEURL+"restGetParentActivityPreListDate_v2";
    //获得最新的20条新闻
    public static final String restGetParentTopNew_v2=KIG_BASEURL+"restGetParentTopNew_v2";
    //获取更多新闻20条
    public static final String restGetParentNewListPreDate_v2=KIG_BASEURL+"restGetParentNewListPreDate_v2";
    //获取全部的代接
    public static final String restGetStudentAgenAll=KIG_BASEURL+"restGetStudentAgenAll";
    //确认代接
    public static final String restSetStudentAgen=KIG_BASEURL+"restSetStudentAgen";
    //确认通知为已读
    public static final String restSetParentNoticeRead=KIG_BASEURL+"restSetParentNoticeRead";
    //确认活动为已读
    public static final String restSetParentActivityRead=KIG_BASEURL+"restSetParentActivityRead";
    //确认新闻为已读
    public static final String restSetParentNewRead=KIG_BASEURL+"restSetParentNewRead";
    //获取最新的20条圈子
    public static final String restGetCircleTop20_v2=KIG_BASEURL+"restGetCircleTop20_v2";
    //点赞
    public static final String restSetCircleVote_v2=KIG_BASEURL+"restSetCircleVote_v2";
    //取消点赞
    public static final String restSetCircleVoteCancel=KIG_BASEURL+"restSetCircleVoteCancel";
    //获得班级课程
    public static final String restGetStudentCourse=KIG_BASEURL+"restGetStudentCourse";
    //获取未完成的作业
    public static final String restGetStudentJob=KIG_BASEURL+"restGetStudentJob";
    //获得留言群成员
    public static final String restGetAddressBook=KIG_BASEURL+"restGetAddressBook";
    //根据时间获取当天的食谱
    public static final String restGetKindergartenReceipt=KIG_BASEURL+"restGetKindergartenReceipt";
    //获取时光相册列表
    public static final String restBrownStudentPhotoMonthList=KIG_BASEURL+"restBrownStudentPhotoMonthList";
    //获取晨检相册内容
    public static final String restBrownStudentCheckPhoto=KIG_BASEURL+"restBrownStudentCheckPhoto";
    //根据时间获取时光相册图片
    public static final String restBrownStudentPhotoDetail=KIG_BASEURL+"restBrownStudentPhotoDetail";
    //最新的3个月晨检照片
    public static final String restBrownStudentCheckPhoto_v2=KIG_BASEURL+"restBrownStudentCheckPhoto_v2";
    //获取更多3个月晨检照片
    public static final String restBrownStudentCheckPhotoPre_v2=KIG_BASEURL+"restBrownStudentCheckPhotoPre_v2";
}
