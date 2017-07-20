package com.tongxun.atongmu.parent.ui.notice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.model.BaseCallBack;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.MediaType;

public class ActivityRemarkActivity extends BaseActivity {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.huodong_baoming_edit)
    EditText huodongBaomingEdit;
    @BindView(R.id.huodong_baoming_complete)
    Button huodongBaomingComplete;

    private String activityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remark);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        try {
            Intent intent=getIntent();
            activityId=intent.getStringExtra("activityId");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.iv_title_back, R.id.huodong_baoming_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.huodong_baoming_complete:
                signInActivity();
                break;
        }
    }

    private void signInActivity() {
        String url= Constants.restSetParentActivityAct;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toasty.error(ActivityRemarkActivity.this, ParentApplication.getContext().getString(R.string.net_error), Toast.LENGTH_SHORT).show();
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
                                setResult(RESULT_OK);
                                finish();
                            }else {
                                Toasty.error(ActivityRemarkActivity.this,callBack.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toasty.error(ActivityRemarkActivity.this, ParentApplication.getContext().getString(R.string.date_error), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private String CreateJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("activityPersonStatusId", activityId);
            jsonObject.put("actRemark",huodongBaomingEdit.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
