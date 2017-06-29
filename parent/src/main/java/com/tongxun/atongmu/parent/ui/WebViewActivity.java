package com.tongxun.atongmu.parent.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.wv_web_info)
    WebView wvWebInfo;

    private String title;
    private String url;
    private boolean isCanShare;

    KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        setStatusColor(R.color.colorMainYellow);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        try {
            title=intent.getStringExtra("title");
            url=intent.getStringExtra("url");
            isCanShare=intent.getBooleanExtra("isCanShare",false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);

        toolbarTitle.setText(title);
        wvWebInfo.getSettings().setJavaScriptEnabled(true);
        wvWebInfo.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wvWebInfo.setWebViewClient(new WebViewClient(){
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
        });
        wvWebInfo.loadUrl(url);
    }

    public static void startWebViewActivity(Context context,String title,String url,boolean isCanShare){
        Intent intent=new Intent(context,WebViewActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("url",url);
        intent.putExtra("isCanShare",isCanShare);
        context.startActivity(intent);
    }
}
