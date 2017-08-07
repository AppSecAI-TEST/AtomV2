package com.tongxun.atongmu.parent;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.ImageView;

import java.io.IOException;

/**
 * Created by Anro on 2017/7/19.
 */

public class VoicePlayListener {

    private MediaPlayer mediaPlayer = null;

    private Context mContext;

    private static boolean isPlaying=false;

    private ImageView mImageView;

    private AnimationDrawable voiceAnimation = null;
    public static String  oldUrl="";

    private static VoicePlayListener listener;

    public VoicePlayListener(Context context, ImageView imageView) {
        mContext=context;
        mImageView=imageView;
    }

    public void playVoice(Uri uri) throws IOException {
        showAnimation();
        mediaPlayer=new MediaPlayer();
        mediaPlayer.reset();

        mediaPlayer.setDataSource(mContext,uri);
        mediaPlayer.prepare();
        mediaPlayer.start();
        isPlaying=true;
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
                mediaPlayer=null;
                isPlaying=false;
                stopPlayVoice();
            }
        });
        listener=this;
    }

    private void showAnimation() {
        mImageView.setImageResource(R.drawable.voice_anim);
        voiceAnimation= (AnimationDrawable) mImageView.getDrawable();
        voiceAnimation.start();
    }

    private void stopPlayVoice() {
        voiceAnimation.stop();
        mImageView.setImageResource(R.drawable.icon_voice_level3);
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        isPlaying=false;
    }


    public void onClick(String url,Uri uri) throws IOException {
        if(isPlaying){
            if(oldUrl==url){
                listener.stopPlayVoice();
                return;
            }
            listener.stopPlayVoice();
        }

        playVoice(uri);
        oldUrl=url;

    }

    public static void stop(){
        if(isPlaying){
            listener.stopPlayVoice();
        }
    }
}
