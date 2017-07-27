package com.tongxun.atongmu.parent.ui.my.growprofile;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.GrowProfileModel;

import java.util.List;

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

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(List<GrowProfileModel> datas) {
        if(mView!=null){
            mView.onSuccess(datas);
        }
    }
}
