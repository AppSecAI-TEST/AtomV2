package com.tongxun.atongmu.parent.ui.babysign;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class AskForLeaveActivity extends Base2Activity<IAskForLeaveContract.View, AskForLeavePresenter> implements IAskForLeaveContract.View {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.ll_start_time)
    LinearLayout llStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.ll_end_time)
    LinearLayout llEndTime;
    @BindView(R.id.tv_personal_leave)
    TextView tvPersonalLeave;
    @BindView(R.id.tv_sick_leave)
    TextView tvSickLeave;
    @BindView(R.id.ll_title_homework)
    LinearLayout llTitleHomework;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    private KProgressHUD hud;

    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

    private String type="病假";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_for_leave);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorWhite);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);

        setLeaveType(1);
    }

    @Override
    protected AskForLeavePresenter initPresenter() {
        return new AskForLeavePresenter();
    }

    @Override
    public void showProgress() {
        hud.show();
    }

    @Override
    public void hideProgress() {
        hud.dismiss();
    }

    @OnClick({R.id.iv_title_back, R.id.ll_start_time, R.id.ll_end_time, R.id.tv_personal_leave, R.id.tv_sick_leave, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.ll_start_time:
                showDatePickDialog(0);
                break;
            case R.id.ll_end_time:
                showDatePickDialog(1);
                break;
            case R.id.tv_personal_leave:
                setLeaveType(0);
                break;
            case R.id.tv_sick_leave:
                setLeaveType(1);
                break;
            case R.id.btn_submit:
                showProgress();

                mPresenter.postSubmitLeave(tvStartTime.getText().toString(),tvEndTime.getText().toString(),type,etRemark.getText().toString());
                break;
        }
    }

    private void showDatePickDialog(final int i) {
        Calendar calendar = Calendar.getInstance();
        if(i==0){
           String start=tvStartTime.getText().toString();
            if(TextUtils.isEmpty(start)){

            }else {
                try {
                    Date startDate=simpleDateFormat.parse(start);
                    calendar.setTime(startDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }else {
            String end=tvEndTime.getText().toString();
            if(TextUtils.isEmpty(end)){

            }else {
                try {
                    Date endDate=simpleDateFormat.parse(end);
                    calendar.setTime(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(i==0){
                    tvStartTime.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                }else {
                    tvEndTime.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();


    }

    private void setLeaveType(int i) {
        resetLeaveType();
        switch (i) {
            case 0:
                type="事假";
                tvPersonalLeave.setSelected(true);
                break;
            case 1:
                type="病假";
                tvSickLeave.setSelected(true);
                break;
        }
    }

    private void resetLeaveType() {
        tvPersonalLeave.setSelected(false);
        tvSickLeave.setSelected(false);
    }

    @Override
    public void onError(String string) {
        hideProgress();
        Toasty.error(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        hideProgress();
        setResult(RESULT_OK);
        finish();
    }
}
