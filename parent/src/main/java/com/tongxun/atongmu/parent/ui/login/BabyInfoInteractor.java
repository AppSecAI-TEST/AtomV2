package com.tongxun.atongmu.parent.ui.login;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.model.BabyInfoCallBack;
import com.tongxun.atongmu.parent.model.BabyInfoModel;
import com.tongxun.atongmu.parent.model.TokenIdModel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Anro on 2017/7/25.
 */

public class BabyInfoInteractor implements IBabyInfoContract.Interactor {

    @Override
    public void getBabyInfo(final onFinishListener listener) {
        String url= Constants.restGetStudentInfoList;
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
                        BabyInfoCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,BabyInfoCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            listener.onSuccess();
                            if(callBack.getStatus().equals("success")){
                                try {
                                    DataSupport.deleteAll(BabyInfoModel.class);
                                    DataSupport.saveAll(callBack.getDatas());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
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
        List<TokenIdModel> tokenDataBeanList = DataSupport.findAll(TokenIdModel.class);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < tokenDataBeanList.size(); i++) {
                jsonArray.put(tokenDataBeanList.get(i).getTokenId());
            }
            jsonObject.put("sourceIdList", jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}


