package com.tongxun.atongmu.teacher.ui.notice;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.teacher.Constants;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.application.TeacherApplication;
import com.tongxun.atongmu.teacher.model.ActivityListCallBack;
import com.tongxun.atongmu.teacher.model.BaseCallBack;
import com.tongxun.atongmu.teacher.model.NewsListCallBack;
import com.tongxun.atongmu.teacher.model.NoticeListCallBack;
import com.tongxun.atongmu.teacher.util.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Anro on 2017/8/2.
 */

public class NoticeListInteractor implements INoticeListContract.Interactor {

    @Override
    public void getListDate(final String type, final onFinishListener listener) {
        String url = null;
        if(type.equals("Notice")){
            url= Constants.restGeTeacherNoticeListNew_v2;
        }else if(type.equals("Activity")){
            url= Constants.restGetTeacherActivityListNew_v2;
        }else if(type.equals("News")){
            url= Constants.restGetTeacherNewList_v2;
        }
        if(url==null){
            return;
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
                        listener.onError(TeacherApplication.getContext().getString(R.string.net_error));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        if(type.equals("Notice")){
                            NoticeListCallBack callBack= null;
                            try {
                                callBack = gson.fromJson(response,NoticeListCallBack.class);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                            if(callBack!=null){
                                if(callBack.getStatus().equals("success")){
                                    listener.onSuccess(callBack.getData());
                                }else {
                                    listener.onError(callBack.getMessage());
                                }
                            }else {
                                listener.onError(TeacherApplication.getContext().getString(R.string.date_error));
                            }
                        }else if(type.equals("Activity")){
                            ActivityListCallBack callBack= null;
                            try {
                                callBack = gson.fromJson(response,ActivityListCallBack.class);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                            if(callBack!=null){
                                if(callBack.getStatus().equals("success")){
                                    listener.onSuccess(callBack.getData());
                                }else {
                                    listener.onError(callBack.getMessage());
                                }
                            }else {
                                listener.onError(TeacherApplication.getContext().getString(R.string.date_error));
                            }
                        }else if(type.equals("News")){
                            NewsListCallBack callBack= null;
                            try {
                                callBack = gson.fromJson(response,NewsListCallBack.class);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                            if(callBack!=null){
                                if(callBack.getStatus().equals("success")){
                                    listener.onSuccess(callBack.getData());
                                }else {
                                    listener.onError(callBack.getMessage());
                                }
                            }else {
                                listener.onError(TeacherApplication.getContext().getString(R.string.date_error));
                            }
                        }

                    }
                });
    }

    @Override
    public void loadMoreListDate(final String type, String createDate, final onFinishListener listener) {
        String url = null;
        if(type.equals("Notice")){
            url= Constants.restGeTeacherNoticeListPreDate_v2;
        }else if(type.equals("Activity")){
            url= Constants.restGetTeacherActivityPreListDate_v2;
        }else if(type.equals("News")){
            url= Constants.restGetTeacherNewListPreDate_v2;
        }
        if(url==null){
            return;
        }
        OkHttpUtils.postString()
                .url(url)
                .content(CreateLoadMoreJson(type,createDate))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onError(TeacherApplication.getContext().getString(R.string.net_error));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        if(type.equals("Notice")){
                            NoticeListCallBack callBack= null;
                            try {
                                callBack = gson.fromJson(response,NoticeListCallBack.class);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                            if(callBack!=null){
                                if(callBack.getStatus().equals("success")){
                                    listener.onLoadMoreSuccess(callBack.getData());
                                }else {
                                    listener.onError(callBack.getMessage());
                                }
                            }else {
                                listener.onError(TeacherApplication.getContext().getString(R.string.date_error));
                            }
                        }else if(type.equals("Activity")){
                            ActivityListCallBack callBack= null;
                            try {
                                callBack = gson.fromJson(response,ActivityListCallBack.class);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                            if(callBack!=null){
                                if(callBack.getStatus().equals("success")){
                                    listener.onLoadMoreSuccess(callBack.getData());
                                }else {
                                    listener.onError(callBack.getMessage());
                                }
                            }else {
                                listener.onError(TeacherApplication.getContext().getString(R.string.date_error));
                            }
                        }else if(type.equals("News")){
                            NewsListCallBack callBack= null;
                            try {
                                callBack = gson.fromJson(response,NewsListCallBack.class);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                            if(callBack!=null){
                                if(callBack.getStatus().equals("success")){
                                    listener.onLoadMoreSuccess(callBack.getData());
                                }else {
                                    listener.onError(callBack.getMessage());
                                }
                            }else {
                                listener.onError(TeacherApplication.getContext().getString(R.string.date_error));
                            }
                        }

                    }
                });
    }

    @Override
    public void upRedPoint(final String type, String statusId, final onFinishListener listener) {
        String url = null;
        if(type.equals("Notice")){
            url= Constants.restSetTeacherNoticeRead;
        }else if(type.equals("Activity")){
            url= Constants.restSetTeacherActivityRead;
        }else if(type.equals("News")){
            url= Constants.restSetTeacherNewRead;
        }
        if(url==null){
            return;
        }
        OkHttpUtils.postString()
                .url(url)
                .content(CreateRedPointJson(type,statusId))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onError(TeacherApplication.getContext().getString(R.string.net_error));
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
                                listener.onUpRedPointSuccess();
                            }else {
                                listener.onError(callBack.getMessage());
                            }
                        }else {
                            listener.onError(TeacherApplication.getContext().getString(R.string.date_error));
                        }

                    }
                });

    }

    private String CreateRedPointJson(String type, String statusId) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            switch (type){
                case "Notice":
                    jsonObject.put("noticePersonStatusId", statusId);
                    break;
                case "News":
                    jsonObject.put("newPersonStatusId", statusId);
                    break;
                case "Activity":
                    jsonObject.put("activityPersonStatusId", statusId);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    private String CreateLoadMoreJson(String type, String createDate) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            switch (type){
                case "Notice":
                    jsonObject.put("noticeCreateDate", createDate);
                    break;
                case "News":
                    jsonObject.put("newsCreateDate", createDate);
                    break;
                case "Activity":
                    jsonObject.put("activityDate", createDate);
                    break;
            }
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
}
