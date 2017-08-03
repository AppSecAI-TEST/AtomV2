package com.tongxun.atongmu.teacher.ui.notice;

import com.tongxun.atongmu.teacher.BasePresenter;
import com.tongxun.atongmu.teacher.model.DynamicModel;
import com.tongxun.atongmu.teacher.model.IDynamicContract;

import java.util.List;

/**
 * Created by Anro on 2017/8/3.
 */

public class DynamicPresenter extends BasePresenter<IDynamicContract.View> implements IDynamicContract.Presenter, IDynamicContract.Interactor.onFinishListener {

    private DynamicInteractor interactor;

    public DynamicPresenter() {
        interactor=new DynamicInteractor();
    }

    @Override
    public void getDynamicList() {
        interactor.getDynamicList(this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(List<DynamicModel> datas) {
        if(mView!=null){
            mView.onSuccess(datas);
        }
    }
}
