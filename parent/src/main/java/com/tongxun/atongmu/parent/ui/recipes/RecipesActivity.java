package com.tongxun.atongmu.parent.ui.recipes;

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
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.RecipesAdapter;
import com.tongxun.atongmu.parent.model.RecipesModel;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class RecipesActivity extends Base2Activity<IRecipesContract.View, RecipesPresenter> implements IRecipesContract.View {

    @BindView(R.id.tv_last_week)
    TextView tvLastWeek;
    @BindView(R.id.tv_this_week)
    TextView tvThisWeek;
    @BindView(R.id.tv_next_week)
    TextView tvNextWeek;
    @BindView(R.id.ll_title_course)
    LinearLayout llTitleCourse;
    @BindView(R.id.rv_course_content)
    RecyclerView rvCourseContent;
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_day_one)
    TextView tvDayOne;
    @BindView(R.id.tv_day_two)
    TextView tvDayTwo;
    @BindView(R.id.tv_day_three)
    TextView tvDayThree;
    @BindView(R.id.tv_day_four)
    TextView tvDayFour;
    @BindView(R.id.tv_day_five)
    TextView tvDayFive;
    @BindView(R.id.tv_day_six)
    TextView tvDaySix;
    @BindView(R.id.tv_day_seven)
    TextView tvDaySeven;

    SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd");
    SimpleDateFormat format=new SimpleDateFormat("E");
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

    private static int titlePagePosition=1;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;

    private RecipesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);
        rvCourseContent.setItemAnimator(new DefaultItemAnimator());
        rvCourseContent.setLayoutManager(new LinearLayoutManager(this));
        rvCourseContent.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(this, 10), getResources().getColor(R.color.colorLineGray)));
        setPagePosition();

    }

    @Override
    protected RecipesPresenter initPresenter() {
        return new RecipesPresenter();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


    private void setPagePosition() {
        resetCourseTitle();
        switch (titlePagePosition) {
            case 0:
                tvLastWeek.setSelected(true);
                setWeekStatus();
                break;
            case 1:
                tvThisWeek.setSelected(true);
                setWeekStatus();
                break;
            case 2:
                tvNextWeek.setSelected(true);
                setWeekStatus();
                break;
        }
    }

    private void setWeekStatus() {
        resetWeekStatus();

        String str=format.format(new Date());
        Calendar cal=Calendar.getInstance();
        if(titlePagePosition==1){
            cal.add(Calendar.DATE,0);
            cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
            monday=simpleDateFormat.format(cal.getTime());
            if(str.contains("周一")){
                tvDayOne.setText("今天\n"+dateFormat.format(cal.getTime()));
                tvDayOne.setSelected(true);
                mPresenter.getRecipes(monday);
            }else {
                tvDayOne.setText("周一\n"+dateFormat.format(cal.getTime()));
            }
            cal.add(Calendar.DATE, 1);
            tuesday=simpleDateFormat.format(cal.getTime());
            if(str.contains("周二")){
                tvDayTwo.setText("今天\n"+dateFormat.format(cal.getTime()));
                tvDayTwo.setSelected(true);
                mPresenter.getRecipes(tuesday);
            }else {
                tvDayTwo.setText("周二\n"+dateFormat.format(cal.getTime()));
            }
            cal.add(Calendar.DATE, 1);
            wednesday=simpleDateFormat.format(cal.getTime());
            if(str.contains("周三")){
                tvDayThree.setText("今天\n"+dateFormat.format(cal.getTime()));
                tvDayThree.setSelected(true);
                mPresenter.getRecipes(wednesday);
            }else {
                tvDayThree.setText("周三\n"+dateFormat.format(cal.getTime()));
            }
            cal.add(Calendar.DATE, 1);
            thursday=simpleDateFormat.format(cal.getTime());
            if(str.contains("周四")){
                tvDayFour.setText("今天\n"+ dateFormat.format(cal.getTime()));
                tvDayFour.setSelected(true);
                mPresenter.getRecipes(thursday);
            }else {
                tvDayFour.setText("周四\n"+ dateFormat.format(cal.getTime()));
            }
            cal.add(Calendar.DATE, 1);
            friday=simpleDateFormat.format(cal.getTime());
            if(str.contains("周五")){
                tvDayFive.setText("今天\n"+dateFormat.format(cal.getTime()));
                tvDayFive.setSelected(true);
                mPresenter.getRecipes(friday);
            }else {
                tvDayFive.setText("周五\n"+dateFormat.format(cal.getTime()));
            }
            cal.add(Calendar.DATE, 1);
            saturday=simpleDateFormat.format(cal.getTime());
            if(str.contains("周六")){
                tvDaySix.setText("今天\n"+dateFormat.format(cal.getTime()));
                tvDaySix.setSelected(true);
                mPresenter.getRecipes(saturday);
            }else {
                tvDaySix.setText("周六\n"+dateFormat.format(cal.getTime()));
            }
            cal.add(Calendar.DATE, 1);
            sunday=simpleDateFormat.format(cal.getTime());
            if(str.contains("周日")){
                tvDaySeven.setText("今天\n"+dateFormat.format(cal.getTime()));
                tvDaySeven.setSelected(true);
                mPresenter.getRecipes(sunday);
            }else {
                tvDaySeven.setText("周日\n"+dateFormat.format(cal.getTime()));
            }


        }
        if(titlePagePosition==0){
            cal.add(Calendar.DATE,-7);
            cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
            tvDayOne.setText("周一\n"+dateFormat.format(cal.getTime()));
            monday=simpleDateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, 1);
            tvDayTwo.setText("周二\n"+dateFormat.format(cal.getTime()));
            tuesday=simpleDateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, 1);
            tvDayThree.setText("周三\n"+dateFormat.format(cal.getTime()));
            wednesday=simpleDateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, 1);
            tvDayFour.setText("周四\n"+ dateFormat.format(cal.getTime()));
            thursday=simpleDateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, 1);
            tvDayFive.setText("周五\n"+dateFormat.format(cal.getTime()));
            friday=simpleDateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, 1);
            tvDaySix.setText("周六\n"+dateFormat.format(cal.getTime()));
            saturday=simpleDateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, 1);
            tvDaySeven.setText("周日\n"+dateFormat.format(cal.getTime()));
            sunday=simpleDateFormat.format(cal.getTime());
            tvDayOne.setSelected(true);
            mPresenter.getRecipes(monday);
        }
        if(titlePagePosition==2){
            cal.add(Calendar.DATE,7);
            cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
            tvDayOne.setText("周一\n"+dateFormat.format(cal.getTime()));
            monday=simpleDateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, 1);
            tvDayTwo.setText("周二\n"+dateFormat.format(cal.getTime()));
            tuesday=simpleDateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, 1);
            tvDayThree.setText("周三\n"+dateFormat.format(cal.getTime()));
            wednesday=simpleDateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, 1);
            tvDayFour.setText("周四\n"+ dateFormat.format(cal.getTime()));
            thursday=simpleDateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, 1);
            tvDayFive.setText("周五\n"+dateFormat.format(cal.getTime()));
            friday=simpleDateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, 1);
            tvDaySix.setText("周六\n"+dateFormat.format(cal.getTime()));
            saturday=simpleDateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, 1);
            tvDaySeven.setText("周日\n"+dateFormat.format(cal.getTime()));
            sunday=simpleDateFormat.format(cal.getTime());
            tvDayOne.setSelected(true);
            mPresenter.getRecipes(monday);
        }

    }

    private void resetCourseTitle() {
        tvLastWeek.setSelected(false);
        tvThisWeek.setSelected(false);
        tvNextWeek.setSelected(false);
        tvDayOne.setSelected(false);
        tvDayTwo.setSelected(false);
        tvDayFour.setSelected(false);
        tvDayFive.setSelected(false);
        tvDaySix.setSelected(false);
        tvDaySeven.setSelected(false);

    }


    @OnClick({R.id.iv_title_back, R.id.tv_last_week, R.id.tv_this_week, R.id.tv_next_week,R.id.tv_day_one, R.id.tv_day_two, R.id.tv_day_three, R.id.tv_day_four, R.id.tv_day_five, R.id.tv_day_six, R.id.tv_day_seven})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_last_week:
                titlePagePosition=0;
                setPagePosition();
                break;
            case R.id.tv_this_week:
                titlePagePosition=1;
                setPagePosition();
                break;
            case R.id.tv_next_week:
                titlePagePosition=2;
                setPagePosition();
                break;
            case R.id.tv_day_one:
                setweekPosition(1);
                mPresenter.getRecipes(monday);
                break;
            case R.id.tv_day_two:
                setweekPosition(2);
                mPresenter.getRecipes(tuesday);
                break;
            case R.id.tv_day_three:
                setweekPosition(3);
                mPresenter.getRecipes(wednesday);
                break;
            case R.id.tv_day_four:
                setweekPosition(4);
                mPresenter.getRecipes(thursday);
                break;
            case R.id.tv_day_five:
                setweekPosition(5);
                mPresenter.getRecipes(friday);
                break;
            case R.id.tv_day_six:
                setweekPosition(6);
                mPresenter.getRecipes(saturday);
                break;
            case R.id.tv_day_seven:
                setweekPosition(7);
                mPresenter.getRecipes(sunday);
                break;
        }
    }

    private void setweekPosition(int i) {
        resetWeekStatus();
        switch (i){
            case 1:
                tvDayOne.setSelected(true);
                break;
            case 2:
                tvDayTwo.setSelected(true);
                break;
            case 3:
                tvDayThree.setSelected(true);
                break;
            case 4:
                tvDayFour.setSelected(true);
                break;
            case 5:
                tvDayFive.setSelected(true);
                break;
            case 6:
                tvDaySix.setSelected(true);
                break;
            case 7:
                tvDaySeven.setSelected(true);
                break;
        }
    }

    private void resetWeekStatus() {
        tvDayOne.setSelected(false);
        tvDayTwo.setSelected(false);
        tvDayThree.setSelected(false);
        tvDayFour.setSelected(false);
        tvDayFive.setSelected(false);
        tvDaySix.setSelected(false);
        tvDaySeven.setSelected(false);
    }

    /**
     * 刷新Adapter
     */
    @Override
    public void refreshAdapter(List<RecipesModel> list) {
        mAdapter=new RecipesAdapter(this,list);
        rvCourseContent.setAdapter(mAdapter);
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }
}
