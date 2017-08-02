package com.tongxun.atongmu.teacher.model;

/**
 * Created by Anro on 2017/6/30.
 */

public class BaseCallBack {

    /**
     * data :
     * message :
     * status : success
     */

    private String data;
    private String message;
    private String status;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

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
}
