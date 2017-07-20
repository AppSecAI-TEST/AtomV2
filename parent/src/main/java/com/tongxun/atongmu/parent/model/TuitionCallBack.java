package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/17.
 */

public class TuitionCallBack {

    /**
     * datas : [{"itemId":"2781560ca47e4e7e85bb24c224e0a3a6","itemTitle":"又到一年缴费时期","totalNum":"¥0.01","createTime":"2017-07-15 14:32","remark":"缴费啦","itemList":[{"number":"¥0.01","title":"1"}],"schoolName":"江苏童讯幼儿园"}]
     * status : success
     * message :
     */

    private String status;
    private String message;
    private List<TuitionModel> datas;

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

    public List<TuitionModel> getData() {
        return datas;
    }

    public void setDatas(List<TuitionModel> datas) {
        this.datas = datas;
    }


}
