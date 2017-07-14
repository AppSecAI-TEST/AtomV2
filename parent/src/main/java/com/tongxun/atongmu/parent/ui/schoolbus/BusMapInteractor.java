package com.tongxun.atongmu.parent.ui.schoolbus;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.model.SchoolBusInfoModel;
import com.tongxun.atongmu.parent.model.SchoolBusPositionModel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Anro on 2017/7/14.
 */

public class BusMapInteractor implements IBusMapContract.Interactor {

    @Override
    public void getPositionForBus(final onFinishListener listener) {
        String url= Constants.restGetCarPosition_v2;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onError(ParentApplication.getContext().getString(R.string.net_error));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        SchoolBusPositionModel callBack = null;
                        try {
                            callBack = gson.fromJson(response, SchoolBusPositionModel.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if (callBack != null) {
                            if (callBack.getStatus().equals("success")) {
                                listener.onSuccess(callBack.getCarStatus(),callBack.getLongitude(),callBack.getLatitude());
                            } else {
                                listener.onError(callBack.getMessage());
                            }
                        } else {
                            listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    /**
     * 获取校车信息
     * @param listener
     */
    @Override
    public void getBusinfo(final onFinishListener listener) {
        String url=Constants.restGetCarInfo;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onError(ParentApplication.getContext().getString(R.string.net_error));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        SchoolBusInfoModel callBack = null;
                        try {
                            callBack = gson.fromJson(response, SchoolBusInfoModel.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if (callBack != null) {
                            if (callBack.getStatus().equals("success")) {
                                listener.ongetInfoSuccess(callBack.getStatus(),callBack.getCarName(),callBack.getCarNum(),callBack.getTeacher(),callBack.getTeaNum(),callBack.getDriver(),callBack.getDriverNum(),callBack.getLatlng_list().get(0).getLongitude(),callBack.getLatlng_list().get(0).getLatitude());
                            } else {
                                listener.onError(ParentApplication.getContext().getString(R.string.no_school_car));
                            }
                        } else {
                            listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    private String CreateJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            // jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, ""));
            jsonObject.put("tokenId", "d5a0996a-aa62-4ec6-8f5c-8548077a441e");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
