package com.tongxun.atongmu.parent;

/**
 * Created by Anro on 2017/6/26.
 */

public class BasePresenter<V> {

    public V mView;

    protected  void  attachView(V mView){
        this.mView=mView;
    }

    public void detachView() {
        this.mView=null;
    }
}
