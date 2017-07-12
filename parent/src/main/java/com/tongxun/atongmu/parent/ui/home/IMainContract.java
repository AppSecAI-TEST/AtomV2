package com.tongxun.atongmu.parent.ui.home;

/**
 * Created by Anro on 2017/7/12.
 */

public interface IMainContract {
    interface View<T>{
        void setPresenter(T presenter);
    }
}
