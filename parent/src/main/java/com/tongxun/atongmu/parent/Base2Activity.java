package com.tongxun.atongmu.parent;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Anro on 2017/6/28.
 */

public abstract class Base2Activity<V,T extends BasePresenter<V>> extends BaseActivity {

    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attachView((V)this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();

        super.onDestroy();
    }

    protected abstract T initPresenter();
}
