package com.tongxun.atongmu.parent.ui.healthygrowth;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/7/21.
 */

public class HealthyGrowthPresenter extends BasePresenter<IHealthyGrowthContract.View> implements IHealthyGrowthContract.Presenter, IHealthyGrowthContract.Interactor.onFinishListener {

    private HealthyGrowthInteractor interactor=null;

    public HealthyGrowthPresenter() {
        interactor=new HealthyGrowthInteractor();
    }

    @Override
    public void getHealthyGrowth(String date) {
        interactor.getHealthGrowth(date,this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(String grownUrl) {
        if(mView!=null){
            mView.onSuccess(grownUrl);
        }
    }
}
