package com.tongxun.atongmu.teacher.model;

import java.util.List;

/**
 * Created by Anro on 2017/8/3.
 */

public class FamilyGroupCallBack {

    /**
     * status : success
     * message :
     * datas : [{"parent_name":"董金波","parent_relation":"爸爸","parent_phone":"18912607789","parent_level":"LV0","parent_photo":"https://www.atongmu.net:8443","parent_id":"92d69cfffc05434283b82778b0e32c00"}]
     */

    private String status;
    private String message;
    private List<FamilyPersonModel> datas;

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

    public List<FamilyPersonModel> getDatas() {
        return datas;
    }

    public void setDatas(List<FamilyPersonModel> datas) {
        this.datas = datas;
    }


}
