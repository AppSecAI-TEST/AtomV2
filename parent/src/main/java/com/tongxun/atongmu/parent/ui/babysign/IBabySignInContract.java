package com.tongxun.atongmu.parent.ui.babysign;

import com.tongxun.atongmu.parent.BaseView;
import com.tongxun.atongmu.parent.model.BabySignInModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/13.
 */

public interface IBabySignInContract  {

    interface View extends BaseView {

        void setRefreshSignInDate(List<BabySignInModel> datas, String signNum);

        void onError(String mesaage);
    }

    interface Presenter{

        void getSignInRecord(String format);
    }

    interface Interactor{
        void getSignInRecord(String format,onFinishListener listener);

        interface onFinishListener{
            void onSignInRecordSuccess(List<BabySignInModel> datas, String signNum);
            void onError(String mesaage);
        }
    }
}
