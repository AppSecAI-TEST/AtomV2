package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/13.
 */

public class MornCheckCallBack {
    private String message;
    private String status;
    private List<MornCheckModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MornCheckModel> getData() {
        return data;
    }

    public void setData(List<MornCheckModel> data) {
        this.data = data;
    }
}
