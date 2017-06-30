package com.tongxun.atongmu.parent.ui.notice;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.NoticeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

public class NoticeActivity extends Base2Activity<INoticeContract.View,NoticePresenter> implements BGARefreshLayout.BGARefreshLayoutDelegate,INoticeContract.View, View.OnClickListener {

    @BindView(R.id.tv_title_notice)
    TextView tvTitleNotice;
    @BindView(R.id.tv_title_news)
    TextView tvTitleNews;
    @BindView(R.id.tv_title_activity)
    TextView tvTitleActivity;
    @BindView(R.id.tv_title_sign_up)
    TextView tvTitleSignUp;
    @BindView(R.id.rv_notice_content)
    RecyclerView rvNoticeContent;
    @BindView(R.id.rl_notice_refresh)
    BGARefreshLayout rlNoticeRefresh;

    private static int pagePosition=0;

    private boolean isCanChange=true;

    private List<NoticeModel> noticeList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);


        setPagePostion();


        tvTitleNotice.setOnClickListener(this);
        tvTitleNews.setOnClickListener(this);
        tvTitleActivity.setOnClickListener(this);
        tvTitleSignUp.setOnClickListener(this);

        setRecyclerView();


        rlNoticeRefresh.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this,true);
        // 设置下拉刷新和上拉加载更多的风格
        rlNoticeRefresh.setRefreshViewHolder(refreshViewHolder);

    }

    private void setPagePostion() {
        pagePosition=0;
        tvTitleNotice.setSelected(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        beginRefreshing();

    }

    @Override
    protected NoticePresenter initPresenter() {
        return new NoticePresenter();
    }

    private void setRecyclerView() {
        rvNoticeContent.setLayoutManager(new LinearLayoutManager(this));
        rvNoticeContent.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isCanChange=false;
        switch (pagePosition){
            case 0:
                mPresenter.getNotice("Notice");
                break;
            case 1:
                mPresenter.getNotice("News");
                break;
            case 2:
                mPresenter.getNotice("Activity");
                break;
            case 3:
                mPresenter.getSignUpWaiting();
                break;
        }

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void beginRefreshing() {
        rlNoticeRefresh.beginRefreshing();
    }

    @Override
    public void beginLoadingMore() {
        rlNoticeRefresh.beginLoadingMore();
    }

    @Override
    public void setRefreshNoticeList(List<NoticeModel> list) {
        noticeList.clear();
        noticeList.addAll(list);
    }

    @Override
    public void loadMoreNoticeList(List<NoticeModel> list) {

    }

    @Override
    public void onClick(View v) {
        if(isCanChange){
            resetTitle();
            switch (v.getId()){
                case R.id.tv_title_notice:
                    pagePosition=0;
                    tvTitleNotice.setSelected(true);
                    break;
                case R.id.tv_title_news:
                    pagePosition=1;
                    tvTitleNews.setSelected(true);
                    break;
                case R.id.tv_title_activity:
                    pagePosition=2;
                    tvTitleActivity.setSelected(true);
                    break;
                case R.id.tv_title_sign_up:
                    pagePosition=3;
                    tvTitleSignUp.setSelected(true);
                    break;

            }
        }

    }

    private void resetTitle() {
        tvTitleNotice.setSelected(false);
        tvTitleNews.setSelected(false);
        tvTitleActivity.setSelected(false);
        tvTitleSignUp.setSelected(false);
    }
}
