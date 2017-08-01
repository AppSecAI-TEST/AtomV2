package com.tongxun.atongmu.parent.ui.homework;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeworkActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.fl_homework_container)
    FrameLayout flHomeworkContainer;
    @BindView(R.id.tv_homework)
    TextView tvHomework;
    @BindView(R.id.tv_course)
    TextView tvCourse;
    @BindView(R.id.tv_last_week)
    TextView tvLastWeek;
    @BindView(R.id.tv_this_week)
    TextView tvThisWeek;
    @BindView(R.id.tv_next_week)
    TextView tvNextWeek;
    @BindView(R.id.ll_title_course)
    LinearLayout llTitleCourse;
    @BindView(R.id.tv_no_finish)
    TextView tvNoFinish;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.ll_title_homework)
    LinearLayout llTitleHomework;
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;


    private HomeworkNoFinishFragment noFinishFragment = null;
    private HomeworkFinishFragment finishFragment = null;
    private CourseFragment courseFragment = null;

    private HomeworkNoFinishPresenter nofinishPresenter;
    private HomeworkFinishPresenter finishPresenter;
    private CoursePresenter coursePresenter;

    private int homeworkPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);

        setPagePostiton(0);


        tvHomework.setOnClickListener(this);
        tvCourse.setOnClickListener(this);
        tvNoFinish.setOnClickListener(this);
        tvFinish.setOnClickListener(this);
        tvLastWeek.setOnClickListener(this);
        tvThisWeek.setOnClickListener(this);
        tvNextWeek.setOnClickListener(this);
        ivTitleBack.setOnClickListener(this);
    }

    private void setPagePostiton(int i) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        resetBottom();
        if (i == 0) {

            llTitleHomework.setVisibility(View.VISIBLE);
            llTitleCourse.setVisibility(View.GONE);
            tvHomework.setSelected(true);
            resetHomeworkTitle();
            if (homeworkPosition == 0) {
                if (noFinishFragment == null) {
                    noFinishFragment = new HomeworkNoFinishFragment();
                    transaction.add(R.id.fl_homework_container, noFinishFragment);
                    nofinishPresenter = new HomeworkNoFinishPresenter(noFinishFragment);
                } else {
                    transaction.show(noFinishFragment);
                }
                tvNoFinish.setSelected(true);
            } else {
                if (finishFragment == null) {
                    finishFragment = new HomeworkFinishFragment();
                    transaction.add(R.id.fl_homework_container, finishFragment);
                    finishPresenter = new HomeworkFinishPresenter(finishFragment);
                } else {
                    transaction.show(finishFragment);
                }
                tvFinish.setSelected(true);
            }
        } else {
            llTitleHomework.setVisibility(View.GONE);
            llTitleCourse.setVisibility(View.VISIBLE);
            if (courseFragment == null) {
                courseFragment = new CourseFragment();
                transaction.add(R.id.fl_homework_container, courseFragment);
                coursePresenter = new CoursePresenter(courseFragment);
                setCoursePosition(1);
            } else {
                transaction.show(courseFragment);
            }
            tvCourse.setSelected(true);

        }

        transaction.commitAllowingStateLoss();


    }

    private void setCoursePosition(int i) {
        resetCourseTitle();
        switch (i) {
            case 0:
                tvLastWeek.setSelected(true);
                break;
            case 1:
                tvThisWeek.setSelected(true);
                break;
            case 2:
                tvNextWeek.setSelected(true);
                break;
        }
    }

    private void resetCourseTitle() {
        tvLastWeek.setSelected(false);
        tvThisWeek.setSelected(false);
        tvNextWeek.setSelected(false);
    }


    private void resetHomeworkTitle() {
        tvNoFinish.setSelected(false);
        tvFinish.setSelected(false);
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (noFinishFragment != null) {
            transaction.hide(noFinishFragment);
        }

        if (courseFragment != null) {
            transaction.hide(courseFragment);
        }
        if (finishFragment != null) {
            transaction.hide(finishFragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_homework:
                setPagePostiton(0);
                break;
            case R.id.tv_course:
                setPagePostiton(1);
                break;
            case R.id.tv_no_finish:
                homeworkPosition = 0;
                setPagePostiton(0);
                break;
            case R.id.tv_finish:
                homeworkPosition = 1;
                setPagePostiton(0);
                break;
            case R.id.tv_last_week:
                setCoursePosition(0);
                if (courseFragment != null) {
                    courseFragment.setPosition(0);
                }
                break;
            case R.id.tv_this_week:
                setCoursePosition(1);
                if (courseFragment != null) {
                    courseFragment.setPosition(1);
                }
                break;
            case R.id.tv_next_week:
                setCoursePosition(2);
                if (courseFragment != null) {
                    courseFragment.setPosition(2);
                }
                break;
            case R.id.iv_title_back:
                finish();
                break;
        }
    }

    private void resetBottom() {
        tvHomework.setSelected(false);
        tvCourse.setSelected(false);
    }
}
