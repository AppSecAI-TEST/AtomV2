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

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.HomeworkNoFinishAdpater;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Anro on 2017/7/4.
 * 作业
 */

public class HomeworkNoFinishFragment extends Fragment implements IHomeworkNoFinishContract.View<HomeworkNoFinishPresenter> {


    @BindView(R.id.rv_course_content)
    RecyclerView rvCourseContent;
    Unbinder unbinder;
    private HomeworkNoFinishPresenter mPresenter = null;

    private HomeworkNoFinishAdpater mAdpater;


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
        rvCourseContent.addItemDecoration(new RecycleViewDivider(getActivity(),LinearLayoutManager.VERTICAL));
    }

    @Override
    public void setPresenter(HomeworkNoFinishPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setData() {
        mAdpater=new HomeworkNoFinishAdpater();
        rvCourseContent.setAdapter(mAdpater);
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
