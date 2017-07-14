package com.tongxun.atongmu.parent.model;

/**
 * Created by Anro on 2017/7/14.
 */

public class SchoolBusPositionModel {


    /**
     * status : success
     * carStatus : 1
     * longitude :
     * latitude :
     * message :
     */

    private String status;
    private String carStatus;
    private String longitude;
    private String latitude;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
