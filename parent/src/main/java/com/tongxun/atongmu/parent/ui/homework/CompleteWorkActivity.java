package com.tongxun.atongmu.parent.ui.homework;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.piasy.rxandroidaudio.AudioRecorder;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.PickPhotoAdapter;
import com.tongxun.atongmu.parent.dialog.AudioDialog;
import com.tongxun.atongmu.parent.dialog.CommonDialog;
import com.tongxun.atongmu.parent.ui.AtomAlbumActivity;
import com.tongxun.atongmu.parent.ui.CircleViedoActivity;
import com.tongxun.atongmu.parent.ui.PhotoSelectContainer;
import com.tongxun.atongmu.parent.util.DividerGridItemDecoration;
import com.tongxun.atongmu.parent.util.GlideOption;
import com.tongxun.atongmu.parent.util.SDCardUtil;
import com.tongxun.atongmu.parent.util.SystemUtil;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileBatchCallback;
import com.zxy.tiny.callback.FileCallback;

import java.io.File;
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

public class CompleteWorkActivity extends Base2Activity<IComepleteWorkContract.View, CompleteWorkPresenter> implements IComepleteWorkContract.View, PickPhotoAdapter.PickListener {


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
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_audio_duration)
    TextView tvAudioDuration;
    @BindView(R.id.rv_homework_photo)
    RecyclerView rvHomeworkPhoto;
    @BindView(R.id.iv_audio_anim)
    ImageView ivAudioAnim;
    @BindView(R.id.ll_audio)
    LinearLayout llAudio;

    private String fileName = "";
    private static final int REQUEST_CODE = 0x110;
    private static final int PICK_CODE = 0x111;
    private static final int VIDEO_RECORD = 0x112;

    private List<String> filelist = new ArrayList<>();

    private String jobID = "";

    private KProgressHUD hud;

    private AudioDialog audioDialog;

    private PickPhotoAdapter mAdapter;

    private boolean isCanVoice = true;
    private boolean isCanCamera = true;
    private boolean isCanPhoto = true;
    private boolean isCanVideo = true;

    private String videoUrl;
    private String videoImage;

    private AudioRecorder mAudioRecorder;
    private File mAudioFile;

    private MediaPlayer mp;

    private boolean isPlay=false;

    private CommonDialog commonDialog;

    private AnimationDrawable voiceAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_work);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorWhite);
        Intent intent = getIntent();
        try {
            jobID = intent.getStringExtra("jobID");
        } catch (Exception e) {
            e.printStackTrace();
        }

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);

        setRecyclerView();
        setIconStatus();

        etCompleteHomework.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvFontSize.setText(s.toString().length() + "/400");
            }
        });
    }

    /**
     * 设置按钮状态
     */
    private void setIconStatus() {
        if (isCanVoice) {
            ivVoice.setSelected(true);
        } else {
            ivVoice.setSelected(false);
        }

        if (isCanCamera) {
            ivCamera.setSelected(true);
        } else {
            ivCamera.setSelected(false);
        }

        if (isCanPhoto) {
            ivPhoto.setSelected(true);
        } else {
            ivPhoto.setSelected(false);
        }

        if (isCanVideo) {
            ivVideo.setSelected(true);
        } else {
            ivVideo.setSelected(false);
        }
    }

    private void setRecyclerView() {
        rvHomeworkPhoto.setItemAnimator(new DefaultItemAnimator());
        rvHomeworkPhoto.setLayoutManager(new GridLayoutManager(this, 4));
        rvHomeworkPhoto.addItemDecoration(new DividerGridItemDecoration(this));
        mAdapter = new PickPhotoAdapter(CompleteWorkActivity.this, filelist, this);
        rvHomeworkPhoto.setAdapter(mAdapter);
    }

    @Override
    protected CompleteWorkPresenter initPresenter() {
        return new CompleteWorkPresenter();
    }

    @OnClick({R.id.tv_homework_back, R.id.tv_homework_commit, R.id.ll_audio, R.id.iv_voice, R.id.iv_camera, R.id.iv_photo, R.id.iv_video, R.id.iv_voice_delete, R.id.iv_video_holder, R.id.iv_video_delete, R.id.iv_video_play})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_homework_back:
                if(etCompleteHomework.getText().toString().length()>0 || mAudioFile!=null || !TextUtils.isEmpty(videoUrl) || filelist.size()>0){
                    commonDialog = new CommonDialog(CompleteWorkActivity.this, getResources().getString(R.string.give_up),getResources().getString(R.string.confirm),getResources().getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
                        @Override
                        public void go() {
                            finish();
                        }

                        @Override
                        public void cancel() {
                            commonDialog.dismiss();
                        }
                    });
                    commonDialog.show();
                }else {
                    finish();
                }

                break;
            case R.id.tv_homework_commit:
                commitHomework();
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
            case R.id.ll_audio:
                playAudio();
                break;
        }
    }

    private void commitHomework() {
        if(mAudioFile!=null){
            if(mAudioFile.exists()){
                mPresenter.commitHomework(etCompleteHomework.getText().toString(),filelist,true,mAudioFile.getPath(),mAudioFile.getName());
            }
            return;
        }
        if(!TextUtils.isEmpty(videoUrl)){
            File file=new File(videoUrl);
            if(file.exists()){
                mPresenter.commitHomework(etCompleteHomework.getText().toString(),filelist,true,file.getPath(),file.getName());
            }
            return;
        }
        mPresenter.commitHomework(etCompleteHomework.getText().toString(),filelist,false,mAudioFile.getPath(),mAudioFile.getName());
    }

    /**
     * 播放音频
     */
    private void playAudio() {
        ivAudioAnim.setImageResource(R.drawable.voice_anim);
        voiceAnimation= (AnimationDrawable) ivAudioAnim.getDrawable();

        if(mp==null){
            mp=MediaPlayer.create(CompleteWorkActivity.this, Uri.fromFile(mAudioFile));
        }
        if(!isPlay){
            isPlay=true;
            mp.start();
            voiceAnimation.start();
        }else {
            mp.pause();
            isPlay=false;
            voiceAnimation.stop();
            ivAudioAnim.setImageResource(R.drawable.icon_voice_level3);
        }
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                isPlay=false;
                voiceAnimation.stop();
                ivAudioAnim.setImageResource(R.drawable.icon_voice_level3);
            }
        });

    }

    /**
     * 删除视频
     */
    private void deleteVideo() {
        llVideo.setVisibility(View.GONE);
        videoLine.setVisibility(View.GONE);
        videoUrl = "";
        videoImage = "";
        isCanVoice = true;
        isCanCamera = true;
        isCanPhoto = true;
        isCanVideo = true;
        setIconStatus();
    }

    /**
     * 播放视频
     */
    private void playVideo() {
        SystemUtil.openSystemVideo(this, videoUrl);

    }

    /**
     * 删除已存在的音频
     */
    private void deleteVioce() {
        if (mAudioFile.exists()) {
            mAudioFile.delete();
        }
        mAudioFile = null;
        llVoice.setVisibility(View.GONE);
    }

    /**
     * 打开视频录制
     */
    private void openVideoRecord() {
        if (isCanVideo) {
            PermissionGen.with(this)
                    .addRequestCode(Constants.PERMISSION_VIDEO_CODE)
                    .permissions(
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    .request();
        }
    }

    /**
     * 打开本地相册
     */
    private void openLoaclPhoto() {
        if (isCanPhoto) {
            if (filelist.size() < 9) {
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
    }

    /**
     * 如果照片数量少于九张
     * 打开系统相机
     */
    private void openSystemCamera() {
        if (isCanCamera) {
            if (filelist.size() < 9) {
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
    }

    /**
     * 音频录制对话框
     */
    private void showVoiceDialog() {
        PermissionGen.with(this)
                .addRequestCode(Constants.PERMISSION_AUDIO_CODE)
                .permissions(
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .request();
    }

    @Override
    public void showProgress() {
        hud.show();
    }

    @Override
    public void hideProgress() {
        hud.dismiss();
    }

    /**
     * 获取相机权限后打开系统相机
     */
    @PermissionSuccess(requestCode = 100)
    public void doCamera() {
        fileName = UUID.randomUUID() + ".jpg";
        SystemUtil.opSystemCamera(this, SDCardUtil.getInstance().getFilePath() + fileName, REQUEST_CODE);
    }

    @PermissionFail(requestCode = 100)
    public void doSomeError() {
        Toasty.error(this, getResources().getString(R.string.get_camera_permission_error), Toast.LENGTH_LONG).show();
    }

    @PermissionSuccess(requestCode = 102)
    public void doPhoto() {
        int num = 9 - filelist.size();
        PhotoSelectContainer.setMaxSize(num);
        Intent intent = new Intent(CompleteWorkActivity.this, AtomAlbumActivity.class);
        startActivityForResult(intent, PICK_CODE);
    }

    @PermissionFail(requestCode = 102)
    public void doPhotoError() {
        Toasty.error(this, getResources().getString(R.string.get_sd_permission_error), Toast.LENGTH_LONG).show();
    }


    @PermissionSuccess(requestCode = 103)
    public void doVideo() {
        Intent intent = new Intent(CompleteWorkActivity.this, CircleViedoActivity.class);
        startActivityForResult(intent, VIDEO_RECORD);
    }

    @PermissionFail(requestCode = 103)
    public void doVideoError() {
        Toasty.error(this, getResources().getString(R.string.get_camera_permission_error), Toast.LENGTH_LONG).show();
    }


    @PermissionSuccess(requestCode = 101)
    public void showAudioDialogSuccess() {
        audioDialog = new AudioDialog(CompleteWorkActivity.this, new IAudioRecordListener() {
            @Override
            public void startRecordAudio() {
                if (mAudioRecorder == null) {
                    mAudioRecorder = AudioRecorder.getInstance();
                }

                mAudioFile = new File(SDCardUtil.getInstance().getFilePath() + UUID.randomUUID() + ".m4a");
                mAudioRecorder.prepareRecord(MediaRecorder.AudioSource.MIC,
                        MediaRecorder.OutputFormat.MPEG_4, MediaRecorder.AudioEncoder.AAC,
                        mAudioFile);
                mAudioRecorder.startRecord();
            }

            @Override
            public void stopRecordAudio() {
                if (mAudioRecorder != null) {
                    mAudioRecorder.stopRecord();
                }

                if (mAudioFile.exists()) {
                    showAudioUI();
                }
                audioDialog.dismiss();
            }


        });
        audioDialog.show();
    }

    private void showAudioUI() {
        llVoice.setVisibility(View.VISIBLE);
        mp = MediaPlayer.create(CompleteWorkActivity.this, Uri.fromFile(mAudioFile));
        int duration = mp.getDuration();
        duration = duration / 1000;
        tvAudioDuration.setText(duration + "秒");
    }

    @PermissionFail(requestCode = 101)
    public void doAudioDialogError() {
        Toasty.error(this, getResources().getString(R.string.get_audio_permission_error), Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
                Tiny.getInstance().source(SDCardUtil.getInstance().getFilePath() + fileName).asFile().withOptions(options).compress(new FileCallback() {
                    @Override
                    public void callback(boolean isSuccess, String outfile) {
                        if (isSuccess) {
                            if (!filelist.contains(outfile)) {
                                filelist.add(outfile);
                            }
                            isCanVoice = true;
                            isCanCamera = true;
                            isCanPhoto = true;
                            isCanVideo = false;
                            setIconStatus();
                            mAdapter.notifyDataSetChanged();

                        } else {
                            Toasty.error(CompleteWorkActivity.this, getResources().getString(R.string.pick_photo_error), Toast.LENGTH_SHORT).show();
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
                            List<String> list = new ArrayList<String>();
                            list = Arrays.asList(outfile);
                            for (String str : list) {
                                if (!filelist.contains(str)) {
                                    filelist.add(str);
                                }
                            }
                            isCanVoice = true;
                            isCanCamera = true;
                            isCanPhoto = true;
                            isCanVideo = false;
                            setIconStatus();
                            mAdapter.notifyDataSetChanged();
                            hud.dismiss();
                        } else {
                            Toasty.error(CompleteWorkActivity.this, getResources().getString(R.string.pick_photo_error), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            if (requestCode == VIDEO_RECORD) {
                isCanVoice = false;
                isCanCamera = false;
                isCanPhoto = false;
                isCanVideo = true;
                setIconStatus();
                videoImage = data.getStringExtra("imgUrl");
                videoUrl = data.getStringExtra("videoUrl");
                llVideo.setVisibility(View.VISIBLE);
                videoLine.setVisibility(View.VISIBLE);
                Glide.with(this).load(videoImage).apply(GlideOption.getPHOption()).into(ivVideoHolder);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    /**
     * 删除选中的图片
     *
     * @param position
     */
    @Override
    public void delete(int position) {
        filelist.remove(position);
        if (filelist.size() == 0) {
            isCanVoice = true;
            isCanCamera = true;
            isCanPhoto = true;
            isCanVideo = true;
            setIconStatus();
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mp!=null){
            mp.release();
        }

    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

}
