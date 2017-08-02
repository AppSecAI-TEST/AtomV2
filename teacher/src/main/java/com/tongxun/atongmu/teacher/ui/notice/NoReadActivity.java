package com.tongxun.atongmu.teacher.ui.notice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tongxun.atongmu.teacher.Base2Activity;
import com.tongxun.atongmu.teacher.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoReadActivity extends Base2Activity<INoReadContract.View, NoReadPresenter> implements INoReadContract.View {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_check_left)
    TextView tvCheckLeft;
    @BindView(R.id.tv_check_right)
    TextView tvCheckRight;
    @BindView(R.id.ll_title_check)
    LinearLayout llTitleCheck;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;

    private String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_read);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorWhite);
        Intent intent=getIntent();
        action=intent.getStringExtra("action");
        if(action.equals("Activity")){
            llTitleCheck.setVisibility(View.VISIBLE);

        }else {
            tvTitleName.setVisibility(View.VISIBLE);
        }
        tvTitleName.setText(getString(R.string.no_read));
        mPresenter.getNoReadList(action);
    }

    @Override
    protected NoReadPresenter initPresenter() {
        return new NoReadPresenter();
    }

    @OnClick({R.id.iv_title_back, R.id.tv_check_left, R.id.tv_check_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_check_left:
                break;
            case R.id.tv_check_right:
                break;
        }
    }
}
