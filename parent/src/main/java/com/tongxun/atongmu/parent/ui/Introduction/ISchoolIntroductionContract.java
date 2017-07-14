package com.tongxun.atongmu.parent.ui.Introduction;

import com.tongxun.atongmu.parent.BaseView;

/**
 * Created by Anro on 2017/7/14.
 */

public interface ISchoolIntroductionContract {
    interface View<T> extends BaseView{
       void setPresenter(T presenter);
    }

    interface Presenter{

    }

    interface Interactor{

    }
}
