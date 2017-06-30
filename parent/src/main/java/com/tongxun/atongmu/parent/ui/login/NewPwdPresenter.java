package com.tongxun.atongmu.parent.ui.login;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/6/30.
 */

public class NewPwdPresenter extends BasePresenter<INewPwdContract.View> implements INewPwdContract.Presenter, INewPwdContract.Interactor.onFinishListener {

    private NewPwdInteractor interactor;

    public NewPwdPresenter() {
        interactor=new NewPwdInteractor();
    }

    @Override
    public void setNewPwd(String phone, String password) {
        mView.showProgress();
        interactor.setNewPwd(phone,password,this);
    }

    @Override
    public void onSuccess() {
        mView.hideProgress();
        mView.onResetPwdSuccess();
    }

    @Override
    public void onError(String message) {
        mView.hideProgress();
        mView.onResetPwdError(message);
    }
}
