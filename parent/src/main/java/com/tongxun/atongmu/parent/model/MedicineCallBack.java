package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by admin on 2017/7/30.
 */

public class MedicineCallBack {


    /**
     * medicine : [{"note_id":"e3a9d2d1c3cc43e5b373bd339f936540","start_time":"2017-07-30","medicine_days":"1","notes":"测试提醒","image":[{"image_url":"https://www.atongmu.net:8443/backwork/userfiles/images/kigStudentMedical/2017/07/476c5a92_000d_4e22_8903_87ee336ccae2.jpg"},{"image_url":"https://www.atongmu.net:8443/backwork/userfiles/images/kigStudentMedical/2017/07/f934c07a_b5f2_4e23_aa47_d3089f65bfd5.jpg"}]}]
     * status : success
     * message :
     */

    private String status;
    private String message;
    private List<MedicineModel> medicine;

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

    public List<MedicineModel> getMedicine() {
        return medicine;
    }

    public void setMedicine(List<MedicineModel> medicine) {
        this.medicine = medicine;
    }


}
