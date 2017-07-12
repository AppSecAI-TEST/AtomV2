package com.tongxun.atongmu.parent.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
}
