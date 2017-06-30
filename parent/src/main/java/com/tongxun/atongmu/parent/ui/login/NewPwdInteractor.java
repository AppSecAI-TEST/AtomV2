package com.tongxun.atongmu.parent.ui.login;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.model.BaseCallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Anro on 2017/6/30.
 */

public class NewPwdInteractor implements INewPwdContract.Interactor {

    @Override
    public void setNewPwd(String phone, String password, final onFinishListener listener) {
        String url= Constants.restParentResetPWD;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson(phone,password))
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
                        Gson gson=new Gson();
                        BaseCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,BaseCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                listener.onSuccess();
                            }else {
                                listener.onError(ParentApplication.getContext().getString(R.string.reset_pwd_error));
                            }
                        }else {
                            listener.onError(ParentApplication.getContext().getString(R.string.reset_pwd_error));
                        }
                    }
                });
    }

    private String CreateJson(String phone, String password) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("username",phone);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
