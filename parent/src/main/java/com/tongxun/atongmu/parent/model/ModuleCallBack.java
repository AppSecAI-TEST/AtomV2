package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/24.
 */

public class ModuleCallBack {


    /**
     * datas : [{"havenNewRecord":false,"enId":"1","enName":"活动通知"},{"havenNewRecord":false,"enId":"2","enName":"校园新闻"},{"havenNewRecord":true,"enId":"3","enName":"作业课程"},{"havenNewRecord":false,"enId":"4","enName":"校园简介"},{"havenNewRecord":false,"enId":"5","enName":"天天食谱"},{"havenNewRecord":false,"enId":"6","enName":"宝宝签到"},{"havenNewRecord":false,"enId":"7","enName":"时光相册"},{"havenNewRecord":false,"enId":"8","enName":"健康成长"},{"havenNewRecord":false,"enId":"9","enName":"快乐天地"},{"havenNewRecord":false,"enId":"10","enName":"校车动态"},{"havenNewRecord":false,"enId":"13","enName":"实时视频"},{"havenNewRecord":false,"enId":"15","enName":"校园环境"},{"havenNewRecord":false,"enId":"16","enName":"圈子"},{"havenNewRecord":false,"enId":"17","enName":"校园缴费"}]
     * status : success
     * message :
     */

    private String status;
    private String message;
    private List<ModuleModel> datas;

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

    public List<ModuleModel> getDatas() {
        return datas;
    }

    public void setDatas(List<ModuleModel> datas) {
        this.datas = datas;
    }


}
