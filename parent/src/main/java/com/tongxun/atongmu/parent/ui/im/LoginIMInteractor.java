package com.tongxun.atongmu.parent.ui.im;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.model.ContactModel;
import com.tongxun.atongmu.parent.model.IMCallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by admin on 2017/7/30.
 */

public class LoginIMInteractor implements ILoginIMInteractor  {


    private String CreateJson(String tokenId) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId",tokenId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    public void LoginIM(String tokenId, final onFinishListener listener) {
        String url= Constants.restGetIMMsg;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson(tokenId))
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
                        IMCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,IMCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                listener.onIMSuccess(callBack.getGroupId(),callBack.getImNickName(),callBack.getImUsername(),callBack.getImPassword());
                                try {
                                    DataSupport.deleteAll(ContactModel.class);
                                    DataSupport.saveAll(callBack.getContact_list());
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
}
