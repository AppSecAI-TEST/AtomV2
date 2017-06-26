package com.tongxun.atongmu.parent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tongxun.atongmu.parent.util.ActivityControl;

/**
 * Created by Anro on 2017/6/26.
 */

public abstract class BaseActivity<V,T extends BasePresenter<V>> extends AppCompatActivity {
    public T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=initPresenter();
        ActivityControl.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView((V)this);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        ActivityControl.removeActivity(this);
        super.onDestroy();
    }

    protected abstract T initPresenter();


}
