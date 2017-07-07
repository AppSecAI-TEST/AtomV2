package com.tongxun.atongmu.parent.ui.homework;

import com.tongxun.atongmu.parent.model.CourseListModel;

/**
 * Created by Anro on 2017/7/4.
 */

public class CoursePresenter implements ICourseContract.Presenter, ICourseContract.Interactor.onFinishListener {

    ICourseContract.View mView;
    CourseInteractor interactor;


    public CoursePresenter(ICourseContract.View view) {
        mView=view;
        mView.setPresenter(this);
        interactor=new CourseInteractor();
    }

    public void detachView(){
        if(mView!=null){
            mView=null;
        }
    }

    @Override
    public void getCourseInfo() {
        interactor.getCourseInfo(this);
    }

    @Override
    public void onSuccess(CourseListModel listModel) {
        if(mView!=null){
            mView.setData(listModel);
        }
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }
}
