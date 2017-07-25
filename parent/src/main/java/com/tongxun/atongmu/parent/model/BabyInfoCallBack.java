package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/25.
 */

public class BabyInfoCallBack {

    /**
     * datas : [{"tokenId":"687cda4f-dd01-4d9d-aa47-ab622d6007c2","id":"a6979ee3478b41c6ba504b75e7ee969e","personCode":"STU0000044698","personName":"张路","sex":"男","birthDate":"2013-03-06","deviceCarnumber":"","status":"1","personIdcode":"","address":"","photo1":"https://www.atongmu.net:8443/backwork/userfiles/images/kigStudent/2017/04/6350ba55_ec90_4962_af4a_0071d5afcd03.jpg","photo2":"","kindgId":"7d5eba4fc341488a906330390a9f526a","kindgName":"江苏童讯幼儿园","inDate":"","xuexin":"","xingzuo":"","interest":"","relation":"爸爸","classDesc":"中二班"},{"tokenId":"ea837126-69ba-44f1-ba7b-a2fe0941225b","id":"3f7f715135bc496fa21b5fc5ff374e28","personCode":"STU0000000004","personName":"TT4","sex":"男","birthDate":"2015-12-09","deviceCarnumber":"","status":"1","personIdcode":"","address":"江苏省苏州市","photo1":"https://www.atongmu.net:8443/backwork/userfiles/images/kigStudent/2016/11/99fd7e4c_d392_4755_8128_ca859a19f924.jpg","photo2":"","kindgId":"9fc53ae121ea483e935a4b0a19c0bcb5","kindgName":"A苏州市第一幼儿园","inDate":"2015-07-23","xuexin":"O","xingzuo":"","interest":"写字","relation":"爸爸","classDesc":"中一班"}]
     * status : success
     * message :
     */

    private String status;
    private String message;
    private List<BabyInfoModel> datas;

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

    public List<BabyInfoModel> getDatas() {
        return datas;
    }

    public void setDatas(List<BabyInfoModel> datas) {
        this.datas = datas;
    }


}
