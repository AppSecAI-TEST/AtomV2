package com.tongxun.atongmu.teacher.ui.personsign;

import com.tongxun.atongmu.teacher.BasePresenter;
import com.tongxun.atongmu.teacher.model.PersonSignModel;

import java.util.List;

/**
 * Created by Anro on 2017/8/4.
 */

public class PersonSignPresenter extends BasePresenter<IPersonContract.View> implements IPersonContract.Presenter, IPersonContract.Interactor.onFinishListener {

    private PersonSignInteractor interactor;

    public PersonSignPresenter() {
        interactor=new PersonSignInteractor();
    }

    @Override
    public void getPersonSign(String selectDate) {
        interactor.getPersonSign(selectDate,this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onPersonSignSuccess(String identity, String total, String car, String arrived, String unArrived, String leave, String arrivedPre, String unArrivedPre, String leavePre, List<PersonSignModel> datas) {
        if(mView!=null){
            mView.onPersonSignSuccess(identity,total,car,arrived,leave,unArrived,arrivedPre,unArrivedPre,leavePre,datas);
        }
    }
}
