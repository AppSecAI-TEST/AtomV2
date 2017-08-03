package com.tongxun.atongmu.teacher.ui.notice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.util.DensityUtil;
import com.tongxun.atongmu.teacher.Base2Activity;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.adapter.NoReadAdapter;
import com.tongxun.atongmu.teacher.model.ActivityHaveActModel;
import com.tongxun.atongmu.teacher.model.ActivityNoReadModel;
import com.tongxun.atongmu.teacher.model.NoReadModel;
import com.tongxun.atongmu.teacher.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class NoReadActivity extends Base2Activity<INoReadContract.View, NoReadPresenter> implements INoReadContract.View, NoReadItemClickListener {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_check_left)
    TextView tvCheckLeft;
    @BindView(R.id.tv_check_right)
    TextView tvCheckRight;
    @BindView(R.id.ll_title_check)
    LinearLayout llTitleCheck;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;

    private String action;
    private String sourceId;
    private int position;
    private List<NoReadModel> mlist=new ArrayList<>();
    private List<ActivityHaveActModel> haveActlist=new ArrayList<>();
    private List<ActivityNoReadModel> noReadlist=new ArrayList<>();

    private NoReadAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_read);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorWhite);
        Intent intent=getIntent();
        action=intent.getStringExtra("action");
        sourceId=intent.getStringExtra("sourceId");
        position=intent.getIntExtra("position",0);
        if(action.equals("Activity")){
            llTitleCheck.setVisibility(View.VISIBLE);
            tvCheckLeft.setText(getString(R.string.have_act));
            tvCheckRight.setText(getString(R.string.no_read));
            setSelectPostion(position);
        }else {
            tvTitleName.setVisibility(View.VISIBLE);
        }
        tvTitleName.setText(getString(R.string.no_read));
        mPresenter.getNoReadList(action,sourceId);
        setRecyclerView();

    }

    private void setSelectPostion(int i) {
        resetCheck();
        switch (i){
            case 0:
                tvCheckLeft.setSelected(true);
                mAdapter=new NoReadAdapter(this,haveActlist,this);
                rvContent.setAdapter(mAdapter);
                break;
            case 1:
                tvCheckRight.setSelected(true);
                mAdapter=new NoReadAdapter(this,noReadlist,this);
                rvContent.setAdapter(mAdapter);
                break;
        }
    }

    private void resetCheck() {
        tvCheckLeft.setSelected(false);
        tvCheckRight.setSelected(false);
    }

    private void setRecyclerView() {
        rvContent.setItemAnimator(new DefaultItemAnimator());
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        rvContent.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(this, 10), getResources().getColor(R.color.colorLineGray)));

    }

    @Override
    protected NoReadPresenter initPresenter() {
        return new NoReadPresenter();
    }

    @OnClick({R.id.iv_title_back, R.id.tv_check_left, R.id.tv_check_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_check_left:
                position=0;
                setSelectPostion(position);
                break;
            case R.id.tv_check_right:
                position=1;
                setSelectPostion(position);
                break;
        }
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(List datas) {
        if(action.equals("Notice")){
            mAdapter=new NoReadAdapter(this,datas,this);
            rvContent.setAdapter(mAdapter);
        }
    }

    @Override
    public void onActivitySuccess(List<ActivityHaveActModel> havenActStudents, List<ActivityNoReadModel> noReadActStudents) {
        haveActlist=havenActStudents;
        noReadlist=noReadActStudents;
        if(position==0){
            mAdapter=new NoReadAdapter(this,haveActlist,this);
            rvContent.setAdapter(mAdapter);
        }else {
            mAdapter=new NoReadAdapter(this,noReadlist,this);
            rvContent.setAdapter(mAdapter);
        }
    }

    @Override
    public void onPresonClick(String personId) {
        if(action.equals("Notice")){
            Intent intent=new Intent(NoReadActivity.this,NoticeNoReadActivity.class);
            intent.putExtra("studentId",personId);
            intent.putExtra("action","noRead");
            startActivity(intent);
        }else {
            Intent intent=new Intent(NoReadActivity.this,NoticeNoReadActivity.class);
            intent.putExtra("studentId",personId);

            if(position==0){
                intent.putExtra("action","haveAct");
            }else {
                intent.putExtra("action","noRead");
            }
            startActivity(intent);
        }

    }
}
