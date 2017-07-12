package com.tongxun.atongmu.parent.ui.album;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.TimeAlbumModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/12.
 */

public class TimeAlbumPresenter extends BasePresenter<ITimeAlbumContract.View> implements ITimeAlbumContract.Presenter, ITimeAlbumContract.Interactor.onFinishListener {
    TimeAlbumInteractor interactor=null;
    public TimeAlbumPresenter() {
        interactor=new TimeAlbumInteractor();
    }

    @Override
    public void getTimeAlbum() {
        interactor.getTimeAlbum(this);
    }

    @Override
    public void onSuccess(List<TimeAlbumModel> datas) {
        if(mView!=null){
            mView.setDates(datas);
        }
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }
}
