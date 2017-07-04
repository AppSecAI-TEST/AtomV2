package com.tongxun.atongmu.parent.ui.homework;

/**
 * Created by Anro on 2017/7/4.
 */

public class HomeworkPresenter implements IHomeworkContract.Presenter {

    private IHomeworkContract.View mView=null;


    public HomeworkPresenter(IHomeworkContract.View view) {
        mView=view;
        mView.setPresenter(this);
    }


    public void detachView(){
        if(mView!=null){
            mView=null;
        }
    }
}
