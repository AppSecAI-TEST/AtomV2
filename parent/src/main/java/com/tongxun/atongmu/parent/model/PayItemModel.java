package com.tongxun.atongmu.parent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Anro on 2017/7/18.
 */

public class PayItemModel implements Parcelable {
    /**
     * number : ¥0.01
     * title : 1
     */

    private String number;
    private String title;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.number);
        dest.writeString(this.title);
    }

    public PayItemModel() {
    }

    protected PayItemModel(Parcel in) {
        this.number = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<PayItemModel> CREATOR = new Parcelable.Creator<PayItemModel>() {
        @Override
        public PayItemModel createFromParcel(Parcel source) {
            return new PayItemModel(source);
        }

        @Override
        public PayItemModel[] newArray(int size) {
            return new PayItemModel[size];
        }
    };
}
