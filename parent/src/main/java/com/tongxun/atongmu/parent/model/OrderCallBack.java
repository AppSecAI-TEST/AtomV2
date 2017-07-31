package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/31.
 */

public class OrderCallBack {

    /**
     * status : success
     * message :
     * data : [{"packgType":"欢乐","packgTitle":"一年欢乐套餐","packgRemark":"APP基础功能套餐","packgPrice":"0.02","createDate":"2017-07-30 23:21:34","orderType":"已取消","payName":"张路爸爸","payType":"微信","orderId":"7b1da4d42e7f4af3aa9f828bdbd9c5fc","payId":"20170730232133732","payObject":"阿童目","packgId":"ac64e8dff0104be2a170a5bb825cca87","orderFailTime":""},{"packgType":"欢乐","packgTitle":"一年欢乐套餐","packgRemark":"APP基础功能套餐","packgPrice":"0.02","createDate":"2017-07-30 23:17:08","orderType":"已取消","payName":"张路爸爸","payType":"支付宝","orderId":"96eceb3785c94aaeb2cdbc094111119b","payId":"20170730231708397","payObject":"阿童目","packgId":"ac64e8dff0104be2a170a5bb825cca87","orderFailTime":""},{"packgType":"欢乐","packgTitle":"一年欢乐套餐","packgRemark":"APP基础功能套餐","packgPrice":"0.02","createDate":"2017-07-30 23:16:51","orderType":"已取消","payName":"张路爸爸","payType":"支付宝","orderId":"0f6db848001f4fec8e817072a0efa23e","payId":"20170730231650846","payObject":"阿童目","packgId":"ac64e8dff0104be2a170a5bb825cca87","orderFailTime":""},{"packgType":"欢乐","packgTitle":"一年欢乐套餐","packgRemark":"APP基础功能套餐","packgPrice":"0.02","createDate":"2017-07-30 23:09:20","orderType":"已取消","payName":"张路爸爸","payType":"支付宝","orderId":"1b3424dcce324f809c4f1e778f014f22","payId":"20170730230920229","payObject":"阿童目","packgId":"ac64e8dff0104be2a170a5bb825cca87","orderFailTime":""},{"packgType":"功能","packgTitle":"一学期实时视频","packgRemark":"实时观看宝宝在园情况","packgPrice":"0.01","createDate":"2017-03-29 13:31:15","orderType":"已支付","payName":"张路爸爸","payType":"后台","orderId":"bf14bd2ea4d54af0925bd8c7de892f18","payId":"","payObject":"阿童目","packgId":"d651cf342a234f1d9223dd82835065ea","orderFailTime":""},{"packgType":"欢乐","packgTitle":"一年欢乐套餐","packgRemark":"APP基础功能套餐","packgPrice":"0.02","createDate":"2017-03-29 12:05:04","orderType":"已支付","payName":"张路爸爸","payType":"支付宝","orderId":"f0e38d734d184a2881c0751e803ee536","payId":"20170329120503692","payObject":"阿童目","packgId":"ac64e8dff0104be2a170a5bb825cca87","orderFailTime":""},{"packgType":"欢乐","packgTitle":"一学期欢乐套餐","packgRemark":"APP基础功能套餐","packgPrice":"0.01","createDate":"2017-03-29 11:59:41","orderType":"已支付","payName":"张路爸爸","payType":"支付宝","orderId":"94db1d2659fd4272bf75836ad19873f7","payId":"20170329115941254","payObject":"阿童目","packgId":"98727a6f6dee48b5814ba19fe7b94571","orderFailTime":""}]
     */

    private String status;
    private String message;
    private List<OrderModel> data;

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

    public List<OrderModel> getData() {
        return data;
    }

    public void setData(List<OrderModel> data) {
        this.data = data;
    }


}
