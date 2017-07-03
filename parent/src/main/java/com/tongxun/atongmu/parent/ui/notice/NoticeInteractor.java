package com.tongxun.atongmu.parent.ui.notice;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.model.BaseCallBack;
import com.tongxun.atongmu.parent.model.NoticeCallBack;
import com.tongxun.atongmu.parent.model.SignWaitCallBack;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

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
                            }else {
                                listener.onNoticeError(callBack.getMessage());
                            }
                        }else {
                            listener.onNoticeError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    @Override
    public void getMoreNotice(String type, String time, final onFinishListener listener) {
        String url="";
        switch (type){
            case "Notice":
                url= Constants.restGetParentNoticeListPreDate_v2;
                break;
            case "News":
                url= Constants.restGetParentNewListPreDate_v2;
                break;
            case "Activity":
                url= Constants.restGetParentActivityPreListDate_v2;
                break;
        }

        OkHttpUtils.postString()
                .url(url)
                .content(CreateMoreJson(time))
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
                                listener.onMoreNoticeSuccess(callBack.getData());
                            }else {
                                listener.onNoticeError(callBack.getMessage());
                            }
                        }else {
                            listener.onNoticeError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    private String CreateMoreJson(String time) {

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("noticeCreateDate", time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    private String CreateJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    public void getSignUpWaiting(final onFinishListener listener) {
        String url=Constants.restGetStudentAgenAll;
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
                        SignWaitCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,SignWaitCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                listener.onSignUpSuccess(callBack.getDatas());
                            }else {
                                listener.onNoticeError(ParentApplication.getContext().getString(R.string.date_error));
                            }
                        }else {
                            listener.onNoticeError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    @Override
    public void onConfirmSignUp(String ageId, final onFinishListener listener) {
        String url=Constants.restSetStudentAgen;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateConfirmJson(ageId))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onConfirmError(ParentApplication.getContext().getString(R.string.net_error));
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
                                listener.onConfirmSuccess();
                            }else {
                                listener.onConfirmError(ParentApplication.getContext().getString(R.string.date_error));
                            }
                        }else {
                            listener.onConfirmError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    private String CreateConfirmJson(String ageId) {
        JSONObject jsonObject=null;
        try {
            jsonObject=new JSONObject();
            jsonObject.put("tokenId",SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("sourceId",ageId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
