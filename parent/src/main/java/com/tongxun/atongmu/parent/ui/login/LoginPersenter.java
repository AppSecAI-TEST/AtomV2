package com.tongxun.atongmu.parent.ui.login;

/**
 * Created by Anro on 2017/6/16.
 */

public class LoginPersenter implements LoginContract.Presenter, LoginContract.Interactor.OnLoginFinishedListener {

    private LoginContract.View mloginView;
    private LoginInteractor mLoginInteractor;


    public LoginPersenter(LoginContract.View loginView) {
        mloginView = loginView;
        mLoginInteractor=new LoginInteractor();
    }



    @Override
    public void Login(String username, String password) {
        if(mloginView!=null){
            mloginView.showProgress();
        }
        mLoginInteractor.Login(username,password,this);
    }

    @Override
    public void onSuccess() {
        if(mloginView!=null){
            mloginView.hideProgress();
            mloginView.loginSuccess();
        }
    }

    @Override
    public void onError(String message) {
        if(mloginView!=null){
            mloginView.hideProgress();
            mloginView.loginError(message);
        }
    }
}
