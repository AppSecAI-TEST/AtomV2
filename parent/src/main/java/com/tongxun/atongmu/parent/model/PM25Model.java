package com.tongxun.atongmu.parent.model;

/**
 * Created by Anro on 2017/7/14.
 */

public class PM25Model {

    /**
     * pm25Url : https://www.atongmu.net:8443/backwork/rest/kig/restShowPM25?classcId=8a6dd7b5c31d4ee0a06a082e2ab43cb8
     * status : success
     * message :
     */

    private String pm25Url;
    private String status;
    private String message;

    public String getPm25Url() {
        return pm25Url;
    }

    public void setPm25Url(String pm25Url) {
        this.pm25Url = pm25Url;
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
}
