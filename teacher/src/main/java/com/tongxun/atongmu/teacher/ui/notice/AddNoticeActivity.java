package com.tongxun.atongmu.teacher.ui.notice;

import android.os.Bundle;

import com.tongxun.atongmu.teacher.Base2Activity;
import com.tongxun.atongmu.teacher.R;

public class AddNoticeActivity extends Base2Activity<IAddNoticeContract.View,AddNoticePresenter>implements IAddNoticeContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
    }

    @Override
    protected AddNoticePresenter initPresenter() {
        return new AddNoticePresenter();
    }
}
