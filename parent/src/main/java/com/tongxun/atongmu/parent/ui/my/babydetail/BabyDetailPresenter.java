package com.tongxun.atongmu.parent.ui.my.babydetail;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/7/28.
 */

public class BabyDetailPresenter extends BasePresenter<IBabyDetailContract.View> implements IBabyDetailContract.Presenter, IBabyDetailContract.Interactor.onFinishListener {

    BabyDetailInteractor interactor;
    public BabyDetailPresenter() {
        interactor=new BabyDetailInteractor();
    }

    @Override
    public void setBabyFace(String path) {
        interactor.setBabyFace(path,this);
    }

    @Override
    public void postBirthDay(String pickDate) {
        interactor.postBirthDay(pickDate,this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(String photoUrl) {
        if(mView!=null){
            mView.onSuccess(photoUrl);
        }
    }

    @Override
    public void onBirthSuccess() {
        if(mView!=null){
            mView.onBirthSuccess();
        }
    }
}
