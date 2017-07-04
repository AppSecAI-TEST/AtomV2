package com.tongxun.atongmu.parent.ui.homework;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
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


    private HomeworkFragment homeworkFragment = null;
    private CourseFragment courseFragment = null;

    private HomeworkPresenter homeworkPresenter;
    private CoursePresenter coursePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);

        setPagePostiton(0);


        tvHomework.setOnClickListener(this);
        tvCourse.setOnClickListener(this);
    }

    private void setPagePostiton(int i) {
        resetBottom();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:
                if(homeworkFragment==null){
                    homeworkFragment=new HomeworkFragment();
                    transaction.add(R.id.fl_homework_container,homeworkFragment);
                    homeworkPresenter=new HomeworkPresenter(homeworkFragment);
                }else {
                    transaction.show(homeworkFragment);
                }
                tvHomework.setSelected(true);
                break;
            case 1:
                if(courseFragment==null){
                    courseFragment=new CourseFragment();
                    transaction.add(R.id.fl_homework_container,courseFragment);
                    coursePresenter=new CoursePresenter(courseFragment);
                }else {
                    transaction.show(courseFragment);
                }
                tvCourse.setSelected(true);
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (homeworkFragment != null) {
            transaction.hide(homeworkFragment);
        }

        if (courseFragment != null) {
            transaction.hide(courseFragment);
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
        }
    }

    private void resetBottom() {
        tvHomework.setSelected(false);
        tvCourse.setSelected(false);
    }
}
