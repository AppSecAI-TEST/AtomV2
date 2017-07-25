package com.tongxun.atongmu.parent.model;

/**
 * Created by Anro on 2017/7/25.
 */

public class WxPayCallBack {

    /**
     * status : success
     * message :
     * data : {"appid":"wx7a4a3e22cae881f5","partnerid":"1373614402","packageDesc":"Sign=WXPay","noncestr":"5lchVqz0iTHdwioR","timestamp":1500951367454,"prepayid":"wx20170725105607e8c6fcead20805215095","sign":"4D9B297194117ABB52FBE696E9FFDA8A"}
     */

    private String status;
    private String message;
    private WxPayModel data;

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

    public WxPayModel getData() {
        return data;
    }

    public void setData(WxPayModel data) {
        this.data = data;
    }


}
