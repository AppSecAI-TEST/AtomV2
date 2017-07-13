package com.tongxun.atongmu.parent.ui.babysign;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/7/13.
 */

public class BabySignPresenter extends BasePresenter<IBabySignInContract.View> implements IBabySignInContract.Presenter {

    BabySignInteractor interactor;

    public BabySignPresenter() {
        interactor=new BabySignInteractor();
    }
}
