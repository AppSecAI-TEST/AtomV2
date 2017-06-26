package com.tongxun.atongmu.parent.ui.login;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/6/16.
 */

public class LoginPresenter extends BasePresenter<ILoginContract.View> implements ILoginContract.Presenter, ILoginContract.Interactor.OnLoginFinishedListener {


    private LoginInteractor mLoginInteractor;

    public LoginPresenter() {
        mLoginInteractor=new LoginInteractor();
    }

    @Override
    public void Login(String username, String password) {
        if(mView!=null){
            mView.showProgress();
        }
        mLoginInteractor.Login(username,password,this);
    }

    @Override
    public void onSuccess() {
        if(mView!=null){
            mView.hideProgress();
            mView.loginSuccess();
        }
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.hideProgress();
            mView.loginError(message);
        }
    }
}
