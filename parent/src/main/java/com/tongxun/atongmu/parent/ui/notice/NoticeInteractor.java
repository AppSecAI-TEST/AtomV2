package com.tongxun.atongmu.parent.ui.notice;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.model.NoticeCallBack;
import com.tongxun.atongmu.parent.model.NoticeModel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Anro on 2017/6/30.
 */

public class NoticeInteractor implements INoticeContract.Interactor {


    private static final String TAG = "NoticeInteractor";

    @Override
    public void getTopNotice(String type, final onFinishListener listener) {
        String url="";
        switch (type){
            case "Notice":
                url= Constants.restGetParentNoticeListNew_v2;
                break;
            case "News":
                url= Constants.restGetParentTopNew_v2;
                break;
            case "Activity":
                url= Constants.restGetParentActivityListNew_v2;
                break;
        }

        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onNoticeError(ParentApplication.getContext().getString(R.string.net_error));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        NoticeCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,NoticeCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                listener.onNoticeSuccess(callBack.getData());
                                if(callBack.getData().size()>0){
                                    try {
                                        DataSupport.deleteAll(NoticeModel.class);
                                        DataSupport.saveAll(callBack.getData());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }else {
                                listener.onNoticeError(callBack.getMessage());
                            }
                        }else {
                            listener.onNoticeError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    private String CreateJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
           // jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString("tokenId",""));
            jsonObject.put("tokenId","1f6ccd97-fb15-4ae8-944e-7ece9c109e4e");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    public void getSignUpWaiting(onFinishListener listener) {

    }
}
