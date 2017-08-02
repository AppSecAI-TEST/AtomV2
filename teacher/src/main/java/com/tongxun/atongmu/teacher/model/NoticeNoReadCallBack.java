package com.tongxun.atongmu.teacher.model;

import java.util.List;

/**
 * Created by Anro on 2017/8/2.
 */

public class NoticeNoReadCallBack {


    /**
     * noticeId : 654405cc8bd1485e8ddf258239e2ef22
     * noReadStudents : [{"noReadNum":"3人未阅读","studentList":[{"personName":"李涣曦","studentId":"14315d9172af40a8a675b07c548a4ad2","photo1":""},{"personName":"吴陈萱","studentId":"4485e41807c345cbaf226a9acaef1f36","photo1":""},{"personName":"朱梓轩","studentId":"41e0487e7e1d4a8bab86b1d49915e57f","photo1":"http://127.0.0.1:10010/backwork/userfiles/images/kigStudent/2016/09/a079aa02_e828_48d6_bf86_867d16d735dd.jpg"}],"className":"小三班"}]
     * status : success
     * message :
     */

    private String noticeId;
    private String status;
    private String message;
    private List<NoReadModel> noReadStudents;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
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

    public List<NoReadModel> getNoReadStudents() {
        return noReadStudents;
    }

    public void setNoReadStudents(List<NoReadModel> noReadStudents) {
        this.noReadStudents = noReadStudents;
    }


}
