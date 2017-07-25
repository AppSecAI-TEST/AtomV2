package com.tongxun.atongmu.parent.model;

/**
 * Created by Anro on 2017/7/25.
 */

public class WxPayModel {
    /**
     * appid : wx7a4a3e22cae881f5
     * partnerid : 1373614402
     * packageDesc : Sign=WXPay
     * noncestr : 5lchVqz0iTHdwioR
     * timestamp : 1500951367454
     * prepayid : wx20170725105607e8c6fcead20805215095
     * sign : 4D9B297194117ABB52FBE696E9FFDA8A
     */

    private String appid;
    private String partnerid;
    private String packageDesc;
    private String noncestr;
    private long timestamp;
    private String prepayid;
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPackageDesc() {
        return packageDesc;
    }

    public void setPackageDesc(String packageDesc) {
        this.packageDesc = packageDesc;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
