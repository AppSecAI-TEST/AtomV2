package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/20.
 */

public class OrderRecordCallBack {

    /**
     * datas : [{"itemId":"274158b4760248fb9fc7bf2ba5e64a96","payType":"支付宝","orderId":"20170718145637773","packgPrice":"¥0.03","packgRemark":"缴费呼啦啦","packgTitle":"一学期费用","itemList":[{"number":"¥0.01","title":"123"},{"number":"¥0.01","title":"2"},{"number":"¥0.01","title":"时尚最低学费"},{"number":"¥0.01","title":"衣服"},{"number":"¥0.01","title":"1"}],"schoolName":"A阿童目新幼儿园QQQ","payName":"窦晓冬爸爸","createDate":"2017-07-18 14:56"}]
     * status : success
     * message :
     */

    private String status;
    private String message;
    private List<OrderRecordModel> datas;

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

    public List<OrderRecordModel> getDatas() {
        return datas;
    }

    public void setDatas(List<OrderRecordModel> datas) {
        this.datas = datas;
    }


}
