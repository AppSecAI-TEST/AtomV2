package com.tongxun.atongmu.teacher.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.hyphenate.util.DensityUtil;
import com.tongxun.atongmu.teacher.R;


/**
 * Created by Jayce on 2016/6/20.
 */
public class CommonDialog extends BaseDialog implements View.OnClickListener{
    private GoCommonDialog goCommonDialog;
    private String title,confirmStr,cancelStr;
    private TextView tvDiaTitle,tvDiaConfirm,tvDiaCancel;

    public CommonDialog(Context context, String title
            , String confirmStr, String cancelStr, GoCommonDialog goCommonDialog){
        super(context, View.inflate(context, R.layout.dlg_common, null), new LayoutParams(DensityUtil.dip2px(context, 280), DensityUtil.dip2px(context,
                LayoutParams.WRAP_CONTENT)));
        this.goCommonDialog = goCommonDialog;
        this.title = title;
        this.confirmStr = confirmStr;
        this.cancelStr = cancelStr;
        init();
    }

    private void init() {
        tvDiaTitle = (TextView) findViewById(R.id.tv_common_dialog_title);
        tvDiaConfirm = (TextView) findViewById(R.id.btn_confirm);
        tvDiaCancel = (TextView) findViewById(R.id.btn_cancel);
        tvDiaTitle.setText(title);
        tvDiaConfirm.setText(confirmStr);
        tvDiaCancel.setText(cancelStr);
        tvDiaConfirm.setOnClickListener(this);
        tvDiaCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                goCommonDialog.go();
                break;
            case R.id.btn_cancel:
                goCommonDialog.cancel();
                break;
            default:
                break;
        }
    }
    public interface GoCommonDialog{
        void go();
        void cancel();
    }
}
