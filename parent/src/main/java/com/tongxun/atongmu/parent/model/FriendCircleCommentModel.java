package com.tongxun.atongmu.parent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Anro on 2017/7/6.
 */

public class FriendCircleCommentModel implements Parcelable {



    private String commentPersonId;
    private String commentId;
    private Boolean commentCurrentPerson;
    private String commentPersonName;
    private String commentText;
    private String commentSourcePersonId;
    private String commentSourcePersonName;
    private String commentType;

    public String getCommentPersonId() {
        return commentPersonId;
    }

    public void setCommentPersonId(String commentPersonId) {
        this.commentPersonId = commentPersonId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public Boolean getCommentCurrentPerson() {
        return commentCurrentPerson;
    }

    public void setCommentCurrentPerson(Boolean commentCurrentPerson) {
        this.commentCurrentPerson = commentCurrentPerson;
    }

    public String getCommentPersonName() {
        return commentPersonName;
    }

    public void setCommentPersonName(String commentPersonName) {
        this.commentPersonName = commentPersonName;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentSourcePersonId() {
        return commentSourcePersonId;
    }

    public void setCommentSourcePersonId(String commentSourcePersonId) {
        this.commentSourcePersonId = commentSourcePersonId;
    }

    public String getCommentSourcePersonName() {
        return commentSourcePersonName;
    }

    public void setCommentSourcePersonName(String commentSourcePersonName) {
        this.commentSourcePersonName = commentSourcePersonName;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.commentPersonId);
        dest.writeString(this.commentId);
        dest.writeValue(this.commentCurrentPerson);
        dest.writeString(this.commentPersonName);
        dest.writeString(this.commentText);
        dest.writeString(this.commentSourcePersonId);
        dest.writeString(this.commentSourcePersonName);
        dest.writeString(this.commentType);
    }

    public FriendCircleCommentModel() {
    }

    protected FriendCircleCommentModel(Parcel in) {
        this.commentPersonId = in.readString();
        this.commentId = in.readString();
        this.commentCurrentPerson = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.commentPersonName = in.readString();
        this.commentText = in.readString();
        this.commentSourcePersonId = in.readString();
        this.commentSourcePersonName = in.readString();
        this.commentType = in.readString();
    }

    public static final Parcelable.Creator<FriendCircleCommentModel> CREATOR = new Parcelable.Creator<FriendCircleCommentModel>() {
        @Override
        public FriendCircleCommentModel createFromParcel(Parcel source) {
            return new FriendCircleCommentModel(source);
        }

        @Override
        public FriendCircleCommentModel[] newArray(int size) {
            return new FriendCircleCommentModel[size];
        }
    };
}
