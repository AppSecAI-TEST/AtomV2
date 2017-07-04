package com.tongxun.atongmu.parent.model;

/**
 * Created by Anro on 2017/7/4.
 */

public class CourseCallBack {

    /**
     * message : value
     * status : value
     * datas : {"thisWeek":[{"mon":[{"time":"","content":""},{"time":"","content":""}],"aft":[]},{"mon":[{"time":"","content":""},{"time":"","content":""}],"aft":[{"time":"","content":""},{"time":"","content":""}]}],"lastWeek":[{"mon":[{"time":"","content":""},{"time":"","content":""}],"aft":[]},{"mon":[{"time":"","content":""},{"time":"","content":""}],"aft":[{"time":"","content":""},{"time":"","content":""}]}],"nextWeek":[{"mon":[{"time":"","content":""},{"time":"","content":""}],"aft":[]},{"mon":[{"time":"","content":""},{"time":"","content":""}],"aft":[{"time":"","content":""},{"time":"","content":""}]}]}
     */

    private String message;
    private String status;
    private CourseListModel datas;

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

    public CourseListModel getDatas() {
        return datas;
    }

    public void setDatas(CourseListModel datas) {
        this.datas = datas;
    }

}
