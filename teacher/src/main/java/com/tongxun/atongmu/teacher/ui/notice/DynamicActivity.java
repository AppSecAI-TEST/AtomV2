package com.tongxun.atongmu.teacher.ui.notice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.util.DensityUtil;
import com.tongxun.atongmu.teacher.Base2Activity;
import com.tongxun.atongmu.teacher.Constants;
import com.tongxun.atongmu.teacher.IonItemClickListener;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.adapter.DynamicAdapter;
import com.tongxun.atongmu.teacher.model.DynamicModel;
import com.tongxun.atongmu.teacher.model.IDynamicContract;
import com.tongxun.atongmu.teacher.util.RecycleViewDivider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import es.dmoral.toasty.Toasty;

public class DynamicActivity extends Base2Activity<IDynamicContract.View, DynamicPresenter> implements IDynamicContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, IonItemClickListener {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.bga_refresh)
    BGARefreshLayout bgaRefresh;
    @BindView(R.id.rl_no_data)
    RelativeLayout rlNoData;

    private boolean isFirstIn = true;

    private DynamicAdapter mAdapter;

    private List<DynamicModel> mlist=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        tvTitleName.setText(getString(R.string.dynamic));

        setRecyclerViewUI();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isFirstIn) {
            isFirstIn = false;
            mPresenter.getDynamicList();
        }
    }

    private void setRecyclerViewUI() {
        rvContent.setItemAnimator(new DefaultItemAnimator());
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        rvContent.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(this, 10), getResources().getColor(R.color.colorLineGray)));

        bgaRefresh.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        // 设置下拉刷新和上拉加载更多的风格
        bgaRefresh.setRefreshViewHolder(refreshViewHolder);
    }

    @Override
    protected DynamicPresenter initPresenter() {
        return new DynamicPresenter();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.getDynamicList();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onError(String message) {
        bgaRefresh.endRefreshing();
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(List<DynamicModel> datas) {
        if (datas.size() == 0) {
            rvContent.setVisibility(View.GONE);
            rlNoData.setVisibility(View.VISIBLE);
        } else {
            mlist=datas;
            rvContent.setVisibility(View.VISIBLE);
            rlNoData.setVisibility(View.GONE);
            bgaRefresh.endRefreshing();
            mAdapter = new DynamicAdapter(this, datas,this);
            rvContent.setAdapter(mAdapter);
        }

    }

    @OnClick(R.id.iv_title_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemClick(int position) {
        if (mlist != null) {
            Intent intent = new Intent(DynamicActivity.this, DynamicAuditActivity.class);
            intent.putExtra("url", mlist.get(position).getHtmlPath());
            intent.putExtra("type", mlist.get(position).getRecType());
            intent.putExtra("sourceId", mlist.get(position).getNoticeActivityId());
            startActivityForResult(intent, Constants.REQ_CODE);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode==Constants.REQ_CODE){
                mPresenter.getDynamicList();
            }
        }
    }
}
