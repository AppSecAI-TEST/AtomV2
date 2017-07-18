package com.tongxun.atongmu.parent.ui.schooltuition;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.model.PayItemModel;
import com.tongxun.atongmu.parent.model.TuitionCallBack;
import com.tongxun.atongmu.parent.model.TuitionModel;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Anro on 2017/7/17.
 */

public class SchoolTuitionInteractor implements ISchoolTuitionContract.Interactor {
    /**
     * 缴费通知
     * @param listener
     */
    @Override
    public void getPayNotice(final onFinishListener listener) {
        String url= Constants.restGetStudentTuitionList;
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
                        TuitionCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,TuitionCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                TuitionModel tuitionModel=new TuitionModel();
                                tuitionModel.setCreateTime("2017-10-24");
                                tuitionModel.setItemId("123456");
                                tuitionModel.setItemTitle("第一学期缴费");
                                tuitionModel.setRemark("这次所有人都要交钱,不交钱打死");
                                tuitionModel.setSchoolName("江苏童讯幼儿园");
                                tuitionModel.setTotalNum("￥1200");
                                List<PayItemModel> itemListBeen=new ArrayList<PayItemModel>();
                                PayItemModel bean=new PayItemModel();
                                bean.setNumber("￥12.00");
                                bean.setTitle("校服");
                                itemListBeen.add(bean);
                                tuitionModel.setItemList(itemListBeen);
                                List<TuitionModel> list=new ArrayList<TuitionModel>();
                                list.add(tuitionModel);
                                listener.onSuccess(list);
                              //  listener.onSuccess(callBack.getData());
                            }else {
                                listener.onError(callBack.getMessage());
                            }
                        }else {
                            listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    /**
     * 获取订单记录
     * @param listener
     */
    @Override
    public void getPayRecord(final onFinishListener listener) {
        String url= Constants.restGetTuitionCompletedList;
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
                        /*Gson gson=new Gson();
                        TuitionCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,TuitionCallBack.class);
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
                            listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                        }*/
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
}
