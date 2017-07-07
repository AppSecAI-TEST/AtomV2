package com.tongxun.atongmu.parent.ui.classcircle;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.FriendCircleAdapter;
import com.tongxun.atongmu.parent.model.FriendCircleModel;
import com.tongxun.atongmu.parent.model.FriendCirlceVoteModel;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import de.hdodenhof.circleimageview.CircleImageView;



public class FriendCircleActivity extends Base2Activity<IFriendCircleContract.View,FriendCirclePresenter> implements BGARefreshLayout.BGARefreshLayoutDelegate,IFriendCircleContract.View, ICircleListener {

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

    private static boolean isFirstIn=true;

    private static boolean isCanClick=true;

    private FriendCircleAdapter mAdapter;

    private List<FriendCircleModel> mlist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_circle);
        setStatusColor(R.color.colorWhite);
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
        mPresenter.getTopCircle();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isFirstIn){
            beginRefreshing();
        }
    }

    @Override
    public void beginRefreshing() {
        rlCircleRefresh.beginRefreshing();
    }

    @Override
    public void beginLoadingMore() {
        rlCircleRefresh.beginLoadingMore();
    }

    @Override
    public void setRefreshSuccess(List<FriendCircleModel> datas) {
        mlist=datas;
        rlCircleRefresh.endRefreshing();
        mAdapter=new FriendCircleAdapter(this,datas);
        rvCircleContainer.setAdapter(mAdapter);
        FriendCircleAdapter.setListener(this);
        rvCircleContainer.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                NiceVideoPlayer niceVideoPlayer = (NiceVideoPlayer) view.findViewById(R.id.nice_circle_video_play);
                if (niceVideoPlayer == NiceVideoPlayerManager.instance().getCurrentNiceVideoPlayer()) {
                    NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
                }
            }
        });
    }

    /**
     * 点赞成功
     * @param position
     */
    @Override
    public void onLikeSuccess(int position) {
        isCanClick=true;
        FriendCirlceVoteModel model=new FriendCirlceVoteModel();
        model.setVoteNickName("ZhangLu");
        mlist.get(position).getVotePersons().add(0,model);
        mlist.get(position).setCurrentPersonVote(true);
        mlist.get(position).setVoteSum(mlist.get(position).getVoteSum()+1);
        mAdapter.notifyItemChanged(position);
    }

    /**
     * 点赞或取消点赞失败
     */
    @Override
    public void onLikeOrRemoveError() {
        isCanClick=true;
    }

    /**
     * 取消点赞成功
     * @param position
     */
    @Override
    public void onRemoveListSuccess(int position) {
        isCanClick=true;
        for(int i=0;i<mlist.get(position).getVotePersons().size();i++){
            if(mlist.get(position).getVotePersons().get(i).getVoteNickName().equals("邵丹妈妈")){
                mlist.get(position).getVotePersons().remove(i);
            }
        }
        mlist.get(position).setCurrentPersonVote(false);
        mlist.get(position).setVoteSum(mlist.get(position).getVoteSum()-1);
        mAdapter.notifyItemChanged(position);
    }

    /**
     * 点赞按钮
     * @param position
     */
    @Override
    public void vote(int position) {
        if(isCanClick){
            isCanClick=false;
            if(mlist.get(position).isCurrentPersonVote()){
                mPresenter.removeItemLisk(position,mlist.get(position).getCircleId());
            }else {
                mPresenter.setItemLisk(position,mlist.get(position).getCircleId());
            }
        }

    }

    @Override
    public void onBackPressed() {
        if (NiceVideoPlayerManager.instance().onBackPressd()) {
            return;
        }
        super.onBackPressed();
    }
}
