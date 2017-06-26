package com.tongxun.atongmu.parent.webhelper;

import com.tongxun.atongmu.parent.model.LoginCallBack;
import com.tongxun.atongmu.parent.model.LoginModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Anro on 2017/6/20.
 */

public interface RetrofitService {

    @Headers({"Content-Type:application/json","Accept:application/json"})
    @POST("restParentLoginList")
    Call<LoginCallBack> postWebLogin(@Body LoginModel body);

}
