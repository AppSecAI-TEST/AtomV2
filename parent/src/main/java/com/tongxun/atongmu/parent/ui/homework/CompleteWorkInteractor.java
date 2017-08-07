package com.tongxun.atongmu.parent.ui.homework;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.http.RetrofitHelper;
import com.tongxun.atongmu.parent.model.BaseCallBack;
import com.tongxun.atongmu.parent.model.OssPathCallback;
import com.tongxun.atongmu.parent.util.OssUtil;
import com.tongxun.atongmu.parent.util.RxPartMapUtils;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.File;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Anro on 2017/7/5.
 */

public class CompleteWorkInteractor implements IComepleteWorkContract.Interactor {

    @Override
    public void getCommitOssPath(final onFinishListener listener) {
        String url= Constants.getUploadSavePath;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson())
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
                        OssPathCallback callBack= null;
                        try {
                            callBack = gson.fromJson(response,OssPathCallback.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                listener.ongetPathSuccess(callBack.getSavePath());
                            }else {
                                listener.onError(callBack.getMessage());
                            }
                        }else {
                            listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    @Override
    public void putOssFile(String savePath, String path, String name, final onFinishListener listener) {
        PutObjectRequest put=new PutObjectRequest("atongmu",savePath+"/"+name,path);
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest putObjectRequest, long l, long l1) {

            }
        });
        OSSAsyncTask task= OssUtil.getInstance().asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
               listener.onPutFileSuccess();
            }

            @Override
            public void onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1) {
                listener.onError(ParentApplication.getContext().getString(R.string.post_file_error));
            }
        });
    }

    @Override
    public void commitHomework(String jobId, String content, List<String> filelist, String type, String osspath, String mAduiloLength, String videoImageUrl, final onFinishListener listener) {
        String url=Constants.restPutStudentJob;

        MultipartBody.Builder builder=new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        builder.addFormDataPart("tokenId",SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
        builder.addFormDataPart("jobId",jobId);
        builder.addFormDataPart("content",content);
        if(type.equals("Video")){
            builder.addFormDataPart("videoUrl",osspath);
            File file=new File(videoImageUrl);
            builder.addFormDataPart("mediaFile", file.getName(), RxPartMapUtils.toRequestBodyOfDateImage(file));
        }
        if(type.equals("Audio")){
            builder.addFormDataPart("voiceUrl",osspath);
            builder.addFormDataPart("voiceLength",mAduiloLength);
        }

        for(int i=1;i<=filelist.size();i++){
            File file=new File(filelist.get(i));
            builder.addFormDataPart(("sourceImg"+i), file.getName(), RxPartMapUtils.toRequestBodyOfDateImage(file));
        }

        List<MultipartBody.Part> parts = builder.build().parts();



        RetrofitHelper.getInstance().getServer().postFeedBack(url,parts).enqueue(new Callback<BaseCallBack>() {
            @Override
            public void onResponse(retrofit2.Call<BaseCallBack> call, Response<BaseCallBack> response) {
                if(response.body()!=null){
                    if(response.body().getStatus().equals("success")){
                        listener.onCommitSuccess();
                    }else {
                        listener.onError(response.body().getMessage());
                    }
                }else {
                    listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                }
            }

            @Override
            public void onFailure(retrofit2.Call<BaseCallBack> call, Throwable t) {
                listener.onError(ParentApplication.getContext().getString(R.string.net_error));
            }

        });
    }

    private String CreateJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("type", "parent");
            jsonObject.put("remarks", "kig_job");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}
