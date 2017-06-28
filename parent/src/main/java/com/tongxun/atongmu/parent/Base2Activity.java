package com.tongxun.atongmu.parent;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Anro on 2017/6/28.
 */

public abstract class Base2Activity<V,T extends BasePresenter<V>> extends BaseActivity {

    public T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView((V)this);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();

        super.onDestroy();
    }

    protected abstract T initPresenter();
}
