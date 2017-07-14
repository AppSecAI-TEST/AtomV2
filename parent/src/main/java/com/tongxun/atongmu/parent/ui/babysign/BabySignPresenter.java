package com.tongxun.atongmu.parent.ui.babysign;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/7/13.
 */

public class BabySignPresenter extends BasePresenter<IBabySignInContract.View> implements IBabySignInContract.Presenter, IBabySignInContract.Interactor.onFinishListener {

    BabySignInteractor interactor;

    public BabySignPresenter() {
        interactor=new BabySignInteractor();
    }

    @Override
    public void getSignInRecord(String format) {
        interactor.getSignInRecord(format,this);
    }

    @Override
    public void onSignInRecordSuccess() {
        if(mView!=null){
            mView.setRefreshSignInDate();
        }
    }

    @Override
    public void onError(String mesaage) {

    }
}
