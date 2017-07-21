package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/21.
 */

public class SchoolIntroductCallBack {


    /**
     * datas : [{"htmlSharePath":"http://127.0.0.1:10010/backwork/rest/kig/restKindergartenInfo?baseId=7d5eba4fc341488a906330390a9f526a","htmlPath":"http://127.0.0.1:10010/backwork/rest/kig/restKindergartenInfo?baseId=7d5eba4fc341488a906330390a9f526a","title":"校园信息"},{"htmlSharePath":"http://127.0.0.1:10010/backwork/rest/kig/restShowInfo?infoId=c274b51544c848db9f5f276bee389d19","htmlPath":"http://127.0.0.1:10010/backwork/rest/kig/restShowInfo?infoId=c274b51544c848db9f5f276bee389d19","title":"校园简介"},{"htmlSharePath":"http://127.0.0.1:10010/backwork/rest/kig/restShowInfo?infoId=c274b51544c848db9f5f276bee389d19","htmlPath":"http://127.0.0.1:10010/backwork/rest/kig/restShowInfo?infoId=c274b51544c848db9f5f276bee389d19","title":"园长寄语"},{"htmlSharePath":"http://127.0.0.1:10010/backwork/rest/kig/restShowInfo?infoId=c274b51544c848db9f5f276bee389d19","htmlPath":"http://127.0.0.1:10010/backwork/rest/kig/restShowInfo?infoId=c274b51544c848db9f5f276bee389d19","title":"校园实景"},{"htmlSharePath":"http://127.0.0.1:10010/backwork/rest/kig/restShowInfo?infoId=c274b51544c848db9f5f276bee389d19","htmlPath":"http://127.0.0.1:10010/backwork/rest/kig/restShowInfo?infoId=c274b51544c848db9f5f276bee389d19","title":"校园资质"}]
     * status : success
     * message :
     */

    private String status;
    private String message;
    private List<SchoolIntroductModel> datas;

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

    public List<SchoolIntroductModel> getDatas() {
        return datas;
    }

    public void setDatas(List<SchoolIntroductModel> datas) {
        this.datas = datas;
    }

}
