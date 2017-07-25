package com.tongxun.atongmu.parent.ui.login;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/6/29.
 */

public class VerificationPresenter extends BasePresenter<IVerificationContract.View> implements IVerificationContract.Presenter, IVerificationContract.Interactor.onFinishLinstener, IBabyInfoContract.Interactor.onFinishListener {

    private VerificationInteractor interactor;

    private BabyInfoInteractor infoInteractor;

    public VerificationPresenter() {
        interactor=new VerificationInteractor();
        infoInteractor=new BabyInfoInteractor();
    }

    @Override
    public void checkVerCode(String code) {
        interactor.checkVerCode(code,this);
    }

    @Override
    public void getWebVer(String phone) {
        interactor.getWebVer(phone,this);
    }

    @Override
    public void getUserInfo() {
        infoInteractor.getBabyInfo(this);
    }

    @Override
    public void onCheckSuccess() {
        mView.LoginSuccess();
    }

    @Override
    public void onCheckError(String message) {
        mView.LoginError(message);
    }

    @Override
    public void onSendSuccess() {
        mView.sendSuccess();
    }

    @Override
    public void onSendError() {
        mView.sendError();
    }

    @Override
    public void onError(String string) {
        if(mView!=null){
            mView.LoginError(string);
        }
    }
    //获取宝宝信息成功
    @Override
    public void onSuccess() {
        if(mView!=null){
            mView.onBabyInfoSuccess();
        }
    }
}
