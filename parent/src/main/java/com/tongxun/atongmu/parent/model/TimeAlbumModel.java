package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/12.
 */

public class TimeAlbumModel {
    /**
     * month : 晨检照片
     * monthNum : 28张
     * photos : ["http://127.0.0.1:10010/backwork/userfiles/images/kigGrown/2017/06/5c428243_f3d4_4024_bd58_faa572a216fd.jpg","http://127.0.0.1:10010/backwork/userfiles/images/kigGrown/2017/06/1cb854b5_7ead_49fa_895e_32d6ced7ce01.jpg","http://127.0.0.1:10010/backwork/userfiles/images/kigGrown/2017/06/e89dbaf7_dd69_46cd_80ee_1755e9fdd2ba.jpg","http://127.0.0.1:10010/backwork/userfiles/images/kigGrown/2017/06/8a664fa8_7151_491e_85e6_7cf22f9823cc.jpg"]
     */

    private String month;
    private String monthNum;
    private List<String> photos;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonthNum() {
        return monthNum;
    }

    public void setMonthNum(String monthNum) {
        this.monthNum = monthNum;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
