package com.tongxun.atongmu.parent.ui.schooltuition;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.model.PayOrderModel;
import com.tongxun.atongmu.parent.model.WxPayCallBack;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Anro on 2017/7/18.
 */

public class PaySchoolOrderInteractor implements IPaySchoolOrderContract.Interactor {

    @Override
    public void createOrder(String packgId, final String type, final onFinishListener listener) {
        String url= Constants.restGetStudentTuitionOrder_android;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson(packgId,type))
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
                        if(type.equals("alipay")){
                            PayOrderModel callBack= null;
                            try {
                                callBack = gson.fromJson(response,PayOrderModel.class);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                            if(callBack!=null){
                                if(callBack.getStatus().equals("success")){
                                    listener.onSuccess(callBack.getData().getOrderString());
                                }else {
                                    listener.onError(callBack.getMessage());
                                }
                            }else {
                                listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                            }
                        }else {
                            WxPayCallBack callBack= null;
                            try {
                                callBack = gson.fromJson(response,WxPayCallBack.class);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                            if(callBack!=null){
                                if(callBack.getStatus().equals("success")){
                                    listener.onWxSuccess(callBack.getData());
                                }else {
                                    listener.onError(callBack.getMessage());
                                }
                            }else {
                                listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                            }
                        }

                    }
                });
    }

    private String CreateJson(String packgId, String type) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("packgId", packgId);
            jsonObject.put("payType", type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
