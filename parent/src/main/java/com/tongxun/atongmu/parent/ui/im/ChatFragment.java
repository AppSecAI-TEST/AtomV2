package com.tongxun.atongmu.parent.ui.im;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseBaiduMapActivity;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.ui.AtomAlbumActivity;
import com.tongxun.atongmu.parent.ui.CircleViedoActivity;
import com.tongxun.atongmu.parent.ui.PhotoSelectContainer;
import com.tongxun.atongmu.parent.util.SDCardUtil;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileBatchCallback;

import java.io.File;
import java.util.UUID;

import es.dmoral.toasty.Toasty;

/**
 * Created by Anro on 2017/7/12.
 */

public class ChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper {

    private static final int ITEM_PHOTO = 922;
    private static final int ITEM_CAMERA = 52;
    private static final int ITEM_VIDEO = 908;
    private static final int ITEM_LOCATION = 580;
    private String filePath;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void setUpView() {
        setChatFragmentHelper(this);
        super.setUpView();

    }

    @Override
    protected void registerExtendMenuItem() {
       // super.registerExtendMenuItem();
        inputMenu.registerExtendMenuItem(R.string.photo,R.drawable.icon_im_photo,ITEM_PHOTO,extendMenuItemClickListener);
        inputMenu.registerExtendMenuItem(R.string.camera,R.drawable.icon_im_camera,ITEM_CAMERA,extendMenuItemClickListener);
        inputMenu.registerExtendMenuItem(R.string.small_video,R.drawable.icon_im_video,ITEM_VIDEO,extendMenuItemClickListener);
        inputMenu.registerExtendMenuItem(R.string.localtion,R.drawable.icon_im_localtion,ITEM_LOCATION,extendMenuItemClickListener);
    }

    /**
     * 底部Item点击事件
     * @param itemId
     * @param view
     * @return
     */
    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        switch (itemId){
            case ITEM_PHOTO:
                pick_photo();
                break;
            case ITEM_CAMERA:
                take_camera();
                break;
            case ITEM_VIDEO:
                take_video();
                break;
            case ITEM_LOCATION:
                startActivityForResult(new Intent(getActivity(), EaseBaiduMapActivity.class), REQUEST_CODE_MAP);
                break;
        }
        return false;
    }

    private void take_video() {
        Intent intent = new Intent(getActivity(), CircleViedoActivity.class);
        startActivityForResult(intent, Constants.VIDEO_RECORD);
    }

    private void take_camera() {
        filePath= SDCardUtil.getInstance().getFilePath()+ UUID.randomUUID()+".jpg";
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = Uri.fromFile(new File(filePath));
        //为拍摄的图片指定一个存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, Constants.CAMERA_RESULT);
    }

    private void pick_photo() {
        PhotoSelectContainer.setMaxSize(9);
        Intent intent = new Intent(getActivity(), AtomAlbumActivity.class);
        startActivityForResult(intent, Constants.PICK_CODE);
    }

    @Override
    public void onSetMessageAttributes(EMMessage message) {

    }

    @Override
    public void onEnterToChatDetails() {

    }

    @Override
    public void onAvatarClick(String username) {

    }

    @Override
    public void onAvatarLongClick(String username) {

    }

    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {

    }



    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==Activity.RESULT_OK){
            if(requestCode==Constants.CAMERA_RESULT){
                File file=new File(filePath);
                if(file.exists()){
                    sendImageMessage(file
                            .getAbsolutePath());
                }

            }
            if(requestCode==Constants.PICK_CODE){
                Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
                String[] strs = PhotoSelectContainer.getFileList().toArray(new String[PhotoSelectContainer.getFileList().size()]);
                Tiny.getInstance().source(strs).batchAsFile().withOptions(options).batchCompress(new FileBatchCallback() {
                    @Override
                    public void callback(boolean isSuccess, String[] outfile) {
                        if (isSuccess) {
                            PhotoSelectContainer.clear();
                            for(int i=0;i<outfile.length-1;i++){
                                sendImageMessage(outfile[i]);
                            }
                        } else {
                            Toasty.error(getActivity(), getResources().getString(R.string.pick_photo_error), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            if(requestCode==Constants.VIDEO_RECORD){
                String videoImage = data.getStringExtra("imgUrl");
                String videoUrl = data.getStringExtra("videoUrl");
                int mTimeCount=data.getIntExtra("mTimeCount",0);
                sendVideoMessage(videoUrl, videoImage, mTimeCount);
            }
        }

    }
}
