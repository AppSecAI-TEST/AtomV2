package com.tongxun.atongmu.teacher.ui.notice;

import com.tongxun.atongmu.teacher.BasePresenter;
import com.tongxun.atongmu.teacher.model.NoticeNewsModel;

/**
 * Created by Anro on 2017/8/2.
 */

public class NoticeNewsPresenter extends BasePresenter<INoticeNewsContract.View> implements INoticeNewsContract.Presenter, INoticeNewsContract.Interactor.onFinishListener {

    private NoticeNewsInteractor interactor=null;

    public NoticeNewsPresenter() {
        interactor=new NoticeNewsInteractor();
    }

    @Override
    public void getWebNoticeRedPoint() {
       interactor.getWebNoticeRedPoint(this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(NoticeNewsModel data) {
        if(mView!=null){
            mView.onSuccess(data);
        }
    }
}
