package com.tongxun.atongmu.parent.model;

/**
 * Created by Anro on 2017/7/21.
 */

public class GrowthModel {


    /**
     * grownUrl : http://127.0.0.1:10010/backwork/rest/kig/restGetStudentDayGrown?grownId=205f90057c124430829abccac34462b3
     * status : success
     * message :
     */

    private String grownUrl;
    private String status;
    private String message;

    public String getGrownUrl() {
        return grownUrl;
    }

    public void setGrownUrl(String grownUrl) {
        this.grownUrl = grownUrl;
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
