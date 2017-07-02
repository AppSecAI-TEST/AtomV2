package com.tongxun.atongmu.parent.model;

import org.litepal.crud.DataSupport;

/**
 * Created by Anro on 2017/6/30.
 */

public class NoticeModel extends DataSupport {

    /**
     * id : 21e31a66b7d24a97b7166475463573d9
     * isNewRecord : true
     * createDate : 2017-06-23 19:29:16
     * updateDate : 2017-06-23 19:29:16
     * classcId : 656f579c241e45ac8d597a16400a21b8
     * photoMin :
     * photoBody :
     * title : 紧急通知
     * context :
     * checked : 1
     * noticeType :
     * teacherId : 1a316ada4f6346f98377738a39e8d6e9
     * noticePersonStatusId : 1aefc6031ef1432385e284cc0ccfef37
     * personCode : STU0000020393
     * noRead : true
     * shareHtmlPath : https://www.atongmu.net:8443/backwork/rest/kig/restShareNotice_v2?noticeId=21e31a66b7d24a97b7166475463573d9
     * htmlPath : https://www.atongmu.net:8443/backwork/rest/kig/restShowNotice_v2?noticeId=21e31a66b7d24a97b7166475463573d9
     * remarks :
     */

    private boolean isNewRecord;
    private String createDate;
    private String updateDate;
    private String classcId;
    private String photoMin;
    private String photoBody;
    private String title;
    private String context;
    private String checked;
    private String noticeType;
    private String teacherId;
    private String noticePersonStatusId;
    private String personCode;
    private String noRead;
    private String shareHtmlPath;
    private String htmlPath;
    private String remarks;

    public boolean isIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getClasscId() {
        return classcId;
    }

    public void setClasscId(String classcId) {
        this.classcId = classcId;
    }

    public String getPhotoMin() {
        return photoMin;
    }

    public void setPhotoMin(String photoMin) {
        this.photoMin = photoMin;
    }

    public String getPhotoBody() {
        return photoBody;
    }

    public void setPhotoBody(String photoBody) {
        this.photoBody = photoBody;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getNoticePersonStatusId() {
        return noticePersonStatusId;
    }

    public void setNoticePersonStatusId(String noticePersonStatusId) {
        this.noticePersonStatusId = noticePersonStatusId;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getNoRead() {
        return noRead;
    }

    public void setNoRead(String noRead) {
        this.noRead = noRead;
    }

    public String getShareHtmlPath() {
        return shareHtmlPath;
    }

    public void setShareHtmlPath(String shareHtmlPath) {
        this.shareHtmlPath = shareHtmlPath;
    }

    public String getHtmlPath() {
        return htmlPath;
    }

    public void setHtmlPath(String htmlPath) {
        this.htmlPath = htmlPath;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
