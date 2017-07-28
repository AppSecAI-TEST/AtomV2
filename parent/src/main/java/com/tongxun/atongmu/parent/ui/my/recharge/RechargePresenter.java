package com.tongxun.atongmu.parent.ui.my.recharge;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.RechargeItemModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/28.
 */

public class RechargePresenter extends BasePresenter<IRechargeContract.View> implements IRechargeContract.Presenter, IRechargeContract.Interactor.onFinishListener {
    RechargeInteractor interactor;

    public RechargePresenter() {
        interactor=new RechargeInteractor();
    }

    @Override
    public void getWebPayItemList() {
        interactor.getWebPayItemList(this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onChargeItemSuccess(String haveExtrapackg, String joyPath, String functionPath, String funLastTime, String packgLastTime, List<RechargeItemModel> data) {
        if(mView!=null){
            mView.onChargeItemSuccess(haveExtrapackg,joyPath,functionPath,funLastTime,packgLastTime,data);
        }
    }
}
