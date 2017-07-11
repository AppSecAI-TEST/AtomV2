package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/11.
 */

public class RecipesCallBack {


    /**
     * message :
     * status :
     * datas : [{"title":"value","content":"value","images":[]}]
     */

    private String message;
    private String status;
    private List<RecipesModel> datas;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RecipesModel> getDatas() {
        return datas;
    }

    public void setDatas(List<RecipesModel> datas) {
        this.datas = datas;
    }


}
