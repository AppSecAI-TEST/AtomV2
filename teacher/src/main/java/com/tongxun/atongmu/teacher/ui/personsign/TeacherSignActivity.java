package com.tongxun.atongmu.teacher.ui.personsign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.teacher.Base2Activity;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.adapter.TeacherSignAdapter;
import com.tongxun.atongmu.teacher.model.TeacherSignModel;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class TeacherSignActivity extends Base2Activity<ITeacherSignContract.View, TeacherSignPresenter> implements ITeacherSignContract.View {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.tv_check_left)
    TextView tvCheckLeft;
    @BindView(R.id.tv_check_right)
    TextView tvCheckRight;
    @BindView(R.id.ll_title_homework)
    LinearLayout llTitleHomework;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;

    private String selectDate = "";
    private String arrived="";
    private String unArrived="";
    private String classcId="";

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private int pagePosition=0;

    private TeacherSignAdapter arrivedAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_sign);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        selectDate=intent.getStringExtra("selectDate");
        arrived=intent.getStringExtra("arrived");
        unArrived=intent.getStringExtra("unArrived");
        classcId=intent.getStringExtra("classcId");

        tvTitleName.setText(selectDate);

        tvCheckLeft.setText("已到"+arrived+"人");
        tvCheckRight.setText("未到"+unArrived+"人");

        setPagePosition();

        mPresenter.getTeacherSign(classcId,selectDate);
    }

    private void setPagePosition() {
        resetPosition();
        switch (pagePosition){
            case 0:
                tvCheckLeft.setSelected(true);
                break;
            case 1:
                tvCheckRight.setSelected(true);
                break;

        }
    }

    private void resetPosition() {
        tvCheckLeft.setSelected(false);
        tvCheckRight.setSelected(false);
    }

    @Override
    protected TeacherSignPresenter initPresenter() {
        return new TeacherSignPresenter();
    }

    @OnClick({R.id.iv_title_back, R.id.tv_check_left, R.id.tv_check_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_check_left:
                pagePosition=0;
                setPagePosition();
                break;
            case R.id.tv_check_right:
                pagePosition=1;
                setPagePosition();
                break;
        }
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(List<TeacherSignModel> arrived, List<TeacherSignModel> unArrived) {
        arrivedAdapter=new TeacherSignAdapter(this,arrived);
        rvContent.setAdapter(arrivedAdapter);
    }
}
