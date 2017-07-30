package com.tongxun.atongmu.parent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Anro on 2017/7/28.
 */

public class RechargeItemModel implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.packgId);
        dest.writeString(this.packgTitle);
        dest.writeString(this.packgPrice);
        dest.writeString(this.packgRemark);
        dest.writeString(this.packgType);
        dest.writeByte(this.isItemCheck ? (byte) 1 : (byte) 0);
    }

    public RechargeItemModel() {
    }

    protected RechargeItemModel(Parcel in) {
        this.packgId = in.readString();
        this.packgTitle = in.readString();
        this.packgPrice = in.readString();
        this.packgRemark = in.readString();
        this.packgType = in.readString();
        this.isItemCheck = in.readByte() != 0;
    }

    public static final Parcelable.Creator<RechargeItemModel> CREATOR = new Parcelable.Creator<RechargeItemModel>() {
        @Override
        public RechargeItemModel createFromParcel(Parcel source) {
            return new RechargeItemModel(source);
        }

        @Override
        public RechargeItemModel[] newArray(int size) {
            return new RechargeItemModel[size];
        }
    };
}
