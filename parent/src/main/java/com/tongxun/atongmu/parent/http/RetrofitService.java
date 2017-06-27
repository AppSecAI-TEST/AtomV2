package com.tongxun.atongmu.parent.http;

import com.tongxun.atongmu.parent.model.LoginCallBack;
import com.tongxun.atongmu.parent.model.LoginModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Anro on 2017/6/20.
 */

public interface RetrofitService {

    @Headers({"Content-Type:application/json","Accept:application/json"})
    @POST("restParentLoginList")
    Call<LoginCallBack> postWebLogin(@Body LoginModel body);

    /**
     * HTTP方法请求注解
     * GET POST PUT DELETE PATCH HEAD OPTIONS HTTP
     *
     * http注解可以代替任意一个注解
     * method  请求的方法，区分大小写
     * path 表示路径
     * hasBody 是否有请求体
     */
    @HTTP(method = "GET",path = "blog/{id}",hasBody = false)
    Call<ResponseBody> getBlog(@Path("id") int id);

    /**
     * 表单请求
     * FormUrlEncoded 表示请求体是一个Form表单
     * Content-Type:application/x-www-form-urlencoded
     *
     * Multipart 表示请求体是一个支持文件上传的Form表单
     * Content-Type：multpart/form-data
     */

    /**
     * 标记
     * Streaming
     * 表示响应体的数据用流的形式返回
     * 所有如你的返回的数据比较大，你就需要使用这个注解
     */
}
