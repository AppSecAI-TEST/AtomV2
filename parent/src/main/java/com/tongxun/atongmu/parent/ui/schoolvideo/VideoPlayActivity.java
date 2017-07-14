package com.tongxun.atongmu.parent.ui.schoolvideo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.util.SDCardUtil;
import com.videogo.openapi.EZConstants;
import com.videogo.openapi.EZPlayer;
import com.videogo.realplay.RealPlayStatus;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

import static com.tongxun.atongmu.parent.R.id.iv_continue_play;
import static com.tongxun.atongmu.parent.R.id.tv_loading;
import static com.tongxun.atongmu.parent.application.ParentApplication.getOpenSDK;

public class VideoPlayActivity extends BaseActivity implements SurfaceHolder.Callback, Handler.Callback {

    @BindView(R.id.sfv_video_play)
    SurfaceView sfvVideoPlay;
    @BindView(tv_loading)
    TextView tvLoading;
    @BindView(iv_continue_play)
    ImageView ivContinuePlay;
    @BindView(R.id.activity_video_play)
    RelativeLayout activityVideoPlay;

    private SurfaceHolder mRealPlaySh = null;
    private static Handler mHandler = null;

    private EZPlayer mEZPlayer = null;

    private int mStatus = RealPlayStatus.STATUS_INIT;

    private String fileName;

    private String derviceId;
    private String channelNo;
    private String endDate;
    private String nowDate;
    private String videoClarity;
    private String verificationCode;

    private TimerTask task = null;

    private Timer timer = null;

    private TimerTask timerTask = null;
    private Timer timerclose = null;

    private SimpleDateFormat formatss = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    /**
     * 监听锁屏解锁的事件
     */
    private RealPlayBroadcastReceiver mBroadcastReceiver = null;

