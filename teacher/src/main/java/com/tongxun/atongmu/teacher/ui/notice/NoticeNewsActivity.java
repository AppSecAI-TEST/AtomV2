package com.tongxun.atongmu.teacher.ui.notice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.teacher.Base2Activity;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.model.NoticeNewsModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class NoticeNewsActivity extends Base2Activity<INoticeNewsContract.View, NoticeNewsPresenter> implements INoticeNewsContract.View {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.id_notice_red_point)
    ImageView idNoticeRedPoint;
    @BindView(R.id.ll_notice)
    LinearLayout llNotice;
    @BindView(R.id.id_activity_red_point)
    ImageView idActivityRedPoint;
    @BindView(R.id.ll_activity)
    LinearLayout llActivity;
    @BindView(R.id.id_answer_red_point)
    ImageView idAnswerRedPoint;
    @BindView(R.id.ll_dynamic)
    LinearLayout llDynamic;
    @BindView(R.id.id_news_red_point)
    ImageView idNewsRedPoint;
    @BindView(R.id.ll_news)
    LinearLayout llNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_news);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
    }

    @Override
    protected NoticeNewsPresenter initPresenter() {
        return new NoticeNewsPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getWebNoticeRedPoint();
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(NoticeNewsModel data) {
        if(data.getIsHavenNoticeRecord().equals("true")){
            findViewById(R.id.id_notice_red_point).setVisibility(View.VISIBLE);
        }else {
            findViewById(R.id.id_notice_red_point).setVisibility(View.INVISIBLE);
        }

        if(data.getIsHavenActivityRecord().equals("true")){
            findViewById(R.id.id_activity_red_point).setVisibility(View.VISIBLE);
        }else {
            findViewById(R.id.id_activity_red_point).setVisibility(View.INVISIBLE);
        }

        if(data.getIsHavenDynamicRecord().equals("true")){
            findViewById(R.id.id_answer_red_point).setVisibility(View.VISIBLE);
        }else {
            findViewById(R.id.id_answer_red_point).setVisibility(View.INVISIBLE);
        }

        if(data.getIsHavenNewRecord().equals("true")){
            findViewById(R.id.id_news_red_point).setVisibility(View.VISIBLE);
        }else {
            findViewById(R.id.id_news_red_point).setVisibility(View.INVISIBLE);
        }

    }

    @OnClick({R.id.iv_title_back, R.id.ll_notice, R.id.ll_activity, R.id.ll_dynamic, R.id.ll_news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.ll_notice:
                goNotice("Notice");
                break;
            case R.id.ll_activity:
                goNotice("Activity");
                break;
            case R.id.ll_dynamic:
                goNotice("News");
                break;
            case R.id.ll_news:
                goNotice("News");
                break;
        }
    }

    private void goNotice(String type) {
        Intent intent=new Intent(NoticeNewsActivity.this,NoticeListActivity.class);
        intent.putExtra("type",type);
        startActivity(intent);
    }
}
