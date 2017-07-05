package com.tongxun.atongmu.parent.ui.homework;

/**
 * Created by Anro on 2017/7/5.
 */

public class HomeworkFinishPresenter  implements IHomeworkFinishContract.Presenter {
    private IHomeworkFinishContract.View mView=null;


    public HomeworkFinishPresenter(IHomeworkFinishContract.View view) {
        mView=view;
        mView.setPresenter(this);
    }


    public void detachView(){
        if(mView!=null){
            mView=null;
        }
    }
}
