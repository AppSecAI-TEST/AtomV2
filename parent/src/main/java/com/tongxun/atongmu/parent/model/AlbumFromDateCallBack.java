package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/13.
 */

public class AlbumFromDateCallBack {



    private String status;
    private String message;
    private List<AlbumFromDateModel> datas;

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

    public List<AlbumFromDateModel> getDatas() {
        return datas;
    }

    public void setDatas(List<AlbumFromDateModel> datas) {
        this.datas = datas;
    }


}
