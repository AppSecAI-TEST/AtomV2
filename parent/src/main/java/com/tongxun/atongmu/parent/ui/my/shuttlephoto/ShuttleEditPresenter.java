package com.tongxun.atongmu.parent.ui.my.shuttlephoto;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/7/27.
 */

public class ShuttleEditPresenter extends BasePresenter<IShuttleEditContract.View> implements IShuttleEditContract.Presenter, IShuttleEditContract.Interactor.onFinishListener {

    ShuttleEditInteractor interactor;

    public ShuttleEditPresenter() {
        interactor=new ShuttleEditInteractor();
    }

    @Override
    public void postSignPhoto(String targetId, String path) {
        interactor.postSignPhoto(targetId,path,this);
    }

    @Override
    public void saveCardNum(String targetId, String cardStatus, String cardNum) {
        interactor.saveCardNum(targetId,cardStatus,cardNum,this);
    }

    @Override
    public void onSuccess() {
        if(mView!=null){
            mView.onSuccess();
        }
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSaveSuccess() {
        if(mView!=null){
            mView.onSaveSuccess();
        }
    }
}
