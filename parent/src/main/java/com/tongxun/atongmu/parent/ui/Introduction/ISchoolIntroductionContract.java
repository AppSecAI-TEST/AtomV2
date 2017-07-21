package com.tongxun.atongmu.parent.ui.Introduction;

import com.tongxun.atongmu.parent.BaseView;
import com.tongxun.atongmu.parent.model.SchoolIntroductModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/14.
 */

public interface ISchoolIntroductionContract {
    interface View extends BaseView{

        void onError(String message);

        void onSuccessDate(List<SchoolIntroductModel> datas);
    }

    interface Presenter{

        void getSchoolIntroduct();
    }

    interface Interactor{

    }
}
