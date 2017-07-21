package com.tongxun.atongmu.parent.ui.healthygrowth;

/**
 * Created by Anro on 2017/7/21.
 */

public interface IHealthyGrowthContract {
    interface View{

        void onError(String message);

        void onSuccess(String grownUrl);
    }

    interface Presenter{

        void getHealthyGrowth(String date);

    }

    interface Interactor{
        void getHealthGrowth(String date, onFinishListener listener);

        interface onFinishListener{

            void onError(String message);

            void onSuccess(String grownUrl);
        }
    }
}
