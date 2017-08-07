package com.tongxun.atongmu.parent.ui.notice;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.dialog.CommonDialog;
import com.tongxun.atongmu.parent.model.BaseCallBack;
import com.tongxun.atongmu.parent.ui.PhotoViewActivity;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.tongxun.atongmu.parent.util.SystemUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 活动报名页面
 */
public class ActivityRegisterActivity extends BaseActivity {

    private static final int REQ_CODE = 10001;
    @BindView(R.id.toolbar_right_text)
    TextView toolbarRightText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.wv_activity_info)
    WebView wvActivityInfo;

    private String statusId;
    private String url;
    private String endDate;

    KProgressHUD hud;

    private boolean isSignUp=false;//是否已经报名

    private CommonDialog commonDialog;

    private String mPhone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("isSignUp",isSignUp);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        Intent intent = getIntent();
        try {
            statusId = intent.getStringExtra("statusId");
            url = intent.getStringExtra("url");
            endDate = intent.getStringExtra("endDate");
            isSignUp = intent.getBooleanExtra("isSignUp",false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);


        wvActivityInfo.getSettings().setJavaScriptEnabled(true);
        wvActivityInfo.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wvActivityInfo.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hud.dismiss();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                hud.show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.contains("callPhone")){
                    int i=url.indexOf("#");
                    mPhone=url.substring(i+1);
                    commonDialog=new CommonDialog(ActivityRegisterActivity.this, getResources().getString(R.string.is_call_phone) + mPhone + "?", getResources().getString(R.string.confirm), getResources().getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
                        @Override
                        public void go() {
                            PermissionGen.with(ActivityRegisterActivity.this)
                                    .addRequestCode(Constants.PERMISSION_PHONE_CODE)
                                    .permissions(
                                            Manifest.permission.CALL_PHONE
                                    )
                                    .request();
                            commonDialog.dismiss();
                        }

                        @Override
                        public void cancel() {
                            commonDialog.dismiss();
                        }
                    });
                    commonDialog.show();
                }
                if(url.contains("#image")){
                    String s = url.substring(0, url.lastIndexOf("#"));
                    ArrayList<String> list=new ArrayList<String>();
                    list.add(s);
                    PhotoViewActivity.startActivity(ActivityRegisterActivity.this,list);
                }
                return true;
            }
        });
        wvActivityInfo.loadUrl(url);
        setActivityTextUI();
    }

    private void setActivityTextUI() {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date=format.parse(endDate);
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE,1);
            Calendar now=Calendar.getInstance();
            if(calendar.before(now)){
                toolbarRightText.setVisibility(View.GONE);
            }else {
                toolbarRightText.setVisibility(View.VISIBLE);
                refreshTitleUI();

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void refreshTitleUI() {
        if (isSignUp) {
            toolbarRightText.setText(getResources().getString(R.string.cancel_register));
        } else {
            toolbarRightText.setText(getResources().getString(R.string.activity_register));
        }
    }

    @OnClick(R.id.toolbar_right_text)
    public void onViewClicked() {
        if(isSignUp){
            //弹出对话框 点击确认取消报名
            String msg = "是否取消报名?";
            commonDialog = new CommonDialog(ActivityRegisterActivity.this, msg,getResources().getString(R.string.confirm),getResources().getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
                @Override
                public void go() {
                    hud.show();
                    cancelSignInActivity();
                    commonDialog.dismiss();
                }

                @Override
                public void cancel() {
                    commonDialog.dismiss();
                }
            });
            commonDialog.show();

        }else {
            Intent intent=new Intent(this,ActivityRemarkActivity.class);
            intent.putExtra("activityId",statusId);
            startActivityForResult(intent,REQ_CODE);
        }
    }

    private void cancelSignInActivity() {
        String posturl= Constants.restSetParentActivityActCancel;
        OkHttpUtils.postString()
                .url(posturl)
                .content(CreateJson())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toasty.error(ActivityRegisterActivity.this, ParentApplication.getContext().getString(R.string.net_error), Toast.LENGTH_SHORT).show();
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
                                if(isSignUp){
                                    isSignUp=false;
                                }else {
                                    isSignUp=true;
                                }
                                refreshTitleUI();
                                wvActivityInfo.loadUrl(url);
                            }else {
                                Toasty.error(ActivityRegisterActivity.this,callBack.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toasty.error(ActivityRegisterActivity.this, ParentApplication.getContext().getString(R.string.date_error), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private String CreateJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("activityPersonStatusId",statusId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode==REQ_CODE){
                if(isSignUp){
                    isSignUp=false;
                }else {
                    isSignUp=true;
                }
                refreshTitleUI();
                wvActivityInfo.loadUrl(url);
            }
        }
    }

    @PermissionSuccess(requestCode = 104)
    public void doPhone() {
        SystemUtil.openSystemPhone(ActivityRegisterActivity.this,mPhone);
    }

    @PermissionFail(requestCode = 104)
    public void doSomeError() {
        Toasty.error(this, getResources().getString(R.string.get_phone_permission_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Intent intent=new Intent();
            intent.putExtra("isSignUp",isSignUp);
            setResult(RESULT_OK,intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
