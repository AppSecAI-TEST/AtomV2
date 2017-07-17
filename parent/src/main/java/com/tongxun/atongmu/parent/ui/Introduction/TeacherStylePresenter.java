package com.tongxun.atongmu.parent.ui.Introduction;

import com.tongxun.atongmu.parent.model.TeacherStyleModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/14.
 */

public class TeacherStylePresenter implements ISchoolIntroductionContract.Presenter, ISchoolIntroductionContract.Interactor.onFinishListener {

    private ISchoolIntroductionContract.View mView;

    private SchoolIntroductionInteractor interactor;

    public TeacherStylePresenter(ISchoolIntroductionContract.View view) {
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
}
