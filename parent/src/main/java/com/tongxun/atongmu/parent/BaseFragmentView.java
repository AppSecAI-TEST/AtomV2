package com.tongxun.atongmu.parent;

/**
 * Created by Anro on 2017/6/20.
 */

public interface BaseFragmentView<T> extends BaseView{
    void setPresenter(T presenter);
}
