package com.tongxun.atongmu.teacher.ui.notice;

import com.tongxun.atongmu.teacher.BasePresenter;

import java.util.List;

/**
 * Created by Anro on 2017/8/2.
 */

public class NoticeListPresenter extends BasePresenter<INoticeListContract.View> implements INoticeListContract.Presenter, INoticeListContract.Interactor.onFinishListener {

    private NoticeListInteractor interactor;
    public NoticeListPresenter() {
        interactor=new NoticeListInteractor();
    }

    @Override
    public void getListDate(String type) {
        interactor.getListDate(type,this);
    }

    @Override
    public void loadMoreListDate(String type, String createDate) {
        interactor.loadMoreListDate(type,createDate,this);
    }

    /**
     * 更新通知 新闻 活动 红点
     * @param type
     * @param StatusId
     */
    @Override
    public void upRedPoint(String type, String StatusId) {
        interactor.upRedPoint(type,StatusId,this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(List data) {
        if(mView!=null){
            mView.onNewListSuccess(data);
        }
    }

    @Override
    public void onLoadMoreSuccess(List data) {
        if(mView!=null){
            mView.onLoadMoreSuccess(data);
        }
    }

    @Override
    public void onUpRedPointSuccess() {
        if(mView!=null){
            mView.onUpRedPointSuccess();
        }
    }
}
