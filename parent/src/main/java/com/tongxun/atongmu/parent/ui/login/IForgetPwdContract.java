package com.tongxun.atongmu.parent.ui.login;

/**
 * Created by Anro on 2017/6/26.
 */

public interface IForgetPwdContract {
    interface view {
        String getRegisterPhone();
        void LoginSuccess();
        void LoginError(String message);
        void sendSuccess();
        void sendError(String message);
    }

    interface presenter{
        void checkVerCode(String code);
        void getWebVer(String phone);
    }

    interface interactor{
        void checkVerCode(String code,onFinishLinstener linstener);
        void getWebVer(String phone,onFinishLinstener linstener);

        interface onFinishLinstener{
            void onCheckSuccess();
            void onCheckError(String message);
            void onSendSuccess();
            void onSendError(String message);
        }
    }
}
