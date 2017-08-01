package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/8/1.
 */

public class HomeworkMonCallBack {


    /**
     * datas : ["2017年08月","2017年04月"]
     * status : success
     * message :
     */

    private String status;
    private String message;
    private List<String> datas;

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

    public List<String> getDatas() {
        return datas;
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
    }
}
