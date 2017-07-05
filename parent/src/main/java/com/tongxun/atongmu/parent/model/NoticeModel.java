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


    private String createDate;

    private String photoMin;
    private String title;
    private String context;

    private String noticePersonStatusId;

    private String noRead;
    private String shareHtmlPath;
    private String htmlPath;




    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPhotoMin() {
        return photoMin;
    }

    public void setPhotoMin(String photoMin) {
        this.photoMin = photoMin;
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


    public String getNoticePersonStatusId() {
        return noticePersonStatusId;
    }

    public void setNoticePersonStatusId(String noticePersonStatusId) {
        this.noticePersonStatusId = noticePersonStatusId;
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


}
