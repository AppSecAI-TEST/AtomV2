package com.tongxun.atongmu.parent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Anro on 2017/7/10.
 */

public class ImageLoadBean implements Parcelable {
    private String dirPath;//文件夹路径
    private String dirName;//文件名称
    private String firstImgPath;//第一张图片的路径
    private int count;//文件夹中文件的数量

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public String getFirstImgPath() {
        return firstImgPath;
    }

    public void setFirstImgPath(String firstImgPath) {
        this.firstImgPath = firstImgPath;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dirPath);
        dest.writeString(this.dirName);
        dest.writeString(this.firstImgPath);
        dest.writeInt(this.count);
    }

    public ImageLoadBean() {
    }

    protected ImageLoadBean(Parcel in) {
        this.dirPath = in.readString();
        this.dirName = in.readString();
        this.firstImgPath = in.readString();
        this.count = in.readInt();
    }

    public static final Parcelable.Creator<ImageLoadBean> CREATOR = new Parcelable.Creator<ImageLoadBean>() {
        @Override
        public ImageLoadBean createFromParcel(Parcel source) {
            return new ImageLoadBean(source);
        }

        @Override
        public ImageLoadBean[] newArray(int size) {
            return new ImageLoadBean[size];
        }
    };
}
