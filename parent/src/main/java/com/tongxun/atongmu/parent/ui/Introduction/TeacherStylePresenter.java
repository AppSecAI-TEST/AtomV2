package com.tongxun.atongmu.parent.ui.Introduction;

import com.tongxun.atongmu.parent.model.SchoolIntroductModel;
import com.tongxun.atongmu.parent.model.TeacherStyleModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/14.
 */

public class TeacherStylePresenter implements ITeacherStyleContract.Presenter, ITeacherStyleContract.Interactor.onFinishListener {

    private ITeacherStyleContract.View mView;

    private SchoolIntroductionInteractor interactor;

    public TeacherStylePresenter(ITeacherStyleContract.View view) {
        mView=view;
        mView.setPresenter(this);
        interactor=new SchoolIntroductionInteractor();
    }

    @Override
    public void getTeacherStyleList() {
        interactor.getTeacherStyleList(this);
    }


    @Override
    public void onError(String message) {
        if (mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(List<TeacherStyleModel> data) {
        if(mView!=null){
            mView.onRefreshAdapter(data);
        }
    }

    public void detachView(){
        if(mView!=null){
            mView=null;
        }
    }

    @Override
    public void onIntroductSuccess(List<SchoolIntroductModel> datas) {

    }
}
