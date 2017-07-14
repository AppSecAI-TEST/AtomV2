package com.tongxun.atongmu.parent.ui.album;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.model.MornCheckCallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Anro on 2017/7/13.
 */

public class MornCheckInteractor implements IMoreCheckContract.Interactor {
    @Override
    public void getTopMornCheckAlbum(final onFinishListener listener) {
        String url = Constants.restBrownStudentCheckPhoto_v2;

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
                        Gson gson = new Gson();
                        MornCheckCallBack callBack = null;
                        try {
                            callBack = gson.fromJson(response, MornCheckCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if (callBack != null) {
                            if (callBack.getStatus().equals("success")) {
                                listener.onSuccess(callBack.getData());
                            } else {
                                listener.onError(callBack.getMessage());
                            }
                        } else {
                            listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });

    }

    private String CreateJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
           // jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, ""));
            jsonObject.put("tokenId", "ba1654db-e475-47af-bc9e-68dc49a45c37");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}