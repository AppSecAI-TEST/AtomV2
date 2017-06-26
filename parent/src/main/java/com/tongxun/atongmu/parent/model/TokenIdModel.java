package com.tongxun.atongmu.parent.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * Created by Anro on 2017/6/26.
 */

public class TokenIdModel extends DataSupport implements Parcelable {
    private String tokenId;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tokenId);
    }

    public TokenIdModel() {
    }

    protected TokenIdModel(Parcel in) {
        this.tokenId = in.readString();
    }

    public static final Parcelable.Creator<TokenIdModel> CREATOR = new Parcelable.Creator<TokenIdModel>() {
        @Override
        public TokenIdModel createFromParcel(Parcel source) {
            return new TokenIdModel(source);
        }

        @Override
        public TokenIdModel[] newArray(int size) {
            return new TokenIdModel[size];
        }
    };
}
