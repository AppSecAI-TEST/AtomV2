package com.tongxun.atongmu.parent.ui.classcircle;

import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hyphenate.easeui.utils.GlideOption;
import com.hyphenate.easeui.widget.EmojiEditText;
import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.FriendCircleAdapter;
import com.tongxun.atongmu.parent.dialog.CommonDialog;
import com.tongxun.atongmu.parent.model.BabyInfoModel;
import com.tongxun.atongmu.parent.model.FriendCircleCommentModel;
import com.tongxun.atongmu.parent.model.FriendCircleModel;
import com.tongxun.atongmu.parent.model.FriendCirclePhotoModel;
import com.tongxun.atongmu.parent.model.FriendCirlceVoteModel;
import com.tongxun.atongmu.parent.ui.CircleVideoPlayActivity;
import com.tongxun.atongmu.parent.ui.PhotoViewActivity;
import com.tongxun.atongmu.parent.util.AnroUtil;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;
import com.tongxun.atongmu.parent.util.SharePopupWindow;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.tongxun.atongmu.parent.util.ShareUtil;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

import static com.tongxun.atongmu.parent.util.NavigationBarUtil.checkDeviceHasNavigationBar;
import static com.tongxun.atongmu.parent.util.NavigationBarUtil.getNavigationBarHeight;


public class FriendCircleActivity extends Base2Activity<IFriendCircleContract.View, FriendCirclePresenter> implements BGARefreshLayout.BGARefreshLayoutDelegate, IFriendCircleContract.View, ICircleListener, ShareUtil.IShareListener {

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
    @BindView(R.id.ll_friend_circle)
    LinearLayout llFriendCircle;
    @BindView(R.id.tv_circle_name)
    TextView tvCircleName;


    private boolean isFirstIn = true;

    private boolean isCanClick = true;


    private FriendCircleAdapter mAdapter;

    private List<FriendCircleModel> mlist = new ArrayList<>();

    private BabyInfoModel model;

    private String commentNickName;

    private int mPosition;

    private PopupWindow popupWindow;
    private EmojiEditText circle_bottom_edit;
    private TextView circle_item_send;
    private String commentSourceName = "";

    private boolean isCanLoadMore = true;

