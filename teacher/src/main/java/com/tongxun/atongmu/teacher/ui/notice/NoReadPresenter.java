package com.tongxun.atongmu.teacher.ui.notice;

import com.tongxun.atongmu.teacher.BasePresenter;

/**
 * Created by Anro on 2017/8/2.
 */

public class NoReadPresenter extends BasePresenter<INoReadContract.View> implements INoReadContract.Presenter, INoReadContract.Interactor.onFinishListener {

    private NoReadInteractor interactor;

    public NoReadPresenter() {
        interactor=new NoReadInteractor();
    }


    @Override
    public void getNoReadList(String action) {
        interactor.getNoReadList(action,this);
    }
}
