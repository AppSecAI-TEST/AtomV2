package com.tongxun.atongmu.teacher.model;

import java.util.List;

/**
 * Created by Anro on 2017/8/2.
 */

public class ActivityListCallBack {


    private String status;
    private String message;
    private List<ActivityListModel> data;

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

    public List<ActivityListModel> getData() {
        return data;
    }

    public void setData(List<ActivityListModel> data) {
        this.data = data;
    }


}
