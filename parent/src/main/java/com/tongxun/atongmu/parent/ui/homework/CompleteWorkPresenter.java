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

    public CompleteWorkPresenter() {
        interactor=new CompleteWorkInteractor();
    }

    @Override
    public void commitHomework(String content, List<String> filelist, boolean isOss, String path, String name) {
        if(isOss){
            interactor.getCommitOssPath(this);
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
        interactor.putOssFile(savePath,path,name,this);
    }
}
