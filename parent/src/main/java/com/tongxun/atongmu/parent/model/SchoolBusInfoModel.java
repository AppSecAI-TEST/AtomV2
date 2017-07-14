package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/14.
 */

public class SchoolBusInfoModel {

    /**
     * status : success
     * carName : 校车董总
     * carNum : 苏E-88887
     * driver : 李师傅
     * teacher : 赵老师
     * driverNum : 18912607789
     * teaNum : 18912607789
     * latlng_list : [{"longitude":"","latitude":""}]
     */

    private String status;
    private String carName;
    private String carNum;
    private String driver;
    private String teacher;
    private String driverNum;
    private String teaNum;
    private List<LatlngListBean> latlng_list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDriverNum() {
        return driverNum;
    }

    public void setDriverNum(String driverNum) {
        this.driverNum = driverNum;
    }

    public String getTeaNum() {
        return teaNum;
    }

    public void setTeaNum(String teaNum) {
        this.teaNum = teaNum;
    }

    public List<LatlngListBean> getLatlng_list() {
        return latlng_list;
    }

    public void setLatlng_list(List<LatlngListBean> latlng_list) {
        this.latlng_list = latlng_list;
    }

    public static class LatlngListBean {
        /**
         * longitude :
         * latitude :
         */

        private String longitude;
        private String latitude;

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
    }
}
