package com.tongxun.atongmu.parent.model;

/**
 * Created by Anro on 2017/7/18.
 */

public class TuitionOrderCallBack {


    /**
     * status : success
     * message :
     * orderString : partner="2088421545378269"&seller_id="jinbo.dong@atongmu.net"&out_trade_no="20170715161527663"&subject="阿童目服务"&body="幼儿园缴费"&total_fee="0.01"&notify_url="http://127.0.0.1:10010/backwork/rest/kig/restPayReceiveALiPayNotify"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="30m"&return_url="m.alipay.com"&sign="Ti60KlKIm1CvLrwAVjE0SgEPMoIlP8t%2FiFzVTF9xQwQ1XtCAV9MUbryQgfjJAP9g5moVoYd%2F2fHZv86jFQqHtAPyg0FbIYUR8OXlSdorDxbZoB5hv7qgdK4%2FBTwYuR3RRbtwIAWVcLnwk%2BBo8Xe72pVI9HN1yZDlGDiKmdIyJvg%3D"&sign_type="RSA"
     */

    private String status;
    private String message;
    private String orderString;

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

    public String getOrderString() {
        return orderString;
    }

    public void setOrderString(String orderString) {
        this.orderString = orderString;
    }
}
