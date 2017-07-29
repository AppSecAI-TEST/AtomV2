package com.tongxun.atongmu.parent.ui.my.personreminder;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddReminderActivity extends Base2Activity<IAddReminderContract.View, AddReminderPresenter> implements IAddReminderContract.View {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_reduce)
    TextView tvReduce;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.rv_photo)
    RecyclerView rvPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        tvTitleName.setText(getString(R.string.create_new_reminder));
    }

    @Override
    protected AddReminderPresenter initPresenter() {
        return new AddReminderPresenter();
    }

    @OnClick({R.id.iv_title_back, R.id.tv_title_right, R.id.tv_reduce, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_title_right:
                break;
            case R.id.tv_reduce:
                break;
            case R.id.tv_add:
                break;
        }
    }
}
