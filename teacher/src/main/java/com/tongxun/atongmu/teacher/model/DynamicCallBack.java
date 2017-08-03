package com.tongxun.atongmu.teacher.model;

import java.util.List;

/**
 * Created by Anro on 2017/8/3.
 */

public class DynamicCallBack {
    /**
     * datas : [{"recType":"通知","noticeActivityId":"9466f712677c4da5983289a9c5f76a22","createDate":"2016-07-03","classcId":"33da9555d4234296925a3535b1c0dcb1","classcName":"中一班","teacherId":"","teacherName":"","photoMin":"http://192.168.0.30:10010/backwork/userfiles/1/files/kig/kigNotice/2016/07/timg%20(1).jpg","photoBody":"http://192.168.0.30:10010/backwork/userfiles/1/files/kig/kigNotice/2016/07/timg%20(1).jpg","title":"0703测试通知","context":"","htmlPath":"http://192.168.0.30:10010/backwork/rest/tea/restShowNotice?noticeId=9466f712677c4da5983289a9c5f76a22","createTime":1467515118000},{"recType":"活动","noticeActivityId":"5436b88b62434270b83fd04c05e9c3c2","createDate":"2016-06-25","classcId":"33da9555d4234296925a3535b1c0dcb1","classcName":"中一班","teacherId":"","teacherName":"","photoMin":"","photoBody":"","title":"互动的顶顶顶顶顶","context":"","htmlPath":"http://192.168.0.30:10010/backwork/rest/tea/restShowNotice?noticeId=5436b88b62434270b83fd04c05e9c3c2","createTime":1466849865000},{"recType":"活动","noticeActivityId":"976e3ecbdba04668bd6d70040792419b","createDate":"2016-06-25","classcId":"33da9555d4234296925a3535b1c0dcb1","classcName":"中一班","teacherId":"","teacherName":"","photoMin":"http://192.168.0.30:10010/backwork/userfiles/1/files/kig/kigActivity/2016/06/Hydrangeas.jpg","photoBody":"","title":"test","context":"","htmlPath":"http://192.168.0.30:10010/backwork/rest/tea/restShowNotice?noticeId=976e3ecbdba04668bd6d70040792419b","createTime":1466848939000}]
     * status : success
     * message :
     */

    private String status;
    private String message;
    /**
     * recType : 通知
     * noticeActivityId : 9466f712677c4da5983289a9c5f76a22
     * createDate : 2016-07-03
     * classcId : 33da9555d4234296925a3535b1c0dcb1
     * classcName : 中一班
     * teacherId :
     * teacherName :
     * photoMin : http://192.168.0.30:10010/backwork/userfiles/1/files/kig/kigNotice/2016/07/timg%20(1).jpg
     * photoBody : http://192.168.0.30:10010/backwork/userfiles/1/files/kig/kigNotice/2016/07/timg%20(1).jpg
     * title : 0703测试通知
     * context :
     * htmlPath : http://192.168.0.30:10010/backwork/rest/tea/restShowNotice?noticeId=9466f712677c4da5983289a9c5f76a22
     * createTime : 1467515118000
     */

    private List<DynamicModel> datas;

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

    public List<DynamicModel> getDatas() {
        return datas;
    }

    public void setDatas(List<DynamicModel> datas) {
        this.datas = datas;
    }

}
