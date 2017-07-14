package com.tongxun.atongmu.parent.ui.schoolvideo;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.VideoListModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/13.
 */

public class VideoListPresenter extends BasePresenter<IVideoListContract.View> implements IVideoListContract.Presenter, IVideoListContract.Interactor.onFinishListener {


    VideoListInteractor interactor = null;

    public VideoListPresenter() {
        interactor = new VideoListInteractor();
    }

    @Override
    public void getVideoList() {
        interactor.getVideoList(this);
    }

    /**
     * 获取系统时间
     */
    @Override
    public void getSystemTime() {
        interactor.getSystemTime(this);
    }


    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(List<VideoListModel> datas) {
        if(mView!=null){
            mView.onSuccess(datas);
        }
    }

    @Override
    public void onSystemSuccess(String systemTime) {
        if(mView!=null){
            mView.onSystemSuccess(systemTime);
        }
    }
}
