package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/6/30.
 */

public class NoticeCallBack {


    private String status;
    private String message;
    private List<NoticeModel> data;

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

    public List<NoticeModel> getData() {
        return data;
    }

    public void setData(List<NoticeModel> data) {
        this.data = data;
    }


}
