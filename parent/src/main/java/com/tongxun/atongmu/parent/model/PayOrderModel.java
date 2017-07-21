package com.tongxun.atongmu.parent.model;

/**
 * Created by Anro on 2017/1/11.
 */

public class PayOrderModel {


    /**
     * status :
     * message :
     * data : {"orderString":""}
     */

    private String status;
    private String message;
    /**
     * orderString :
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String orderString;

        public String getOrderString() {
            return orderString;
        }

        public void setOrderString(String orderString) {
            this.orderString = orderString;
        }
    }
}
