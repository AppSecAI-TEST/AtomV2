package com.tongxun.atongmu.parent.ui.home;

import com.tongxun.atongmu.parent.model.BannerDataBean;
import com.tongxun.atongmu.parent.model.ModuleModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/12.
 */

public class MainPresenter implements IMainContract.Presenter, IMainContract.Interactor.onFinishListener {

    private IMainContract.View mView;
    private MainInteractor interactor;


    public MainPresenter(IMainContract.View view) {
        mView = view;
        mView.setPresenter(this);
        interactor=new MainInteractor();
    }

    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }

    /**
     * 获得主页模块
     */
    @Override
    public void getModuleList() {
        interactor.getModuleList(this);
    }

    /**
     * 获得主页banner图
     */
    @Override
    public void getBannerList() {
        interactor.getBannerList(this);
    }

    /**
     * 获取主页小卡片
     */
    @Override
    public void getTipList() {
        interactor.getTipList(this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(List<ModuleModel> data) {
        if(mView!=null){
            mView.onModuleSuccess(data);
        }
    }

    @Override
    public void onBannerSuccess(List<BannerDataBean> data) {
        if(mView!=null){
            mView.onBannerSuccess(data);
        }
    }
}
