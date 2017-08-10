package com.tongxun.atongmu.parent.ui;

import com.tongxun.atongmu.parent.model.ImageLoadBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anro on 17-7-10.
 */

public class PhotoSelectContainer {


    public static ArrayList<String> allList = new ArrayList<>();
    public static List<ImageLoadBean> dirlist = new ArrayList<>();


    private static List<String> fileList=new ArrayList<>();

    public static List<String> getFileList() {
        return fileList;
    }

    private static int max_size=9;

    public static boolean isHaveFile(String filepath){
        if(fileList.contains(filepath)){
            return true;
        }
        return false;
    }

    public static int getFilePosition(String filepath){
        return fileList.indexOf(filepath);
    }

    public static void setFileList(List<String> fileList) {
        PhotoSelectContainer.fileList = fileList;
    }

    public static void removeFile(String filePath){
        fileList.remove(filePath);
    }

    public static boolean addFile(String filePath){
        if(fileList.size()<max_size){
            fileList.add(filePath);
            return true;
        }
        return false;
    }

    public static void setMaxSize(int size) {
        max_size=size;
    }

    public static void clear() {
        fileList.clear();
    }

    public static int getFileSize(){
        return fileList.size();
    }
}
