package com.tongxun.atongmu.parent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Anro on 2017/7/6.
 */

public class FriendCirlceVoteModel implements Parcelable {
    /**
     * voteNickName : 刁刁刁刁刁老师
     */

    private String voteNickName;

    public String getVoteNickName() {
        return voteNickName;
    }

    public void setVoteNickName(String voteNickName) {
        this.voteNickName = voteNickName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.voteNickName);
    }

    public FriendCirlceVoteModel() {
    }

    protected FriendCirlceVoteModel(Parcel in) {
        this.voteNickName = in.readString();
    }

    public static final Parcelable.Creator<FriendCirlceVoteModel> CREATOR = new Parcelable.Creator<FriendCirlceVoteModel>() {
        @Override
        public FriendCirlceVoteModel createFromParcel(Parcel source) {
            return new FriendCirlceVoteModel(source);
        }

        @Override
        public FriendCirlceVoteModel[] newArray(int size) {
            return new FriendCirlceVoteModel[size];
        }
    };
}
