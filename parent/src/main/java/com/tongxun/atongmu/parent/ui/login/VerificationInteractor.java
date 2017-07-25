package com.tongxun.atongmu.parent.ui.login;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.model.SmsModel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Anro on 2017/6/29.
 */

public class VerificationInteractor implements IVerificationContract.Interactor {

    private static String mCode="ZL12";

    @Override
    public void checkVerCode(String code,onFinishLinstener linstener) {
        if(TextUtils.isEmpty(code)){
            linstener.onCheckError(ParentApplication.getContext().getResources().getString(R.string.code_empty));
        }else {
            if(mCode.equals(code)){
                linstener.onCheckSuccess();
            }else {
                linstener.onCheckError(ParentApplication.getContext().getResources().getString(R.string.code_error));
            }
        }
    }

    @Override
    public void getWebVer(String phone, final onFinishLinstener linstener) {
        String url= Constants.restSendSMS;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson(phone))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        linstener.onSendError();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        SmsModel smsModel=gson.fromJson(response,SmsModel.class);
                        if(smsModel.getStatus().equals("success")){
                            mCode=smsModel.getCheckCode();
                            linstener.onSendSuccess();
                        }else {
                            linstener.onSendError();
                        }
                    }
                });
    }



    private String CreateJson(String phone) {
        JSONObject jsonObject=null;
        try {
            jsonObject=new JSONObject();
            jsonObject.put("phoneCode",phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}
