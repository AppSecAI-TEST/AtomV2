package com.tongxun.atongmu.parent;

/**
 * Created by Anro on 2017/6/21.
 */

public class Constants {

    public static int PERMISSION_CAMERA_CODE = 100;
    public static int PERMISSION_AUDIO_CODE = 101;
    public static int PERMISSION_PHOTO_CODE = 102;
    public static int PERMISSION_VIDEO_CODE = 103;
    public static int PERMISSION_PHONE_CODE = 104;

    public static int CAMERA_RESULT = 0x100;
    public static int VIDEO_RECORD = 0x101;


    public static int REQ_CODE = 0x222;
    public static int PICK_CODE = 0x223;
    public static int CROP_PHOTO = 0x224;
    public static int REQ_INTENT_CODE = 0x225;
    public static int CHANGE_INFO = 1001;
    public static int ChANGE_ACCOUNT = 1002;


    //默认头像
    public static String DEFAULTICON = "http://www.atongmu.net:10010/backwork/userfiles/images/touxiang.png";

    //测试地址
    public static final String TEST_URL = "http://192.168.0.22:10010/backwork/rest/kig/";

    public static final String BASEURL = "https://www.atongmu.net:8443/backwork/rest/";

    public static final String KIG_BASEURL = BASEURL + "kig/";

    //官方网址
    public static final String WEBSITE = "http://www.atongmu.net";
    //服务条款
    public static String restShowProvision = KIG_BASEURL + "restShowProvision";
    //免责声明
    public static String restDutyShowContext = KIG_BASEURL + "restDutyShowContext";

