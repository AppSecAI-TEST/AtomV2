package com.tongxun.atongmu.parent.ui.homework;

import com.tongxun.atongmu.parent.model.FinishWorkModel;

import java.util.List;

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

    @Override
    public void getFinishHomeworkFromDate(int position, String date) {
        interactor.getFinishHomeworkFromDate(position,date,this);
    }

    /**
     * 家长删除已完成的作业
     * @param jobId
     */
    @Override
    public void deleteHomework(String jobId) {
        interactor.deleteHomework(jobId,this);
    }

    /**
     * 获取已完成的时间列表
     */
    @Override
    public void onGetDateSuccess() {

    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onMonSuccess(List<String> datas) {
        if(mView!=null){
            mView.onMonSuccess(datas);
        }
    }

    @Override
    public void onHomeWorkSuccess(int position, List<FinishWorkModel> datas) {
        if(mView!=null){
            mView.onHomeWorkSuccess(position,datas);
        }
    }

    @Override
    public void onDeleteHomeWorkSuccess() {
        if(mView!=null){
            mView.onDeleteHomeWorkSuccess();
        }
    }

}
