package com.tongxun.atongmu.parent.ui.login;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/7/25.
 */

public class BabyInfoPresenter extends BasePresenter<IBabyInfoContract.View> implements IBabyInfoContract.Presenter, IBabyInfoContract.Interactor.onFinishListener {

    BabyInfoInteractor infoInteractor;

    public BabyInfoPresenter() {
        infoInteractor=new BabyInfoInteractor();
    }

    @Override
    public void getBabyInfo() {
        infoInteractor.getBabyInfo(this);
    }

    @Override
    public void onError(String string) {

    }

    @Override
    public void onSuccess() {

    }


}
