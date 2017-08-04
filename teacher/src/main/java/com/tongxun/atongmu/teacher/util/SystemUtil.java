package com.tongxun.atongmu.teacher.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by Anro on 2017/7/6.
 */

public class SystemUtil {

    public static void opSystemCamera(Activity context, String picPath,int REQUEST_CODE){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = Uri.fromFile(new File(picPath));
        //为拍摄的图片指定一个存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        context.startActivityForResult(intent, REQUEST_CODE);
    }

    public static void openSystemVideo(Context context,String videoUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String type = "video/mp4";
        Uri uri = Uri.fromFile(new File(videoUrl));
        intent.setDataAndType(uri, type);
        context.startActivity(intent);
    }

    public static void openSystemPhone(Context context,String phone) {
        //用intent启动拨打电话
        Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phone));
        context.startActivity(intent);
    }

    public static void startSystemCropPhoto(Activity activity,Uri inputUri,Uri outputUri,int aspectX,int aspectY,int outputX,int outputY,int REQ_CODE) {
        //启动裁剪程序
        Intent intent=new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(inputUri,"image/*");
        //裁剪框比例
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        //图片输出大小
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,outputUri);
        activity.startActivityForResult(intent,REQ_CODE);
    }

    public static void StartSystemAlbum(Activity activity, int reqPhoto) {
        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        //根据版本号不同使用不同的Action
        if (Build.VERSION.SDK_INT <19) {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        }else {
            intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            //MediaStore.Images.Media.EXTERNAL_CONTENT_URI获取系统图册的uri

        }
        activity.startActivityForResult(intent,reqPhoto);
    }
}
