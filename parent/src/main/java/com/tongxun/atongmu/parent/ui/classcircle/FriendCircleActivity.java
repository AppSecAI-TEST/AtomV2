package com.tongxun.atongmu.parent.ui.classcircle;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import de.hdodenhof.circleimageview.CircleImageView;

public class FriendCircleActivity extends Base2Activity<IFriendCircleContract.View,FriendCirclePresenter> implements BGARefreshLayout.BGARefreshLayoutDelegate,IFriendCircleContract.View {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.civ_circle_face)
    CircleImageView civCircleFace;
    @BindView(R.id.iv_circle_title_add)
    ImageView ivCircleTitleAdd;
    @BindView(R.id.rv_circle_container)
    RecyclerView rvCircleContainer;
    @BindView(R.id.rl_circle_refresh)
    BGARefreshLayout rlCircleRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_circle);
        ButterKnife.bind(this);
        setRecyclerView();
    }

    @Override
    protected FriendCirclePresenter initPresenter() {
        return new FriendCirclePresenter();
    }

    private void setRecyclerView() {
        rvCircleContainer.setLayoutManager(new LinearLayoutManager(this));
        rvCircleContainer.setItemAnimator(new DefaultItemAnimator());
        rvCircleContainer.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(this,9), getResources().getColor(R.color.colorLineGray)));


        rlCircleRefresh.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        // 设置下拉刷新和上拉加载更多的风格
        rlCircleRefresh.setRefreshViewHolder(refreshViewHolder);
    }

    @OnClick({R.id.iv_title_back, R.id.civ_circle_face, R.id.iv_circle_title_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.civ_circle_face:

                break;
            case R.id.iv_circle_title_add:

                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


    @Override
    public void beginRefreshing() {
        rlCircleRefresh.beginRefreshing();
    }

    @Override
    public void beginLoadingMore() {
        rlCircleRefresh.beginLoadingMore();
    }
}
