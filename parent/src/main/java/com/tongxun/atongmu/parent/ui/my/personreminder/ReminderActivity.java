package com.tongxun.atongmu.parent.ui.my.personreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.ReminderAdapter;
import com.tongxun.atongmu.parent.model.MedicineModel;
import com.tongxun.atongmu.parent.ui.PhotoViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class ReminderActivity extends Base2Activity<IReminderContract.View,ReminderPresenter> implements IReminderContract.View, ReminderAdapter.ReminderListener {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.rv_course_content)
    RecyclerView rvCourseContent;
    private ReminderAdapter mAdapter;

    private KProgressHUD hud;
    private List<MedicineModel> mlist=null;
    private int deletePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        tvTitleName.setText("");
        tvTitleRight.setText(getString(R.string.create));

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);

        rvCourseContent.setItemAnimator(new DefaultItemAnimator());
        rvCourseContent.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.getReminderList();
    }

    @Override
    protected ReminderPresenter initPresenter() {
        return new ReminderPresenter();
    }

    @OnClick({R.id.iv_title_back, R.id.tv_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_title_right:

                goNewReminder();
                break;
        }
    }

    private void goNewReminder() {
        Intent intent=new Intent(ReminderActivity.this,AddReminderActivity.class);
        startActivityForResult(intent, Constants.REQ_INTENT_CODE);
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetReminderSuccess(List<MedicineModel> medicine) {
        mlist=medicine;
        mAdapter=new ReminderAdapter(this,medicine,this);
        rvCourseContent.setAdapter(mAdapter);
    }

    @Override
    public void onDeleteReminderSuccess() {
        mlist.remove(deletePosition);
        mAdapter.notifyItemRemoved(deletePosition);
    }

    @Override
    public void onPhoto(int ListPosition, int itemPosition) {
        if(mlist!=null){
            ArrayList<String> list=new ArrayList<>();
            for(MedicineModel.ImageBean bean:mlist.get(ListPosition).getImage()){
                list.add(bean.getImage_url());
            }
            PhotoViewActivity.startActivity(this,list,itemPosition);
        }
    }

    @Override
    public void onDelete(int position) {
        deletePosition=position;
        mPresenter.deleteReminder(mlist.get(position).getNote_id());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode==Constants.REQ_INTENT_CODE){
                mPresenter.getReminderList();
            }
        }
    }
}
