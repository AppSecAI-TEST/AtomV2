package com.tongxun.atongmu.teacher.ui.personsign;

import com.tongxun.atongmu.teacher.BasePresenter;
import com.tongxun.atongmu.teacher.model.TeacherSignModel;

import java.util.List;

/**
 * Created by Anro on 2017/8/8.
 */

public class TeacherSignPresenter extends BasePresenter<ITeacherSignContract.View> implements ITeacherSignContract.Presenter, ITeacherSignContract.Interactor.onFinishListener {

    private TeacherSignInteractor interactor;

    public TeacherSignPresenter() {
        interactor=new TeacherSignInteractor();
    }

    @Override
    public void getTeacherSign(String classcId, String selectDate) {
        interactor.getTeacherSign(classcId,selectDate,this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(List<TeacherSignModel> arrived, List<TeacherSignModel> unArrived) {
        if(mView!=null){
            mView.onSuccess(arrived,unArrived);
        }
    }
}
