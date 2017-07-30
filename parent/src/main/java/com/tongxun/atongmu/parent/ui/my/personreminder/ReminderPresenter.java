package com.tongxun.atongmu.parent.ui.my.personreminder;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.MedicineModel;

import java.util.List;

/**
 * Created by admin on 2017/7/29.
 */

public class ReminderPresenter extends BasePresenter<IReminderContract.View> implements IReminderContract.Presenter, IAddReminderInteractor.onFinishListener {

    ReminderInteractor interactor;

    public ReminderPresenter() {
        interactor=new ReminderInteractor();
    }

    @Override
    public void getReminderList() {
        interactor.getReminderList(this);
    }

    @Override
    public void deleteReminder(String note_id) {
        interactor.deleteReminder(note_id,this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onPostReminderSuccess() {

    }

    @Override
    public void onGetReminderSuccess(List<MedicineModel> medicine) {
        if(mView!=null){
            mView.onGetReminderSuccess(medicine);
        }
    }

    @Override
    public void onDeleteReminderSuccess() {
        if(mView!=null){
            mView.onDeleteReminderSuccess();
        }
    }


}
