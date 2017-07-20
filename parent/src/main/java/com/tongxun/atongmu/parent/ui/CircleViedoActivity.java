package com.tongxun.atongmu.parent.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.MediaMetadataRetriever;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.dialog.CommonDialog;
import com.tongxun.atongmu.parent.util.SDCardUtil;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileCallback;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircleViedoActivity extends BaseActivity implements View.OnClickListener, MediaRecorder.OnErrorListener {
    @BindView(R.id.tv_homework_back)
    TextView tvHomeworkBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_homework_commit)
    TextView tvHomeworkCommit;


    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private ProgressBar mProgressBar;
    private Button shoot_button;
    private ImageView circle_light_btn;
    private ImageView circle_change_camera_btn;
    private TextView circle_camera_time;

    private MediaRecorder mMediaRecorder;
    private Camera mCamera;
    private Timer mTimer;// 计时器
    private OnRecordFinishListener mOnRecordFinishListener;// 录制完成回调接口
    private int mWidth;// 视频分辨率宽度
    private int mHeight;// 视频分辨率高度
    private boolean isOpenCamera;// 是否一开始就打开摄像头
    private int mRecordMaxTime;// 一次拍摄最长时间
    private int mTimeCount;// 时间计数
    private int mNumberOfCameras = 0;//手机摄像头的数量
    Bitmap bitmap;//图片缩略图
    private int screenWidth;
    private File mVecordFile = null;// 文件
    private boolean isOpenFlash = false;
    private boolean isBackCamera = true;
    private int mbackCamera;
    private int mfrontCamera;
    private CommonDialog commonDialog;

    private static final int MOVICE_SUCCESS = 1000;//录制完成
    private static final int MOVICE_FILE = 1001;//录制失败
    private static final String TAG = "CircleViedoActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_video);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorWhite);
        init_datas();
        init_view();
        set_datas();
        init_event();

    }

    private void init_datas() {
        isOpenCamera = true;//默认一开始就打开相机
        mRecordMaxTime = 10;//设置录制的时间

        //获取手机摄像头的数量
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        mNumberOfCameras = Camera.getNumberOfCameras();
        for (int camidx = 0; camidx < mNumberOfCameras; camidx++) {
            Camera.getCameraInfo(camidx, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                mbackCamera = camidx;
            } else if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                mfrontCamera = camidx;
            }
        }


    }

    private void init_view() {
        mWidth = 640;
        mHeight = 480;

        //获取屏幕的宽度
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceview);//预览界面
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);//进度条
        shoot_button = (Button) findViewById(R.id.shoot_button);//拍摄按钮
        circle_camera_time = (TextView) findViewById(R.id.circle_camera_time);
        circle_change_camera_btn = (ImageView) findViewById(R.id.circle_change_camera_btn);//切换摄像头
        circle_light_btn = (ImageView) findViewById(R.id.circle_light_btn);//开启闪光灯
        ViewGroup.LayoutParams params = mSurfaceView.getLayoutParams();
        params.height = (screenWidth * 4) / 3;
        params.width = screenWidth;
        mSurfaceView.setLayoutParams(params);

    }


    private void set_datas() {
        mSurfaceHolder = mSurfaceView.getHolder();
        mProgressBar.setMax(mRecordMaxTime);// 设置进度条最大量
        mSurfaceHolder.setKeepScreenOn(true);//设置屏幕常亮
        mSurfaceHolder.addCallback(new CustomCallBack());
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        circle_camera_time.setText("");
        tvHomeworkBack.setText(getResources().getString(R.string.cancel));
        tvHomeworkCommit.setText(getResources().getString(R.string.confirm));
        tvTitleName.setText("");
    }

    private void init_event() {
        tvHomeworkBack.setOnClickListener(this);
        tvHomeworkCommit.setOnClickListener(this);
        circle_light_btn.setOnClickListener(this);
        circle_change_camera_btn.setOnClickListener(this);
        shoot_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    try {
                        circle_change_camera_btn.setVisibility(View.INVISIBLE);
                        circle_light_btn.setVisibility(View.INVISIBLE);
                        record(new OnRecordFinishListener() {
                            @Override
                            public void onRecordFinish() {
                                //录制时间达到最大值
                                handler.sendEmptyMessage(MOVICE_SUCCESS);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        finish();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    circle_change_camera_btn.setVisibility(View.VISIBLE);
                    circle_light_btn.setVisibility(View.VISIBLE);
                    if (mTimeCount > 1 && mTimeCount < 10) { //防止达到最大值up事件
                        //录制时间大于一秒
                        handler.sendEmptyMessage(MOVICE_SUCCESS);
                    } else if (mTimeCount <= 1) {
                        //删除小于一秒的视频
                        if (getmVecordFile() != null) {
                            getmVecordFile().delete();
                        }
                        handler.sendEmptyMessage(MOVICE_FILE);

                    }
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCamera != null) {
            freeCameraResource();
        }
        try {
            initCamera();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //   mRecorderView.stop();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //停止拍摄
            stopRecord();
            releaseRecord();
            switch (msg.what) {
                case MOVICE_SUCCESS:
                    String msgs = "确认上传吗?";
                    commonDialog = new CommonDialog(CircleViedoActivity.this, msgs, getResources().getString(R.string.confirm), getResources().getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
                        @Override
                        public void go() {
                            finishActivity();
                            commonDialog.dismiss();
                        }

                        @Override
                        public void cancel() {
                            //删除没有上传的视频
                            if (getmVecordFile() != null) {
                                getmVecordFile().delete();
                            }
                            try {
                                initCamera();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            commonDialog.dismiss();
                        }
                    });
                    commonDialog.show();


                    break;
                case MOVICE_FILE:
                    Toast.makeText(CircleViedoActivity.this, "视频录制时间太短", Toast.LENGTH_SHORT).show();
                    try {
                        initCamera();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
    };

    private void finishActivity() {
        stop();
        Bitmap bitmap = null;
        if (getmVecordFile() != null) {
            //得到文件 File类型
            File mfile = getmVecordFile();
            bitmap = createVideoThumbnail(mfile.toString());

        }
        if (bitmap == null) {

        }
        Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
        Tiny.getInstance().source(bitmap).asFile().withOptions(options).compress(new FileCallback() {
            @Override
            public void callback(boolean isSuccess, String outfile) {
                Intent intent = new Intent();
                intent.putExtra("videoUrl", getmVecordFile().getAbsolutePath());
                if (isSuccess) {
                    intent.putExtra("imgUrl", outfile);
                } else {
                    intent.putExtra("imgUrl", "");
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    @Override
    public void onError(MediaRecorder mr, int what, int extra) {
        try {
            if (mr != null)
                mr.reset();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.circle_light_btn:
                //开启关闭闪光灯 默认关闭
                if (isOpenFlash) {
                    isOpenFlash = false;
                    circle_light_btn.setImageResource(R.drawable.camera_no_light);
                } else {
                    isOpenFlash = true;
                    circle_light_btn.setImageResource(R.drawable.camera_light);
                }
                try {
                    initCamera();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.circle_change_camera_btn:
                if (isBackCamera) {
                    isBackCamera = false;
                } else {
                    isBackCamera = true;
                }
                try {
                    initCamera();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_homework_commit:
                stop();
                finish();
                break;
        }
    }

    private class CustomCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (!isOpenCamera)
                return;
            try {
                initCamera();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }

    /**
     * 初始化摄像头
     */
    private void initCamera() throws IOException {
        if (mCamera != null) {
            freeCameraResource();
        }
        try {
            if (isBackCamera) {
                mCamera = Camera.open(mbackCamera);//打开后摄像头
                setCameraParams(isOpenFlash);
            } else {
                mCamera = Camera.open(mfrontCamera);//打开前摄像头
            }
        } catch (Exception e) {
            e.printStackTrace();
            freeCameraResource();
        }
        if (mCamera == null)
            return;
        if (isBackCamera) {
            mCamera.setDisplayOrientation(90);
        } else {
            mCamera.setDisplayOrientation(90);
        }
        mCamera.setPreviewDisplay(mSurfaceHolder);
        mCamera.startPreview();
        mCamera.unlock();
    }


    /**
     * 设置摄像头为竖屏
     */
    private void setCameraParams(Boolean isOpenFlash) {
        if (mCamera != null) {
            Camera.Parameters params = mCamera.getParameters();
            //获取预览的各种分辨率
            List<Camera.Size> supportedPreviewSizes = params.getSupportedPreviewSizes();
            params.set("orientation", "portrait");//竖屏录制
            params.setPreviewSize(mWidth, mHeight);//默认640*480
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//持续对焦
            if (isBackCamera) {
                if (isOpenFlash) {
                    //开启闪光灯
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);

                } else {
                    //关闭闪光灯
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                }

            } else {

            }
            mCamera.setParameters(params);
        }
    }


    /**
     * 释放摄像头资源
     */
    private void freeCameraResource() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.lock();
            mCamera.release();
            mCamera = null;
        }
    }

    private void createRecordDir() {
        mVecordFile=new File(SDCardUtil.getInstance().getFilePath()+ UUID.randomUUID()+".mp4");
    }

    /**
     * 初始化
     */
    private void initRecord() throws IOException {
        mMediaRecorder = new MediaRecorder();
        mMediaRecorder.reset();
        if (mCamera != null)
            mMediaRecorder.setCamera(mCamera);
        mMediaRecorder.setOnErrorListener(this);
        mMediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);// 视频源
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 音频源
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);// 视频输出格式
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);// 音频格式
        mMediaRecorder.setVideoSize(mWidth, mHeight);// 设置分辨率：
        // mMediaRecorder.setVideoFrameRate(16);// 这个我把它去掉了，感觉没什么用
        mMediaRecorder.setVideoEncodingBitRate(2 * 1024 * 512);// 设置帧频率，然后就清晰了
        if (isBackCamera) {
            mMediaRecorder.setOrientationHint(90);// 输出旋转90度，保持竖屏录制
        } else {
            mMediaRecorder.setOrientationHint(270);
        }

        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);// 视频录制格式
        // mediaRecorder.setMaxDuration(Constant.MAXVEDIOTIME * 1000);
        mMediaRecorder.setOutputFile(mVecordFile.getAbsolutePath());
        mMediaRecorder.prepare();
        try {
            mMediaRecorder.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 录制完成回调接口
     */
    public interface OnRecordFinishListener {
        public void onRecordFinish();
    }

    /**
     * 录制
     *
     * @param onRecordFinishListener
     */
    public void record(final OnRecordFinishListener onRecordFinishListener) {
        this.mOnRecordFinishListener = onRecordFinishListener;
        createRecordDir();//创建目录

        try {
            if (!isOpenCamera)// 如果未打开摄像头，则打开
                initCamera();//初始化摄像头
            initRecord();//初始化录制参数
            mTimeCount = 0;// 时间计数器重新赋值

            mTimer = new Timer();//创建一个定时器

            mTimer.schedule(new TimerTask() {

                @Override
                public void run() {

                    mTimeCount++;
                    mProgressBar.setProgress(mTimeCount);// 设置进度条
                    //  handler.sendEmptyMessage(TIME_CHANGW);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            circle_camera_time.setText(mTimeCount + "″");
                        }
                    });
                    if (mTimeCount == mRecordMaxTime) {// 达到指定时间，停止拍摄
                        //录制完成调用录制回调接口
                        if (mOnRecordFinishListener != null)
                            mOnRecordFinishListener.onRecordFinish();
                    }
                }
            }, 0, 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止拍摄
     */
    public void stop() {
        stopRecord();
        releaseRecord();
        freeCameraResource();
    }

    /**
     * 停止录制
     */
    public void stopRecord() {
        mProgressBar.setProgress(0);
        circle_camera_time.setText("");
        if (mTimer != null)
            mTimer.cancel();
        if (mMediaRecorder != null) {
            // 设置后不会崩
            mMediaRecorder.setOnErrorListener(null);
            mMediaRecorder.setPreviewDisplay(null);
            try {
                mMediaRecorder.stop();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (RuntimeException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取的视频的位置
     *
     * @return
     */
    public File getmVecordFile() {
        return mVecordFile;
    }

    /**
     * 释放资源
     */
    private void releaseRecord() {
        if (mMediaRecorder != null) {
            mMediaRecorder.setOnErrorListener(null);
            try {
                mMediaRecorder.release();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mMediaRecorder = null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            stop();
            finish();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    /**
     * 获取视频缩略图
     *
     * @param filePath
     * @return
     */
    public Bitmap getVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public static Bitmap createVideoThumbnail(String filePath) {
        // MediaMetadataRetriever is available on API Level 8
        // but is hidden until API Level 10
        Class<?> clazz = null;
        Object instance = null;
        try {
            clazz = Class.forName("android.media.MediaMetadataRetriever");
            instance = clazz.newInstance();

            Method method = clazz.getMethod("setDataSource", String.class);
            method.invoke(instance, filePath);

            // The method name changes between API Level 9 and 10.
            if (Build.VERSION.SDK_INT <= 9) {
                return (Bitmap) clazz.getMethod("captureFrame").invoke(instance);
            } else {
                byte[] data = (byte[]) clazz.getMethod("getEmbeddedPicture").invoke(instance);
                if (data != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    if (bitmap != null) return bitmap;
                }
                return (Bitmap) clazz.getMethod("getFrameAtTime").invoke(instance);
            }
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
        } catch (InstantiationException e) {
            Log.e(TAG, "createVideoThumbnail", e);
        } catch (InvocationTargetException e) {
            Log.e(TAG, "createVideoThumbnail", e);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "createVideoThumbnail", e);
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "createVideoThumbnail", e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "createVideoThumbnail", e);
        } finally {
            try {
                if (instance != null) {
                    clazz.getMethod("release").invoke(instance);
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap != null) {
            try {
                bitmap.recycle();
            } catch (Exception e) {
                e.printStackTrace();
            }
            bitmap = null;
        }
    }
}
