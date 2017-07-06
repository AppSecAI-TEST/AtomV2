package com.tongxun.atongmu.parent.model;

/**
 * Created by Anro on 2017/7/6.
 */

public class FriendCircleCommentModel {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    private String commentPersonId;
    private String commentId;
    private Boolean commentCurrentPerson;
    private String commentPersonName;
    private String commentText;
    private String commentSourcePersonId;
    private String commentSourcePersonName;
    private String commentType;
}
