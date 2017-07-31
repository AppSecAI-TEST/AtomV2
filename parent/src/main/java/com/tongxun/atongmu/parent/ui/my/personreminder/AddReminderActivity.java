package com.tongxun.atongmu.parent.ui.my.personreminder;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.AddPhotoAdapter;
import com.tongxun.atongmu.parent.ui.AtomAlbumActivity;
import com.tongxun.atongmu.parent.ui.PhotoSelectContainer;
import com.tongxun.atongmu.parent.ui.PhotoViewActivity;
import com.tongxun.atongmu.parent.ui.photoClickListener;
import com.tongxun.atongmu.parent.util.DividerGridItemDecoration;
import com.tongxun.atongmu.parent.util.PickPhotoPopupWindow;
import com.tongxun.atongmu.parent.util.SDCardUtil;
import com.tongxun.atongmu.parent.util.SystemUtil;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileBatchCallback;
import com.zxy.tiny.callback.FileCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

import static com.tongxun.atongmu.parent.Constants.PICK_CODE;

public class AddReminderActivity extends Base2Activity<IAddReminderContract.View, AddReminderPresenter> implements IAddReminderContract.View, photoClickListener, PickPhotoPopupWindow.popClickListener {

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
    @BindView(R.id.ll_add_reminder)
    LinearLayout llAddReminder;

    private AddPhotoAdapter mAdapter;

    private KProgressHUD hud;

    private List<String> mlist = new ArrayList<>();

    private String fileName;

    private int TAKE_TIME = 1;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        tvTitleName.setText(getString(R.string.create_new_reminder));

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);

        tvTime.setText(dateFormat.format(new Date()));
        tvTitleRight.setText(getString(R.string.complete));
        setRecyclerViewUI();
    }

    private void setRecyclerViewUI() {
        rvPhoto.setLayoutManager(new GridLayoutManager(this, 4));
        rvPhoto.setItemAnimator(new DefaultItemAnimator());
        rvPhoto.addItemDecoration(new DividerGridItemDecoration(this));
        mlist.add("SELECT");
        mAdapter = new AddPhotoAdapter(this, mlist, 3, this);
        rvPhoto.setAdapter(mAdapter);
    }

    @Override
    protected AddReminderPresenter initPresenter() {
        return new AddReminderPresenter();
    }

    @OnClick({R.id.iv_title_back, R.id.tv_title_right, R.id.tv_reduce, R.id.tv_add, R.id.tv_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_title_right:
                hud.show();
                createNewReminder();
                break;
            case R.id.tv_reduce:
                if (TAKE_TIME > 1) {
                    TAKE_TIME -= 1;
                }
                tvNum.setText(TAKE_TIME + "");
                break;
            case R.id.tv_add:
                TAKE_TIME += 1;
                tvNum.setText(TAKE_TIME + "");
                break;
            case R.id.tv_time:
                pickDateDialog();
                break;
        }
    }

    private void createNewReminder() {
        mPresenter.createNewReminder(etRemark.getText().toString(), tvTime.getText().toString(), tvNum.getText().toString(), mlist);

    }

    private void pickDateDialog() {
        Calendar c = Calendar.getInstance();
        try {
            Date date = dateFormat.parse(tvTime.getText().toString());
            c.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear, int startDayOfMonth) {
                String str = startYear + "-" + (startMonthOfYear + 1) + "-" + startDayOfMonth;
                try {
                    Date date = dateFormat.parse(str);
                    if (date.getTime() > Calendar.getInstance().getTime().getTime()) {
                        tvTime.setText(str);
                    } else {
                        Toasty.info(AddReminderActivity.this, getString(R.string.create_reminder_before_today), Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE)).show();
    }

    @Override
    public void onAddPhoto(int num) {
        PickPhotoPopupWindow.getInstance().show(llAddReminder, false, this);
    }

    @Override
    public void onPhotoClick(String photoUrl) {
        ArrayList<String> list = new ArrayList<>();
        list.add(photoUrl);
        PhotoViewActivity.startActivity(this, list);
    }

    @Override
    public void onDeletePhoto(int position) {
        mlist.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onCamera() {
        if (mlist.size() < 10) {
            PermissionGen.with(this)
                    .addRequestCode(Constants.PERMISSION_CAMERA_CODE)
                    .permissions(
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    .request();
        } else {
            Toasty.info(this, getResources().getString(R.string.photo_size_nine), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取相机权限后打开系统相机
     */
    @PermissionSuccess(requestCode = 100)
    public void doCamera() {
        fileName = UUID.randomUUID() + ".jpg";
        SystemUtil.opSystemCamera(this, SDCardUtil.getInstance().getFilePath() + fileName, Constants.REQ_CODE);
    }

    @PermissionFail(requestCode = 100)
    public void doSomeError() {
        Toasty.error(this, getResources().getString(R.string.get_camera_permission_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPhoto() {
        if (mlist.size() < 10) {
            PermissionGen.with(this)
                    .addRequestCode(Constants.PERMISSION_PHOTO_CODE)
                    .permissions(
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                    .request();
        } else {
            Toasty.info(this, getResources().getString(R.string.photo_size_nine), Toast.LENGTH_SHORT).show();
        }
    }

    @PermissionSuccess(requestCode = 102)
    public void doPhoto() {
        int num = 10 - mlist.size();
        PhotoSelectContainer.setMaxSize(num);
        Intent intent = new Intent(AddReminderActivity.this, AtomAlbumActivity.class);
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
                Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
                Tiny.getInstance().source(SDCardUtil.getInstance().getFilePath() + fileName).asFile().withOptions(options).compress(new FileCallback() {
                    @Override
                    public void callback(boolean isSuccess, String outfile) {
                        if (isSuccess) {
                            if (!mlist.contains(outfile)) {
                                mlist.add(outfile);
                            }

                            mAdapter.notifyDataSetChanged();

                        } else {
                            Toasty.error(AddReminderActivity.this, getResources().getString(R.string.pick_photo_error), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            if (requestCode == Constants.PICK_CODE) {
                hud.show();
                Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
                String[] strs = PhotoSelectContainer.getFileList().toArray(new String[PhotoSelectContainer.getFileList().size()]);
                Tiny.getInstance().source(strs).batchAsFile().withOptions(options).batchCompress(new FileBatchCallback() {
                    @Override
                    public void callback(boolean isSuccess, String[] outfile) {
                        if (isSuccess) {
                            PhotoSelectContainer.clear();
                            List<String> list = Arrays.asList(outfile);
                            for (String str : list) {
                                if (!mlist.contains(str)) {
                                    mlist.add(str);
                                }
                            }

                            mAdapter.notifyDataSetChanged();
                            hud.dismiss();
                        } else {
                            Toasty.error(AddReminderActivity.this, getResources().getString(R.string.pick_photo_error), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }


    @Override
    public void onError(String message) {
        hud.dismiss();
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        hud.dismiss();
        Toasty.success(this, getString(R.string.reminder_create_success), Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }
}
