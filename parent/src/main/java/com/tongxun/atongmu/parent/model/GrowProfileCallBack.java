package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/27.
 */

public class GrowProfileCallBack {

    /**
     * datas : [{"title":"朱若尹2016下半年学期报告","name":"朱若尹","sex":"女","age":"4岁(46个月)","date":"2017年03月01日","imgpath":"","growthurl":"https://www.atongmu.net:8443/backwork/rest/kig/showTermPaper?termPaperId=b02091df522642139915759399d5cc94","growthshareurl":"https://www.atongmu.net:8443/backwork/rest/kig/showShareTermPaper?termPaperId=b02091df522642139915759399d5cc94"}]
     * status : success
     * message :
     */

    private String status;
    private String message;
    /**
     * title : 朱若尹2016下半年学期报告
     * name : 朱若尹
     * sex : 女
     * age : 4岁(46个月)
     * date : 2017年03月01日
     * imgpath :
     * growthurl : https://www.atongmu.net:8443/backwork/rest/kig/showTermPaper?termPaperId=b02091df522642139915759399d5cc94
     * growthshareurl : https://www.atongmu.net:8443/backwork/rest/kig/showShareTermPaper?termPaperId=b02091df522642139915759399d5cc94
     */

    private List<GrowProfileModel> datas;

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

    public List<GrowProfileModel> getDatas() {
        return datas;
    }

    public void setDatas(List<GrowProfileModel> datas) {
        this.datas = datas;
    }


}
