package com.tongxun.atongmu.parent.ui.my;

/**
 * Created by Anro on 2017/7/21.
 */

public interface IMyContract {
    interface View<T>{
        void setPresenter(T presenter);

        void onError(String message);

        void onFlowerHonorSuccess(String integral, String flowers, String parentNum, String circleNum);
    }

    interface Presenter{

        void getFlowerHonor();
    }

    interface Interactor{
        void getFlowerHonor(onFinishListener listener);

        interface onFinishListener{

            void onError(String message);

            void onSuccess(String integral, String flowers, String parentNum, String circleNum);
        }
    }
}
