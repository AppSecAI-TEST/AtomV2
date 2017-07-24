package com.tongxun.atongmu.parent.model;

/**
 * Created by Anro on 2017/7/24.
 */

public class ModuleModel {
    /**
     * havenNewRecord : false
     * enId : 1
     * enName : 活动通知
     * useFlag : true
     */

    private boolean havenNewRecord;
    private String enId;
    private String enName;
    private boolean useFlag;

    public boolean isHavenNewRecord() {
        return havenNewRecord;
    }

    public void setHavenNewRecord(boolean havenNewRecord) {
        this.havenNewRecord = havenNewRecord;
    }

    public String getEnId() {
        return enId;
    }

    public void setEnId(String enId) {
        this.enId = enId;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public boolean isUseFlag() {
        return useFlag;
    }

    public void setUseFlag(boolean useFlag) {
        this.useFlag = useFlag;
    }
}
