package com.tongxun.atongmu.parent.model;

/**
 * Created by Anro on 2017/7/28.
 */

public class RechargeItemModel {
    private String packgId;
    private String packgTitle;
    private String packgPrice;
    private String packgRemark;
    private String packgType;
    private boolean isItemCheck;

    public String getPackgId() {
        return packgId;
    }

    public void setPackgId(String packgId) {
        this.packgId = packgId;
    }

    public String getPackgTitle() {
        return packgTitle;
    }

    public void setPackgTitle(String packgTitle) {
        this.packgTitle = packgTitle;
    }

    public String getPackgPrice() {
        return packgPrice;
    }

    public void setPackgPrice(String packgPrice) {
        this.packgPrice = packgPrice;
    }

    public String getPackgRemark() {
        return packgRemark;
    }

    public void setPackgRemark(String packgRemark) {
        this.packgRemark = packgRemark;
    }

    public String getPackgType() {
        return packgType;
    }

    public void setPackgType(String packgType) {
        this.packgType = packgType;
    }

    public boolean isItemCheck() {
        return isItemCheck;
    }

    public void setItemCheck(boolean itemCheck) {
        isItemCheck = itemCheck;
    }
}
