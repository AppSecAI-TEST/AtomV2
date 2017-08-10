package com.tongxun.atongmu.parent.model;

/**
 * Created by Anro on 2017/7/6.
 */

public class FriendCircleCommentModel {



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
}
