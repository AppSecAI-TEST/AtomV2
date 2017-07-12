package com.tongxun.atongmu.parent.ui.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.CourseAdapter;
import com.tongxun.atongmu.parent.model.CourseListModel;
import com.tongxun.atongmu.parent.model.CourseModel;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

/**
 * Created by Anro on 2017/7/4.
 * 课程
 */

public class CourseFragment extends Fragment implements ICourseContract.View<CoursePresenter>{


    @BindView(R.id.rv_course_content)
    RecyclerView rvCourseContent;
    Unbinder unbinder;
    private CoursePresenter mPresenter;

    private static final String TAG = "CourseFragment";

    private CourseAdapter mAdapter;

    private List<CourseModel> thisweek=new ArrayList<>();
    private List<CourseModel> lastweek=new ArrayList<>();
    private List<CourseModel> nextweek=new ArrayList<>();

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
        rvCourseContent.setItemAnimator(new DefaultItemAnimator());
        rvCourseContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCourseContent.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, DensityUtil.dip2px(getActivity(),6), getResources().getColor(R.color.colorHomeworkBg)));
        mPresenter.getCourseInfo();

    }

    @Override
    public void setPresenter(CoursePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setData(CourseListModel listModel) {
        thisweek=listModel.getThisWeek();
        lastweek=listModel.getLastWeek();
        nextweek=listModel.getNextWeek();
        refreshAdapter(1);
    }

    @Override
    public void onError(String message) {
        Toasty.error(getActivity(),message, Toast.LENGTH_SHORT).show();
    }

    private void refreshAdapter(int i) {
        switch (i){
            case 0:
                mAdapter=new CourseAdapter(getActivity(),lastweek);
                rvCourseContent.setAdapter(mAdapter);
                break;
            case 1:
                mAdapter=new CourseAdapter(getActivity(),thisweek);
                rvCourseContent.setAdapter(mAdapter);
                break;
            case 2:
                mAdapter=new CourseAdapter(getActivity(),nextweek);
                rvCourseContent.setAdapter(mAdapter);
                break;
        }
    }


    @Override
    public void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setPosition(int i) {
        refreshAdapter(i);
    }
}
