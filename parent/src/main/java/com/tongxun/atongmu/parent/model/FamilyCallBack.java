package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/8/7.
 */

public class FamilyCallBack {



    private String status;
    private String message;
    private List<FamilyModel> datas;

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

    public List<FamilyModel> getDatas() {
        return datas;
    }

    public void setDatas(List<FamilyModel> datas) {
        this.datas = datas;
    }


}
