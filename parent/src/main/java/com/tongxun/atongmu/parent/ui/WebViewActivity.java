package com.tongxun.atongmu.parent.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.ui.album.TimeAlbumActivity;
import com.tongxun.atongmu.parent.util.SharePopupWindow;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {


    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.iv_toolbar_share)
    ImageView ivToolbarShare;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
            isCanShare = intent.getBooleanExtra("isCanShare", false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (type.equals("white")) {
            setStatusColor(R.color.colorWhite);
            toolbar.setNavigationIcon(R.drawable.icon_back_yellow);
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        } else {
            setStatusColor(R.color.colorMainYellow);
            toolbar.setNavigationIcon(R.drawable.icon_back_white);
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorMainYellow));
        }

        if (isCanShare) {
            ivToolbarShare.setVisibility(View.VISIBLE);
            ivToolbarShare.setImageResource(R.drawable.icon_share_yellow);
        } else {
            ivToolbarShare.setVisibility(View.INVISIBLE);
        }

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);

        //tvToolbarTitle.setText(title);

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
                    String s = url.substring(0, url.lastIndexOf("#"));
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(s);
                    PhotoViewActivity.startActivity(WebViewActivity.this, list);
                    return true;
                }
                if(url.contains("jumpPhoto")){
                    Intent intent=new Intent(WebViewActivity.this,TimeAlbumActivity.class);
                    startActivity(intent);
                    return true;
                }
                if(url.contains("shareHtmlPath")){
                    ivToolbarShare.setVisibility(View.GONE);
                    return false;
               }
                return false;
            }
        });
        wvWebInfo.loadUrl(url);

        ivToolbarShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePopupWindow.getInstance().show(llWebView, title, content, shareUrl, imageUrl);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK&&wvWebInfo.canGoBack()){
            wvWebInfo.goBack();//返回上个页面
            if(isCanShare){
                ivToolbarShare.setVisibility(View.VISIBLE);
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
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.putExtra("imageUrl", imageUrl);
        intent.putExtra("url", url);
        intent.putExtra("shareUrl", shareUrl);
        intent.putExtra("isCanShare", isCanShare);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }


}
