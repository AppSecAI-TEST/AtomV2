package com.tongxun.atongmu.parent.ui.my.feedback;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.AddPhotoAdapter;
import com.tongxun.atongmu.parent.ui.AtomAlbumActivity;
import com.tongxun.atongmu.parent.ui.PhotoSelectContainer;
import com.tongxun.atongmu.parent.ui.photoClickListener;
import com.tongxun.atongmu.parent.util.DividerGridItemDecoration;
import com.tongxun.atongmu.parent.util.PickPhotoPopupWindow;
import com.tongxun.atongmu.parent.util.SDCardUtil;
import com.tongxun.atongmu.parent.util.SystemUtil;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileBatchCallback;
import com.zxy.tiny.callback.FileCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

import static com.tongxun.atongmu.parent.Constants.PICK_CODE;

public class OpinionFeedBackActivity extends Base2Activity<IOpinionFeedBackContract.View, OpinionFeedBackPresenter> implements IOpinionFeedBackContract.View, photoClickListener, PickPhotoPopupWindow.popClickListener {


    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.tv_send_atom)
    TextView tvSendAtom;
    @BindView(R.id.tv_send_garden)
    TextView tvSendGarden;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_content_num)
    TextView tvContentNum;
    @BindView(R.id.tv_photo_title)
    TextView tvPhotoTitle;
    @BindView(R.id.rv_photo)
    RecyclerView rvPhoto;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.ll_opinion_feedback)
    LinearLayout llOpinionFeedback;
    @BindView(R.id.tv_photo_feed_num)
    TextView tvPhotoFeedNum;
    private AddPhotoAdapter mAdapter;

    private List<String> mlist = new ArrayList<>();

    private String fileName;

    private KProgressHUD hud;

    private PackageManager pm;
    private PackageInfo pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion_feed_back);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        tvTitleName.setText(getResources().getString(R.string.feedback));
        tvTitleRight.setText(getResources().getString(R.string.record));

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);

        setRecyclerViewUI();
        setSendPosition(0);

        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvContentNum.setText(s.toString().length() + "/400");
            }
        });


        pm = getPackageManager();
        pi = null;
        try {
            pi = pm.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setRecyclerViewUI() {
        rvPhoto.setLayoutManager(new GridLayoutManager(this, 4));
        rvPhoto.setItemAnimator(new DefaultItemAnimator());
        rvPhoto.addItemDecoration(new DividerGridItemDecoration(this));
        mlist.add("SELECT");
        mAdapter = new AddPhotoAdapter(this, mlist, 3, this);
        rvPhoto.setAdapter(mAdapter);
    }

    @Override
    protected OpinionFeedBackPresenter initPresenter() {
        return new OpinionFeedBackPresenter();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @OnClick({R.id.iv_title_back, R.id.tv_title_right, R.id.tv_send_atom, R.id.tv_send_garden, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_title_right:
                goFeedBackRecord();
                break;
            case R.id.tv_send_atom:
                setSendPosition(0);
                break;
            case R.id.tv_send_garden:
                setSendPosition(1);
                break;
            case R.id.btn_commit:
                if (tvSendAtom.isSelected()) {
                    mPresenter.sendToAtom(etContent.getText().toString(), Build.MODEL, Build.VERSION.RELEASE, pi.versionName, mlist);
                } else {
                    mPresenter.sendToGarten(etContent.getText().toString(), mlist);
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void setSendPosition(int i) {
        resetSend();
        switch (i) {
            case 0:
                tvSendAtom.setSelected(true);
                break;
            case 1:
                tvSendGarden.setSelected(true);
                break;
        }
    }

    private void resetSend() {
        tvSendAtom.setSelected(false);
        tvSendGarden.setSelected(false);
    }

    private void goFeedBackRecord() {
        Intent intent = new Intent(OpinionFeedBackActivity.this, FeedBackRecordActivity.class);
        startActivity(intent);
    }


    @Override
    public void onAddPhoto(int num) {
        PickPhotoPopupWindow.getInstance().show(llOpinionFeedback, false, this);
    }

    @Override
    public void onPhotoClick(String photoUrl) {

    }

    @Override
    public void onDeletePhoto(int position) {
        mlist.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onCamera() {
        if (mlist.size() < 4) {
            PermissionGen.with(this)
                    .addRequestCode(Constants.PERMISSION_CAMERA_CODE)
                    .permissions(
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    .request();
        } else {
            Toasty.info(this, getResources().getString(R.string.photo_size_nine), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取相机权限后打开系统相机
     */
    @PermissionSuccess(requestCode = 100)
    public void doCamera() {
        fileName = UUID.randomUUID() + ".jpg";
        SystemUtil.opSystemCamera(this, SDCardUtil.getInstance().getFilePath() + fileName, Constants.REQ_CODE);
    }

    @PermissionFail(requestCode = 100)
    public void doSomeError() {
        Toasty.error(this, getResources().getString(R.string.get_camera_permission_error), Toast.LENGTH_LONG).show();
    }


    @Override
    public void onPhoto() {
        if (mlist.size() < 4) {
            PermissionGen.with(this)
                    .addRequestCode(Constants.PERMISSION_PHOTO_CODE)
                    .permissions(
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                    .request();
        } else {
            Toasty.info(this, getResources().getString(R.string.photo_size_nine), Toast.LENGTH_SHORT).show();
        }
    }

    @PermissionSuccess(requestCode = 102)
    public void doPhoto() {
        int num = 4 - mlist.size();
        PhotoSelectContainer.setMaxSize(num);
        Intent intent = new Intent(OpinionFeedBackActivity.this, AtomAlbumActivity.class);
        startActivityForResult(intent, PICK_CODE);
    }

    @PermissionFail(requestCode = 102)
    public void doPhotoError() {
        Toasty.error(this, getResources().getString(R.string.get_sd_permission_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onVideo() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Constants.REQ_CODE) {
                Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
                Tiny.getInstance().source(SDCardUtil.getInstance().getFilePath() + fileName).asFile().withOptions(options).compress(new FileCallback() {
                    @Override
                    public void callback(boolean isSuccess, String outfile) {
                        if (isSuccess) {
                            if (!mlist.contains(outfile)) {
                                mlist.add(outfile);
                            }

                            mAdapter.notifyDataSetChanged();

                        } else {
                            Toasty.error(OpinionFeedBackActivity.this, getResources().getString(R.string.pick_photo_error), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            if (requestCode == Constants.PICK_CODE) {
                hud.show();
                Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
                String[] strs = PhotoSelectContainer.getFileList().toArray(new String[PhotoSelectContainer.getFileList().size()]);
                Tiny.getInstance().source(strs).batchAsFile().withOptions(options).batchCompress(new FileBatchCallback() {
                    @Override
                    public void callback(boolean isSuccess, String[] outfile) {
                        if (isSuccess) {
                            PhotoSelectContainer.clear();
                            List<String> list = Arrays.asList(outfile);
                            for (String str : list) {
                                if (!mlist.contains(str)) {
                                    mlist.add(str);
                                }
                            }

                            mAdapter.notifyDataSetChanged();

                            hud.dismiss();
                        } else {
                            Toasty.error(OpinionFeedBackActivity.this, getResources().getString(R.string.pick_photo_error), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
            tvPhotoFeedNum.setText((mlist.size()-1)+"/3");
        }
    }


    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        etContent.setText("");
        mlist.clear();
        mlist.add("SELECT");
        mAdapter.notifyDataSetChanged();
        Toasty.success(this, getString(R.string.feedback_success), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}
