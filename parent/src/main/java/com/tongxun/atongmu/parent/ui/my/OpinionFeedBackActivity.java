package com.tongxun.atongmu.parent.ui.my;

import android.os.Bundle;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;

public class OpinionFeedBackActivity extends Base2Activity<IOpinionFeedBackContract.View,OpinionFeedBackPresenter> implements IOpinionFeedBackContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion_feed_back);
    }

    @Override
    protected OpinionFeedBackPresenter initPresenter() {
        return new OpinionFeedBackPresenter();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
