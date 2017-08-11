package com.tongxun.atongmu.parent.ui.homework;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.VoicePlayListener;
import com.tongxun.atongmu.parent.adapter.HomeworkFinishAdapter;
import com.tongxun.atongmu.parent.dialog.CommonDialog;
import com.tongxun.atongmu.parent.model.FinishWorkModel;
import com.tongxun.atongmu.parent.model.HomeFinishListModel;
import com.tongxun.atongmu.parent.ui.CircleVideoPlayActivity;
import com.tongxun.atongmu.parent.util.SharePopupWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

/**
 * Created by Anro on 2017/7/5.
 */

public class HomeworkFinishFragment extends Fragment implements IHomeworkFinishContract.View<HomeworkFinishPresenter>, ExpandableListView.OnGroupClickListener, IHomeworkFinishListener {


    Unbinder unbinder;
    @BindView(R.id.expand_lv_homework)
    ExpandableListView expandLvHomework;
    @BindView(R.id.ll_finish_layout)
    LinearLayout llFinishLayout;

    private HomeworkFinishPresenter mPresenter = null;
    private List<HomeFinishListModel> mlist = new ArrayList<>();
    private HomeworkFinishAdapter mAdapter;

    private boolean isChangeData = false;

    private CommonDialog commonDialog;

    private int groupPosition;
    private int itemPosition;

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
    public void onStart() {
        super.onStart();
        if (isChangeData) {
            isChangeData = false;
            mPresenter.getFinishHomeworkDate();
        }
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
        for (String str : datas) {
            mlist.add(new HomeFinishListModel(str, new ArrayList<FinishWorkModel>() {
            }));
        }

        mAdapter = new HomeworkFinishAdapter(getActivity(), mlist, this);
        expandLvHomework.setAdapter(mAdapter);
        mPresenter.getFinishHomeworkFromDate(0, mlist.get(0).getDate());
        expandLvHomework.expandGroup(0);
    }

    @Override
    public void onHomeWorkSuccess(int position, List<FinishWorkModel> datas) {
        mlist.get(position).setWorkModelList(datas);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteHomeWorkSuccess() {
        mlist.get(groupPosition).getWorkModelList().remove(itemPosition);
        mAdapter.notifyDataSetChanged();
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
        mPresenter.getFinishHomeworkFromDate(groupPosition, mlist.get(groupPosition).getDate());
        return false;
    }

    public void changeData() {
        isChangeData = true;

    }

    @Override
    public void onShare(int groupPosition, int itemPosition) {
        SharePopupWindow.getInstance().show(llFinishLayout,"","",mlist.get(groupPosition).getWorkModelList().get(itemPosition).getShareUrl(), Constants.DEFAULTICON);
    }

    @Override
    public void onDelete(final int groupPosition, final int childPosition) {
        this.groupPosition=groupPosition;
        this.itemPosition=childPosition;
        commonDialog=new CommonDialog(getActivity(), getString(R.string.confirm_delete_homework), getString(R.string.confirm), getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
            @Override
            public void go() {
                mPresenter.deleteHomework(mlist.get(groupPosition).getWorkModelList().get(childPosition).getJobId());
                commonDialog.dismiss();

            }

            @Override
            public void cancel() {
                commonDialog.dismiss();
            }
        });
        commonDialog.show();
    }

    @Override
    public void onPlayAudio(int groupPosition, int childPosition, String videoUrl, ImageView ivVoiceAnim) {
        Uri uri = Uri.parse(videoUrl);
        try {
            new VoicePlayListener(getActivity(), ivVoiceAnim).onClick(videoUrl, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPlayVideo(int groupPosition, int childPosition) {
        VoicePlayListener.stop();
        CircleVideoPlayActivity.startActivity(getActivity(),mlist.get(groupPosition).getWorkModelList().get(childPosition).getVideoUrl());
    }

    @Override
    public void onPlayCommentAudio(int groupPosition, int childPosition, String commentMedia, ImageView ivCommentVoiceAnim) {
        Uri uri = Uri.parse(commentMedia);
        try {
            new VoicePlayListener(getActivity(), ivCommentVoiceAnim).onClick(commentMedia, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
