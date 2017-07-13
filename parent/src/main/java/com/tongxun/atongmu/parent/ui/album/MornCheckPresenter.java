package com.tongxun.atongmu.parent.ui.album;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.MornCheckModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/13.
 */

public class MornCheckPresenter extends BasePresenter<IMoreCheckContract.View> implements IMoreCheckContract.Presenter, IMoreCheckContract.Interactor.onFinishListener {

    private MornCheckInteractor interactor=null;

    public MornCheckPresenter() {
        interactor=new MornCheckInteractor();
    }

    /**
     * 获取更多晨检照片
     * @param date
     */
    @Override
    public void getMoreCheckAlbum(String date) {

    }

    /**
     * 获取最新晨检照片
     */
    @Override
    public void getTopMornCheckAlbum() {
        interactor.getTopMornCheckAlbum(this);
    }

    @Override
    public void onSuccess(List<MornCheckModel> datas) {
        if(mView!=null){
            mView.setRefreshSuccess(datas);
        }
    }

    @Override
    public void onError(String string) {
        if(mView!=null){
            mView.onError(string);
        }
    }
}
