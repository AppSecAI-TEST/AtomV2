package com.tongxun.atongmu.teacher.model;

import java.util.List;

/**
 * Created by Anro on 2017/8/8.
 */

public class TeacherSignCallBack {



    private String className;
    private String carNum;
    private String arrivedNum;
    private String unArrivedNum;
    private String leaveNum;
    private String identity;
    private String status;
    private String message;
    private List<TeacherSignModel> arrived;
    private List<TeacherSignModel> unArrived;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getArrivedNum() {
        return arrivedNum;
    }

    public void setArrivedNum(String arrivedNum) {
        this.arrivedNum = arrivedNum;
    }

    public String getUnArrivedNum() {
        return unArrivedNum;
    }

    public void setUnArrivedNum(String unArrivedNum) {
        this.unArrivedNum = unArrivedNum;
    }

    public String getLeaveNum() {
        return leaveNum;
    }

    public void setLeaveNum(String leaveNum) {
        this.leaveNum = leaveNum;
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

    public List<TeacherSignModel> getArrived() {
        return arrived;
    }

    public void setArrived(List<TeacherSignModel> arrived) {
        this.arrived = arrived;
    }

    public List<TeacherSignModel> getUnArrived() {
        return unArrived;
    }

    public void setUnArrived(List<TeacherSignModel> unArrived) {
        this.unArrived = unArrived;
    }

}
