package com.tongxun.atongmu.teacher.ui.personsign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannedString;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.teacher.Base2Activity;
import com.tongxun.atongmu.teacher.IonItemClickListener;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.adapter.PersonSignAdapter;
import com.tongxun.atongmu.teacher.model.PersonSignModel;
import com.tongxun.atongmu.teacher.util.RecycleViewDivider;
import com.tongxun.atongmu.teacher.widget.CircleSignInProgress;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class PersonSignActivity extends Base2Activity<IPersonContract.View, PersonSignPresenter> implements IPersonContract.View, IonItemClickListener {


    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.rl_director)
    RelativeLayout rlDirector;
    @BindView(R.id.sign_in_progress)
    CircleSignInProgress signInProgress;
    @BindView(R.id.tv_garden_sum)
    TextView tvGardenSum;
    @BindView(R.id.tv_arrived)
    TextView tvArrived;
    @BindView(R.id.tv_unarrived)
    TextView tvUnarrived;
    @BindView(R.id.tv_leave)
    TextView tvLeave;

    private PersonSignAdapter mAdapter;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private List<PersonSignModel> mlist = new ArrayList<>();

    private String selectDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_sign);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        selectDate = dateFormat.format(new Date());
        tvTitleName.setText(selectDate);
        mPresenter.getPersonSign(selectDate);

        setRecyclcerViewUI();
    }

    private void setRecyclcerViewUI() {
        rvContent.setItemAnimator(new DefaultItemAnimator());
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        rvContent.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.colorLineGray)));
    }

    @Override
    protected PersonSignPresenter initPresenter() {
        return new PersonSignPresenter();
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPersonSignSuccess(String identity, String total, String car, String arrived, String leave, String unArrived, String arrivedPre, String unArrivedPre, String leavePre, List<PersonSignModel> datas) {
        if (identity.equals("director")) {
            rlDirector.setVisibility(View.VISIBLE);
            signInProgress.setProgress(Float.parseFloat(arrivedPre), Float.parseFloat(unArrivedPre), Float.parseFloat(leavePre));
            SpannedString sumString = new SpannedString("全园宝宝应到" + total + "人  报名乘校车" + car + "人");
            tvGardenSum.setText(sumString);
            SpannedString arrivedString = new SpannedString("已到" + arrived + "人  " + Float.parseFloat(arrivedPre) * 100 + "%");
            tvArrived.setText(arrivedString);
            SpannedString unarrivedString = new SpannedString("未到" + unArrived + "人  " + Float.parseFloat(unArrivedPre) * 100 + "%");
            tvUnarrived.setText(unarrivedString);
            SpannedString leavetring = new SpannedString("请假" + leave + "人  " + Float.parseFloat(leavePre) * 100 + "%");
            tvLeave.setText(leavetring);
        } else {
            rlDirector.setVisibility(View.GONE);
        }

        mlist = datas;
        mAdapter = new PersonSignAdapter(this, mlist,this);
        rvContent.setAdapter(mAdapter);
    }

    @OnClick({R.id.iv_title_back, R.id.tv_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
        }
    }


    @Override
    public void onItemClick(int position) {
       if(mlist.get(position).getClassId().equals("gardenerAttendance")){
           Intent intent=new Intent(PersonSignActivity.this,TeacherSignActivity.class);
           intent.putExtra("selectDate",selectDate);
           intent.putExtra("unArrived",mlist.get(position).getClassUnArrived());
           intent.putExtra("arrived",mlist.get(position).getClassArrived());
           intent.putExtra("classcId",mlist.get(position).getClassId());
           startActivity(intent);
       }else {
           Intent intent=new Intent(PersonSignActivity.this,BabySignActivity.class);
           startActivity(intent);
       }
    }
}
