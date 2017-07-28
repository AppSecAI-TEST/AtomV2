package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/28.
 */

public class RechargeItemCallBack {
    private String status;
    private String message;
    private String haveExtrapackg;
    private String packgLastTime;
    private String funLastTime;
    private String joyPath;
    private String functionPath;
    /**
     * packgId :
     * packgTitle :
     * packgPrice :
     * packgRemark :
     * packgType : 0
     */

    private List<RechargeItemModel> data;

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

    public String getHaveExtrapackg() {
        return haveExtrapackg;
    }

    public void setHaveExtrapackg(String haveExtrapackg) {
        this.haveExtrapackg = haveExtrapackg;
    }

    public String getPackgLastTime() {
        return packgLastTime;
    }

    public void setPackgLastTime(String packgLastTime) {
        this.packgLastTime = packgLastTime;
    }

    public String getFunLastTime() {
        return funLastTime;
    }

    public void setFunLastTime(String funLastTime) {
        this.funLastTime = funLastTime;
    }

    public String getJoyPath() {
        return joyPath;
    }

    public void setJoyPath(String joyPath) {
        this.joyPath = joyPath;
    }

    public String getFunctionPath() {
        return functionPath;
    }

    public void setFunctionPath(String functionPath) {
        this.functionPath = functionPath;
    }

    public List<RechargeItemModel> getData() {
        return data;
    }

    public void setData(List<RechargeItemModel> data) {
        this.data = data;
    }
}
