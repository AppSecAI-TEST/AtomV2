package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/13.
 */

public class MornCheckModel {
    private String actMonth;
    private List<MornCheckPhoto> monthData;

    public String getActMonth() {
        return actMonth;
    }

    public void setActMonth(String actMonth) {
        this.actMonth = actMonth;
    }

    public List<MornCheckPhoto> getMonthDate() {
        return monthData;
    }

    public void setMonthDate(List<MornCheckPhoto> monthDate) {
        this.monthData = monthDate;
    }
}
