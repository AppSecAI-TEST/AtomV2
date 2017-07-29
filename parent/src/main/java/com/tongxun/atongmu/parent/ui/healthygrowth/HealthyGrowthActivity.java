package com.tongxun.atongmu.parent.ui.healthygrowth;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class HealthyGrowthActivity extends Base2Activity<IHealthyGrowthContract.View, HealthyGrowthPresenter> implements IHealthyGrowthContract.View {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.wb_growth)
    WebView wbGrowth;

    KProgressHUD hud;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private boolean isDownLoading=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_growth);
        ButterKnife.bind(this);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);

        wbGrowth.getSettings().setJavaScriptEnabled(true);
        wbGrowth.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wbGrowth.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                hud.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hud.dismiss();
            }
        });
        String date = dateFormat.format(new Date());
        tvTitleName.setText(date);
        mPresenter.getHealthyGrowth(date);
    }

    @Override
    protected HealthyGrowthPresenter initPresenter() {
        return new HealthyGrowthPresenter();
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String grownUrl) {
        wbGrowth.loadUrl(grownUrl);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (wbGrowth.canGoBack()) {
            wbGrowth.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.iv_title_back, R.id.tv_title_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_title_name:
                showDatePickDialog();
                break;
        }
    }

    private void showDatePickDialog() {
        Calendar c = Calendar.getInstance();

        try {
            Date date=dateFormat.parse(tvTitleName.getText().toString());
            c.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear, int startDayOfMonth) {
                String str=startYear+"-"+(startMonthOfYear+1)+"-"+startDayOfMonth;

                try {
                    Date date=dateFormat.parse(str);
                    String strdate=dateFormat.format(date);
                    tvTitleName.setText(strdate);
                    mPresenter.getHealthyGrowth(strdate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE)).show();
    }
}
