package com.tongxun.atongmu.parent.ui.Introduction;

/**
 * Created by Anro on 2017/7/14.
 */

public class SchoolIntroductionPresenter  implements ISchoolIntroductionContract.Presenter {

    private ISchoolIntroductionContract.View mView;

    public SchoolIntroductionPresenter(ISchoolIntroductionContract.View view) {
        mView=view;
        mView.setPresenter(this);
    }
}
