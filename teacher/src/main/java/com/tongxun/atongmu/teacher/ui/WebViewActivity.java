package com.tongxun.atongmu.teacher.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.teacher.BaseActivity;
import com.tongxun.atongmu.teacher.Constants;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.dialog.CommonDialog;
import com.tongxun.atongmu.teacher.ui.notice.NoReadActivity;
import com.tongxun.atongmu.teacher.util.SharePopupWindow;
import com.tongxun.atongmu.teacher.util.SystemUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class WebViewActivity extends BaseActivity {


    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.ll_title)
    RelativeLayout llTitle;
    @BindView(R.id.wv_web_info)
    WebView wvWebInfo;
    @BindView(R.id.ll_web_view)
    LinearLayout llWebView;
    private String title;
    private String url;
    private String shareUrl;
    private boolean isCanShare;

    KProgressHUD hud;
    private String type = "white";
    private String content = "";
    private String imageUrl = "";
    private String action="";
    private String value="";

    private CommonDialog commonDialog;

    private String mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        try {
            title = intent.getStringExtra("title");
            url = intent.getStringExtra("url");
            shareUrl = intent.getStringExtra("shareUrl");
            type = intent.getStringExtra("type");
            content = intent.getStringExtra("content");
            imageUrl = intent.getStringExtra("imageUrl");
            action = intent.getStringExtra("action");
            value = intent.getStringExtra("value");
            isCanShare = intent.getBooleanExtra("isCanShare", false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (type.equals("white")) {
            setStatusColor(R.color.colorWhite);
            ivTitleBack.setImageResource(R.drawable.icon_back_yellow);
            llTitle.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        } else {
            setStatusColor(R.color.colorMainYellow);
            ivTitleBack.setImageResource(R.drawable.icon_back_white);
            llTitle.setBackgroundColor(getResources().getColor(R.color.colorMainYellow));
        }

        if (isCanShare) {
            ivTitleRight.setVisibility(View.VISIBLE);
            ivTitleRight.setImageResource(R.drawable.icon_share_yellow);
        } else {
            ivTitleRight.setVisibility(View.INVISIBLE);
        }

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);

        tvTitleName.setText(title);

        wvWebInfo.getSettings().setJavaScriptEnabled(true);
        wvWebInfo.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wvWebInfo.setWebViewClient(new WebViewClient() {
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
                if (url.contains("#image")) {
                   /* String s = url.substring(0, url.lastIndexOf("#"));
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(s);
                    PhotoViewActivity.startActivity(WebViewActivity.this, list);*/
                    return true;
                }
                if (url.contains("jumpPhoto")) {
                    /*Intent intent = new Intent(WebViewActivity.this, TimeAlbumActivity.class);
                    startActivity(intent);*/
                    return true;
                }
                if (url.contains("shareHtmlPath")) {
                    ivTitleRight.setVisibility(View.GONE);
                    return false;
                }

                if(url.contains("callPhone")){
                    int i=url.indexOf("#");
                    mPhone=url.substring(i+1);
                    commonDialog=new CommonDialog(WebViewActivity.this, getResources().getString(R.string.is_call_phone) + mPhone + "?", getResources().getString(R.string.confirm), getResources().getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
                        @Override
                        public void go() {
                            PermissionGen.with(WebViewActivity.this)
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
                    return true;
                }
                if(url.contains("noRead")){
                    Intent intent1=new Intent(WebViewActivity.this, NoReadActivity.class);
                    intent1.putExtra("action",action);
                    intent1.putExtra("sourceId",value);
                    intent1.putExtra("position",1);
                    startActivity(intent1);
                    return true;
                }
                if(url.contains("havenAct")){
                    Intent intent1=new Intent(WebViewActivity.this, NoReadActivity.class);
                    intent1.putExtra("action",action);
                    intent1.putExtra("sourceId",value);
                    intent1.putExtra("position",0);
                    startActivity(intent1);
                    return true;
                }


                return false;
            }
        });
        wvWebInfo.loadUrl(url);



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && wvWebInfo.canGoBack()) {
            wvWebInfo.goBack();//返回上个页面
            if (isCanShare) {
                ivTitleRight.setVisibility(View.VISIBLE);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static void startWebViewActivity(Context context, String title, String content, String imageUrl, String url) {
        startWebViewActivity(context, title, content, imageUrl, url, "white");
    }

    public static void startWebViewActivity(Context context, String title, String content, String imageUrl, String url, String type) {
        startWebViewActivity(context, title, content, imageUrl, url, type, false);
    }

    public static void startWebViewActivity(Context context, String title, String content, String imageUrl, String url, String type, boolean isCanShare) {
        startWebViewActivity(context, title, content, imageUrl, url, type, isCanShare, url);
    }

    public static void startWebViewActivity(Context context, String title, String content, String imageUrl, String url, String type, boolean isCanShare, String shareUrl) {
       startWebViewActivity(context,title,content,imageUrl,url,type,isCanShare,shareUrl,"");
    }
    public static void startWebViewActivity(Context context, String title, String content, String imageUrl, String url, String type, boolean isCanShare, String shareUrl,String action) {
        startWebViewActivity(context,title,content,imageUrl,url,type,isCanShare,shareUrl,"","");
    }
    public static void startWebViewActivity(Context context, String title, String content, String imageUrl, String url, String type, boolean isCanShare, String shareUrl,String action,String value) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.putExtra("imageUrl", imageUrl);
        intent.putExtra("url", url);
        intent.putExtra("shareUrl", shareUrl);
        intent.putExtra("isCanShare", isCanShare);
        intent.putExtra("type", type);
        intent.putExtra("action", action);
        intent.putExtra("value", value);
        context.startActivity(intent);
    }


    @OnClick({R.id.iv_title_back, R.id.iv_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.iv_title_right:
                SharePopupWindow.getInstance().show(llWebView, title, content, shareUrl, imageUrl);
                break;
        }
    }


    @PermissionSuccess(requestCode = 104)
    public void doPhone() {
        SystemUtil.openSystemPhone(WebViewActivity.this,mPhone);
    }

    @PermissionFail(requestCode = 104)
    public void doSomeError() {
        Toasty.error(this, getResources().getString(R.string.get_phone_permission_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }
}
