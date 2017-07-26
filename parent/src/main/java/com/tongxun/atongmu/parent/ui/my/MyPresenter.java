package com.tongxun.atongmu.parent.ui.my;

/**
 * Created by Anro on 2017/7/21.
 */

public class MyPresenter implements IMyContract.Presenter {

    IMyContract.View mView;

    public MyPresenter(IMyContract.View view) {
        mView=view;
        mView.setPresenter(this);
    }

    public void detachView(){
        if(mView!=null){
            mView=null;
        }
    }
}
