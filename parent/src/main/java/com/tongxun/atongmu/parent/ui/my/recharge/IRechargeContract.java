package com.tongxun.atongmu.parent.ui.my.recharge;

import com.tongxun.atongmu.parent.model.RechargeItemModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/28.
 */

public interface IRechargeContract {

    interface View{

        void onError(String message);

        void onChargeItemSuccess(String haveExtrapackg, String joyPath, String functionPath, String funLastTime, String packgLastTime, List<RechargeItemModel> data);
    }

    interface Presenter{

        void getWebPayItemList();
    }

    interface Interactor{
        void getWebPayItemList(onFinishListener listener);

        interface onFinishListener{

            void onError(String message);


            void onChargeItemSuccess(String haveExtrapackg, String joyPath, String functionPath, String funLastTime, String packgLastTime, List<RechargeItemModel> data);
        }
    }
}
