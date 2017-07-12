package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/12.
 */

public class TimeAlbumCallBack {

    private String haveCheck;
    private String status;
    private String message;
    private List<TimeAlbumModel> datas;

    public String getHaveCheck() {
        return haveCheck;
    }

    public void setHaveCheck(String haveCheck) {
        this.haveCheck = haveCheck;
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

    public List<TimeAlbumModel> getDatas() {
        return datas;
    }

    public void setDatas(List<TimeAlbumModel> datas) {
        this.datas = datas;
    }


}
