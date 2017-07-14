package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/14.
 */

public class BabySigninCallBack {



    private String signNum;
    private String status;
    private String message;
    private List<BabySignInModel> datas;

    public String getSignNum() {
        return signNum;
    }

    public void setSignNum(String signNum) {
        this.signNum = signNum;
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

    public List<BabySignInModel> getDatas() {
        return datas;
    }

    public void setDatas(List<BabySignInModel> datas) {
        this.datas = datas;
    }


}
