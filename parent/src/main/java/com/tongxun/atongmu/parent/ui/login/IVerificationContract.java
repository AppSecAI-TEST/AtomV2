package com.tongxun.atongmu.parent.ui.login;

/**
 * Created by Anro on 2017/6/29.
 */

public interface IVerificationContract {
    interface View{
        String getVerCode();
        void LoginSuccess();
        void LoginError(String message);
        void sendSuccess();
        void sendError();

        void onBabyInfoSuccess();
    }
    interface Presenter{
        void checkVerCode(String code);
        void getWebVer(String phone);
        void getUserInfo();
    }

    interface Interactor{
        void checkVerCode(String code,onFinishLinstener linstener);
        void getWebVer(String phone,onFinishLinstener linstener);
        interface onFinishLinstener{
            void onCheckSuccess();
            void onCheckError(String message);
            void onSendSuccess();
            void onSendError();
        }
    }
}
