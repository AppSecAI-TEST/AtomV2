package com.tongxun.atongmu.parent.ui;

import com.tongxun.atongmu.parent.BasePresenter;

import java.util.List;

/**
 * Created by admin on 2017/7/28.
 */

public class AddCirclePhotoPresenter extends BasePresenter<IAddCirclePhotoContract.View> implements IAddCirclePhotoContract.Presenter, IAddCirclePhotoContract.Interactor.onFinishListener {

    AddCirclePhotoInteractor interactor;

    public AddCirclePhotoPresenter() {
        interactor=new AddCirclePhotoInteractor();
    }

    @Override
    public void postTimeAlbum(String content, List<String> mlist) {
        interactor.postTimeAlbum(content,mlist,this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess() {
        if(mView!=null){
            mView.onSuccess();
        }
    }
}
