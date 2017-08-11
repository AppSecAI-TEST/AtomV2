package com.tongxun.atongmu.parent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Anro on 2017/7/6.
 */

public class FriendCirclePhotoModel implements Parcelable {
    /**
     * photo : https://www.atongmu.net:8443/backwork/userfiles/images/kigCircle/2017/06/9df72606_c4e1_4cf7_87dc_c03e1aa1b834.jpg
     */

    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.photo);
    }

    public FriendCirclePhotoModel() {
    }

    protected FriendCirclePhotoModel(Parcel in) {
        this.photo = in.readString();
    }

    public static final Parcelable.Creator<FriendCirclePhotoModel> CREATOR = new Parcelable.Creator<FriendCirclePhotoModel>() {
        @Override
        public FriendCirclePhotoModel createFromParcel(Parcel source) {
            return new FriendCirclePhotoModel(source);
        }

        @Override
        public FriendCirclePhotoModel[] newArray(int size) {
            return new FriendCirclePhotoModel[size];
        }
    };
}
