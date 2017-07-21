package com.tongxun.atongmu.parent.ui.Introduction;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.SchoolIntroductModel;
import com.tongxun.atongmu.parent.model.TeacherStyleModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/14.
 */

public class SchoolIntroductionPresenter extends BasePresenter<ISchoolIntroductionContract.View> implements ISchoolIntroductionContract.Presenter, ITeacherStyleContract.Interactor.onFinishListener {

    private SchoolIntroductionInteractor interactor;

    public SchoolIntroductionPresenter() {
        interactor=new SchoolIntroductionInteractor();
    }

    @Override
    public void getSchoolIntroduct() {
        interactor.getSchoolIntroduct(this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(List<TeacherStyleModel> data) {

    }

    /**
     * 获取校园简介成功
     * @param datas
     */
    @Override
    public void onIntroductSuccess(List<SchoolIntroductModel> datas) {
        if(mView!=null){
            mView.onSuccessDate(datas);
        }
    }
}
