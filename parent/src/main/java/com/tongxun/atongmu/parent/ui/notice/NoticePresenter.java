package com.tongxun.atongmu.parent.ui.notice;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.NoticeModel;
import com.tongxun.atongmu.parent.model.SignWaitModel;

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
    public void getMoreNotice(String type,String time) {
        interactor.getMoreNotice(type,time,this);
    }

    @Override
    public void getSignUpWaiting() {
        interactor.getSignUpWaiting(this);
    }

    @Override
    public void setConfirmSignUp(String ageId) {
        if(mView!=null){
            mView.showProgress();
        }
        interactor.onConfirmSignUp(ageId,this);
    }

    @Override
    public void onNoticeSuccess(List<NoticeModel> list) {
        if(mView!=null){
            mView.setRefreshNoticeList(list);
        }
    }

    @Override
    public void onNoticeError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onMoreNoticeSuccess(List<NoticeModel> list) {
        if(mView!=null){
            mView.loadMoreNoticeList(list);
        }
    }

    @Override
    public void onSignUpSuccess(List<SignWaitModel> list) {
        if(mView!=null){
            mView.setRefreshSignWaitList(list);
        }
    }

    @Override
    public void onConfirmSuccess() {
        if(mView!=null){
            mView.hideProgress();
            mView.onConfirmSuccess();
        }
    }

    @Override
    public void onConfirmError(String message) {
        if(mView!=null){
            mView.hideProgress();
            mView.onConfirmError(message);
        }
    }


}
