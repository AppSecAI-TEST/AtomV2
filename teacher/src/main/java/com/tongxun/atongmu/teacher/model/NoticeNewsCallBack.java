package com.tongxun.atongmu.teacher.model;

/**
 * Created by Anro on 2017/8/2.
 */

public class NoticeNewsCallBack {

    /**
     * data : {"noticeRecordCount":"50","isHavenNoticeRecord":"true","activityRecordCount":"11","isHavenActivityRecord":"true","newRecordCount":"25","isHavenNewRecord":"true","dynamicRecordCount":"0","isHavenDynamicRecord":"false","atmNoticeRecordCount":"3","isHavenAtmNoticeRecord":"true","smallRecordCount":"1","isHavenSmallRecord":"true"}
     * status : success
     * message :
     */

    private NoticeNewsModel data;
    private String status;
    private String message;

    public NoticeNewsModel getData() {
        return data;
    }

    public void setData(NoticeNewsModel data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
