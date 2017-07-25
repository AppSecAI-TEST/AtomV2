package com.tongxun.atongmu.parent.ui.my;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/7/25.
 */

public class UseHelpPresenter extends BasePresenter<IUserHelpContract.View> implements IUserHelpContract.Presenter {

    UseHelpInteractor interactor;

    public UseHelpPresenter() {
        interactor=new UseHelpInteractor();
    }
}
