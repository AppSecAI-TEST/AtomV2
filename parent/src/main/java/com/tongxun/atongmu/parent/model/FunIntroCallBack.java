package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/26.
 */

public class FunIntroCallBack {


    private String status;
    private String message;
    private List<FunIntroModel> data;

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

    public List<FunIntroModel> getData() {
        return data;
    }

    public void setData(List<FunIntroModel> data) {
        this.data = data;
    }


}
