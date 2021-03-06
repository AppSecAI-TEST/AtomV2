package com.tongxun.atongmu.parent.ui.schooltuition;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.OrderRecordModel;
import com.tongxun.atongmu.parent.model.TuitionModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/17.
 */

public class SchoolTuitionPresenter extends BasePresenter<ISchoolTuitionContract.View> implements ISchoolTuitionContract.Presenter, ISchoolTuitionContract.Interactor.onFinishListener {

    SchoolTuitionInteractor interactor;
    public SchoolTuitionPresenter() {
        interactor=new SchoolTuitionInteractor();
    }

    @Override
    public void getPayNotice() {
        interactor.getPayNotice(this);
    }

    @Override
    public void getPayRecord() {
        interactor.getPayRecord(this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(List<TuitionModel> datas) {
        if(mView!=null){
            mView.onPayNoticeSuccess(datas);
        }
    }


    @Override
    public void onOrderRecordSuccess(List<OrderRecordModel> datas) {
        if(mView!=null){
            mView.onOrderRecordSuccess(datas);
        }
    }
}
