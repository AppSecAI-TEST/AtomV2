package com.tongxun.atongmu.parent.ui.my.shuttlephoto;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.ShuttlePhotoModel;

import java.util.List;

/**
 * Created by admin on 2017/7/26.
 */

public class ShuttlePhotoPresenter extends BasePresenter<IShuttlePhotoContract.View> implements IShuttlePhotoContract.Presenter, IShuttlePhotoContract.Interactor.onFinishListener {


    ShuttlePhotoInteractor interactor;
    public ShuttlePhotoPresenter() {
        interactor=new ShuttlePhotoInteractor();
    }


    @Override
    public void getShuttlePhoto() {
        interactor.getShuttlePhoto(this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(List<ShuttlePhotoModel> data) {
        if(mView!=null){
            mView.onSuccess(data);
        }
    }
}
