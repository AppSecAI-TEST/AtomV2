package com.tongxun.atongmu.parent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Anro on 2017/7/31.
 */

public class OrderModel implements Parcelable {
    /**
     * packgType : 欢乐
     * packgTitle : 一年欢乐套餐
     * packgRemark : APP基础功能套餐
     * packgPrice : 0.02
     * createDate : 2017-07-30 23:21:34
     * orderType : 已取消
     * payName : 张路爸爸
     * payType : 微信
     * orderId : 7b1da4d42e7f4af3aa9f828bdbd9c5fc
     * payId : 20170730232133732
     * payObject : 阿童目
     * packgId : ac64e8dff0104be2a170a5bb825cca87
     * orderFailTime :
     */

    private String packgType;
    private String packgTitle;
    private String packgRemark;
    private String packgPrice;
    private String createDate;
    private String orderType;
    private String payName;
    private String payType;
    private String orderId;
    private String payId;
    private String payObject;
    private String packgId;
    private String orderFailTime;

    public String getPackgType() {
        return packgType;
    }

    public void setPackgType(String packgType) {
        this.packgType = packgType;
    }

    public String getPackgTitle() {
        return packgTitle;
    }

    public void setPackgTitle(String packgTitle) {
        this.packgTitle = packgTitle;
    }

    public String getPackgRemark() {
        return packgRemark;
    }

    public void setPackgRemark(String packgRemark) {
        this.packgRemark = packgRemark;
    }

    public String getPackgPrice() {
        return packgPrice;
    }

    public void setPackgPrice(String packgPrice) {
        this.packgPrice = packgPrice;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
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

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getPayObject() {
        return payObject;
    }

    public void setPayObject(String payObject) {
        this.payObject = payObject;
    }

    public String getPackgId() {
        return packgId;
    }

    public void setPackgId(String packgId) {
        this.packgId = packgId;
    }

    public String getOrderFailTime() {
        return orderFailTime;
    }

    public void setOrderFailTime(String orderFailTime) {
        this.orderFailTime = orderFailTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.packgType);
        dest.writeString(this.packgTitle);
        dest.writeString(this.packgRemark);
        dest.writeString(this.packgPrice);
        dest.writeString(this.createDate);
        dest.writeString(this.orderType);
        dest.writeString(this.payName);
        dest.writeString(this.payType);
        dest.writeString(this.orderId);
        dest.writeString(this.payId);
        dest.writeString(this.payObject);
        dest.writeString(this.packgId);
        dest.writeString(this.orderFailTime);
    }

    public OrderModel() {
    }

    protected OrderModel(Parcel in) {
        this.packgType = in.readString();
        this.packgTitle = in.readString();
        this.packgRemark = in.readString();
        this.packgPrice = in.readString();
        this.createDate = in.readString();
        this.orderType = in.readString();
        this.payName = in.readString();
        this.payType = in.readString();
        this.orderId = in.readString();
        this.payId = in.readString();
        this.payObject = in.readString();
        this.packgId = in.readString();
        this.orderFailTime = in.readString();
    }

    public static final Parcelable.Creator<OrderModel> CREATOR = new Parcelable.Creator<OrderModel>() {
        @Override
        public OrderModel createFromParcel(Parcel source) {
            return new OrderModel(source);
        }

        @Override
        public OrderModel[] newArray(int size) {
            return new OrderModel[size];
        }
    };
}
