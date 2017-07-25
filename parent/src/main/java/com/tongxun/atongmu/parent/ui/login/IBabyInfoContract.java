package com.tongxun.atongmu.parent.ui.login;

/**
 * Created by Anro on 2017/7/25.
 */

public interface IBabyInfoContract {

    interface View{

    }

    interface Presenter{
        void getBabyInfo();
    }

    interface Interactor{
        void getBabyInfo(onFinishListener listener);
        interface onFinishListener{

            void onError(String string);

            void onSuccess();

        }
    }

}
