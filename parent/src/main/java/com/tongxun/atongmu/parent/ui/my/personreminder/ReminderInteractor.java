package com.tongxun.atongmu.parent.ui.my.personreminder;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.http.RetrofitHelper;
import com.tongxun.atongmu.parent.model.BaseCallBack;
import com.tongxun.atongmu.parent.model.MedicineCallBack;
import com.tongxun.atongmu.parent.util.RxPartMapUtils;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Anro on 2017/7/29.
 */

public class ReminderInteractor implements IAddReminderInteractor {

    @Override
    public void getReminderList(final onFinishListener listener) {
        String url= Constants.restGetStudentMedicine_v2;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        MedicineCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,MedicineCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                listener.onGetReminderSuccess(callBack.getMedicine());
                            }else {
                                listener.onError(callBack.getMessage());
                            }
                        }else {
                            listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
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
    public void createNewReminder(String remark, String starttime, String days, List<String> mlist, final onFinishListener listener) {
        String url=Constants.restPutStudentMedicine_v2;
        MultipartBody.Builder builder=new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        builder.addFormDataPart("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
        builder.addFormDataPart("notes",remark);
        builder.addFormDataPart("start_time",starttime);
        builder.addFormDataPart("medicine_days",days);
        builder.addFormDataPart("ishaveImg","true");
        for(int i=1;i<mlist.size();i++){
            File file=new File(mlist.get(i));
            builder.addFormDataPart(("sourceImage"+i), file.getName(), RxPartMapUtils.toRequestBodyOfDateImage(file));
        }

        List<MultipartBody.Part> parts = builder.build().parts();



        RetrofitHelper.getInstance().getServer().postFeedBack(url,parts).enqueue(new Callback<BaseCallBack>() {
            @Override
            public void onResponse(Call<BaseCallBack> call, Response<BaseCallBack> response) {
                if(response.body()!=null){
                    if(response.body().getStatus().equals("success")){
                        listener.onPostReminderSuccess();
                    }else {
                        listener.onError(response.body().getMessage());
                    }
                }else {
                    listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                }


            }

            @Override
            public void onFailure(Call<BaseCallBack> call, Throwable t) {
                listener.onError(ParentApplication.getContext().getString(R.string.net_error));
            }
        });
    }

    @Override
    public void deleteReminder(String note_id, final onFinishListener listener) {
        String url=Constants.restDeleteStudentMedicine_v2;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateDeleteJson(note_id))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {

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
                                listener.onDeleteReminderSuccess();
                            }else {
                                listener.onError(callBack.getMessage());
                            }
                        }else {
                            listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    private String CreateDeleteJson(String note_id) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("note_id",note_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
