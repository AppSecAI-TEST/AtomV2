package com.tongxun.atongmu.parent.ui.login;

import com.google.gson.Gson;
import com.tongxun.atongmu.parent.model.LoginCallBack;
import com.tongxun.atongmu.parent.model.LoginModel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Anro on 2017/6/21.
 */

public class LoginInteractor implements ILoginContract.Interactor {

    @Override
    public void Login(String username, String password, final OnLoginFinishedListener listener) {
        String url="https://www.atongmu.net:8443/backwork/rest/restParentLoginList";
        OkHttpUtils.postString()
                .url(url)
                .content(new Gson().toJson(new LoginModel(username,password)))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        LoginCallBack callBack=gson.fromJson(response,LoginCallBack.class);
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                listener.onSuccess();

                            }else {
                                listener.onError(callBack.getMessage());
                            }
                        }

                    }
                });
    }
}
