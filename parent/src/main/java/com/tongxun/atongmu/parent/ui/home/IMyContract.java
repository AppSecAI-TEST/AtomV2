package com.tongxun.atongmu.parent.ui.home;

/**
 * Created by Anro on 2017/7/21.
 */

public class IMyContract {
    interface View<T>{
        void setPresenter(T presenter);
    }

    interface Presenter{

    }
}