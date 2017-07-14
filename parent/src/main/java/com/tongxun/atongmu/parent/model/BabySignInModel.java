package com.tongxun.atongmu.parent.model;

/**
 * Created by Anro on 2017/7/14.
 */

public class BabySignInModel {
    /**
     * date : 2017-06-01
     * type : arrived
     */

    private String date;
    private String type;

    public BabySignInModel(String date, String type) {
        this.date = date;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
