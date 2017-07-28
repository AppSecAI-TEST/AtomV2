package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/28.
 */

public class BannerCallBack {
    private String status;
    private String message;
    /**
     * photo : http://192.168.0.27:10010/backwork/userfiles/images/kigEntrancePhoto/2016/12/f46dfc92_c60f_47ea_8285_3cf2a0dcac91.jpg
     * action :
     */

    private List<BannerDataBean> data;

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

    public List<BannerDataBean> getData() {
        return data;
    }

    public void setData(List<BannerDataBean> data) {
        this.data = data;
    }
}
