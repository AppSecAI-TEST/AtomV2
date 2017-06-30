package com.tongxun.atongmu.parent.ui.login;

/**
 * Created by Anro on 2017/6/30.
 */

public interface INewPwdContract {
    interface View{
        String getNewPwd();
        String getConfirmPwd();
        void showProgress();
        void hideProgress();
        void onResetPwdSuccess();
        void onResetPwdError(String message);

    }
    interface Presenter{
        void setNewPwd(String phone,String password);
    }

    interface Interactor{
        void setNewPwd(String phone,String password,onFinishListener listener);

        interface onFinishListener{
            void onSuccess();
            void onError(String message);
        }
    }
}
