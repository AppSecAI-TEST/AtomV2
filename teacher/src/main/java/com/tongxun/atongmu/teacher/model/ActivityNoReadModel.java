package com.tongxun.atongmu.teacher.model;

import java.util.List;

/**
 * Created by Anro on 2017/8/3.
 */

public class ActivityNoReadModel {
    /**
     * noReadManCount : 0人未阅读
     * studentData : []
     * className : 大二班
     */

    private String noReadManCount;
    private String className;
    private List<NoReadPresonModel> studentData;

    public String getNoReadManCount() {
        return noReadManCount;
    }

    public void setNoReadManCount(String noReadManCount) {
        this.noReadManCount = noReadManCount;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<NoReadPresonModel> getStudentData() {
        return studentData;
    }

    public void setStudentData(List<NoReadPresonModel> studentData) {
        this.studentData = studentData;
    }
}
