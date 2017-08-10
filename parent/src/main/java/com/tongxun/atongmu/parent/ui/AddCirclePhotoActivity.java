package com.tongxun.atongmu.parent.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.AddCirclePhotoAdapter;
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

public class AddCirclePhotoActivity extends Base2Activity<IAddCirclePhotoContract.View, AddCirclePhotoPresenter> implements IAddCirclePhotoContract.View, photoClickListener, PickPhotoPopupWindow.popClickListener {

    @BindView(R.id.tv_homework_back)
    TextView tvHomeworkBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_homework_commit)
    TextView tvHomeworkCommit;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.rv_photo)
    RecyclerView rvPhoto;
    @BindView(R.id.ll_add_photo)
    LinearLayout llAddPhoto;

    private List<String> mlist = new ArrayList<>();

    private AddCirclePhotoAdapter mAdapter;
    private boolean isCanVideo;
    private String action;
    private int maxSize;

    private String fileName;

    private KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_circle_photo);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        isCanVideo=intent.getBooleanExtra("isCanVideo",false);
        action=intent.getStringExtra("action");
        maxSize=intent.getIntExtra("maxSize",9);
        rvPhoto.setLayoutManager(new GridLayoutManager(this, 4));
        rvPhoto.setItemAnimator(new DefaultItemAnimator());
        rvPhoto.addItemDecoration(new DividerGridItemDecoration(this));
        mlist.add("SELECT");
        mAdapter = new AddCirclePhotoAdapter(this, mlist, false, 9, this);
        rvPhoto.setAdapter(mAdapter);


        if(action.equals("TimeAlbum")){
            tvTitleName.setText("");
            tvHomeworkCommit.setText(getString(R.string.release));
        }

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);
    }


    public static void startActivity(Context context,String action,int maxSize,boolean isCanVideo){
        Intent intent=new Intent(context,AddCirclePhotoActivity.class);
        intent.putExtra("isCanVideo",isCanVideo);
        intent.putExtra("action",action);
        intent.putExtra("maxSize",maxSize);
        context.startActivity(intent);
    }

    @Override
    protected AddCirclePhotoPresenter initPresenter() {
        return new AddCirclePhotoPresenter();
    }

    @OnClick({R.id.tv_homework_back, R.id.tv_homework_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_homework_back:
                finish();
                break;
            case R.id.tv_homework_commit:
                hud.show();
                postCirclePhoto();
                break;
        }
    }

    private void postCirclePhoto() {
        if(action.equals("TimeAlbum")){
            mPresenter.postTimeAlbum(etContent.getText().toString(),mlist);
        }
    }

    @Override
    public void onAddPhoto(int num) {
        PickPhotoPopupWindow.getInstance().show(llAddPhoto, isCanVideo, this);
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
        if (mlist.size() < (maxSize+1)) {
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
        if (mlist.size() < (maxSize+1)) {
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
        int num = (maxSize+1) - mlist.size();
        PhotoSelectContainer.setMaxSize(maxSize);
        Intent intent = new Intent(AddCirclePhotoActivity.this, AtomAlbumActivity.class);
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
                            Toasty.error(AddCirclePhotoActivity.this, getResources().getString(R.string.pick_photo_error), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            if (requestCode == PICK_CODE) {
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
                            Toasty.error(AddCirclePhotoActivity.this, getResources().getString(R.string.pick_photo_error), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onSuccess() {
        hud.dismiss();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onError(String message) {
        hud.dismiss();
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }
}
