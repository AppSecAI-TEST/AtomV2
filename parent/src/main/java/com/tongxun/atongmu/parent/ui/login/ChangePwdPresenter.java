package com.tongxun.atongmu.parent.ui.login;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/7/28.
 */

public class ChangePwdPresenter extends BasePresenter<IChangePwdContract.View> implements IChangePwdContract.Presenter, IChangePwdContract.Interactor.onFinishListener {

    ChangePwdInteractor interactor;

    public ChangePwdPresenter() {
        interactor=new ChangePwdInteractor();
    }

    @Override
    public void setNewPwd(String pwd) {
        interactor.setNewPwd(pwd,this);
    }

    @Override
    public void onSuccess() {
        if(mView!=null){
            mView.onSuccess();
        }
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }
}
