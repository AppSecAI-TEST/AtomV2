package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/8/1.
 */

public class HomeFinishListModel {
    private String date;
    private List<FinishWorkModel> workModelList;

    public HomeFinishListModel(String date, List<FinishWorkModel> workModelList) {
        this.date = date;
        this.workModelList = workModelList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<FinishWorkModel> getWorkModelList() {
        return workModelList;
    }

    public void setWorkModelList(List<FinishWorkModel> workModelList) {
        this.workModelList = workModelList;
    }
}
