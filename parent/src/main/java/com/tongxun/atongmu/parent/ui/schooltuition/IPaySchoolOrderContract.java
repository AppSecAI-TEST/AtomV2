package com.tongxun.atongmu.parent.ui.schooltuition;

import com.tongxun.atongmu.parent.BaseView;

/**
 * Created by Anro on 2017/7/18.
 */

public interface IPaySchoolOrderContract {

    interface View extends BaseView{

        void onError(String message);

        void onSuccess(String orderString);
    }

    interface Presenter{

        void createOrder(String packgId, String type);
    }

    interface Interactor{
        void createOrder(String packgId, String type, onFinishListener listener);

        interface onFinishListener{

            void onError(String message);

            void onSuccess(String orderString);
        }
    }
}
