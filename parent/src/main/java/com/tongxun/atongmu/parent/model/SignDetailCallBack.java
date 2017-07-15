package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/15.
 */

public class SignDetailCallBack {

    private String type;
    private String status;
    private String message;
    private List<SignDetailModel> datas;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<SignDetailModel> getDatas() {
        return datas;
    }

    public void setDatas(List<SignDetailModel> datas) {
        this.datas = datas;
    }


}
