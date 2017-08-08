package com.tongxun.atongmu.teacher.model;

/**
 * Created by Anro on 2017/8/8.
 */

public class PersonSignModel {
    /**
     * classLeave : 0
     * classId : gardenerAttendance
     * classTotal : 51
     * classArrived : 0
     * classUnArrived : 51
     * className : 园丁签到
     */

    private String classLeave;
    private String classId;
    private String classTotal;
    private String classArrived;
    private String classUnArrived;
    private String className;

    public String getClassLeave() {
        return classLeave;
    }

    public void setClassLeave(String classLeave) {
        this.classLeave = classLeave;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassTotal() {
        return classTotal;
    }

    public void setClassTotal(String classTotal) {
        this.classTotal = classTotal;
    }

    public String getClassArrived() {
        return classArrived;
    }

    public void setClassArrived(String classArrived) {
        this.classArrived = classArrived;
    }

    public String getClassUnArrived() {
        return classUnArrived;
    }

    public void setClassUnArrived(String classUnArrived) {
        this.classUnArrived = classUnArrived;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
