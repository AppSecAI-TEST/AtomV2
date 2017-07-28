package com.tongxun.atongmu.parent.ui.my;

/**
 * Created by Anro on 2017/7/21.
 */

public class MyPresenter implements IMyContract.Presenter, IMyContract.Interactor.onFinishListener {

    IMyContract.View mView;
    MyInteractor interactor;

    public MyPresenter(IMyContract.View view) {
        mView=view;
        mView.setPresenter(this);
        interactor=new MyInteractor();
    }

    public void detachView(){
        if(mView!=null){
            mView=null;
        }
    }

    @Override
    public void getFlowerHonor() {
        interactor.getFlowerHonor(this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(String integral, String flowers, String parentNum, String circleNum) {
        if(mView!=null){
            mView.onFlowerHonorSuccess(integral,flowers,parentNum,circleNum);
        }
    }
}
