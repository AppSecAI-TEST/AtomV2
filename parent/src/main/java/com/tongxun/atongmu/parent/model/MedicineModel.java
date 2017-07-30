package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by admin on 2017/7/30.
 */

public class MedicineModel {
    /**
     * note_id : e3a9d2d1c3cc43e5b373bd339f936540
     * start_time : 2017-07-30
     * medicine_days : 1
     * notes : 测试提醒
     * image : [{"image_url":"https://www.atongmu.net:8443/backwork/userfiles/images/kigStudentMedical/2017/07/476c5a92_000d_4e22_8903_87ee336ccae2.jpg"},{"image_url":"https://www.atongmu.net:8443/backwork/userfiles/images/kigStudentMedical/2017/07/f934c07a_b5f2_4e23_aa47_d3089f65bfd5.jpg"}]
     */

    private String note_id;
    private String start_time;
    private String medicine_days;
    private String notes;
    private List<ImageBean> image;

    public String getNote_id() {
        return note_id;
    }

    public void setNote_id(String note_id) {
        this.note_id = note_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getMedicine_days() {
        return medicine_days;
    }

    public void setMedicine_days(String medicine_days) {
        this.medicine_days = medicine_days;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<ImageBean> getImage() {
        return image;
    }

    public void setImage(List<ImageBean> image) {
        this.image = image;
    }

    public static class ImageBean {
        /**
         * image_url : https://www.atongmu.net:8443/backwork/userfiles/images/kigStudentMedical/2017/07/476c5a92_000d_4e22_8903_87ee336ccae2.jpg
         */

        private String image_url;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}
