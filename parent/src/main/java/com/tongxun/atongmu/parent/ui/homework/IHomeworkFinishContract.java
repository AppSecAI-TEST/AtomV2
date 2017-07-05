package com.tongxun.atongmu.parent.ui.homework;

/**
 * Created by Anro on 2017/7/5.
 */

public interface IHomeworkFinishContract {
    interface Presenter{

    }

    interface View<T> {
        void setPresenter(T presenter);
    }
}
