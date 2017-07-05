package com.tongxun.atongmu.parent.ui.homework;

/**
 * Created by Anro on 2017/7/4.
 */

public class HomeworkNoFinishPresenter implements IHomeworkNoFinishContract.Presenter, IHomeworkNoFinishContract.Interactor.onFinishListener {

    private IHomeworkNoFinishContract.View mView=null;
    private HomeworkInteractor interactor=null;

    public HomeworkNoFinishPresenter(IHomeworkNoFinishContract.View view) {
        mView=view;
        mView.setPresenter(this);
        interactor=new HomeworkInteractor();
    }


    public void detachView(){
        if(mView!=null){
            mView=null;
        }
    }

    @Override
    public void getNoFinishHomeWork() {
        interactor.getNoFinishHomework(this);
    }

    @Override
    public void onSuccess() {
        if(mView!=null){
            mView.setData();
        }
    }

    @Override
    public void onError(String message) {

    }
}
