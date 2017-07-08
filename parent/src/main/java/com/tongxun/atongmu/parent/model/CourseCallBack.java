package com.tongxun.atongmu.parent.model;

/**
 * Created by Anro on 2017/7/4.
 */

public class CourseCallBack {


    /**
     * status : success
     * message :
     * datas : {"nextWeek":[{"date":"07-10","week":"星期一","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]},{"date":"07-11","week":"星期二","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]},{"date":"07-12","week":"星期三","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]},{"date":"07-13","week":"星期四","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]},{"date":"07-14","week":"星期五","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]},{"date":"07-15","week":"星期六","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]},{"date":"07-16","week":"星期天","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]}],"thisWeek":[{"date":"07-03","week":"星期一","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]},{"date":"07-04","week":"星期二","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]},{"date":"07-05","week":"星期三","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]},{"date":"07-06","week":"星期四","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]},{"date":"07-07","week":"星期五","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]},{"date":"07-08","week":"星期六","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"true","aft":[]},{"date":"07-09","week":"星期天","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]}],"lastWeek":[{"date":"06-26","week":"星期一","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]},{"date":"06-27","week":"星期二","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]},{"date":"06-28","week":"星期三","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]},{"date":"06-29","week":"星期四","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]},{"date":"06-30","week":"星期五","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]},{"date":"07-01","week":"星期六","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]},{"date":"07-02","week":"星期天","mon":[{"time":"10:30","content":""},{"time":"15:00","content":""},{"time":"16:00","content":""}],"nowDay":"false","aft":[]}]}
     */

    private String status;
    private String message;
    private CourseListModel datas;

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

    public CourseListModel getDatas() {
        return datas;
    }

    public void setDatas(CourseListModel datas) {
        this.datas = datas;
    }


}
