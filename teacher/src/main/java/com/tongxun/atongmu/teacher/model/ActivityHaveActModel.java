package com.tongxun.atongmu.teacher.model;

import java.util.List;

/**
 * Created by Anro on 2017/8/3.
 */

public class ActivityHaveActModel {
    /**
     * actManCount : 1已参加活动
     * studentData : [{"studentId":"84ef6b87f1a7434cbb6d88db3f5baa4a","personName":"Diao Xiang Qin","photo1":"https://www.atongmu.net:8443/backwork/userfiles/images/kigStudent/2017/04/c44dcba4_2ab5_4f9d_b036_93a98c9d6cad.jpg","remarks":"我来报名啦，老爸老妈都参加哈，哈哈"}]
     * className : 大二班
     */

    private String actManCount;
    private String className;
    private List<ActivityHaveActPresonModel> studentData;

    public String getActManCount() {
        return actManCount;
    }

    public void setActManCount(String actManCount) {
        this.actManCount = actManCount;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ActivityHaveActPresonModel> getStudentData() {
        return studentData;
    }

    public void setStudentData(List<ActivityHaveActPresonModel> studentData) {
        this.studentData = studentData;
    }


}
