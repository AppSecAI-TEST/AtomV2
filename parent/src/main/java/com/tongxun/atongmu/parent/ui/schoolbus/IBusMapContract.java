package com.tongxun.atongmu.parent.ui.schoolbus;

import com.tongxun.atongmu.parent.BaseView;

/**
 * Created by Anro on 2017/7/14.
 */

public interface IBusMapContract {

    interface View extends BaseView{

        void onError(String message);

        void refreshPositionSuccess(String carStatus, String longitude, String latitude);

        void setSchoolCarInfn(String status, String carName, String carNum, String teacher, String teaNum, String driver, String driverNum, String longitude, String latitude);
    }

    interface Presenter{

        void getPositionForBus();

        void getBusinfo();
    }

    interface Interactor{

        void getPositionForBus(onFinishListener listener);
        void getBusinfo(onFinishListener listener);

        interface onFinishListener{

            void onError(String message);

            void onSuccess(String carStatus, String longitude, String latitude);

            void ongetInfoSuccess(String status, String carName, String carNum, String teacher, String teaNum, String driver, String driverNum, String longitude, String latitude);
        }
    }

}

