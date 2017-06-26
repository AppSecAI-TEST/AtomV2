package com.tongxun.atongmu.parent;

/**
 * Created by Anro on 2017/6/26.
 */

public class BasePresenter<T> {

    public T mView;

    protected  void  attachView(T mView){
        this.mView=mView;
    }

    public void detachView() {
        this.mView=null;
    }
}
