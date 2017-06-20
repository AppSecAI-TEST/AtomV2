package com.tongxun.atongmu.parent.ui.login;

import java.util.List;

/**
 * Created by Anro on 2017/6/20.
 */

public class LoginCallBack {

    /**
     * status : success
     * datas : [{"tokenId":"1e2b6f1b-2ca1-4bbb-8da7-f09cac9ba0cf"}]
     * message :
     */

    private String status;
    private String message;
    private List<DatasBean> datas;

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * tokenId : 1e2b6f1b-2ca1-4bbb-8da7-f09cac9ba0cf
         */

        private String tokenId;

        public String getTokenId() {
            return tokenId;
        }

        public void setTokenId(String tokenId) {
            this.tokenId = tokenId;
        }
    }
}
