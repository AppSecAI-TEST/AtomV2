package com.tongxun.atongmu.parent.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Anro on 2017/7/6.
 */

public class FriendCircleModel implements Parcelable {

    private String circleId;
    private String share_url;
    private String personCode;
    private String personName;
    private String personType;
    private String readQty;
    private String shareQty;
    private String personPhoto;
    private String createDate;
    private String context;
    private boolean selfFlag;
    private int voteSum;
    private boolean currentPersonVote;
    private String bodyType;
    private String mediaURL;
    private String mediaImg;
    private List<FriendCirlceVoteModel> votePersons;
    private List<FriendCirclePhotoModel> photos;
    private List<FriendCircleCommentModel> commentPersons;

    public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getReadQty() {
        return readQty;
    }

    public void setReadQty(String readQty) {
        this.readQty = readQty;
    }

    public String getShareQty() {
        return shareQty;
    }

    public void setShareQty(String shareQty) {
        this.shareQty = shareQty;
    }

    public String getPersonPhoto() {
        return personPhoto;
    }

    public void setPersonPhoto(String personPhoto) {
        this.personPhoto = personPhoto;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public boolean isSelfFlag() {
        return selfFlag;
    }

    public void setSelfFlag(boolean selfFlag) {
        this.selfFlag = selfFlag;
    }

    public int getVoteSum() {
        return voteSum;
    }

    public void setVoteSum(int voteSum) {
        this.voteSum = voteSum;
    }

    public boolean isCurrentPersonVote() {
        return currentPersonVote;
    }

    public void setCurrentPersonVote(boolean currentPersonVote) {
        this.currentPersonVote = currentPersonVote;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getMediaURL() {
        return mediaURL;
    }

    public void setMediaURL(String mediaURL) {
        this.mediaURL = mediaURL;
    }

    public String getMediaImg() {
        return mediaImg;
    }

    public void setMediaImg(String mediaImg) {
        this.mediaImg = mediaImg;
    }

    public List<FriendCirlceVoteModel> getVotePersons() {
        return votePersons;
    }

    public void setVotePersons(List<FriendCirlceVoteModel> votePersons) {
        this.votePersons = votePersons;
    }

    public List<FriendCirclePhotoModel> getPhotos() {
        return photos;
    }

    public void setPhotos(List<FriendCirclePhotoModel> photos) {
        this.photos = photos;
    }

    public List<FriendCircleCommentModel> getCommentPersons() {
        return commentPersons;
    }

    public void setCommentPersons(List<FriendCircleCommentModel> commentPersons) {
        this.commentPersons = commentPersons;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.circleId);
        dest.writeString(this.share_url);
        dest.writeString(this.personCode);
        dest.writeString(this.personName);
        dest.writeString(this.personType);
        dest.writeString(this.readQty);
        dest.writeString(this.shareQty);
        dest.writeString(this.personPhoto);
        dest.writeString(this.createDate);
        dest.writeString(this.context);
        dest.writeByte(this.selfFlag ? (byte) 1 : (byte) 0);
        dest.writeInt(this.voteSum);
        dest.writeByte(this.currentPersonVote ? (byte) 1 : (byte) 0);
        dest.writeString(this.bodyType);
        dest.writeString(this.mediaURL);
        dest.writeString(this.mediaImg);
        dest.writeTypedList(this.votePersons);
        dest.writeTypedList(this.photos);
        dest.writeTypedList(this.commentPersons);
    }

    public FriendCircleModel() {
    }

    protected FriendCircleModel(Parcel in) {
        this.circleId = in.readString();
        this.share_url = in.readString();
        this.personCode = in.readString();
        this.personName = in.readString();
        this.personType = in.readString();
        this.readQty = in.readString();
        this.shareQty = in.readString();
        this.personPhoto = in.readString();
        this.createDate = in.readString();
        this.context = in.readString();
        this.selfFlag = in.readByte() != 0;
        this.voteSum = in.readInt();
        this.currentPersonVote = in.readByte() != 0;
        this.bodyType = in.readString();
        this.mediaURL = in.readString();
        this.mediaImg = in.readString();
        this.votePersons = in.createTypedArrayList(FriendCirlceVoteModel.CREATOR);
        this.photos = in.createTypedArrayList(FriendCirclePhotoModel.CREATOR);
        this.commentPersons = in.createTypedArrayList(FriendCircleCommentModel.CREATOR);
    }

    public static final Parcelable.Creator<FriendCircleModel> CREATOR = new Parcelable.Creator<FriendCircleModel>() {
        @Override
        public FriendCircleModel createFromParcel(Parcel source) {
            return new FriendCircleModel(source);
        }

        @Override
        public FriendCircleModel[] newArray(int size) {
            return new FriendCircleModel[size];
        }
    };
}
