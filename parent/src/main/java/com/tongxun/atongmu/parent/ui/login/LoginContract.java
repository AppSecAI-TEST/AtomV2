package com.tongxun.atongmu.parent.ui.login;

import com.tongxun.atongmu.parent.BaseInteractor;
import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.BaseView;

/**
 * Created by Anro on 2017/6/20.
 */

public interface LoginContract {
    interface View extends BaseView {
        void showProgress();
        void hideProgress();
        String getUserName();
        String getPassword();
        void loginSuccess();
        void loginError(String message);
    }

    interface Presenter extends BasePresenter{
        void Login(String username,String password);
    }

    interface Interactor extends BaseInteractor{
        interface  OnLoginFinishedListener{
            void onSuccess();
            void onError(String message);
        }
        void Login(String username,String password,OnLoginFinishedListener listener);
    }

}
