package com.tongxun.atongmu.parent.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CircleVideoPlayActivity extends BaseActivity {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.nice_video_play)
    NiceVideoPlayer niceVideoPlay;

    private String videoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_video_play);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        videoUrl=intent.getStringExtra("videoUrl");
        niceVideoPlay.setVisibility(View.VISIBLE);
        niceVideoPlay.setPlayerType(NiceVideoPlayer.PLAYER_TYPE_IJK);
        niceVideoPlay.setUp(videoUrl,null);
        TxVideoPlayerController controller = new TxVideoPlayerController(this);
        controller.setTitle("");
        controller.setImage(R.drawable.video_black_shape);
        niceVideoPlay.setController(controller);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(niceVideoPlay!=null){
            try {
                niceVideoPlay.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @OnClick(R.id.iv_title_back)
    public void onViewClicked() {
        finish();
    }

    public static void  startActivity(Context context,String videoUrl){
        Intent intent=new Intent(context,CircleVideoPlayActivity.class);
        intent.putExtra("videoUrl",videoUrl);
        context.startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 在 onStop 时释放掉播放器
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    public void onBackPressed() {
        // 在全屏或者小窗口时按返回键要先退出全屏或小窗口，
        // 所以在Activity中onBackPress要交给NiceVideoPlayer先处理。
        if (NiceVideoPlayerManager.instance().onBackPressd()) return;
        super.onBackPressed();
    }
}
