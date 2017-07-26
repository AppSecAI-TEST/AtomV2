package com.tongxun.atongmu.parent.ui.my;

/**
 * Created by Anro on 2017/7/21.
 */

public interface IMyContract {
    interface View<T>{
        void setPresenter(T presenter);
    }

    interface Presenter{

    }
}
