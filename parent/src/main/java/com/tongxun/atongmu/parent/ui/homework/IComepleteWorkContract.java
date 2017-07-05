package com.tongxun.atongmu.parent.ui.homework;

import com.tongxun.atongmu.parent.BaseView;

/**
 * Created by Anro on 2017/7/5.
 */

public interface IComepleteWorkContract {
    interface View extends BaseView{

    }

    interface Presenter{
        void commitHomework();
    }

    interface Interactor{

    }
}
