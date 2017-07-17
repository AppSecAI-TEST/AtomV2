package com.tongxun.atongmu.parent.ui.Introduction;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/7/14.
 */

public class SchoolIntroductionPresenter extends BasePresenter<ISchoolIntroductionContract.View> implements ISchoolIntroductionContract.Presenter {

    private ISchoolIntroductionContract.View mView;

    public SchoolIntroductionPresenter(ISchoolIntroductionContract.View view) {
        mView=view;
        mView.setPresenter(this);
    }

    @Override
    public void getTeacherStyleList() {

    }
}
