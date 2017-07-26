package com.tongxun.atongmu.parent.ui.my.growprofile;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/7/26.
 */

public class GrowProfilePresenter extends BasePresenter<IGrowProfileContract.View> implements IGrowProfileContract.Presenter, IGrowProfileContract.Interactor.onFinishListener {

    GrowProfileInteractor interactor;

    public GrowProfilePresenter() {
        interactor=new GrowProfileInteractor();
    }

    @Override
    public void getGrowProfileList() {
        interactor.getGrowProfileList(this);
    }
}
