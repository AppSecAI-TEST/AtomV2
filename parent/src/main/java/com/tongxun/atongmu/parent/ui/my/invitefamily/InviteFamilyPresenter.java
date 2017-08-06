package com.tongxun.atongmu.parent.ui.my.invitefamily;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.InviteFamilyBindModel;
import com.tongxun.atongmu.parent.model.InviteFamilyUnBindModel;

import java.util.List;

/**
 * Created by admin on 2017/7/26.
 */

public class InviteFamilyPresenter extends BasePresenter<InviteFamilyContract.View> implements InviteFamilyContract.Presenter, InviteFamilyContract.Interactor.onFinishListener {

    private InviteFamilyInteractor interactor;

    public InviteFamilyPresenter() {
        interactor=new InviteFamilyInteractor();
    }

    @Override
    public void getFamilyInfo() {
        interactor.getFamilyInfo(this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onInviteInfoSuccess(List<InviteFamilyBindModel> bindingData, List<InviteFamilyUnBindModel> unBindingData) {
        if(mView!=null){
            mView.onInviteInfoSuccess(bindingData,unBindingData);
        }
    }
}
