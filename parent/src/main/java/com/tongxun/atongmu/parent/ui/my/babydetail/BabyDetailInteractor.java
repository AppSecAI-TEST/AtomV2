package com.tongxun.atongmu.parent.ui.my.babydetail;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.model.BaseCallBack;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.File;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Anro on 2017/7/28.
 */

public class BabyDetailInteractor implements IBabyDetailContract.Interactor {


    @Override
    public void setBabyFace(String path, final onFinishListener listener) {
        String url= Constants.restUploadFile;
        File file=new File(path);
        OkHttpUtils.post()
                .url(url)
                .addParams("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""))
                .addParams("targetId","")
                .addParams("targetType","studentInfo")
                .addFile("sourceImg",file.getName(),file)
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
                                listener.onError(callBack.getMessage());
                            }
                        }else {
                            listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    @Override
    public void postBirthDay(String pickDate, final onFinishListener listener) {
        String url=Constants.restUpdateStudentInfo_v2;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson(pickDate))
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
                                listener.onBirthSuccess();
                            }else {
                                listener.onError(callBack.getMessage());
                            }
                        }else {
                            listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    private String CreateJson(String pickDate) {
        JSONObject jsonObject=null;
        try {
            jsonObject=new JSONObject();
            jsonObject.put("tokenId",SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("birthDate",pickDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
