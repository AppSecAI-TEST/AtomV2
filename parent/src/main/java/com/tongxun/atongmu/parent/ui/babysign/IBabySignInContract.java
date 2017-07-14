package com.tongxun.atongmu.parent.ui.babysign;

import com.tongxun.atongmu.parent.BaseView;

/**
 * Created by Anro on 2017/7/13.
 */

public interface IBabySignInContract  {

    interface View extends BaseView {

        void setRefreshSignInDate();
    }

    interface Presenter{

        void getSignInRecord(String format);
    }

    interface Interactor{
        void getSignInRecord(String format,onFinishListener listener);

        interface onFinishListener{
            void onSignInRecordSuccess();
            void onError(String mesaage);
        }
    }
}
