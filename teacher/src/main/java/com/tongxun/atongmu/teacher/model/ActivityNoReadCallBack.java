package com.tongxun.atongmu.teacher.model;

import java.util.List;

/**
 * Created by Anro on 2017/8/3.
 */

public class ActivityNoReadCallBack {



    private String activityId;
    private String startDate;
    private String endDate;
    private String contractPerson;
    private String contractPhone;
    private String status;
    private String message;
    private List<ActivityHaveActModel> havenActStudents;
    private List<ActivityNoReadModel> noReadActStudents;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getContractPerson() {
        return contractPerson;
    }

    public void setContractPerson(String contractPerson) {
        this.contractPerson = contractPerson;
    }

    public String getContractPhone() {
        return contractPhone;
    }

    public void setContractPhone(String contractPhone) {
        this.contractPhone = contractPhone;
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

    public List<ActivityHaveActModel> getHavenActStudents() {
        return havenActStudents;
    }

    public void setHavenActStudents(List<ActivityHaveActModel> havenActStudents) {
        this.havenActStudents = havenActStudents;
    }

    public List<ActivityNoReadModel> getNoReadActStudents() {
        return noReadActStudents;
    }

    public void setNoReadActStudents(List<ActivityNoReadModel> noReadActStudents) {
        this.noReadActStudents = noReadActStudents;
    }




}
