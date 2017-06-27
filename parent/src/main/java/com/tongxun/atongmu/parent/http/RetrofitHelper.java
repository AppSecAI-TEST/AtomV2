package com.tongxun.atongmu.parent.http;

import com.google.gson.GsonBuilder;
import com.tongxun.atongmu.parent.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Anro on 2017/6/20.
 */

public class RetrofitHelper {

    OkHttpClient client = new OkHttpClient();
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;

    public static RetrofitHelper getInstance() {
        if (instance == null) {
            instance = new RetrofitHelper();
        }
        return instance;
    }

    private RetrofitHelper() {
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASEURL) //retrofit2 baseUrl 结尾需要以/结尾
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//支持RxJava
                .build();
    }

    public RetrofitService getServer(){
        return mRetrofit.create(RetrofitService.class);
    }

}
