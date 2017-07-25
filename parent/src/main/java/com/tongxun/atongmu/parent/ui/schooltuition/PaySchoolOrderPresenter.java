package com.tongxun.atongmu.parent.ui.schooltuition;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.WxPayModel;

/**
 * Created by Anro on 2017/7/18.
 */

public class PaySchoolOrderPresenter extends BasePresenter<IPaySchoolOrderContract.View> implements IPaySchoolOrderContract.Presenter, IPaySchoolOrderContract.Interactor.onFinishListener {

    PaySchoolOrderInteractor interactor;
    public PaySchoolOrderPresenter() {
        interactor=new PaySchoolOrderInteractor();
    }

    @Override
    public void createOrder(String packgId, String type) {
        interactor.createOrder(packgId,type,this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(String orderString) {
        if(mView!=null){
            mView.onSuccess(orderString);
        }
    }

    @Override
    public void onWxSuccess(WxPayModel data) {
        if(mView!=null){
            mView.onWxSuccess(data);
        }
    }
}
