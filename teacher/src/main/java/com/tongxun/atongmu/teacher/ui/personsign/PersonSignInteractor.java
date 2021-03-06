package com.tongxun.atongmu.teacher.ui.personsign;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.teacher.Constants;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.application.TeacherApplication;
import com.tongxun.atongmu.teacher.model.PersonSignCallBack;
import com.tongxun.atongmu.teacher.util.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Anro on 2017/8/4.
 */

public class PersonSignInteractor implements IPersonContract.Interactor {

    @Override
    public void getPersonSign(String selectDate, final onFinishListener listener) {
        String url= Constants.getStudentDutySummary;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson(selectDate))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onError(TeacherApplication.getContext().getString(R.string.net_error));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        PersonSignCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,PersonSignCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                listener.onPersonSignSuccess(callBack.getIdentity(),callBack.getTotal(),callBack.getCar(),callBack.getArrived(),callBack.getUnArrived(),callBack.getLeave(),callBack.getArrivedPre(),callBack.getUnArrivedPre(),callBack.getLeavePre(),callBack.getDatas());
                            }else {
                                listener.onError(callBack.getMessage());
                            }
                        }else {
                            listener.onError(TeacherApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    private String CreateJson(String selectDate) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("date",selectDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
