package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/24.
 */

public class ModuleCallBack {


    /**
     * data : [{"havenNewRecord":false,"enId":"1","enName":"活动通知","useFlag":true},{"havenNewRecord":false,"enId":"2","enName":"校园新闻","useFlag":true},{"havenNewRecord":false,"enId":"3","enName":"作业课程","useFlag":true},{"havenNewRecord":false,"enId":"4","enName":"校园简介","useFlag":true},{"havenNewRecord":false,"enId":"5","enName":"天天食谱","useFlag":true},{"havenNewRecord":false,"enId":"6","enName":"宝宝签到","useFlag":true},{"havenNewRecord":false,"enId":"7","enName":"时光相册","useFlag":true},{"havenNewRecord":false,"enId":"8","enName":"健康成长","useFlag":true},{"havenNewRecord":false,"enId":"9","enName":"快乐天地","useFlag":true},{"havenNewRecord":false,"enId":"10","enName":"校车动态","useFlag":false},{"havenNewRecord":false,"enId":"11","enName":"兴趣部落","useFlag":false},{"havenNewRecord":false,"enId":"12","enName":"童讯商城","useFlag":false},{"havenNewRecord":false,"enId":"13","enName":"实时视频","useFlag":false},{"havenNewRecord":false,"enId":"14","enName":"激活报告","useFlag":false}]
     * status : success
     * message :
     */

    private String status;
    private String message;
    private List<ModuleModel> data;

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

    public List<ModuleModel> getData() {
        return data;
    }

    public void setData(List<ModuleModel> data) {
        this.data = data;
    }


}
