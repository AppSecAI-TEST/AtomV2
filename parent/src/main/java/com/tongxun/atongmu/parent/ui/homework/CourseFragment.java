package com.tongxun.atongmu.parent.ui.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.CourseAdapter;
import com.tongxun.atongmu.parent.model.CourseListModel;
import com.tongxun.atongmu.parent.model.CourseModel;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Anro on 2017/7/4.
 * 课程
 */

public class CourseFragment extends Fragment implements ICourseContract.View<CoursePresenter>, View.OnClickListener {


    @BindView(R.id.tv_last_week)
    TextView tvLastWeek;
    @BindView(R.id.tv_this_week)
    TextView tvThisWeek;
    @BindView(R.id.tv_next_week)
    TextView tvNextWeek;
    @BindView(R.id.toolbar_course)
    Toolbar toolbarCourse;
    @BindView(R.id.rv_course_content)
    RecyclerView rvCourseContent;
    Unbinder unbinder;
    private CoursePresenter mPresenter;

    private static int weekPositon=1;

    private static final String TAG = "CourseFragment";

    private CourseAdapter mAdapter;

    private List<CourseModel> thisweek=new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_layout, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setWeekPosition(weekPositon);

        rvCourseContent.setItemAnimator(new DefaultItemAnimator());
        rvCourseContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCourseContent.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, 12, getResources().getColor(R.color.colorHomeworkBg)));

        toolbarCourse.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        tvLastWeek.setOnClickListener(this);
        tvThisWeek.setOnClickListener(this);
        tvNextWeek.setOnClickListener(this);

        mPresenter.getCourseInfo();

    }

    @Override
    public void setPresenter(CoursePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setData(CourseListModel listModel) {
        thisweek=listModel.getThisWeek();
        refreshAdapter();
    }

    private void refreshAdapter() {
        switch (weekPositon){
            case 0:

                break;
            case 1:
                mAdapter=new CourseAdapter(getActivity(),thisweek);
                rvCourseContent.setAdapter(mAdapter);
                break;
            case 2:
                break;
        }
    }


    @Override
    public void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_last_week:
                setWeekPosition(0);
                break;
            case R.id.tv_this_week:
                setWeekPosition(1);
                break;
            case R.id.tv_next_week:
                setWeekPosition(2);
                break;
        }
    }

    private void setWeekPosition(int i) {
        resetToolbar();
        switch (i){
            case 0:
                weekPositon=0;
                tvLastWeek.setSelected(true);
                break;
            case 1:
                weekPositon=1;
                tvThisWeek.setSelected(true);
                break;
            case 2:
                weekPositon=0;
                tvNextWeek.setSelected(true);
                break;
        }

    }

    private void resetToolbar() {
        tvLastWeek.setSelected(false);
        tvThisWeek.setSelected(false);
        tvNextWeek.setSelected(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
