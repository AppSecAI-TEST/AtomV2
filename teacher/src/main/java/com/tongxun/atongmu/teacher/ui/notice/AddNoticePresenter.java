package com.tongxun.atongmu.teacher.ui.notice;

import com.tongxun.atongmu.teacher.BasePresenter;

/**
 * Created by Anro on 2017/8/3.
 */

public class AddNoticePresenter extends BasePresenter<IAddNoticeContract.View> implements IAddNoticeContract.Presenter {

    private AddNoticeInteractor interactor;

    public AddNoticePresenter() {
        interactor=new AddNoticeInteractor();
    }
}
