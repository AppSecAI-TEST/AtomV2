package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/7.
 */

public class HomeworkNoFinishModel {
    /**
     * photoList : ["http://127.0.0.1:10010/backwork/userfiles/images/kigJob/2017/05/6a0cdfc1_4075_4f49_8797_4d5aefb5522e.jpeg"]
     * photoSize : 1
     * videoImg :
     * voiceLength :
     * haveVoice : false
     * content : 本周作业:回去和爸爸妈妈一起做“溶解”实验，并用自己的方式记录实验结果，下周来园与大家分享你的实验经历！实验材料不限制，但是，请一定要做一下“糖果”的溶解实验！
     * voiceUrl :
     * jobId : ec7cf9a0b89f4c85b335c3c295d84e66
     * teaName : 李蒙蒙老师
     * videoUrl :
     * haveVideo : false
     * teaImg :
     * createDate : 2017-05-19 12:03:04
     */

    private int photoSize;
    private String videoImg;
    private String voiceLength;
    private String haveVoice;
    private String content;
    private String voiceUrl;
    private String jobId;
    private String teaName;
    private String videoUrl;
    private String haveVideo;
    private String teaImg;
    private String createDate;
    private List<String> photoList;

    public int getPhotoSize() {
        return photoSize;
    }

    public void setPhotoSize(int photoSize) {
        this.photoSize = photoSize;
    }

    public String getVideoImg() {
        return videoImg;
    }

    public void setVideoImg(String videoImg) {
        this.videoImg = videoImg;
    }

    public String getVoiceLength() {
        return voiceLength;
    }

    public void setVoiceLength(String voiceLength) {
        this.voiceLength = voiceLength;
    }

    public String getHaveVoice() {
        return haveVoice;
    }

    public void setHaveVoice(String haveVoice) {
        this.haveVoice = haveVoice;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getHaveVideo() {
        return haveVideo;
    }

    public void setHaveVideo(String haveVideo) {
        this.haveVideo = haveVideo;
    }

    public String getTeaImg() {
        return teaImg;
    }

    public void setTeaImg(String teaImg) {
        this.teaImg = teaImg;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<String> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<String> photoList) {
        this.photoList = photoList;
    }
}
