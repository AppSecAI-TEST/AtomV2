package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/6/20.
 */

public class LoginCallBack {

    /**
     * status : success
     * datas : [{"tokenId":"1e2b6f1b-2ca1-4bbb-8da7-f09cac9ba0cf"}]
     * message :
     */

    private String status;
    private String message;
    private List<TokenIdModel> datas;

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

    public List<TokenIdModel> getDatas() {
        return datas;
    }

    public void setDatas(List<TokenIdModel> datas) {
        this.datas = datas;
    }

}
