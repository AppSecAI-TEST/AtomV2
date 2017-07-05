package com.tongxun.atongmu.parent.ui.notice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 活动报名页面
 */
public class ActivityRegisterActivity extends BaseActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        try {
            statusId=intent.getStringExtra("statusId");
            url=intent.getStringExtra("url");
            endDate=intent.getStringExtra("endDate");
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
        });
        wvActivityInfo.loadUrl(url);

    }
}
