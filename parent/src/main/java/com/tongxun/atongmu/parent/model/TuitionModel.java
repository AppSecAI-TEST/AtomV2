package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/17.
 */

public class TuitionModel {
    /**
     * itemId : 2781560ca47e4e7e85bb24c224e0a3a6
     * itemTitle : 又到一年缴费时期
     * totalNum : ¥0.01
     * createTime : 2017-07-15 14:32
     * remark : 缴费啦
     * itemList : [{"number":"¥0.01","title":"1"}]
     * schoolName : 江苏童讯幼儿园
     */

    private String itemId;
    private String itemTitle;
    private String totalNum;
    private String createTime;
    private String remark;
    private String schoolName;
    private List<PayItemModel> itemList;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public List<PayItemModel> getItemList() {
        return itemList;
    }

    public void setItemList(List<PayItemModel> itemList) {
        this.itemList = itemList;
    }


}
