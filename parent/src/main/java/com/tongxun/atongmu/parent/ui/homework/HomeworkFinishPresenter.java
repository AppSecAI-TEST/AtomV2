package com.tongxun.atongmu.parent.ui.homework;

/**
 * Created by Anro on 2017/7/5.
 */

public class HomeworkFinishPresenter  implements IHomeworkFinishContract.Presenter, IHomeworkFinishContract.Interactor.onFinishListener {
    private IHomeworkFinishContract.View mView=null;
    private HomeworkFinishInteractor interactor=null;


    public HomeworkFinishPresenter(IHomeworkFinishContract.View view) {
        mView=view;
        mView.setPresenter(this);
        interactor=new HomeworkFinishInteractor();
    }


    public void detachView(){
        if(mView!=null){
            mView=null;
        }
    }

    /**
     * 获取完成作业的日期列表
     */
    @Override
    public void getFinishHomeworkDate() {
        interactor.getFinishHomeworkDate(this);
    }

    /**
     * 获取已完成的时间列表
     */
    @Override
    public void onGetDateSuccess() {

    }

    @Override
    public void onError() {

    }
}
