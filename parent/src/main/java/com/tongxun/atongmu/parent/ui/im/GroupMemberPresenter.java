package com.tongxun.atongmu.parent.ui.im;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.GroupMemberModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/11.
 */

public class GroupMemberPresenter extends BasePresenter<IGroupMemberContract.View> implements IGroupMemberContract.Presenter, IGroupMemberContract.Interactor.onFinishListener {

    GroupMemberInteractor interactor=null;

    public GroupMemberPresenter() {
        interactor=new GroupMemberInteractor();
    }

    @Override
    public void getGroupMember() {
        if(mView!=null){
            mView.showProgress();
        }
        interactor.getGroupMember(this);
    }

    @Override
    public void onSuccess(List<GroupMemberModel> parentdatas, List<GroupMemberModel> teacherdatas) {
        if(mView!=null){
            mView.hideProgress();
            mView.setRefreshAdapter(parentdatas,teacherdatas);
        }
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.hideProgress();
            mView.onError(message);
        }
    }
}
