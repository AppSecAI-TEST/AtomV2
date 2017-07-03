package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/3.
 */

public class SignWaitCallBack {

    /**
     * datas : [{"agenId":"8beba92d23264af4b34f95b3f15ebec6","techerName":"Anny","agenTime":"2017-07-03 14:59:32","techerPhoto":"https://www.atongmu.net:8443/backwork/userfiles/images/kigTeacherPhoto/2017/02/cd08fc63_90ea_42fc_afb9_6b4117f0b7e0.jpg","actPhoto":"https://www.atongmu.net:8443/backwork/userfiles/images/kigStudentAgen/2017/07/509d5d95_2bd6_4b1b_87ce_aad09e3257bc.jpg","techerPhone":"18896973509","actStatus":"false","relation":"","actPersonPhoto":"","actTime":""}]
     * status : success
     */

    private String status;
    private List<SignWaitModel> datas;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SignWaitModel> getDatas() {
        return datas;
    }

    public void setDatas(List<SignWaitModel> datas) {
        this.datas = datas;
    }


}
