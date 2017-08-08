package com.tongxun.atongmu.teacher.ui.personsign;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.teacher.Constants;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.application.TeacherApplication;
import com.tongxun.atongmu.teacher.model.TeacherSignCallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Anro on 2017/8/8.
 */

public class TeacherSignInteractor implements ITeacherSignContract.Interactor {

    @Override
    public void getTeacherSign(String classcId, String selectDate, final onFinishListener listener) {
        String url= Constants.getStudentDutyWithClass;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson(classcId,selectDate))
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
                        TeacherSignCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,TeacherSignCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                listener.onSuccess(callBack.getArrived(),callBack.getUnArrived());
                            }else {
                                listener.onError(callBack.getMessage());
                            }
                        }else {
                            listener.onError(TeacherApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    private String CreateJson(String classcId, String selectDate) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            //jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("tokenId", "c57c18ab-6383-4415-87ca-b455be8a1a38");
            jsonObject.put("classcId",classcId);
           // jsonObject.put("date",selectDate);
            jsonObject.put("date","2017-6-29");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
