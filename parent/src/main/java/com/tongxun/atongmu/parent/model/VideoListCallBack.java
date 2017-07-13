package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/13.
 */

public class VideoListCallBack {


    /**
     * datas : [{"deviceName":"室外","deviceId":"666439025","verificationCode":"ZRNRXG","videoClarity":"流畅","startTime":"08:00","endTime":"17:30","currentTime":"11:02:42","channelNo":"1","isOnline":"false"},{"deviceName":"中二班","deviceId":"699631684","verificationCode":"RXIEIS","videoClarity":"流畅","startTime":"08:00","endTime":"23:59","currentTime":"11:02:42","channelNo":"1","isOnline":"true"}]
     * accessToken : at.33czo5jf78cjzoji33tl0r6t72nw8sut-2o8hozx8z7-1vid2p4-c5mykvjg5
     * status : success
     * message :
     */

    private String accessToken;
    private String status;
    private String message;
    /**
     * deviceName : 室外
     * deviceId : 666439025
     * verificationCode : ZRNRXG
     * videoClarity : 流畅
     * startTime : 08:00
     * endTime : 17:30
     * currentTime : 11:02:42
     * channelNo : 1
     * isOnline : false
     */

    private List<VideoListModel> datas;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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

    public List<VideoListModel> getDatas() {
        return datas;
    }

    public void setDatas(List<VideoListModel> datas) {
        this.datas = datas;
    }
}
