package com.tongxun.atongmu.parent.ui.notice;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.NoticeModel;

import java.util.List;

/**
 * Created by Anro on 2017/6/30.
 */

public class NoticePresenter extends BasePresenter<INoticeContract.View> implements INoticeContract.Presenter, INoticeContract.Interactor.onFinishListener {


    private NoticeInteractor interactor;

    public NoticePresenter() {
        interactor=new NoticeInteractor();
    }

    @Override
    public void getNotice(String type) {
        interactor.getTopNotice(type,this);
    }

    @Override
    public void getSignUpWaiting() {
        interactor.getSignUpWaiting(this);
    }

    @Override
    public void onNoticeSuccess(List<NoticeModel> list) {
        if(mView!=null){
            mView.setRefreshNoticeList(list);
        }
    }

    @Override
    public void onNoticeError(String message) {

    }

    @Override
    public void onSignUpSuccess() {

    }

    @Override
    public void onSignUpError(String message) {

    }
}
