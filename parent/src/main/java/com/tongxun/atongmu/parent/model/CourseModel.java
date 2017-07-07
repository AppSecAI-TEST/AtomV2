package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/4.
 */

public class CourseModel {
    private List<WeekBean> mon;
    private List<WeekBean> aft;
    private String date;
    private String week;
    private String nowDay;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getNowDay() {
        return nowDay;
    }

    public void setNowDay(String nowDay) {
        this.nowDay = nowDay;
    }

    public List<WeekBean> getMon() {
        return mon;
    }

    public void setMon(List<WeekBean> mon) {
        this.mon = mon;
    }

    public List<WeekBean> getAft() {
        return aft;
    }

    public void setAft(List<WeekBean> aft) {
        this.aft = aft;
    }


}
