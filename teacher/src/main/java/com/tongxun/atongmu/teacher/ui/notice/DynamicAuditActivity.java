package com.tongxun.atongmu.teacher.ui.notice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.teacher.BaseActivity;
import com.tongxun.atongmu.teacher.Constants;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.model.BaseCallBack;
import com.tongxun.atongmu.teacher.util.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.MediaType;

public class DynamicAuditActivity extends BaseActivity {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.wv_web_info)
    WebView wvWebInfo;
    @BindView(R.id.check_pass_imgview)
    TextView checkPassImgview;
    @BindView(R.id.check_unpass_imgview)
    TextView checkUnpassImgview;

    private String url;
    private String type;
    private String sourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_audit);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        url=intent.getStringExtra("url");
        type=intent.getStringExtra("type");
        sourceId=intent.getStringExtra("sourceId");
        //// TODO: 2017/8/3 获取老师身份判断能否审核
        wvWebInfo.loadUrl(url);
    }

    @OnClick({R.id.iv_title_back, R.id.check_pass_imgview, R.id.check_unpass_imgview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.check_pass_imgview:
                passNotice();
                break;
            case R.id.check_unpass_imgview:
                unpassNotice();
                break;
        }
    }

    private void unpassNotice() {
        String url= Constants.restDelNoticeActivity;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Toasty.error(DynamicAuditActivity.this, getString(R.string.net_error), Toast.LENGTH_SHORT).show();
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
                                Toasty.success(DynamicAuditActivity.this,getString(R.string.audit_success), Toast.LENGTH_SHORT).show();
                                setResult(RESULT_OK);
                                finish();
                            }else {

                                Toasty.error(DynamicAuditActivity.this, callBack.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toasty.error(DynamicAuditActivity.this, getString(R.string.date_error), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }



    private void passNotice() {
        String url= Constants.restCheckNoticeActivity;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toasty.error(DynamicAuditActivity.this, getString(R.string.net_error), Toast.LENGTH_SHORT).show();
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
                                Toasty.success(DynamicAuditActivity.this,getString(R.string.audit_success), Toast.LENGTH_SHORT).show();
                                setResult(RESULT_OK);
                                finish();
                            }else {

                                Toasty.error(DynamicAuditActivity.this, callBack.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toasty.error(DynamicAuditActivity.this, getString(R.string.date_error), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private String CreateJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("type",type);
            jsonObject.put("sourceId",sourceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
