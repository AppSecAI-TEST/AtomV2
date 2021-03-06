package com.tongxun.atongmu.parent.ui.my.personreminder;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.MedicineModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/29.
 */

public class AddReminderPresenter extends BasePresenter<IAddReminderContract.View> implements IAddReminderContract.Presenter, IAddReminderInteractor.onFinishListener {

    ReminderInteractor interactor;
    public AddReminderPresenter() {
        interactor=new ReminderInteractor();
    }

    @Override
    public void createNewReminder(String remark, String starttime, String days, List<String> mlist) {
        interactor.createNewReminder(remark,starttime,days,mlist,this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onPostReminderSuccess() {
        if(mView!=null){
            mView.onSuccess();
        }
    }

    @Override
    public void onGetReminderSuccess(List<MedicineModel> medicine) {

    }

    @Override
    public void onDeleteReminderSuccess() {

    }

}
