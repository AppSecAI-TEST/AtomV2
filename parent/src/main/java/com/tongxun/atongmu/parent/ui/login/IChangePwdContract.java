package com.tongxun.atongmu.parent.ui.login;

/**
 * Created by Anro on 2017/7/28.
 */

public interface IChangePwdContract {
    interface View{

        void onError(String message);

        void onSuccess();

    }

    interface Presenter{

        void setNewPwd(String pwd);
    }

    interface Interactor{

        void setNewPwd(String pwd,onFinishListener listener);

        interface onFinishListener{
            void onSuccess();
            void onError(String message);
        }
    }
}