    //重置密码
    public static final String restParentResetPWD = BASEURL + "restParentResetPWD";
    //登录
    public static final String restParentLoginList = BASEURL + "restParentLoginList";
    //发送短信
    public static final String restSendSMS = KIG_BASEURL + "restSendSMS";
    //获取最新的20条通知
    public static final String restGetParentNoticeListNew_v2 = KIG_BASEURL + "restGetParentNoticeListNew_v2";
    //获取更多通知20条
    public static final String restGetParentNoticeListPreDate_v2 = KIG_BASEURL + "restGetParentNoticeListPreDate_v2";
    //获得最新的20条活动
    public static final String restGetParentActivityListNew_v2 = KIG_BASEURL + "restGetParentActivityListNew_v2";
    //获取更多活动20条
    public static final String restGetParentActivityPreListDate_v2 = KIG_BASEURL + "restGetParentActivityPreListDate_v2";
    //获得最新的20条新闻
    public static final String restGetParentTopNew_v2 = KIG_BASEURL + "restGetParentTopNew_v2";
    //获取更多新闻20条
    public static final String restGetParentNewListPreDate_v2 = KIG_BASEURL + "restGetParentNewListPreDate_v2";
    //获取全部的代接
    public static final String restGetStudentAgenAll = KIG_BASEURL + "restGetStudentAgenAll";
    //确认代接
    public static final String restSetStudentAgen = KIG_BASEURL + "restSetStudentAgen";
    //确认通知为已读
    public static final String restSetParentNoticeRead = KIG_BASEURL + "restSetParentNoticeRead";
    //确认活动为已读
    public static final String restSetParentActivityRead = KIG_BASEURL + "restSetParentActivityRead";
    //确认新闻为已读
    public static final String restSetParentNewRead = KIG_BASEURL + "restSetParentNewRead";
    //获取最新的20条圈子
    public static final String restGetCircleTop20_v2 = KIG_BASEURL + "restGetCircleTop20_v2";
    //点赞
    public static final String restSetCircleVote_v2 = KIG_BASEURL + "restSetCircleVote_v2";
    //取消点赞
    public static final String restSetCircleVoteCancel = KIG_BASEURL + "restSetCircleVoteCancel";
    //获得班级课程
    public static final String restGetStudentCourse = KIG_BASEURL + "restGetStudentCourse";
    //获取未完成的作业
    public static final String restGetStudentJob = KIG_BASEURL + "restGetStudentJob";
    //获得留言群成员
    public static final String restGetAddressBook = KIG_BASEURL + "restGetAddressBook";
    //根据时间获取当天的食谱
    public static final String restGetKindergartenReceipt = KIG_BASEURL + "restGetKindergartenReceipt";
    //获取时光相册列表
    public static final String restBrownStudentPhotoMonthList = KIG_BASEURL + "restBrownStudentPhotoMonthList";
    //获取晨检相册内容
    public static final String restBrownStudentCheckPhoto = KIG_BASEURL + "restBrownStudentCheckPhoto";
    //根据时间获取时光相册图片
    public static final String restBrownStudentPhotoDetail = KIG_BASEURL + "restBrownStudentPhotoDetail";
    //最新的3个月晨检照片
    public static final String restBrownStudentCheckPhoto_v2 = KIG_BASEURL + "restBrownStudentCheckPhoto_v2";
    //获取更多3个月晨检照片
    public static final String restBrownStudentCheckPhotoPre_v2 = KIG_BASEURL + "restBrownStudentCheckPhotoPre_v2";
    //获取实时视频列表
    public static final String restGetCameraInfo_v2 = KIG_BASEURL + "restGetCameraInfo_v2";
    //获取系统时间
    public static final String restGetSystemTime = KIG_BASEURL + "restGetSystemTime";
    //获取校车定位
    public static final String restGetCarPosition_v2 = KIG_BASEURL + "restGetCarPosition_v2";
    //获取校车信息
    public static final String restGetCarInfo = KIG_BASEURL + "restGetCarInfo";
    //获取PM25
    public static final String restGetPM25 = KIG_BASEURL + "restGetPM25";
    //获取当月宝宝签到
    public static final String downloadSignInMessage_v2 = KIG_BASEURL + "downloadSignInMessage_v2";
    //获取宝宝当天签到明细
    public static final String downloadSignInMessageDay_v2 = KIG_BASEURL + "downloadSignInMessageDay_v2";
    //上传假条
    public static final String restGenStudentLeave = KIG_BASEURL + "restGenStudentLeave";
    //获取园丁风采列表
    public static final String restGetKindgTecherList = KIG_BASEURL + "restGetKindgTecherList";
    //获取缴费通知列表
    public static final String restGetStudentTuitionList = KIG_BASEURL + "restGetStudentTuitionList";
    //创建订单
    public static final String restGetStudentTuitionOrder_android = KIG_BASEURL + "restGetStudentTuitionOrder_android";
    //获取订单历史
    public static final String restGetTuitionCompletedList = KIG_BASEURL + "restGetTuitionCompletedList";
    //活动报名
    public static final String restSetParentActivityAct = KIG_BASEURL + "restSetParentActivityAct";
    //取消报名
    public static final String restSetParentActivityActCancel = KIG_BASEURL + "restSetParentActivityActCancel";
    //请求家长能否发布圈子
    public static final String restIsCanPutCircle_v2 = KIG_BASEURL + "restIsCanPutCircle_v2";
    //获得当天健康成长数据
    public static final String restGetStudentGrownUrl = KIG_BASEURL + "restGetStudentGrownUrl";
    //获取校园简介
    public static final String restGetKindgInfo_v2 = KIG_BASEURL + "restGetKindgInfo_v2";
    //获取意见反馈记录
    public static final String restGetDirectorMail = KIG_BASEURL + "restGetDirectorMail";
    //清空意见反馈
    public static final String restEmptyDirectorMail = KIG_BASEURL + "restEmptyDirectorMail";
    //获取家长端可用模块
    public static final String restGetEntranceInfo_v2 = KIG_BASEURL + "restGetEntranceInfo_v2";
    //意见反馈给阿童目
    public static final String restPutSuggestParent_v2 = KIG_BASEURL + "restPutSuggestParent_v2";
    //获取所有宝宝信息
    public static final String restGetStudentInfoList = KIG_BASEURL + "restGetStudentInfoList";
    //意见反馈给幼儿园
    public static final String restPutDirectorMail = KIG_BASEURL + "restPutDirectorMail";
    //使用帮助功能介绍
    public static final String restGetWizardList = KIG_BASEURL + "restGetWizardList";
    //使用帮助问题帮助
    public static final String restGetAppHelpList = KIG_BASEURL + "restGetAppHelpList";
    //获取接送照片列表
    public static final String restGetStudentParentList_v2 = KIG_BASEURL + "restGetStudentParentList_v2";
    //获取成长档案列表
    public static final String getTermPaper = KIG_BASEURL + "getTermPaper";
    //上传接送照片
    public static final String restUpParentPhoto2_v2 = KIG_BASEURL + "restUpParentPhoto2_v2";
    //上传卡号和状态
    public static final String restUpdateCardStatus = KIG_BASEURL + "restUpdateCardStatus";
    //修改密码
    public static final String restParentChangPWD = BASEURL + "restParentChangPWD";
    //我的获取积分和红花数
    public static final String restGetIntegralAndFlowers_v2 = KIG_BASEURL + "restGetIntegralAndFlowers_v2";
    //上传宝宝头像
    public static final String restUploadFile = KIG_BASEURL + "restUploadFile";
    //修改个人信息
    public static final String restUpdateStudentInfo_v2 = KIG_BASEURL + "restUpdateStudentInfo_v2";
    //获取主页Banner
    public static final String restGetEntrancePhoto_v2 = KIG_BASEURL + "restGetEntrancePhoto_v2";
    //拉取付费列表
    public static final String restPayItemList_v2 = KIG_BASEURL + "restPayItemList_v2";
    //成长档案提示
    public static final String getTermPaperInstructions = KIG_BASEURL + "getTermPaperInstructions";
    //拉取吃药提醒列表
    public static final String restGetStudentMedicine_v2 = KIG_BASEURL + "restGetStudentMedicine_v2";
    //上传吃药提醒
    public static final String restPutStudentMedicine_v2 = KIG_BASEURL + "restPutStudentMedicine_v2";
    //删除吃药提醒
    public static final String restDeleteStudentMedicine_v2 = KIG_BASEURL + "restDeleteStudentMedicine_v2";
    //获取IM账号密码
    public static final String restGetIMMsg = KIG_BASEURL + "restGetIMMsg";
    //创建支付订单
    public static final String restPayOrderCreate_v2 = KIG_BASEURL + "restPayOrderCreate_v2";
    //充值专享订单记录
    public static final String restPayOrderList_v2 = KIG_BASEURL + "restPayOrderList_v2";
    //删除订单
    public static final String restPayOrderDelete_v2 = KIG_BASEURL + "restPayOrderDelete_v2";
    //取消订单
    public static final String restPayOrderCancel_v2 = KIG_BASEURL + "restPayOrderCancel_v2";
    //上传时光相册
    public static final String restStudentGrownPhoto = KIG_BASEURL + "restStudentGrownPhoto";
    //获取OSS保存地址
    public static final String getUploadSavePath ="https://www.atongmu.net:8443/backwork/rest/common/getUploadSavePath";
    //上传作业
    public static final String restPutStudentJob =KIG_BASEURL+"restPutStudentJob";
    //获得已完成作业月份列表
    public static final String restGetFinishedJob =KIG_BASEURL+"restGetFinishedJob";
    //根据月份获取已完成的作业
    public static final String restGetFinishedJobStatusList =KIG_BASEURL+"restGetFinishedJobStatusList";
    //收不到短信验证码
    public static final String solutionOfVerifyCodeNotReceived =KIG_BASEURL+"solutionOfVerifyCodeNotReceived";
    //扫码签到
    public static final String restPushStudentSignIn =KIG_BASEURL+"restPushStudentSignIn";
}
