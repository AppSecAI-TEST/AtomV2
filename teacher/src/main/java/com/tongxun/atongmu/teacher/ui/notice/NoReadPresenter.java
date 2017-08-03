package com.tongxun.atongmu.teacher.ui.notice;

import com.tongxun.atongmu.teacher.BasePresenter;
import com.tongxun.atongmu.teacher.model.ActivityHaveActModel;
import com.tongxun.atongmu.teacher.model.ActivityNoReadModel;

import java.util.List;

/**
 * Created by Anro on 2017/8/2.
 */

public class NoReadPresenter extends BasePresenter<INoReadContract.View> implements INoReadContract.Presenter, INoReadContract.Interactor.onFinishListener {

    private NoReadInteractor interactor;

    public NoReadPresenter() {
        interactor=new NoReadInteractor();
    }


    @Override
    public void getNoReadList(String action, String sourceId) {
        interactor.getNoReadList(action,sourceId,this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(List datas) {
        if(mView!=null){
            mView.onSuccess(datas);
        }
    }

    @Override
    public void onActiviytSuccess(List<ActivityHaveActModel> havenActStudents, List<ActivityNoReadModel> noReadActStudents) {
        if(mView!=null){
            mView.onActivitySuccess(havenActStudents,noReadActStudents);
        }
    }


}
