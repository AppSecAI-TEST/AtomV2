package com.tongxun.atongmu.parent.ui.login;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/6/26.
 */

public class ForgetPwdPresenter extends BasePresenter<IForgetPwdContract.view> implements IForgetPwdContract.presenter, IForgetPwdContract.interactor.onFinishLinstener {

    ForgetPwdInteractor interactor;

    public ForgetPwdPresenter() {
        interactor=new ForgetPwdInteractor();
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
    public void onSendError(String message) {
        mView.sendError(message);
    }
}
