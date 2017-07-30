package com.tongxun.atongmu.parent.ui.my.recharge;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.WxPayModel;

/**
 * Created by admin on 2017/7/30.
 */

public class PayOrderPresenter extends BasePresenter<IPayOrderContract.View> implements IPayOrderContract.Presenter, IPayOrderContract.Interactor.onFinishListener
{

    PayOrderInteractor interactor;

    public PayOrderPresenter() {
        interactor=new PayOrderInteractor();
    }

    @Override
    public void postWebPayMoney(String type, String packgId) {
        interactor.postWebPayMoney(type,packgId,this);
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
