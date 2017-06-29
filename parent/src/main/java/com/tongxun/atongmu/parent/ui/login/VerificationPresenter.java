package com.tongxun.atongmu.parent.ui.login;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/6/29.
 */

public class VerificationPresenter extends BasePresenter<IVerificationContract.View> implements IVerificationContract.Presenter, IVerificationContract.Interactor.onFinishLinstener {

    private VerificationInteractor interactor;

    public VerificationPresenter() {
        interactor=new VerificationInteractor();
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
}
