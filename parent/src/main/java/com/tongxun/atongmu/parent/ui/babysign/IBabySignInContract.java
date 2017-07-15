package com.tongxun.atongmu.parent.ui.babysign;

import com.tongxun.atongmu.parent.BaseView;
import com.tongxun.atongmu.parent.model.BabySignInModel;
import com.tongxun.atongmu.parent.model.SignDetailModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/13.
 */

public interface IBabySignInContract  {

    interface View extends BaseView {

        void setRefreshSignInDate(List<BabySignInModel> datas, String signNum);

        void onError(String mesaage);

        void setSignDetailSuccess(List<SignDetailModel> datas, String type);
    }

    interface Presenter{

        void getSignInRecord(String format);

        void getSignInDetail(String date);
    }

    interface Interactor{
        void getSignInRecord(String format,onFinishListener listener);

        void getSignInDetail(String date,onFinishListener listener);

        interface onFinishListener{
            void onSignInRecordSuccess(List<BabySignInModel> datas, String signNum);
            void onError(String mesaage);

            void onSignDetailSuccess(List<SignDetailModel> datas, String type);
        }
    }
}
