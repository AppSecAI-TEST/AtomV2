package com.tongxun.atongmu.parent.ui.schoolvideo;

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
import com.tongxun.atongmu.parent.adapter.VideoListAdapter;
import com.tongxun.atongmu.parent.model.VideoListModel;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class VideoListActivity extends Base2Activity<IVideoListContract.View, VideoListPresenter> implements IVideoListContract.View, VideoListAdapter.videoListListener {


    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.rv_course_content)
    RecyclerView rvCourseContent;
    private VideoListAdapter mAdapter;
    private String deviceId;
    private String channelNo;
    private String startTime;
    private String endTime;
    private String verificitionCode;
    private String videoClarity;

    private SimpleDateFormat formatss=new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat format=new SimpleDateFormat("HH:mm");

    private static final int REQ_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorWhite);
        tvTitleName.setText(getResources().getString(R.string.video_live));
        rvCourseContent.setItemAnimator(new DefaultItemAnimator());
        rvCourseContent.setLayoutManager(new LinearLayoutManager(this));
        rvCourseContent.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(this, 10), getResources().getColor(R.color.colorLineGray)));
        mPresenter.getVideoList();
    }

    @Override
    protected VideoListPresenter initPresenter() {
        return new VideoListPresenter();
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(List<VideoListModel> datas) {
        mAdapter=new VideoListAdapter(this,datas,this);
        rvCourseContent.setAdapter(mAdapter);
    }

    @Override
    public void onSystemSuccess(String systemTime) {
        try {
            Date startDate = format.parse(startTime);
            Date endDate=format.parse(endTime);
            Date nowDate=formatss.parse(systemTime);
            if(startDate.getTime()<nowDate.getTime() && nowDate.getTime()<endDate.getTime()){
                Intent intent=new Intent(VideoListActivity.this,VideoPlayActivity.class);
                intent.putExtra("derviceID",deviceId);
                intent.putExtra("channelNo",channelNo);
                intent.putExtra("endDate",endTime);
                intent.putExtra("nowDate",systemTime);
                intent.putExtra("verificationCode",verificitionCode);
                intent.putExtra("videoClarity",videoClarity);
                startActivityForResult(intent,REQ_CODE);
            }else {
                Toast.makeText(VideoListActivity.this, "当前设备不在播放时间内", Toast.LENGTH_SHORT).show();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.iv_title_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 点击视频列表
     * @param deviceId
     * @param channelNo
     * @param startTime
     * @param endTime
     * @param verificitionCode
     * @param videoClarity
     */
    @Override
    public void onClick(String deviceId, String channelNo, String startTime, String endTime, String verificitionCode, String videoClarity) {
        this.deviceId=deviceId;
        this.channelNo=channelNo;
        this.startTime=startTime;
        this.endTime=endTime;
        this.verificitionCode=verificitionCode;
        this.videoClarity=videoClarity;
        mPresenter.getSystemTime();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode==REQ_CODE){
                mAdapter.notifyDataSetChanged();
            }
        }
    }

}
