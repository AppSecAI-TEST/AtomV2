package com.tongxun.atongmu.parent.ui.my.recharge;

import com.tongxun.atongmu.parent.model.WxPayModel;

/**
 * Created by admin on 2017/7/30.
 */

public interface IPayOrderContract {
    interface View{

        void onError(String message);

        void onSuccess(String orderString);

        void onWxSuccess(WxPayModel data);
    }

    interface Presenter{

        void postWebPayMoney(String type, String packgId);
    }

    interface Interactor{

        void postWebPayMoney(String type, String packgId, onFinishListener listener);

        interface onFinishListener{

            void onError(String message);

            void onSuccess(String orderString);

            void onWxSuccess(WxPayModel data);
        }
    }
}
