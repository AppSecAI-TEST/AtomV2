package com.tongxun.atongmu.parent.ui.my.personreminder;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/7/29.
 */

public class AddReminderPresenter extends BasePresenter<IAddReminderContract.View> implements IAddReminderContract.Presenter{

    ReminderInteractor interactor;
    public AddReminderPresenter() {
        interactor=new ReminderInteractor();
    }

}
