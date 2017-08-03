package com.tongxun.atongmu.teacher.ui.notice;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.teacher.Constants;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.application.TeacherApplication;
import com.tongxun.atongmu.teacher.model.ActivityNoReadCallBack;
import com.tongxun.atongmu.teacher.model.NoticeNoReadCallBack;
import com.tongxun.atongmu.teacher.util.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Anro on 2017/8/2.
 */

public class NoReadInteractor implements INoReadContract.Interactor {
    @Override
    public void getNoReadList(final String action, String sourceId, final onFinishListener listener) {
        String url= "";
        if(action.equals("Notice")){
            url=Constants.restGetNoticeDetail_v2;
        }
        if(action.equals("Activity")){
            url=Constants.restGetTeacherActivityActList_v2;
        }
        if (url == null) {
            return;
        }
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson(sourceId))
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
                        if(action.equals("Notice")){
                            NoticeNoReadCallBack callBack= null;
                            try {
                                callBack = gson.fromJson(response,NoticeNoReadCallBack.class);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                            if(callBack!=null){
                                if(callBack.getStatus().equals("success")){
                                    listener.onSuccess(callBack.getNoReadStudents());
                                }else {
                                    listener.onError(callBack.getMessage());
                                }
                            }else {
                                listener.onError(TeacherApplication.getContext().getString(R.string.date_error));
                            }
                        }
                        if(action.equals("Activity")){
                            ActivityNoReadCallBack callBack= null;
                            try {
                                callBack = gson.fromJson(response,ActivityNoReadCallBack.class);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                            if(callBack!=null){
                                if(callBack.getStatus().equals("success")){
                                    listener.onActiviytSuccess(callBack.getHavenActStudents(),callBack.getNoReadActStudents());
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

    private String CreateJson(String sourceId) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("sourceId", sourceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
