package com.tongxun.atongmu.parent.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.AddPhotoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpinionFeedBackActivity extends Base2Activity<IOpinionFeedBackContract.View, OpinionFeedBackPresenter> implements IOpinionFeedBackContract.View {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.tv_send_atom)
    TextView tvSendAtom;
    @BindView(R.id.tv_send_garden)
    TextView tvSendGarden;
    TextView tvContentNum;
    @BindView(R.id.tv_photo_num)
    TextView tvPhotoNum;
    @BindView(R.id.rv_photo)
    RecyclerView rvPhoto;

    private AddPhotoAdapter mAdapter;

    private List<String> mlist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion_feed_back);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        tvTitleName.setText(getResources().getString(R.string.feedback));
        tvTitleRight.setText(getResources().getString(R.string.record));
        ButterKnife.bind(this);
        mAdapter=new AddPhotoAdapter(this,mlist);
        setSendPosition(0);
    }

    @Override
    protected OpinionFeedBackPresenter initPresenter() {
        return new OpinionFeedBackPresenter();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @OnClick({R.id.iv_title_back, R.id.tv_title_right, R.id.tv_send_atom, R.id.tv_send_garden})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_title_right:
                goFeedBackRecord();
                break;
            case R.id.tv_send_atom:
                setSendPosition(0);
                break;
            case R.id.tv_send_garden:
                setSendPosition(1);
                break;
        }
    }

    private void setSendPosition(int i) {
        resetSend();
        switch (i){
            case 0:
                tvSendAtom.setSelected(true);
                break;
            case 1:
                tvSendGarden.setSelected(true);
                break;
        }
    }

    private void resetSend() {
        tvSendAtom.setSelected(false);
        tvSendGarden.setSelected(false);
    }

    private void goFeedBackRecord() {
        Intent intent=new Intent(OpinionFeedBackActivity.this,FeedBackRecordActivity.class);
        startActivity(intent);
    }


}
