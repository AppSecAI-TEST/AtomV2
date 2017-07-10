package com.tongxun.atongmu.parent.ui.homework;

import android.content.Intent;
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
import com.tongxun.atongmu.parent.model.HomeworkNoFinishModel;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Anro on 2017/7/4.
 * 作业
 */

public class HomeworkNoFinishFragment extends Fragment implements IHomeworkNoFinishContract.View<HomeworkNoFinishPresenter>, IHomeworkNoFinishListener {


    @BindView(R.id.rv_course_content)
    RecyclerView rvCourseContent;
    Unbinder unbinder;
    private HomeworkNoFinishPresenter mPresenter = null;

    private HomeworkNoFinishAdpater mAdpater;

    private int playPosition=-1;


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
        rvCourseContent.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL));
        mPresenter.getNoFinishHomeWork();
    }

    @Override
    public void setPresenter(HomeworkNoFinishPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setData(List<HomeworkNoFinishModel> datas) {
        mAdpater = new HomeworkNoFinishAdpater(getActivity(), datas);
        rvCourseContent.setAdapter(mAdpater);
        HomeworkNoFinishAdpater.setListener(this);
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

    @Override
    public void goComplete(String jobId) {
        Intent intent = new Intent(getActivity(), CompleteWorkActivity.class);
        intent.putExtra("jobID", jobId);
        startActivity(intent);
    }

    /**
     * 播放音频
     *
     * @param position
     */
    @Override
    public void playAudio(int position) {
        if(playPosition>0){
            RecyclerView.LayoutManager layoutManager = rvCourseContent.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                int firstPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                int endPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                if(firstPosition<=playPosition && playPosition<=endPosition){
                    View view=rvCourseContent.getChildAt(playPosition);
                    HomeworkNoFinishAdpater.NoFinishViewHolder viewHolder= (HomeworkNoFinishAdpater.NoFinishViewHolder) rvCourseContent.getChildViewHolder(view);

                }
            }
            playPosition=position;
        }else {

        }

    }
}
