package com.tongxun.atongmu.parent.ui.album;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.AlbumFromDateAdapter;
import com.tongxun.atongmu.parent.model.AlbumFromDateModel;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class AlbumFromDateActivity extends Base2Activity<IAlbumFromDateContract.View, AlbumFromDatePresenter> implements IAlbumFromDateContract.View {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.rv_course_content)
    RecyclerView rvCourseContent;

    private AlbumFromDateAdapter mAdapter;

    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_from_date);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorWhite);
        Intent intent=getIntent();
        date=intent.getStringExtra("date");

        rvCourseContent.setItemAnimator(new DefaultItemAnimator());
        rvCourseContent.setLayoutManager(new LinearLayoutManager(this));
        rvCourseContent.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(this,10), getResources().getColor(R.color.colorLineGray)));
        mPresenter.getAlbumFromDate(date);
    }

    @Override
    protected AlbumFromDatePresenter initPresenter() {
        return new AlbumFromDatePresenter();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @OnClick(R.id.iv_title_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onRefreshAdapter(List<AlbumFromDateModel> datas) {
        mAdapter=new AlbumFromDateAdapter(this,datas);
        rvCourseContent.setAdapter(mAdapter);
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }
}
