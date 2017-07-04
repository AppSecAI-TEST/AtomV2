package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/4.
 */

public class CourseListModel {
    private List<CourseModel> thisWeek;
    private List<CourseModel> lastWeek;
    private List<CourseModel> nextWeek;

    public List<CourseModel> getThisWeek() {
        return thisWeek;
    }

    public void setThisWeek(List<CourseModel> thisWeek) {
        this.thisWeek = thisWeek;
    }

    public List<CourseModel> getLastWeek() {
        return lastWeek;
    }

    public void setLastWeek(List<CourseModel> lastWeek) {
        this.lastWeek = lastWeek;
    }

    public List<CourseModel> getNextWeek() {
        return nextWeek;
    }

    public void setNextWeek(List<CourseModel> nextWeek) {
        this.nextWeek = nextWeek;
    }
}
