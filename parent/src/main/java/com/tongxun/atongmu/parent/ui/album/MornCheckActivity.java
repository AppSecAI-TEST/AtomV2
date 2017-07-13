package com.tongxun.atongmu.parent.ui.album;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.MornCheckAdapter;
import com.tongxun.atongmu.parent.model.MornCheckModel;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import es.dmoral.toasty.Toasty;

public class MornCheckActivity extends Base2Activity<IMoreCheckContract.View, MornCheckPresenter> implements IMoreCheckContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.rv_notice_content)
    RecyclerView rvNoticeContent;
    @BindView(R.id.rl_notice_refresh)
    BGARefreshLayout rlNoticeRefresh;

    private  boolean isFirstIn=true;


    private boolean isCanClick=true;

    private MornCheckAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morn_check);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        tvTitleName.setText(getResources().getString(R.string.morn_check_album));
        setRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isFirstIn){
            isFirstIn=false;
            beginRefresh();
        }

    }

    private void setRecyclerView() {
        rvNoticeContent.setItemAnimator(new DefaultItemAnimator());
        rvNoticeContent.setLayoutManager(new LinearLayoutManager(this));
        rvNoticeContent.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(this, 10), getResources().getColor(R.color.colorLineGray)));
        rlNoticeRefresh.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        // 设置下拉刷新和上拉加载更多的风格
        rlNoticeRefresh.setRefreshViewHolder(refreshViewHolder);
    }

    @Override
    protected MornCheckPresenter initPresenter() {
        return new MornCheckPresenter();
    }


    @OnClick(R.id.iv_title_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isCanClick=false;
        mPresenter.getTopMornCheckAlbum();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isCanClick=false;
        return false;
    }

    @Override
    public void beginRefresh() {
        rlNoticeRefresh.beginRefreshing();
    }

    @Override
    public void beginLoadMore() {
        rlNoticeRefresh.beginLoadingMore();
    }

    @Override
    public void setRefreshSuccess(List<MornCheckModel> datas) {
        isCanClick=true;
        rlNoticeRefresh.endRefreshing();
        mAdapter=new MornCheckAdapter(this,datas);
        rvNoticeContent.setAdapter(mAdapter);
    }

    @Override
    public void onError(String string) {
        Toasty.error(this, string, Toast.LENGTH_SHORT).show();
        rlNoticeRefresh.endRefreshing();
        rlNoticeRefresh.endLoadingMore();
    }
}
