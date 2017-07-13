package com.tongxun.atongmu.parent.ui.album;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.AlbumFromDateModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/13.
 */

public class AlbumFromDatePresenter extends BasePresenter<IAlbumFromDateContract.View> implements IAlbumFromDateContract.Presenter, IAlbumFromDateContract.Interactor.onFinishListener {

    private AlbumFromDateInteractor interactor;

    public AlbumFromDatePresenter() {
        interactor=new AlbumFromDateInteractor();
    }

    @Override
    public void getAlbumFromDate(String date) {
        interactor.getAlbumFromDate(date,this);
    }

    @Override
    public void onSuccess(List<AlbumFromDateModel> datas) {
        if(mView!=null){
            mView.onRefreshAdapter(datas);
        }

    }

    @Override
    public void onError(String string) {
        if(mView!=null){
            mView.onError(string);
        }
    }
}
