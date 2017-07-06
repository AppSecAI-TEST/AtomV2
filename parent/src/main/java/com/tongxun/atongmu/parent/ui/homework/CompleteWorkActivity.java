package com.tongxun.atongmu.parent.ui.homework;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.util.SDCardUtil;
import com.tongxun.atongmu.parent.util.SystemUtil;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileCallback;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class CompleteWorkActivity extends Base2Activity<IComepleteWorkContract.View, CompleteWorkPresenter> implements IComepleteWorkContract.View {

    @BindView(R.id.tv_homework_back)
    TextView tvHomeworkBack;
    @BindView(R.id.tv_homework_commit)
    TextView tvHomeworkCommit;
    @BindView(R.id.et_complete_homework)
    EditText etCompleteHomework;
    @BindView(R.id.iv_voice)
    ImageView ivVoice;
    @BindView(R.id.iv_camera)
    ImageView ivCamera;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.iv_video)
    ImageView ivVideo;
    @BindView(R.id.tv_font_size)
    TextView tvFontSize;
    @BindView(R.id.iv_voice_delete)
    ImageView ivVoiceDelete;
    @BindView(R.id.ll_voice)
    LinearLayout llVoice;
    @BindView(R.id.video_line)
    View videoLine;
    @BindView(R.id.iv_video_holder)
    ImageView ivVideoHolder;
    @BindView(R.id.iv_video_delete)
    ImageView ivVideoDelete;
    @BindView(R.id.iv_video_play)
    ImageView ivVideoPlay;
    @BindView(R.id.ll_video)
    LinearLayout llVideo;
    @BindView(R.id.photo_line)
    View photoLine;
    @BindView(R.id.rv_homework_photo)
    RecyclerView rvHomeworkPhoto;

    private String fileName="";
    private static int REQUEST_CODE=0x110;

    private Set<String> fileSet=new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_work);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorWhite);

    }

    @Override
    protected CompleteWorkPresenter initPresenter() {
        return new CompleteWorkPresenter();
    }

    @OnClick({R.id.tv_homework_back, R.id.tv_homework_commit, R.id.iv_voice, R.id.iv_camera, R.id.iv_photo, R.id.iv_video, R.id.iv_voice_delete, R.id.iv_video_holder, R.id.iv_video_delete, R.id.iv_video_play})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_homework_back:
                finish();
                break;
            case R.id.tv_homework_commit:
                mPresenter.commitHomework();
                break;
            case R.id.iv_voice:
                showVoiceDialog();
                break;
            case R.id.iv_camera:
                openSystemCamera();
                break;
            case R.id.iv_photo:
                openLoaclPhoto();
                break;
            case R.id.iv_video:
                openVideoRecord();
                break;
            case R.id.iv_voice_delete:
                deleteVioce();
                break;
            case R.id.iv_video_holder:
                playVideo();
                break;
            case R.id.iv_video_delete:
                deleteVideo();
                break;
            case R.id.iv_video_play:
                playVideo();
                break;
        }
    }

    /**
     * 删除视频
     */
    private void deleteVideo() {

    }

    /**
     * 播放视频
     */
    private void playVideo() {

    }

    /**
     * 删除已存在的音频
     */
    private void deleteVioce() {

    }

    /**
     * 打开视频录制
     */
    private void openVideoRecord() {

    }

    /**
     * 打开本地相册
     */
    private void openLoaclPhoto() {

    }

    /**
     * 打开系统相机
     */
    private void openSystemCamera() {
        PermissionGen.with(this)
                .addRequestCode(Constants.PERMISSION_CAMERA_CODE)
                .permissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .request();
    }

    /**
     * 音频录制对话框
     */
    private void showVoiceDialog() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    /**
     * 获取相机权限后打开系统相机
     */
    @PermissionSuccess(requestCode = 100)
    public void doCamera(){
        fileName= UUID.randomUUID()+".jpg";
        SystemUtil.opSystemCamera(this, SDCardUtil.getInstance().getFilePath()+fileName,REQUEST_CODE);
    }

    @PermissionFail(requestCode =100)
    public void doSomeError(){
        Toasty.error(this, "系统相机权限获取失败", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if (requestCode==REQUEST_CODE){
                Tiny.FileCompressOptions options=new Tiny.FileCompressOptions();
                Tiny.getInstance().source(SDCardUtil.getInstance().getFilePath()+fileName).asFile().withOptions(options).compress(new FileCallback() {
                    @Override
                    public void callback(boolean isSuccess, String outfile) {
                        if(isSuccess){
                            if(!fileSet.contains(outfile)){
                                fileSet.add(outfile);
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }
}
