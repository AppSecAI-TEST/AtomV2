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
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.adapter.NoticeListAdapter;
import com.tongxun.atongmu.teacher.model.ActivityListModel;
import com.tongxun.atongmu.teacher.model.NewsListModel;
import com.tongxun.atongmu.teacher.model.NoticeListModel;
import com.tongxun.atongmu.teacher.ui.WebViewActivity;
import com.tongxun.atongmu.teacher.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import es.dmoral.toasty.Toasty;

public class NoticeListActivity extends Base2Activity<INoticeListContract.View, NoticeListPresenter> implements INoticeListContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, INoticeListClickListener {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.ll_title)
    RelativeLayout llTitle;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.bga_refresh)
    BGARefreshLayout bgaRefresh;
    private String type;

    private NoticeListAdapter mAdapter;

    private boolean isFirstIn = true;
    private boolean isCanLoadMore=true;

    private List mlist = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorWhite);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        if (type.equals("Notice")) {
            tvTitleName.setText(getString(R.string.notice));
        } else if (type.equals("Activity")) {
            tvTitleName.setText(getString(R.string.activity));
        } else if (type.equals("News")) {
            tvTitleName.setText(getString(R.string.news));
        }

        ivTitleRight.setImageResource(R.drawable.icon_course_add);

        setRecyclerViewUI();


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isFirstIn) {
            isFirstIn = false;
            mPresenter.getListDate(type);
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
    protected NoticeListPresenter initPresenter() {
        return new NoticeListPresenter();
    }

    @OnClick({R.id.iv_title_back, R.id.iv_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.iv_title_right:
                goAddNotice();
                break;
        }
    }

    private void goAddNotice() {
        Intent intent=new Intent(this,AddNoticeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onError(String message) {
        bgaRefresh.endRefreshing();
        bgaRefresh.endLoadingMore();
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNewListSuccess(List data) {
        mlist = data;
        bgaRefresh.endRefreshing();
        mAdapter = new NoticeListAdapter(this, data, this);
        rvContent.setAdapter(mAdapter);
    }

    @Override
    public void onLoadMoreSuccess(List data) {
        if(data.size()<20){
            isCanLoadMore=false;
        }
        bgaRefresh.endLoadingMore();
        mlist.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onUpRedPointSuccess() {

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isCanLoadMore=true;
        mPresenter.getListDate(type);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if(!isCanLoadMore){
            return false;
        }
        if (mlist.get(mlist.size() - 1) instanceof NoticeListModel) {
            mPresenter.loadMoreListDate(type, ((NoticeListModel) mlist.get(mlist.size() - 1)).getCreateDate());
            return true;
        } else if (mlist.get(mlist.size() - 1) instanceof ActivityListModel) {
            mPresenter.loadMoreListDate(type, ((ActivityListModel) mlist.get(mlist.size() - 1)).getCreateDate());
            return true;
        } else if (mlist.get(mlist.size() - 1) instanceof NewsListModel) {
            mPresenter.loadMoreListDate(type, ((NewsListModel) mlist.get(mlist.size() - 1)).getCreateDate());
            return true;
        }

        return false;
    }

    @Override
    public void onNoticeNewsClick(int position) {
        if (mlist.get(position) instanceof NoticeListModel) {
            NoticeListModel model = (NoticeListModel) mlist.get(position);
            if (model.isIsNewRecord()) {
                ((NoticeListModel) mlist.get(position)).setIsNewRecord(false);
                mAdapter.notifyItemChanged(position);
                mPresenter.upRedPoint(type, model.getNoticePersonStatusId());
            }
            WebViewActivity.startWebViewActivity(this, model.getTitle(), "", model.getPhotoMin(), model.getHtmlPath(), "white", true, model.getShareHtmlPath(),"Notice",model.getNoticePersonStatusId());
        } else if (mlist.get(position) instanceof NewsListModel) {
            NewsListModel model = (NewsListModel) mlist.get(position);
            if (model.isIsNewRecord()) {
                ((NewsListModel) mlist.get(position)).setIsNewRecord(false);
                mAdapter.notifyItemChanged(position);
                mPresenter.upRedPoint(type, model.getNoticePersonStatusId());
            }
            WebViewActivity.startWebViewActivity(this, model.getTitle(), "", model.getPhotoMin(), model.getHtmlPath(), "white", true, model.getShareHtmlPath(),"News");
        } else if (mlist.get(position) instanceof ActivityListModel) {
            ActivityListModel model = (ActivityListModel) mlist.get(position);
            if (model.isIsNewRecord()) {
                ((ActivityListModel) mlist.get(position)).setIsNewRecord(false);
                mAdapter.notifyItemChanged(position);
                mPresenter.upRedPoint(type, model.getActivityPersonStatusId());
            }
             WebViewActivity.startWebViewActivity(this,model.getTitle(),"",model.getPhotoMin(),model.getHtmlPath(),"white",true,model.getShareHtmlPath(),"Activity",model.getActivityPersonStatusId());
        }
    }
}
