package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/6.
 */

public class FriendCircleCallBack {



    private String currentNickName;
    private String status;
    private String message;
    private List<FriendCircleModel> datas;

    public String getCurrentNickName() {
        return currentNickName;
    }

    public void setCurrentNickName(String currentNickName) {
        this.currentNickName = currentNickName;
    }

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

    public List<FriendCircleModel> getDatas() {
        return datas;
    }

    public void setDatas(List<FriendCircleModel> datas) {
        this.datas = datas;
    }


}
