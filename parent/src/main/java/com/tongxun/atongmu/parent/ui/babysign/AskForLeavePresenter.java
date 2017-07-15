package com.tongxun.atongmu.parent.ui.babysign;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/7/15.
 */

public class AskForLeavePresenter extends BasePresenter<IAskForLeaveContract.View> implements IAskForLeaveContract.Presenter, IAskForLeaveContract.Interactor.onFinishListener {

    AskForLeaveInteractor interactor;

    public AskForLeavePresenter() {
        interactor=new AskForLeaveInteractor();
    }

    @Override
    public void postSubmitLeave(String startDate, String endDate, String type, String remarks) {
        interactor.postSubmitLeave(startDate,endDate,type,remarks,this);
    }


    @Override
    public void onSuccess() {
        if(mView!=null){
            mView.onSuccess();
        }
    }

    @Override
    public void onError(String string) {
        if(mView!=null){
            mView.onError(string);
        }
    }
}
