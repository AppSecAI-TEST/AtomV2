package com.tongxun.atongmu.parent.ui.classcircle;

import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hyphenate.easeui.widget.EmojiEditText;
import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.IonItemClickListener;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.CommentAdapter;
import com.tongxun.atongmu.parent.adapter.FriendCirclePhotoAdapter;
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
import com.tongxun.atongmu.parent.util.GlideOption;
import com.tongxun.atongmu.parent.util.ScreenUtils;
import com.tongxun.atongmu.parent.util.SharePopupWindow;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.tongxun.atongmu.parent.util.ShareUtil;
import com.tongxun.atongmu.parent.widget.CircleCommentListView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

import static com.tongxun.atongmu.parent.util.NavigationBarUtil.checkDeviceHasNavigationBar;
import static com.tongxun.atongmu.parent.util.NavigationBarUtil.getNavigationBarHeight;

public class CircleItemDetailActivity extends Base2Activity<ICircleItemDetailContract.View, CircleItemDetailPresenter> implements ICircleItemDetailContract.View, ShareUtil.IShareListener {

    @BindView(R.id.civ_item_teacher_face)
    CircleImageView civItemTeacherFace;
    @BindView(R.id.tv_item_teacher_name)
    TextView tvItemTeacherName;
    @BindView(R.id.tv_item_time)
    TextView tvItemTime;
    @BindView(R.id.tv_item_content)
    TextView tvItemContent;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.iv_video_img)
    ImageView ivVideoImg;
    @BindView(R.id.nice_circle_video)
    RelativeLayout niceCircleVideo;
    @BindView(R.id.rv_photo_list)
    RecyclerView rvPhotoList;
    @BindView(R.id.tv_browse)
    TextView tvBrowse;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_vote)
    TextView tvVote;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.tv_vote_person)
    TextView tvVotePerson;
    @BindView(R.id.tv_remark_person)
    TextView tvRemarkPerson;
    @BindView(R.id.circle_comment_list)
    CircleCommentListView circleCommentList;
    @BindView(R.id.cirlce_comment_more)
    TextView cirlceCommentMore;
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.ll_circle_detail)
    LinearLayout llCircleDetail;
    private FriendCircleModel model;
    private String commentNickName;

    private FriendCirclePhotoAdapter photoAdapter;

    private CommentAdapter commentAdapter;

    private boolean isCanClick = true;

    private BabyInfoModel babyInfoModel;

    private PopupWindow popupWindow;
    private EmojiEditText circle_bottom_edit;
    private TextView circle_item_send;
    private String commentSourceName = "";

    private CommonDialog commonDialog;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_item_detail);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        model = intent.getParcelableExtra("data");
        babyInfoModel = DataSupport.where("tokenid = ?", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, "")).findFirst(BabyInfoModel.class);

        commentNickName = intent.getStringExtra("commentNickName");
        position = intent.getIntExtra("position",-1);
        set_datas();


        tvVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCanClick) {
                    isCanClick = false;
                    if (model.isCurrentPersonVote()) {
                        mPresenter.removeItemLisk(0, model.getCircleId());
                    } else {
                        mPresenter.setItemLisk(0, model.getCircleId());
                    }
                }
            }
        });
        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShare();
            }


        });

        tvRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentPop(0, -1, "", "评论");
                popupInputMethodWindow();
            }
        });
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCircle();
            }
        });
        cirlceCommentMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (mlistener != null) {
//                    mlistener.commentMore(position);
//                }
            }
        });

        circleCommentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                //判断是否是自己的评论  不是则回复
                if (!model.getCommentPersons().get(pos).getCommentCurrentPerson()) {
                    showCommentPop(0, pos, model.getCommentPersons().get(pos).getCommentPersonId(), "回复");
                    popupInputMethodWindow();
                }
            }
        });
        circleCommentList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
                //判断是否是自己的评论则可以删除
                if (model.getCommentPersons().get(pos).getCommentCurrentPerson()) {
                    deleteRemark(pos);
                }
                return true;
            }
        });

        ivVideoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }


        });
    }

    private void playVideo() {
        CircleVideoPlayActivity.startActivity(this, model.getMediaURL());
    }

    private void deleteRemark(final int pos) {
        commonDialog = new CommonDialog(this, getString(R.string.confirm_delete_remark), getString(R.string.confirm), getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
            @Override
            public void go() {
                mPresenter.deleteRemark(model.getCommentPersons().get(pos).getCommentId(), 0, pos);
                commonDialog.dismiss();
            }

            @Override
            public void cancel() {
                commonDialog.dismiss();
            }
        });
        commonDialog.show();
    }

    private void deleteCircle() {
        commonDialog = new CommonDialog(this, getString(R.string.confirm_delete_circle), getString(R.string.confirm), getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
            @Override
            public void go() {
                mPresenter.deleteCircle(model.getCircleId(), 0);
                commonDialog.dismiss();
            }

            @Override
            public void cancel() {
                commonDialog.dismiss();
            }
        });
        commonDialog.show();
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
            commentSourceName = model.getCommentPersons().get(itemPosition).getCommentPersonName();
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
                    mPresenter.postCircleComment(model.getCircleId(), sourcePersonId, commentSourceName, remarks, commentType);
                    popupWindow.dismiss();
                }
            }
        });
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.showAtLocation(llCircleDetail, Gravity.BOTTOM, 0, navigationBar);
    }

    //同步显示输入法
    private void popupInputMethodWindow() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void onShare() {
        String type = model.getBodyType();
        switch (type) {
            case "0":
                SharePopupWindow.getInstance().show(llCircleDetail, babyInfoModel.getKindgName() + "-圈子", "", model.getShare_url(), Constants.DEFAULTICON, this);
                break;
            case "1":
                SharePopupWindow.getInstance().show(llCircleDetail, babyInfoModel.getKindgName() + "-圈子", "", model.getShare_url(), model.getPhotos().get(0).getPhoto(), this);
                break;
            case "2":
                SharePopupWindow.getInstance().show(llCircleDetail, babyInfoModel.getKindgName() + "-圈子", "", model.getShare_url(), model.getMediaImg(), this);
                break;
        }
    }

    private void set_datas() {
        tvItemTeacherName.setText(model.getPersonName());
        tvItemTime.setText(model.getCreateDate());
        //判断评论是否为空
        if (TextUtils.isEmpty(model.getContext())) {
            tvItemContent.setVisibility(View.GONE);
        } else {
            tvItemContent.setVisibility(View.VISIBLE);
            tvItemContent.setText(AnroUtil.unicode2String(model.getContext()));

        }
        //item 头像
        Glide.with(this).load(model.getPersonPhoto()).apply(GlideOption.getImageHolderOption()).into(civItemTeacherFace);
        tvBrowse.setText(getString(R.string.browse_size) + model.getReadQty());
        if (!model.getShareQty().equals("0")) {
            tvShare.setText(model.getShareQty());
        } else {
        }
        //点赞
        if (model.getVoteSum() > 0) {
            tvVotePerson.setVisibility(View.VISIBLE);
            if (model.isCurrentPersonVote()) {
                tvVote.setSelected(true);
            } else {
                tvVote.setSelected(false);
            }
            String likestr = "";
            for (FriendCirlceVoteModel voteModel : model.getVotePersons()) {
                likestr += voteModel.getVoteNickName() + "、";
            }
            likestr = likestr.substring(0, likestr.length() - 1);
            tvVotePerson.setText(likestr);
        } else {
            tvVote.setSelected(false);
            tvVotePerson.setVisibility(View.GONE);
        }

        //判断是否是自己发的
        if (model.isSelfFlag()) {
            ivDelete.setVisibility(View.VISIBLE);
        } else {
            ivDelete.setVisibility(View.GONE);
        }

        //评论
        if (model.getCommentPersons().size() > 0) {
            tvRemarkPerson.setVisibility(View.VISIBLE);
            circleCommentList.setVisibility(View.VISIBLE);
            tvRemarkPerson.setText(model.getCommentPersons().size() + getString(R.string.num_remark));
            commentAdapter = new CommentAdapter(this, R.layout.circle_commet_item_layout, model.getCommentPersons());
            circleCommentList.setAdapter(commentAdapter);
        } else {
            circleCommentList.setVisibility(View.GONE);
            tvRemarkPerson.setVisibility(View.GONE);
        }


        photoAdapter = new FriendCirclePhotoAdapter(this, model.getPhotos(), new IonItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                ArrayList<String> list = new ArrayList<>();
                for (FriendCirclePhotoModel date : model.getPhotos()) {
                    list.add(date.getPhoto());
                }
                PhotoViewActivity.startActivity(CircleItemDetailActivity.this, list, pos);
            }
        });
        rvPhotoList.setItemAnimator(new DefaultItemAnimator());
        ViewGroup.LayoutParams layoutParams = rvPhotoList.getLayoutParams();
        switch (model.getBodyType()) {
            case "0"://纯文本
                niceCircleVideo.setVisibility(View.GONE);
                layoutParams.height = 0;
                break;
            case "1"://图文的时候
                niceCircleVideo.setVisibility(View.GONE);
                int size = 0;
                if (model.getPhotos().size() == 1) {
                    rvPhotoList.setLayoutManager(new GridLayoutManager(this, 1));
                    layoutParams.height = (ScreenUtils.getScreenWidth() / 3) * 2;
                } else if (model.getPhotos().size() == 2 || model.getPhotos().size() == 4) {
                    rvPhotoList.setLayoutManager(new GridLayoutManager(this, 2));
                    if (model.getPhotos().size() == 2) {
                        layoutParams.height = ScreenUtils.getScreenWidth() / 2;
                    } else {
                        layoutParams.height = ScreenUtils.getScreenWidth();
                    }
                } else if (model.getPhotos().size() < 10) {
                    if (model.getPhotos().size() % 3 == 0) {
                        size = model.getPhotos().size() / 3;
                    } else {
                        size = model.getPhotos().size() / 3 + 1;
                    }
                    layoutParams.height = (ScreenUtils.getScreenWidth() / 3) * size;
                    rvPhotoList.setLayoutManager(new GridLayoutManager(this, 3));
                } else {
                    rvPhotoList.setLayoutManager(new GridLayoutManager(this, 3));
                    layoutParams.height = ScreenUtils.getScreenWidth() - DensityUtil.dip2px(this, 2);
                }
                break;
            case "2":
                layoutParams.height = 0;
                niceCircleVideo.setVisibility(View.VISIBLE);
                Glide.with(this).load(model.getMediaImg()).apply(GlideOption.getPHOption()).into(ivVideoImg);
                break;
        }
        rvPhotoList.setLayoutParams(layoutParams);
        rvPhotoList.setAdapter(photoAdapter);
    }

    @Override
    protected CircleItemDetailPresenter initPresenter() {
        return new CircleItemDetailPresenter();
    }

    @OnClick(R.id.iv_title_back)
    public void onViewClicked() {
        Intent intent=new Intent();
        intent.putExtra("data",model);
        intent.putExtra("position",position);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onLikeSuccess() {
        isCanClick = true;
        FriendCirlceVoteModel data = new FriendCirlceVoteModel();
        data.setVoteNickName(commentNickName);
        model.getVotePersons().add(0, data);
        model.setCurrentPersonVote(true);
        model.setVoteSum(model.getVoteSum() + 1);
        set_datas();
    }

    @Override
    public void onRemoveListSuccess() {
        isCanClick = true;
        for (int i = 0; i < model.getVotePersons().size(); i++) {
            if (model.getVotePersons().get(i).getVoteNickName().equals(commentNickName)) {
                model.getVotePersons().remove(i);
            }
        }
        model.setCurrentPersonVote(false);
        model.setVoteSum(model.getVoteSum() - 1);
        set_datas();
    }

    @Override
    public void onLikeOrRemoveError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

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
        model.getCommentPersons().add(person);
        set_datas();
    }

    @Override
    public void onDeleteSuccess(int position) {
        finish();
    }

    @Override
    public void onDeleteRemarkSuccess(int position, int pos) {
        model.getCommentPersons().remove(pos);
        set_datas();
    }

    @Override
    public void onShareSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int count = Integer.parseInt(model.getShareQty()) + 1;
                model.setShareQty(String.valueOf(count));
                set_datas();
            }
        });
        mPresenter.upShareCount(model.getCircleId());
    }

    @Override
    public void onError() {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Intent intent=new Intent();
            intent.putExtra("data",model);
            intent.putExtra("position",position);
            setResult(RESULT_OK,intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);

    }
}
