package com.tongxun.atongmu.parent.ui.my.usehelp;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.FunIntroModel;
import com.tongxun.atongmu.parent.model.ProblemHelpModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/25.
 */

public class UseHelpPresenter extends BasePresenter<IUserHelpContract.View> implements IUserHelpContract.Presenter, IUserHelpContract.Interactor.onFinishListener {

    UseHelpInteractor interactor;

    public UseHelpPresenter() {
        interactor=new UseHelpInteractor();
    }

    @Override
    public void getFunctionIntroduction() {
        interactor.getFunctionIntroduction(this);
    }

    @Override
    public void getUseHelp() {
        interactor.getUseHelp(this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onFunIntroSuccess(List<FunIntroModel> data) {
        if(mView!=null){
            mView.onFunIntroSuccess(data);
        }
    }

    @Override
    public void onProblemSuccess(List<ProblemHelpModel> datas) {
        if(mView!=null){
            mView.onProblemSuccess(datas);
        }
    }
}
