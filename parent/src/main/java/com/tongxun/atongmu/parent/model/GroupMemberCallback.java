package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/12.
 */

public class GroupMemberCallback {

    /**
     * parentdatas : [{"name":"阿童目","avatar":"http://127.0.0.1:10010/backwork/static/images/1.jpg","level":"LV0","relationship":"爸爸","phone":"136666666666"}]
     * teacherdatas : [{"name":"阿童目老师","avatar":"http://127.0.0.1:10010/backwork/static/images/2.jpg","level":"LV0","relationship":"园长","phone":"136666666666"}]
     * status : success
     * message :
     */

    private String status;
    private String message;
    private List<GroupMemberModel> parentdatas;
    private List<GroupMemberModel> teacherdatas;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<GroupMemberModel> getParentdatas() {
        return parentdatas;
    }

    public void setParentdatas(List<GroupMemberModel> parentdatas) {
        this.parentdatas = parentdatas;
    }

    public List<GroupMemberModel> getTeacherdatas() {
        return teacherdatas;
    }

    public void setTeacherdatas(List<GroupMemberModel> teacherdatas) {
        this.teacherdatas = teacherdatas;
    }



}
