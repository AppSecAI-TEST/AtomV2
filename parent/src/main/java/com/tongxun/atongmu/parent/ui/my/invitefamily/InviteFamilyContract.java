package com.tongxun.atongmu.parent.ui.my.invitefamily;

import com.tongxun.atongmu.parent.model.InviteFamilyBindModel;
import com.tongxun.atongmu.parent.model.InviteFamilyUnBindModel;

import java.util.List;

/**
 * Created by admin on 2017/7/26.
 */

public interface InviteFamilyContract {

    interface View{

        void onError(String message);

        void onInviteInfoSuccess(List<InviteFamilyBindModel> bindingData, List<InviteFamilyUnBindModel> unBindingData);
    }

    interface Presenter{

        void getFamilyInfo();

    }

    interface Interactor{
        void getFamilyInfo(onFinishListener listener);
        interface onFinishListener{

            void onError(String message);

            void onInviteInfoSuccess(List<InviteFamilyBindModel> bindingData, List<InviteFamilyUnBindModel> unBindingData);
        }
    }
}
