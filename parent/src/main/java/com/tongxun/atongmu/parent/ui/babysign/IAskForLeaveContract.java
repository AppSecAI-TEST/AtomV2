package com.tongxun.atongmu.parent.ui.babysign;

import com.tongxun.atongmu.parent.BaseView;

/**
 * Created by Anro on 2017/7/15.
 */

public class IAskForLeaveContract {

    interface View extends BaseView {

        void onError(String string);

        void onSuccess();
    }

    interface Presenter {

        void postSubmitLeave(String startDate, String endDate, String type, String remarks);
    }

    interface Interactor {
        void postSubmitLeave(String startDate, String endDate, String type, String remarks,onFinishListener listener);
        interface onFinishListener {
            void onSuccess();

            void onError(String string);
        }
    }
}
