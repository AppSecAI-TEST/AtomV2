package com.tongxun.atongmu.parent.ui.homework;

import com.tongxun.atongmu.parent.BasePresenter;

import java.util.List;

/**
 * Created by Anro on 2017/7/5.
 */

public class CompleteWorkPresenter extends BasePresenter<IComepleteWorkContract.View> implements IComepleteWorkContract.Presenter, IComepleteWorkContract.Interactor.onFinishListener {

    private CompleteWorkInteractor interactor;
    private String path;
    private String name;
    private String savePath;
    private String jobId;
    private String content;
    private String type;
    private String mAduiloLength;
    private String videoImageUrl;
    private List<String> filelist;

    public CompleteWorkPresenter() {
        interactor=new CompleteWorkInteractor();
    }


    @Override
    public void commitHomework(String jobId, String content, List<String> filelist, boolean isOss, String type, String path, String name, String mAduiloLength,String videoImageUrl) {
        if(isOss){
            this.jobId= jobId;
            this.content= content;
            this.filelist= filelist;
            this.type= type;
            this.path= path;
            this.name= name;
            this.mAduiloLength= mAduiloLength;
            this.videoImageUrl= videoImageUrl;
            interactor.getCommitOssPath(this);
        }else {
            interactor.commitHomework(jobId,content,filelist,type,"","","",this);
        }
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void ongetPathSuccess(String savePath) {
        this.savePath=savePath;
        interactor.putOssFile(savePath,path,name,this);
    }

    @Override
    public void onPutFileSuccess() {
        interactor.commitHomework(jobId,content,filelist,type,"http://atongmu.oss-cn-hangzhou.aliyuncs.com/"+savePath+"/"+name,mAduiloLength,videoImageUrl,this);

    }

    @Override
    public void onCommitSuccess() {
        if(mView!=null){
            mView.onCommitSuccess();
        }
    }


}
