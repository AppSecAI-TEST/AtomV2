package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/5.
 */

public class ActivityCallBack {



    private String status;
    private String message;
    private List<ActivityModel> data;

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

    public List<ActivityModel> getData() {
        return data;
    }

    public void setData(List<ActivityModel> data) {
        this.data = data;
    }


}