    private CommonDialog commonDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_circle);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        setRecyclerView();
        mPresenter.getParentIsCanPutCircle();
        model = DataSupport.where("tokenid = ?", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, "")).findFirst(BabyInfoModel.class);
        if (model == null) {
            Toasty.error(this, getString(R.string.user_info_error), Toast.LENGTH_SHORT).show();
            finish();
        }
        tvCircleName.setText(model.getPersonName());
        Glide.with(this).load(model.getPhoto1()).apply(GlideOption.getFaceHolderOption()).into(civCircleFace);
    }

    @Override
    protected FriendCirclePresenter initPresenter() {
        return new FriendCirclePresenter();
    }

    private void setRecyclerView() {
        rvCircleContainer.setLayoutManager(new LinearLayoutManager(this));
        rvCircleContainer.setItemAnimator(new DefaultItemAnimator());
        rvCircleContainer.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(this, 10), getResources().getColor(R.color.colorLineGray)));


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
        isCanLoadMore = true;
        mPresenter.getTopCircle();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (isCanLoadMore) {
            mPresenter.loadMoreCircle(mlist.get(mlist.size() - 1).getCreateDate());
            return true;
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isFirstIn) {
            isFirstIn = false;
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
    public void setRefreshSuccess(String currentNickName, List<FriendCircleModel> datas) {
        commentNickName = currentNickName;
        mlist = datas;
        rlCircleRefresh.endRefreshing();
        mAdapter = new FriendCircleAdapter(this, datas);
        rvCircleContainer.setAdapter(mAdapter);
        mAdapter.setListener(this);
    }

    /**
     * 点赞成功
     *
     * @param position
     */
    @Override
    public void onLikeSuccess(int position) {
        isCanClick = true;
        FriendCirlceVoteModel model = new FriendCirlceVoteModel();
        model.setVoteNickName(commentNickName);
        mlist.get(position).getVotePersons().add(0, model);
        mlist.get(position).setCurrentPersonVote(true);
        mlist.get(position).setVoteSum(mlist.get(position).getVoteSum() + 1);
        mAdapter.notifyItemChanged(position);
    }

    /**
     * 点赞或取消点赞失败
     *
     * @param message
     */
    @Override
    public void onLikeOrRemoveError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
        isCanClick = true;
    }

    /**
     * 取消点赞成功
     *
     * @param position
     */
    @Override
    public void onRemoveListSuccess(int position) {
        isCanClick = true;
        for (int i = 0; i < mlist.get(position).getVotePersons().size(); i++) {
            if (mlist.get(position).getVotePersons().get(i).getVoteNickName().equals(commentNickName)) {
                mlist.get(position).getVotePersons().remove(i);
            }
        }
        mlist.get(position).setCurrentPersonVote(false);
        mlist.get(position).setVoteSum(mlist.get(position).getVoteSum() - 1);
        mAdapter.notifyItemChanged(position);
    }

    /**
     * @param message
     */
    @Override
    public void onError(String message) {
        rlCircleRefresh.endRefreshing();
        rlCircleRefresh.endLoadingMore();
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();

    }

    /**
     * 家长不能发圈子
     */
    @Override
    public void onCanPutFail() {
        ivCircleTitleAdd.setVisibility(View.GONE);
    }

    /**
     * 家长能发圈子
     */
    @Override
    public void onCanPutSuccess() {
        ivCircleTitleAdd.setVisibility(View.VISIBLE);
    }


    /**
     * 点赞按钮
     *
     * @param position
     */
    @Override
    public void vote(int position) {
        if (isCanClick) {
            isCanClick = false;
            if (mlist.get(position).isCurrentPersonVote()) {
                mPresenter.removeItemLisk(position, mlist.get(position).getCircleId());
            } else {
                mPresenter.setItemLisk(position, mlist.get(position).getCircleId());
            }
        }

    }

    /**
     * 分享回调
     *
     * @param position
     */
    @Override
    public void share(int position) {
        mPosition = position;
        String type = mlist.get(position).getBodyType();
        switch (type) {
            case "0":
                SharePopupWindow.getInstance().show(llFriendCircle, model.getKindgName() + "-圈子", "", mlist.get(position).getShare_url(), Constants.DEFAULTICON, this);
                break;
            case "1":
                SharePopupWindow.getInstance().show(llFriendCircle, model.getKindgName() + "-圈子", "", mlist.get(position).getShare_url(), mlist.get(position).getPhotos().get(0).getPhoto(), this);
                break;
            case "2":
                SharePopupWindow.getInstance().show(llFriendCircle, model.getKindgName() + "-圈子", "", mlist.get(position).getShare_url(), mlist.get(position).getMediaImg(), this);
                break;
        }
    }

    /**
     * 图片点击
     *
     * @param groupPosition
     * @param itemPosition
     */
    @Override
    public void onPhotoClick(int groupPosition, int itemPosition) {
        ArrayList<String> list = new ArrayList<>();
        for (FriendCirclePhotoModel model : mlist.get(groupPosition).getPhotos()) {
            list.add(model.getPhoto());
        }
        PhotoViewActivity.startActivity(this, list, itemPosition);
    }

    /**
     * 评论
     *
     * @param position
     */
    @Override
    public void remark(int position) {
        mPosition = position;
        showCommentPop(mPosition, -1, "", "评论");
        popupInputMethodWindow();
    }

    /**
     * 删除圈子
     *
     * @param position
     */
    @Override
    public void delete(final int position) {
        commonDialog = new CommonDialog(this, getString(R.string.confirm_delete_circle), getString(R.string.confirm), getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
            @Override
            public void go() {
                mPresenter.deleteCircle(mlist.get(position).getCircleId(), position);
                commonDialog.dismiss();
            }

            @Override
            public void cancel() {
                commonDialog.dismiss();
            }
        });
        commonDialog.show();

    }

    /**
     * 回复评论
     *
     * @param position
     * @param pos
     */
    @Override
    public void remarkBack(int position, int pos) {
        mPosition = position;
        showCommentPop(mPosition, pos, mlist.get(position).getCommentPersons().get(pos).getCommentPersonId(), "回复");
        popupInputMethodWindow();
    }

    /**
     * 删除自己发的评论
     *
     * @param position
     * @param pos
     */
    @Override
    public void deleteRemark(final int position, final int pos) {
        commonDialog = new CommonDialog(this, getString(R.string.confirm_delete_remark), getString(R.string.confirm), getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
            @Override
            public void go() {
                mPresenter.deleteRemark(mlist.get(position).getCommentPersons().get(pos).getCommentId(), position, pos);
                commonDialog.dismiss();
            }

            @Override
            public void cancel() {
                commonDialog.dismiss();
            }
        });
        commonDialog.show();
    }

    @Override
    public void playVideo(int position) {
        CircleVideoPlayActivity.startActivity(this, mlist.get(position).getMediaURL());
    }
    //查看圈子明细
    @Override
    public void commentMore(int position) {
        Intent intent = new Intent(FriendCircleActivity.this, CircleItemDetailActivity.class);
        intent.putExtra("data", mlist.get(position));
        intent.putExtra("position", position);
        intent.putExtra("commentNickName", commentNickName);
        startActivityForResult(intent,Constants.REQ_INTENT_CODE);
    }

    private void showCommentPop(final int groupPosition, int itemPosition, final String sourcePersonId, final String commentType) {
        View view = LayoutInflater.from(this).inflate(R.layout.circle_bottom_edit_layout, null);
        popupWindow = new PopupWindow(view, ViewPager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        int navigationBar = 0;
        if (checkDeviceHasNavigationBar(getApplicationContext())) {
            navigationBar = getNavigationBarHeight(getApplicationContext());
        }
        circle_bottom_edit = (EmojiEditText) view.findViewById(R.id.circle_bottom_edit);
        circle_item_send = (TextView) view.findViewById(R.id.circle_item_send);

        if (commentType.equals("回复")) {
            //得到被回复人的名字
            commentSourceName = mlist.get(groupPosition).getCommentPersons().get(itemPosition).getCommentPersonName();
            circle_bottom_edit.setHint("回复" + commentSourceName + ":");
        }
        /**
         * 发送评论
         */
        circle_item_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (circle_bottom_edit.getText().toString().equals("") || circle_bottom_edit.getText().toString() == null) {

                } else {
                    String remarks = AnroUtil.string2Unicode(circle_bottom_edit.getText() + "");
                    mPresenter.postCircleComment(mlist.get(groupPosition).getCircleId(), sourcePersonId, commentSourceName, remarks, commentType);
                    popupWindow.dismiss();
                }
            }
        });
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.showAtLocation(llFriendCircle, Gravity.BOTTOM, 0, navigationBar);
    }

    //同步显示输入法
    private void popupInputMethodWindow() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 评论成功
     *
     * @param commentType
     */
    @Override
    public void onCommentSuccess(String commentType, String commentId, String commentSourceName, String remarks) {
        FriendCircleCommentModel person = new FriendCircleCommentModel();
        person.setCommentPersonName(commentNickName);
        if (commentType.equals("回复")) {
            person.setCommentSourcePersonName(commentSourceName);
            person.setCommentType("回复");
        }
        person.setCommentId(commentId);
        person.setCommentCurrentPerson(true);
        person.setCommentText(remarks);
        mlist.get(mPosition).getCommentPersons().add(person);
        mAdapter.notifyItemChanged(mPosition);
    }

    @Override
    public void setLoadMoreSuccess(List<FriendCircleModel> datas) {
        rlCircleRefresh.endLoadingMore();
        if (datas.size() < 20) {
            isCanLoadMore = false;
        }
        mlist.addAll(datas);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteSuccess(int position) {
        mlist.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onDeleteRemarkSuccess(int position, int pos) {
        mlist.get(position).getCommentPersons().remove(pos);
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onBackPressed() {
        if (NiceVideoPlayerManager.instance().onBackPressd()) {
            return;
        }
        super.onBackPressed();
    }

    /**
     * 分享成功
     */
    @Override
    public void onShareSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int count = Integer.parseInt(mlist.get(mPosition).getShareQty()) + 1;
                mlist.get(mPosition).setShareQty(String.valueOf(count));
                mAdapter.notifyItemChanged(mPosition);
            }
        });
        mPresenter.upShareCount(mlist.get(mPosition).getCircleId());
    }

    /**
     * 分享失败
     */
    @Override
    public void onError() {

    }

    /**
     * 分享取消
     */
    @Override
    public void onCancel() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==Constants.REQ_INTENT_CODE){
                FriendCircleModel model=data.getParcelableExtra("data");
                int position=data.getIntExtra("position",-1);
                if(position==-1){
                    return;
                }
                mlist.set(position,model);
                mAdapter.notifyItemChanged(position);
            }
        }
    }
}
