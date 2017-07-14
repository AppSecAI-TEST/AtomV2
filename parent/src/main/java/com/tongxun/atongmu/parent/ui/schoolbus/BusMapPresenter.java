package com.tongxun.atongmu.parent.ui.schoolbus;

import com.tongxun.atongmu.parent.BasePresenter;

/**
 * Created by Anro on 2017/7/14.
 */

public class BusMapPresenter extends BasePresenter<IBusMapContract.View> implements IBusMapContract.Presenter, IBusMapContract.Interactor.onFinishListener {

    private BusMapInteractor interactor=null;

    public BusMapPresenter() {
        interactor=new BusMapInteractor();
    }

    @Override
    public void getPositionForBus() {
        interactor.getPositionForBus(this);
    }

    /**
     * 获取校车信息
     */
    @Override
    public void getBusinfo() {
        interactor.getBusinfo(this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(String carStatus, String longitude, String latitude) {
        if(mView!=null){
            mView.refreshPositionSuccess(carStatus,longitude,latitude);
        }
    }

    @Override
    public void ongetInfoSuccess(String status, String carName, String carNum, String teacher, String teaNum, String driver, String driverNum, String longitude, String latitude) {
        if(mView!=null){
            mView.setSchoolCarInfn(status,carName,carNum,teacher,teaNum,driver,driverNum,longitude,latitude);
        }
    }
}
