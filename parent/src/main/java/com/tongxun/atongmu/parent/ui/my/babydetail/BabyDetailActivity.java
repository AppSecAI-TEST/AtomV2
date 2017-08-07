package com.tongxun.atongmu.parent.ui.my.babydetail;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
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
import com.tongxun.atongmu.parent.model.BabyInfoModel;
import com.tongxun.atongmu.parent.ui.AtomAlbumActivity;
import com.tongxun.atongmu.parent.ui.PhotoSelectContainer;
import com.tongxun.atongmu.parent.util.PickPhotoPopupWindow;
import com.tongxun.atongmu.parent.util.SDCardUtil;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.tongxun.atongmu.parent.util.SystemUtil;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

import static com.tongxun.atongmu.parent.Constants.PICK_CODE;

public class BabyDetailActivity extends Base2Activity<IBabyDetailContract.View, BabyDetailPresenter> implements PickPhotoPopupWindow.popClickListener, IBabyDetailContract.View {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.civ_face)
    CircleImageView civFace;
    @BindView(R.id.ll_face)
    LinearLayout llFace;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.ll_sex)
    LinearLayout llSex;
    @BindView(R.id.tv_blood)
    TextView tvBlood;
    @BindView(R.id.ll_blood)
    LinearLayout llBlood;
    @BindView(R.id.tv_constellation)
    TextView tvConstellation;
    @BindView(R.id.tv_hobby)
    TextView tvHobby;
    @BindView(R.id.ll_hobby)
    LinearLayout llHobby;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_in_date)
    TextView tvInDate;
    @BindView(R.id.tv_class_name)
    TextView tvClassName;
    @BindView(R.id.ll_baby_info)
    LinearLayout llBabyInfo;
    @BindView(R.id.ll_birthday)
    LinearLayout llBirthday;

    private BabyInfoModel babyInfoModel;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private String fileName;
    private Uri uri;
    private CommonDialog commonDialog;

    private KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_info);
        ButterKnife.bind(this);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);

        tvTitleName.setText(getString(R.string.baby_info));
        babyInfoModel = DataSupport.where("tokenid= ? ", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, "")).findFirst(BabyInfoModel.class);
        tvName.setText(babyInfoModel.getPersonName());
        tvBlood.setText(babyInfoModel.getXuexin());

        tvInDate.setText(babyInfoModel.getInDate());
        tvBirthday.setText(babyInfoModel.getBirthDate());
        tvClassName.setText(babyInfoModel.getClassDesc());
        tvSex.setText(babyInfoModel.getSex());
        tvHobby.setText(babyInfoModel.getInterest());

        setFaceUI();

        setConstellation();
    }

    @Override
    protected BabyDetailPresenter initPresenter() {
        return new BabyDetailPresenter();
    }

    /**
     * 设置星座
     */
    private void setConstellation() {
        String birthday = babyInfoModel.getBirthDate();
        try {
            Date birth = dateFormat.parse(birthday);
            if (TextUtils.isEmpty(babyInfoModel.getXingzuo())) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(birth);
                String xinzuo = getAstro(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
                tvConstellation.setText(xinzuo);
            } else {
                tvConstellation.setText(babyInfoModel.getXingzuo());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生日转换星座
     *
     * @param month
     * @param day
     * @return
     */
    private String getAstro(int month, int day) {
        String[] astro = new String[]{"摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座",
                "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};
        int[] arr = new int[]{20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22};// 两个星座分割日
        int index = month;
        // 所查询日期在分割日之前，索引-1，否则不变
        if (day < arr[month - 1]) {
            index = index - 1;
        }
        // 返回索引指向的星座string
        return astro[index];
    }

    /**
     * 设置头像
     */
    private void setFaceUI() {
        if (!TextUtils.isEmpty(babyInfoModel.getPhoto1())) {
            Glide.with(this).load(babyInfoModel.getPhoto1()).into(civFace);
        }
    }

    @OnClick({R.id.iv_title_back, R.id.ll_face, R.id.ll_sex, R.id.ll_blood, R.id.ll_hobby, R.id.ll_birthday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.ll_face:
                //// TODO: 2017/7/30 修改头像需要返回头像地址
                PickPhotoPopupWindow.getInstance().show(llBabyInfo, false, this);
                break;
            case R.id.ll_sex:
                goBabyDetailSex();
                break;
            case R.id.ll_blood:
                goBabyDetailBlood();
                break;
            case R.id.ll_hobby:
                goBabyDetailHobby();
                break;
            case R.id.ll_birthday:
                setBirthDay();
                break;
        }
    }

    private void goBabyDetailBlood() {
        Intent intent=new Intent(BabyDetailActivity.this,BabyDetailEditActivity.class);
        intent.putExtra("action","Self");
        intent.putExtra("type","blood");
        intent.putExtra("value",tvBlood.getText().toString());
        startActivityForResult(intent,Constants.REQ_INTENT_CODE);
    }

    private void goBabyDetailHobby() {
        Intent intent=new Intent(BabyDetailActivity.this,BabyDetailEditActivity.class);
        intent.putExtra("action","Self");
        intent.putExtra("type","hobby");
        intent.putExtra("value",tvHobby.getText().toString());
        startActivityForResult(intent,Constants.REQ_INTENT_CODE);
    }

    private void goBabyDetailSex() {

        Intent intent=new Intent(BabyDetailActivity.this,BabyDetailEditActivity.class);
        intent.putExtra("action","Self");
        intent.putExtra("type","sex");
        intent.putExtra("value",tvSex.getText().toString());
        startActivityForResult(intent,Constants.REQ_INTENT_CODE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            setResult(RESULT_OK);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setBirthDay() {
        Calendar cal=Calendar.getInstance();
        if(!TextUtils.isEmpty(tvBirthday.getText().toString())){
            try {
                Date date=dateFormat.parse(tvBirthday.getText().toString());
                cal.setTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String pickDate=year+"-"+(month+1)+"-"+dayOfMonth;
                try {
                    Date date=dateFormat.parse(pickDate);
                    Date now=Calendar.getInstance().getTime();
                    if(date.getTime()>now.getTime()){
                        Toasty.info(BabyDetailActivity.this,getString(R.string.birthday_error), Toast.LENGTH_SHORT).show();
                    }else {
                        tvBirthday.setText(pickDate);
                        mPresenter.postBirthDay(pickDate);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog.show();
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
        Intent intent = new Intent(BabyDetailActivity.this, AtomAlbumActivity.class);
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
                SystemUtil.startSystemCropPhoto(this, uri, uri, 1, 1, 180, 180, Constants.CROP_PHOTO);
            }

            if (requestCode == Constants.CROP_PHOTO) {
                Glide.with(BabyDetailActivity.this).load(uri).into(civFace);
                commonDialog = new CommonDialog(BabyDetailActivity.this, getString(R.string.confirm_up), getString(R.string.confirm), getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
                    @Override
                    public void go() {
                        showProgress();
                        mPresenter.setBabyFace(uri.getPath());
                        commonDialog.dismiss();
                    }

                    @Override
                    public void cancel() {
                        commonDialog.dismiss();
                    }
                });
                commonDialog.show();
            }

            if (requestCode == PICK_CODE) {
                String filepath = PhotoSelectContainer.getFileList().get(0);
                createPhotoUrl();
                SystemUtil.startSystemCropPhoto(this, Uri.fromFile(new File(filepath)), uri, 1, 1, 180, 180, Constants.CROP_PHOTO);
            }

            if(requestCode==Constants.REQ_INTENT_CODE){
                String type=data.getStringExtra("type");
                String value=data.getStringExtra("value");
                if(type.equals("sex")){
                    tvSex.setText(value);
                    babyInfoModel.setSex(value);
                    babyInfoModel.save();
                }

                if(type.equals("blood")){
                    tvBlood.setText(value);
                    babyInfoModel.setXuexin(value);
                    babyInfoModel.save();
                }

                if(type.equals("hobby")){
                    tvHobby.setText(value);
                    babyInfoModel.setInterest(value);
                    babyInfoModel.save();
                }
            }


        }
    }

    @Override
    public void showProgress() {
        hud.show();
    }

    @Override
    public void hideProgress() {
        hud.dismiss();
    }

    @Override
    public void onError(String message) {
        hideProgress();
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        hideProgress();
        PhotoSelectContainer.clear();
        Toasty.success(this, getString(R.string.photo_up_success), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBirthSuccess() {
        String birthday = tvBirthday.getText().toString();
        babyInfoModel.setBirthDate(birthday);
        babyInfoModel.save();
        try {
            Date birth = dateFormat.parse(birthday);
            if (TextUtils.isEmpty(babyInfoModel.getXingzuo())) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(birth);
                String xinzuo = getAstro(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
                tvConstellation.setText(xinzuo);
            } else {
                tvConstellation.setText(babyInfoModel.getXingzuo());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
