package com.tongxun.atongmu.parent.ui.babysign;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.BabySignInModel;

import java.util.List;

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
    public void onSignInRecordSuccess(List<BabySignInModel> datas, String signNum) {
        if(mView!=null){
            mView.setRefreshSignInDate(datas,signNum);
        }
    }

    @Override
    public void onError(String mesaage) {
        if(mView!=null){
            mView.onError(mesaage);
        }
    }
}
