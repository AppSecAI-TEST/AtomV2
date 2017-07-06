package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/6.
 */

public class FriendCircleModel {

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



}
