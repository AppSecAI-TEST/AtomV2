package com.tongxun.atongmu.parent.ui.schoolbus;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.dialog.CommonDialog;
import com.tongxun.atongmu.parent.util.SystemUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class BusMapActivity extends Base2Activity<IBusMapContract.View, BusMapPresenter> implements IBusMapContract.View {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.iv_map_bus_info)
    ImageView ivMapBusInfo;
    @BindView(R.id.tv_busmap_carstatus)
    TextView tvBusmapCarstatus;
    @BindView(R.id.tv_car_name)
    TextView tvCarName;
    @BindView(R.id.tv_car_num)
    TextView tvCarNum;
    @BindView(R.id.tv_driver)
    TextView tvDriver;
    @BindView(R.id.tv_driver_num)
    TextView tvDriverNum;
    @BindView(R.id.tv_teacher)
    TextView tvTeacher;
    @BindView(R.id.tv_tea_num)
    TextView tvTeaNum;
    @BindView(R.id.ll_busmap_info_tea)
    LinearLayout llBusmapInfoTea;
    @BindView(R.id.ll_bus_info)
    LinearLayout llBusInfo;
    @BindView(R.id.map_bus)
    MapView mapBus;
    BaiduMap mBaiduMap = null;

    private TimeCount time;

    BitmapDescriptor bd;

    private String carName;

    CommonDialog commonDialog;

    private String mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_bus_map);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        tvTitleName.setText(getString(R.string.school_car_status));
        mBaiduMap = mapBus.getMap();
        mapBus.showZoomControls(false);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(
                new MapStatus.Builder().zoom(17).build()));

        time = new TimeCount(10000, 1000);

        mPresenter.getBusinfo();
    }

    @Override
    protected BusMapPresenter initPresenter() {
        return new BusMapPresenter();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    /**
     * 获取位置失败
     *
     * @param message
     */
    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 刷新校车位置
     *
     * @param carStatus
     * @param longitude
     * @param latitude
     */
    @Override
    public void refreshPositionSuccess(String carStatus, String longitude, String latitude) {
        setCarStatus(carStatus);
        if (!TextUtils.isEmpty(latitude) && !TextUtils.isEmpty(longitude)) {
            LatLng ptCenter = new LatLng((Float.valueOf(latitude)), (Float.valueOf(longitude)));
            initViews(carName);
            drawRealtimePoint(ptCenter);
        }
    }

    private void initViews(String title) {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.maker_item, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        tvTitle.setTextColor(Color.BLUE);

        bd = BitmapDescriptorFactory.fromView(view);
    }

    private void drawRealtimePoint(LatLng ptCenter) {
        mBaiduMap.clear();

        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(
                new MapStatus.Builder().target(ptCenter).build()));
        mBaiduMap.addOverlay(new MarkerOptions().position(ptCenter).icon(bd).zIndex(9).draggable(true));
    }

    @Override
    public void setSchoolCarInfn(String carStatus, String carName, String carNum, String teacher, String teaNum, String driver, String driverNum, String longitude, String latitude) {
        this.carName = carName;
        tvCarName.setText(carName);
        tvCarNum.setText(carNum);
        tvDriver.setText(driver);
        if (driverNum == null || driverNum.equals("")) {
            tvDriverNum.setVisibility(View.GONE);
        } else {
            tvDriverNum.setVisibility(View.VISIBLE);
            tvDriverNum.setText(driverNum);
            callNum(tvDriverNum, driverNum);
        }
        if (teacher == null || teacher.equals("")) {
            llBusmapInfoTea.setVisibility(View.GONE);
        } else {
            llBusmapInfoTea.setVisibility(View.VISIBLE);
            tvTeacher.setText(teacher);
            if (teaNum == null || teaNum.equals("")) {
                tvTeaNum.setVisibility(View.GONE);
            } else {
                tvTeaNum.setVisibility(View.VISIBLE);
                tvTeaNum.setText(teaNum);
                callNum(tvTeaNum, teaNum);
            }
        }


        setCarStatus(carStatus);

        time.start();
    }

    private void callNum(TextView tvTeaNum, String teaNum) {
        mPhone = teaNum;
        tvTeaNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commonDialog = new CommonDialog(BusMapActivity.this, getResources().getString(R.string.is_call_phone) + mPhone + "?", getResources().getString(R.string.confirm), getResources().getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
                    @Override
                    public void go() {
                        PermissionGen.with(BusMapActivity.this)
                                .addRequestCode(Constants.PERMISSION_PHONE_CODE)
                                .permissions(
                                        Manifest.permission.CALL_PHONE
                                )
                                .request();
                        commonDialog.dismiss();
                    }

                    @Override
                    public void cancel() {
                        commonDialog.dismiss();
                    }
                });
                commonDialog.show();
            }
        });
    }

    private void setCarStatus(String carstatus) {
        if (carstatus != null) {
            if (carstatus.equals("0")) {
                tvBusmapCarstatus.setText("在线");
                tvBusmapCarstatus.setTextColor(getResources().getColor(R.color.colorMainYellow));
                ivMapBusInfo.setImageResource(R.drawable.iv_map_bus_info);
            } else {
                tvBusmapCarstatus.setText("离线");
                tvBusmapCarstatus.setTextColor(getResources().getColor(R.color.BusStatus));
                ivMapBusInfo.setImageResource(R.drawable.iv_map_bus_info_notonline);
            }
        } else {
            tvBusmapCarstatus.setText("离线");
            tvBusmapCarstatus.setTextColor(getResources().getColor(R.color.BusStatus));
            ivMapBusInfo.setImageResource(R.drawable.iv_map_bus_info_notonline);
        }
    }

    @OnClick(R.id.iv_title_back)
    public void onViewClicked() {
        finish();
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            mPresenter.getPositionForBus();
        }

    }


    @Override
    protected void onPause() {
        mapBus.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mapBus.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mapBus.onDestroy();
        if (time != null) {
            time.cancel();
        }
        time = null;
        super.onDestroy();
    }

    @PermissionSuccess(requestCode = 104)
    public void doPhone() {
        SystemUtil.openSystemPhone(BusMapActivity.this, mPhone);
    }

    @PermissionFail(requestCode = 104)
    public void doSomeError() {
        Toasty.error(this, getResources().getString(R.string.get_phone_permission_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}
