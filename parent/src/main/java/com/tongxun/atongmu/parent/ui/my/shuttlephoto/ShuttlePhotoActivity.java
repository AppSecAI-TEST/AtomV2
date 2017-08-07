package com.tongxun.atongmu.parent.ui.my.shuttlephoto;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.IonItemClickListener;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.ShuttlePhotoAdapter;
import com.tongxun.atongmu.parent.model.ShuttlePhotoModel;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class ShuttlePhotoActivity extends Base2Activity<IShuttlePhotoContract.View, ShuttlePhotoPresenter> implements IShuttlePhotoContract.View, IonItemClickListener {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.iv_title_qr_code)
    ImageView ivTitleQrCode;
    @BindView(R.id.rv_shuttle_content)
    RecyclerView rvShuttleContent;
    private ShuttlePhotoAdapter mAdapter;
    private List<ShuttlePhotoModel> mlist=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuttle_photo);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        mPresenter.getShuttlePhoto();
        rvShuttleContent.setItemAnimator(new DefaultItemAnimator());
        rvShuttleContent.setLayoutManager(new LinearLayoutManager(this));
        rvShuttleContent.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(this, 10), getResources().getColor(R.color.colorLineGray)));

    }

    @Override
    protected ShuttlePhotoPresenter initPresenter() {
        return new ShuttlePhotoPresenter();
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(List<ShuttlePhotoModel> data) {
        mlist=data;
        mAdapter=new ShuttlePhotoAdapter(this,data,this);
        rvShuttleContent.setAdapter(mAdapter);
    }

    @OnClick({R.id.iv_title_back, R.id.iv_title_qr_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.iv_title_qr_code:
                openQRCamera();
                break;
        }
    }

    /**
     * 显示绑卡对话框
     */
    private void showCardDialog() {

    }


    private void openQRCamera() {
        PermissionGen.with(this)
                .addRequestCode(Constants.PERMISSION_CAMERA_CODE)
                .permissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .request();
    }

    /**
     * 获取相机权限后打开系统相机
     */
    @PermissionSuccess(requestCode = 100)
    public void doCamera() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent,Constants.REQ_CODE);
    }

    @PermissionFail(requestCode = 100)
    public void doCameraError() {
        Toasty.error(this, getResources().getString(R.string.get_camera_permission_error), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode==Constants.REQ_CODE){
                //处理扫描结果（在界面上显示）
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String str = bundle.getString(CodeUtils.RESULT_STRING);
                        boolean result=str.matches("[0-9]+");
                        if(result && str.length()==10){
                            showCardDialog();
                        }else {
                            Toasty.error(this, getResources().getString(R.string.please_correct_sign_card), Toast.LENGTH_LONG).show();
                        }

                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        Toasty.error(this, getResources().getString(R.string.please_correct_sign_card), Toast.LENGTH_LONG).show();
                    }
                }
            }

            if(requestCode==Constants.REQ_INTENT_CODE){
                boolean isChangePhoto=data.getBooleanExtra("isChangePhoto",false);
                if(isChangePhoto){
                    mPresenter.getShuttlePhoto();
                }

            }
        }
    }


    @Override
    public void onItemClick(int position) {
        if(mlist!=null){
            Intent intent=new Intent(ShuttlePhotoActivity.this,ShuttleEditActivity.class);
            intent.putExtra("name",mlist.get(position).getPersonDesc());
            intent.putExtra("cardStatus",mlist.get(position).getUserState());
            intent.putExtra("cardNum",mlist.get(position).getCardNumber());
            intent.putExtra("photoUrl",mlist.get(position).getPhoto());
            intent.putExtra("targetId",mlist.get(position).getPersonId());
            startActivityForResult(intent,Constants.REQ_INTENT_CODE);
        }

    }
}
