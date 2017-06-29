package com.tongxun.atongmu.parent.ui.login;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.model.LoginCallBack;
import com.tongxun.atongmu.parent.model.LoginModel;
import com.tongxun.atongmu.parent.model.TokenIdModel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.litepal.crud.DataSupport;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Anro on 2017/6/21.
 */

public class LoginInteractor implements ILoginContract.Interactor {

    @Override
    public void Login(String username, String password, final OnLoginFinishedListener listener) {
        String url= Constants.restParentLoginList;
        OkHttpUtils.postString()
                .url(url)
                .content(new Gson().toJson(new LoginModel(username,password)))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onError("网络异常");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        LoginCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,LoginCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                try {
                                    DataSupport.deleteAll(TokenIdModel.class);
                                    DataSupport.saveAll(callBack.getDatas());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                listener.onSuccess();
                            }else {
                                listener.onError(callBack.getMessage());
                            }
                        }else {
                            listener.onError("用户名密码不能为空");
                        }

                    }
                });
    }
}
