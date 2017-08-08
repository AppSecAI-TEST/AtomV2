package com.tongxun.atongmu.teacher.model;

import java.util.List;

/**
 * Created by Anro on 2017/8/8.
 */

public class PersonSignCallBack {

    private String total;
    private String car;
    private String arrived;
    private String unArrived;
    private String leave;
    private String arrivedPre;
    private String unArrivedPre;
    private String leavePre;
    private String identity;
    private String status;
    private String message;
    private List<PersonSignModel> datas;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getArrived() {
        return arrived;
    }

    public void setArrived(String arrived) {
        this.arrived = arrived;
    }

    public String getUnArrived() {
        return unArrived;
    }

    public void setUnArrived(String unArrived) {
        this.unArrived = unArrived;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    public String getArrivedPre() {
        return arrivedPre;
    }

    public void setArrivedPre(String arrivedPre) {
        this.arrivedPre = arrivedPre;
    }

    public String getUnArrivedPre() {
        return unArrivedPre;
    }

    public void setUnArrivedPre(String unArrivedPre) {
        this.unArrivedPre = unArrivedPre;
    }

    public String getLeavePre() {
        return leavePre;
    }

    public void setLeavePre(String leavePre) {
        this.leavePre = leavePre;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
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

    public List<PersonSignModel> getDatas() {
        return datas;
    }

    public void setDatas(List<PersonSignModel> datas) {
        this.datas = datas;
    }

}
