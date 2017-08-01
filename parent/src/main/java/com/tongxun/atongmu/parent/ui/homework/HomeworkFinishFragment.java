package com.tongxun.atongmu.parent.ui.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.HomeworkFinishAdapter;
import com.tongxun.atongmu.parent.model.FinishWorkModel;
import com.tongxun.atongmu.parent.model.HomeFinishListModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

/**
 * Created by Anro on 2017/7/5.
 */

public class HomeworkFinishFragment extends Fragment implements IHomeworkFinishContract.View<HomeworkFinishPresenter>, ExpandableListView.OnGroupClickListener {


    Unbinder unbinder;
    @BindView(R.id.expand_lv_homework)
    ExpandableListView expandLvHomework;

    private HomeworkFinishPresenter mPresenter = null;
    private List<HomeFinishListModel> mlist = new ArrayList<>();
    private HomeworkFinishAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finish_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getFinishHomeworkDate();
        expandLvHomework.setOnGroupClickListener(this);
    }

    @Override
    public void setPresenter(HomeworkFinishPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onError(String message) {
        Toasty.error(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMonSuccess(List<String> datas) {
        for(String str:datas){
            mlist.add(new HomeFinishListModel(str, new ArrayList<FinishWorkModel>(){}));
        }

        mAdapter=new HomeworkFinishAdapter(getActivity(),mlist);
        expandLvHomework.setAdapter(mAdapter);
    }

    @Override
    public void onHomeWorkSuccess(int position, List<FinishWorkModel> datas) {
        mlist.get(position).setWorkModelList(datas);
        mAdapter=new HomeworkFinishAdapter(getActivity(),mlist);
        expandLvHomework.setAdapter(mAdapter);
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
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        mPresenter.getFinishHomeworkFromDate(groupPosition,mlist.get(groupPosition).getDate());
        return false;
    }
}
