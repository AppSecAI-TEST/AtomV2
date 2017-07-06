package com.tongxun.atongmu.parent.util;

import android.os.Environment;

import java.io.File;

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
    private boolean isHasSdcard(){
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