    public static final int MSG_SET_VEDIOMODE_SUCCESS = 105;
    public static final int MSG_SET_VEDIOMODE_FAIL = 106;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mHandler = new Handler(this);
        Intent intent = getIntent();
        try {
            derviceId = intent.getStringExtra("derviceID");
            channelNo = intent.getStringExtra("channelNo");
            endDate = intent.getStringExtra("endDate");
            nowDate = intent.getStringExtra("nowDate");
            videoClarity = intent.getStringExtra("videoClarity");
            verificationCode = intent.getStringExtra("verificationCode");

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (derviceId == null || derviceId.equals("")) {
            Toasty.error(this, getResources().getString(R.string.device_offline), Toast.LENGTH_SHORT).show();
            finish();
        }
        fileName = derviceId + channelNo + ".jpg";

        mBroadcastReceiver = new RealPlayBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_USER_PRESENT);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mBroadcastReceiver, filter);

        // 保持屏幕常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        timerClose();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (videoClarity.equals("均衡")) {
                        getOpenSDK().setVideoLevel(derviceId, Integer.parseInt(channelNo), 1);
                    } else if (videoClarity.equals("高清")) {
                        getOpenSDK().setVideoLevel(derviceId, Integer.parseInt(channelNo), 2);
                    } else {
                        getOpenSDK().setVideoLevel(derviceId, Integer.parseInt(channelNo), 0);
                    }
                    Message msg = Message.obtain();
                    msg.what = MSG_SET_VEDIOMODE_SUCCESS;
                    mHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();


        mRealPlaySh = sfvVideoPlay.getHolder();
        mRealPlaySh.addCallback(this);
    }


    private void timerClose() {
        if (timerclose == null) {
            timerclose = new Timer();
        }
        Calendar calendar = Calendar.getInstance();
        try {
            Date nowdate = formatss.parse(nowDate);
            Date enddate = format.parse(endDate);
            calendar.setTime(nowdate);
            int nowsecond = calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 + calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND);
            calendar.setTime(enddate);
            int endsecond = calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 + calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND);
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            onCapturePicBtnClick(true);
                            Toast.makeText(VideoPlayActivity.this, getResources().getString(R.string.video_device_time_error), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };
            timerclose.schedule(timerTask, (endsecond - nowsecond) * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnClick(iv_continue_play)
    public void onViewClicked() {
        ivContinuePlay.setVisibility(View.GONE);
        if (mEZPlayer != null) {
            tvLoading.setText(getResources().getString(R.string.loading));
            mEZPlayer.startRealPlay();
        } else {
            startRealPlay();
        }
    }

    /**
     * screen状态广播接收者
     */
    private class RealPlayBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
                if (mStatus != RealPlayStatus.STATUS_STOP) {
                    stopRealPlay();
                    mStatus = RealPlayStatus.STATUS_STOP;
                }
            }
        }
    }

    private void resetTimer() {
        if (task != null) {
            task.cancel();
        }
        if (timer == null) {
            timer = new Timer();
        }

        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onCapturePicBtnClick(false);
                        stopRealPlay();
                        ivContinuePlay.setVisibility(View.VISIBLE);
                        tvLoading.setVisibility(View.VISIBLE);
                        tvLoading.setText(getResources().getString(R.string.continue_play));
                    }
                });
            }
        };
        timer.schedule(task, 1000 * 5 * 60);


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mStatus == RealPlayStatus.STATUS_PLAY) {
                onCapturePicBtnClick(true);
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void onCapturePicBtnClick(final boolean isExit) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bmp = mEZPlayer.capturePicture();
                try {
                    if (bmp != null) {
                        SDCardUtil.getInstance().changeBitmapToFile(bmp, fileName);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (isExit) {
                    if (bmp != null) {
                        try {
                            bmp.recycle();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        bmp = null;
                    }
                    setResult(RESULT_OK, new Intent());
                    finish();
                }
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 开始播放
        startRealPlay();
    }

    /**
     * 停止播放
     *
     * @see
     * @since V1.0
     */
    private void stopRealPlay() {
        mStatus = RealPlayStatus.STATUS_STOP;
        if (mEZPlayer != null) {
            mEZPlayer.stopRealPlay();
        }
    }

    private void startRealPlay() {
        if (mEZPlayer == null) {
            mEZPlayer = ParentApplication.getOpenSDK().createPlayer(derviceId, Integer.parseInt(channelNo));
        }
        if (mEZPlayer == null)
            return;
        mEZPlayer.setPlayVerifyCode(verificationCode);
        mEZPlayer.setHandler(mHandler);
        mEZPlayer.setSurfaceHold(mRealPlaySh);
        mEZPlayer.startRealPlay();
        tvLoading.setText(getResources().getString(R.string.loading));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mEZPlayer != null) {
            mEZPlayer.release();
        }
        if (task != null) {
            try {
                task.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
            task = null;
        }

        if (timerTask != null) {
            try {
                timerTask.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
            timerTask = null;
        }

        if (timer != null) {
            try {
                timer.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
            timer = null;
        }
        if (timerclose != null) {
            try {
                timerclose.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
            timerclose = null;
        }
        mHandler = null;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mEZPlayer != null) {
            mEZPlayer.setSurfaceHold(holder);
        }
        mRealPlaySh = holder;
        mHandler = null;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mEZPlayer != null) {
            mEZPlayer.setSurfaceHold(null);
        }
        mRealPlaySh = null;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case EZConstants.EZRealPlayConstants.MSG_REALPLAY_PLAY_SUCCESS:
                handlePlaySuccess(msg);
                break;
            case EZConstants.EZRealPlayConstants.MSG_REALPLAY_PLAY_FAIL:
                handlePlayFail(msg.obj);
                break;
            case EZConstants.EZRealPlayConstants.MSG_SET_VEDIOMODE_SUCCESS:
                handleSetVedioModeSuccess();
                break;
            case EZConstants.EZRealPlayConstants.MSG_SET_VEDIOMODE_FAIL:
                handleSetVedioModeFail();
                break;
        }
        return false;
    }

    private void handleSetVedioModeFail() {

    }

    private void handleSetVedioModeSuccess() {
        if (mStatus == RealPlayStatus.STATUS_PLAY) {
            // 停止播放
            stopRealPlay();
            //下面语句防止stopRealPlay线程还没释放surface, startRealPlay线程已经开始使用surface
            //因此需要等待500ms
            SystemClock.sleep(500);
            // 开始播放
            startRealPlay();
        }
    }

    private void handlePlayFail(Object obj) {
        mStatus = RealPlayStatus.STATUS_STOP;
        ivContinuePlay.setVisibility(View.VISIBLE);
        tvLoading.setText(getResources().getString(R.string.video_play_error));
    }

    private void handlePlaySuccess(Message msg) {
        mStatus = RealPlayStatus.STATUS_PLAY;
        tvLoading.setVisibility(View.INVISIBLE);
        resetTimer();
    }
}
