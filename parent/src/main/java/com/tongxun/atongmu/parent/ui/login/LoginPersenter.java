package com.tongxun.atongmu.parent.ui.login;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.MediaType;


/**
 * Created by Anro on 2017/6/16.
 */

public class LoginPersenter implements LoginContract.Presenter {
    private LoginContract.View mloginView;

    private static final String TAG = "LoginPersenter";

    public LoginPersenter(LoginContract.View loginView) {
        mloginView = loginView;
    }



    @Override
    public void Login(String username, String password) {
        mloginView.showProgress();
        String url="https://www.atongmu.net:8443/backwork/rest/restParentLoginList";
        OkHttpUtils.postString()
                .url(url)
                .content(new Gson().toJson(new UserModel(username,password)))
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
                                mloginView.hideProgress();
                                mloginView.loginSuccess();
                            }else {
                                mloginView.hideProgress();
                                mloginView.loginError(callBack.getMessage());
                            }
                        }

                    }
                });
    }

    @Override
    public void start() {

    }
}
