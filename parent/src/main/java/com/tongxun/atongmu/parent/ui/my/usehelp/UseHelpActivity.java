package com.tongxun.atongmu.parent.ui.my.usehelp;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.FunIntroAdapter;
import com.tongxun.atongmu.parent.adapter.ProblemAdapter;
import com.tongxun.atongmu.parent.model.FunIntroModel;
import com.tongxun.atongmu.parent.model.ProblemHelpModel;
import com.tongxun.atongmu.parent.ui.WebViewActivity;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

import static com.tongxun.atongmu.parent.R.id.tv_check_left;

public class UseHelpActivity extends Base2Activity<IUserHelpContract.View, UseHelpPresenter> implements IUserHelpContract.View, FunIntroAdapter.IFunIntroListener, ProblemAdapter.IProblemListener {


    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(tv_check_left)
    TextView tvCheckLeft;
    @BindView(R.id.tv_check_right)
    TextView tvCheckRight;
    @BindView(R.id.ll_title_homework)
    LinearLayout llTitleHomework;
    @BindView(R.id.rv_course_content)
    RecyclerView rvCourseContent;

    private FunIntroAdapter funIntroAdapter;
    private ProblemAdapter problemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_help);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        tvCheckLeft.setText(getString(R.string.function_introduction));
        tvCheckRight.setText(getString(R.string.problem_help));
        rvCourseContent.setItemAnimator(new DefaultItemAnimator());
        rvCourseContent.setLayoutManager(new LinearLayoutManager(this));
        rvCourseContent.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(this, 1), getResources().getColor(R.color.colorLineGray)));
        setPosition(0);
    }

    private void setPosition(int i) {
        resetCheck();
        switch (i){
            case 0:
                mPresenter.getFunctionIntroduction();
                tvCheckLeft.setSelected(true);
                break;
            case 1:
                mPresenter.getUseHelp();
                tvCheckRight.setSelected(true);
                break;
        }
    }

    private void resetCheck() {
        tvCheckLeft.setSelected(false);
        tvCheckRight.setSelected(false);
    }

    @Override
    protected UseHelpPresenter initPresenter() {
        return new UseHelpPresenter();
    }


    @OnClick({R.id.iv_title_back, tv_check_left, R.id.tv_check_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case tv_check_left:
                setPosition(0);
                break;
            case R.id.tv_check_right:
                setPosition(1);
                break;
        }
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFunIntroSuccess(List<FunIntroModel> data) {
        funIntroAdapter=new FunIntroAdapter(this,data,this);
        rvCourseContent.setAdapter(funIntroAdapter);
    }

    @Override
    public void onProblemSuccess(List<ProblemHelpModel> datas) {
        problemAdapter=new ProblemAdapter(this,datas,this);
        rvCourseContent.setAdapter(problemAdapter);
    }

    @Override
    public void onItemClick(String url) {
        WebViewActivity.startWebViewActivity(this,"","", Constants.DEFAULTICON,url);
    }


    @Override
    public void onPromItemClick(String url) {
        WebViewActivity.startWebViewActivity(this,"","", Constants.DEFAULTICON,url);
    }
}
