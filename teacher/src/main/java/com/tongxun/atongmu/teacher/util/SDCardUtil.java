package com.tongxun.atongmu.teacher.util;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.tongxun.atongmu.teacher.application.TeacherApplication;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Anro on 2017/7/6.
 */

public class SDCardUtil {

    private static SDCardUtil instance;

    /**
     * SD卡目录
     */
    private static String SD_DIR="";
    /**
     * SD卡下文件夹名称
     */
    private static final String FILE_DIR_NAME="/Atom_Parent/";

    public static SDCardUtil getInstance(){
        if (instance==null){
            instance=new SDCardUtil();
        }
        return instance;
    }

    private SDCardUtil(){
        if(isHasSdcard()){
            SD_DIR=Environment.getExternalStorageDirectory().getAbsolutePath();

        }else {
            SD_DIR=Environment.getDataDirectory().getPath();

        }
        createFileDir();

    }

    //创建目录
    private void createFileDir(){
        File destDir = new File(getFilePath());
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
    }




    public String getFilePath(){
        return SD_DIR+FILE_DIR_NAME;
    }

    //判断是否有sd卡
    public boolean isHasSdcard(){
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * bitmap 转成文件
     */
    public void changeBitmapToFile(Bitmap bm, String filename) throws IOException {
        String path =getFilePath()+filename;
        File dirFile = new File(path);
        if(dirFile.exists()){
            dirFile.delete();
        }
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dirFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 85, bos);
        bos.flush();
        bos.close();
    }

    //加载固定宽度bitmap
    public Bitmap loadFixedBitmap(String path,int width){
        Bitmap bitmap=null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = caculateInSampleSize(options, width);
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }

    /**
     * 计算SampleSize
     * @param options bitmap的尺寸
     * @param reqwidth  期望的宽度
     * 仅对宽度有要求的
     * @return
     */
    private int caculateInSampleSize(BitmapFactory.Options options, int reqwidth) {
        int width = options.outWidth;
        int inSampleSize = 1;
        if (width > reqwidth) {
            inSampleSize = (width/reqwidth)+1;
        }
        return inSampleSize;
    }

    /**
     * Content Uri 转 path
     * @param uri
     * @return
     */
    public  String changeUriToFilePath(final Uri uri) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = TeacherApplication.getContext().getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

}
