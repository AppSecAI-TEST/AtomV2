package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/20.
 */

public class OrderRecordModel {
    /**
     * itemId : 274158b4760248fb9fc7bf2ba5e64a96
     * payType : 支付宝
     * orderId : 20170718145637773
     * packgPrice : ¥0.03
     * packgRemark : 缴费呼啦啦
     * packgTitle : 一学期费用
     * itemList : [{"number":"¥0.01","title":"123"},{"number":"¥0.01","title":"2"},{"number":"¥0.01","title":"时尚最低学费"},{"number":"¥0.01","title":"衣服"},{"number":"¥0.01","title":"1"}]
     * schoolName : A阿童目新幼儿园QQQ
     * payName : 窦晓冬爸爸
     * createDate : 2017-07-18 14:56
     */

    private String itemId;
    private String payType;
    private String orderId;
    private String packgPrice;
    private String packgRemark;
    private String packgTitle;
    private String schoolName;
    private String payName;
    private String createDate;
    private List<PayItemModel> itemList;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getPackgTitle() {
        return packgTitle;
    }

    public void setPackgTitle(String packgTitle) {
        this.packgTitle = packgTitle;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<PayItemModel> getItemList() {
        return itemList;
    }

    public void setItemList(List<PayItemModel> itemList) {
        this.itemList = itemList;
    }



}
