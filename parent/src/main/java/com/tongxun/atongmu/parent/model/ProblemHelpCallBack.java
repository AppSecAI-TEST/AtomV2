package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/26.
 */

public class ProblemHelpCallBack {

    /**
     * datas : [{"help":[{"name":"123","url":"https://www.atongmu.net:8443/backworkrest/common/showAppHelpContext?helpId=2f549c6ed75d41638b3dbf2e5bfa22d1"},{"name":"123321","url":"https://www.atongmu.net:8443/backworkrest/common/showAppHelpContext?helpId=36eafa98bad04325a68baf08e2c829fd"}],"title":"123"}]
     * status : success
     * message :
     */

    private String status;
    private String message;
    private List<ProblemHelpModel> datas;

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

    public List<ProblemHelpModel> getDatas() {
        return datas;
    }

    public void setDatas(List<ProblemHelpModel> datas) {
        this.datas = datas;
    }


}
