package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/17.
 */

public class TeacherStyleCallBack {



    private String status;
    private String message;
    private List<TeacherStyleModel> data;

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

    public List<TeacherStyleModel> getData() {
        return data;
    }

    public void setData(List<TeacherStyleModel> data) {
        this.data = data;
    }


}
