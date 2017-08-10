package com.tongxun.atongmu.parent.ui.my.shuttlephoto;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.dialog.CommonDialog;
import com.tongxun.atongmu.parent.ui.AtomAlbumActivity;
import com.tongxun.atongmu.parent.ui.PhotoSelectContainer;
import com.tongxun.atongmu.parent.util.PickPhotoPopupWindow;
import com.tongxun.atongmu.parent.util.SDCardUtil;
import com.tongxun.atongmu.parent.util.SystemUtil;

import java.io.File;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

import static com.tongxun.atongmu.parent.Constants.PICK_CODE;

public class ShuttleEditActivity extends Base2Activity<IShuttleEditContract.View, ShuttleEditPresenter> implements IShuttleEditContract.View, PickPhotoPopupWindow.popClickListener {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.ll_radio_normal)
    LinearLayout llRadioNormal;
    @BindView(R.id.ll_radio_loss)
    LinearLayout llRadioLoss;
    @BindView(R.id.et_sign_card)
    EditText etSignCard;
    @BindView(R.id.iv_title_qr_code)
    ImageView ivTitleQrCode;
    @BindView(R.id.iv_face)
    ImageView ivFace;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.ll_shuttle_edit)
    LinearLayout llShuttleEdit;
    @BindView(R.id.iv_radio_normal)
    ImageView ivRadioNormal;
    @BindView(R.id.iv_radio_loss)
    ImageView ivRadioLoss;
    private String name;
    private String cardStatus;
    private String cardNum;
    private String photoUrl;
    private String fileName;
    private String targetId;
    private Uri uri;
    private CommonDialog commonDialog;

    private KProgressHUD hud;
    private boolean isChangePhoto=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuttle_edit);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        cardStatus = intent.getStringExtra("cardStatus");
        cardNum = intent.getStringExtra("cardNum");
        photoUrl = intent.getStringExtra("photoUrl");
        targetId = intent.getStringExtra("targetId");

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);

        tvTitleName.setText(getString(R.string.sign_in_card_edit));
        tvTitleRight.setText(getString(R.string.save_image));
        tvUserName.setText(name);
        etSignCard.setText(cardNum);
        //0无卡号，-1卡号挂失，1正常卡号
        if (cardStatus.equals("0")) {
            resetRadio();
        } else if (cardStatus.equals("1")) {
            ivRadioNormal.setImageResource(R.drawable.icon_register_finish);
        } else {
            ivRadioLoss.setImageResource(R.drawable.icon_register_finish);

        }

        setSignImageUI();
    }

    private void setSignImageUI() {
        if(!TextUtils.isEmpty(photoUrl)){
            Glide.with(this).load(photoUrl).into(ivFace);
        }
    }

    private void resetRadio() {
        ivRadioNormal.setImageResource(R.drawable.icon_uncheck);
        ivRadioLoss.setImageResource(R.drawable.icon_uncheck);
    }

    @Override
    protected ShuttleEditPresenter initPresenter() {
        return new ShuttleEditPresenter();
    }

    @Override
    public void showProgress() {
        hud.show();
    }

    @Override
    public void hideProgress() {
        hud.dismiss();
    }

    @OnClick({R.id.iv_title_back, R.id.tv_title_right, R.id.ll_radio_normal, R.id.ll_radio_loss, R.id.iv_face})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                Intent intent=new Intent();
                intent.putExtra("isChangePhoto",isChangePhoto);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.tv_title_right:
                showProgress();
                mPresenter.saveCardNum(targetId,cardStatus,etSignCard.getText().toString());
                break;
            case R.id.ll_radio_normal:
                resetRadio();
                cardStatus="1";
                ivRadioNormal.setImageResource(R.drawable.icon_register_finish);
                break;
            case R.id.ll_radio_loss:
                resetRadio();
                cardStatus="-1";
                ivRadioLoss.setImageResource(R.drawable.icon_register_finish);
                break;
            case R.id.iv_face:
                PickPhotoPopupWindow.getInstance().show(llShuttleEdit, false, this);
                break;
        }
    }

    @Override
    public void onCamera() {
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

        createPhotoUrl();
        SystemUtil.opSystemCamera(this, fileName, Constants.REQ_CODE);
    }

    private void createPhotoUrl() {
        fileName = SDCardUtil.getInstance().getFilePath() + UUID.randomUUID() + ".jpg";
        File file = new File(fileName);
        uri = Uri.fromFile(file);
    }

    @PermissionFail(requestCode = 100)
    public void doCameraError() {
        Toasty.error(this, getResources().getString(R.string.get_camera_permission_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPhoto() {
        PermissionGen.with(this)
                .addRequestCode(Constants.PERMISSION_PHOTO_CODE)
                .permissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .request();
    }

    @PermissionSuccess(requestCode = 102)
    public void doPhoto() {
        PhotoSelectContainer.setMaxSize(1);
        Intent intent = new Intent(ShuttleEditActivity.this, AtomAlbumActivity.class);
        startActivityForResult(intent, PICK_CODE);
    }

    @PermissionFail(requestCode = 102)
    public void doPhotoError() {
        Toasty.error(this, getResources().getString(R.string.get_sd_permission_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onVideo() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Constants.REQ_CODE) {
                SystemUtil.startSystemCropPhoto(this, uri,uri, 4, 3, 640, 480, Constants.CROP_PHOTO);
            }

            if (requestCode == Constants.CROP_PHOTO) {
                Glide.with(ShuttleEditActivity.this).load(uri).into(ivFace);
                commonDialog = new CommonDialog(ShuttleEditActivity.this, getString(R.string.confirm_up), getString(R.string.confirm), getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
                    @Override
                    public void go() {
                        showProgress();
                        mPresenter.postSignPhoto(targetId,uri.getPath());
                        commonDialog.dismiss();
                    }

                    @Override
                    public void cancel() {
                        setSignImageUI();
                        commonDialog.dismiss();
                    }
                });
                commonDialog.show();
            }

            if(requestCode == Constants.PICK_CODE){
                String filepath=PhotoSelectContainer.getFileList().get(0);
                createPhotoUrl();
                SystemUtil.startSystemCropPhoto(this, Uri.fromFile(new File(filepath)),uri, 4, 3, 640, 480, Constants.CROP_PHOTO);
                PhotoSelectContainer.clear();
            }
        }
    }

    @Override
    public void onError(String message) {
        hideProgress();
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        isChangePhoto=true;
        hideProgress();
        Toasty.success(this, getString(R.string.photo_up_success), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveSuccess() {
        hideProgress();
        Intent intent=new Intent();
        intent.putExtra("isChangePhoto",true);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Intent intent=new Intent();
            intent.putExtra("isChangePhoto",isChangePhoto);
            setResult(RESULT_OK,intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
