package com.tongxun.atongmu.parent.ui.homework;

import com.tongxun.atongmu.parent.model.CourseListModel;
import com.tongxun.atongmu.parent.model.CourseModel;
import com.tongxun.atongmu.parent.model.WeekBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anro on 2017/7/4.
 */

public class CourseInteractor implements ICourseContract.Interactor {


    @Override
    public void getCourseInfo(onFinishListener listener) {
        CourseListModel listModel=new CourseListModel();
        CourseModel courseModel=new CourseModel();
        CourseModel courseModel2=new CourseModel();
        WeekBean bean=new WeekBean();
        WeekBean bean2=new WeekBean();
        WeekBean bean3=new WeekBean();
        bean.setTime("8:00");
        bean.setContent("数学");
        bean2.setTime("9:00");
        bean3.setTime("10:00");
        bean2.setContent("语文");
        bean3.setContent("英语");
        List<WeekBean> weekBeanmonList=new ArrayList<>();
        List<WeekBean> weekBeanmonList2=new ArrayList<>();
        List<WeekBean> weekBeanaftList=new ArrayList<>();
        List<WeekBean> weekBeanaftList2=new ArrayList<>();
        courseModel2.setWeek("周二");
        courseModel2.setDate("07-05");
        courseModel2.setNowDay("true");
        courseModel2.setMon(weekBeanmonList2);
        courseModel2.setAft(weekBeanaftList2);

        weekBeanmonList.add(bean);
        weekBeanmonList.add(bean2);
        weekBeanmonList.add(bean3);
        weekBeanaftList.add(bean);
        courseModel.setDate("07-04");
        courseModel.setWeek("周二");
        courseModel.setNowDay("false");
        courseModel.setMon(weekBeanmonList);
        courseModel.setAft(weekBeanaftList);

        List<CourseModel> courseModelList=new ArrayList<>();
        List<CourseModel> courseModelList2=new ArrayList<>();
        courseModelList2.add(courseModel);
        courseModelList.add(courseModel);
        courseModelList.add(courseModel2);
        listModel.setThisWeek(courseModelList);
        listModel.setLastWeek(courseModelList2);
        listener.onSuccess(listModel);
    }
}
