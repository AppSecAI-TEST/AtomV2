package com.tongxun.atongmu.parent.ui.my.recharge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.BabyInfoModel;
import com.tongxun.atongmu.parent.model.RechargeItemModel;
import com.tongxun.atongmu.parent.ui.WebViewActivity;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

import static com.tongxun.atongmu.parent.R.string.recharge;

public class RechargeActivity extends Base2Activity<IRechargeContract.View, RechargePresenter> implements IRechargeContract.View {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.civ_recharge_face)
    CircleImageView civRechargeFace;
    @BindView(R.id.tv_recharge_name)
    TextView tvRechargeName;
    @BindView(R.id.tv_recharge_class)
    TextView tvRechargeClass;
    @BindView(R.id.tv_recharge_last_time)
    TextView tvRechargeLastTime;
    @BindView(R.id.tv_function_last_time)
    TextView tvFunctionLastTime;
    @BindView(R.id.tv_happy_meal)
    TextView tvHappyMeal;
    @BindView(R.id.tv_function_meal)
    TextView tvFunctionMeal;
    @BindView(R.id.tv_recharge_tip)
    TextView tvRechargeTip;
    @BindView(R.id.tv_recharge_more)
    TextView tvRechargeMore;
    @BindView(R.id.gv_recharge)
    GridView gvRecharge;

    private BabyInfoModel babyInfoModel;

    private RechargeAdapter mAdapter;

    private List<RechargeItemModel> happylist = new ArrayList<>();
    private List<RechargeItemModel> funlist = new ArrayList<>();
    private String joyPath;
    private String funPath;
    private int pagePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        tvTitleName.setText(getString(recharge));
        tvTitleRight.setText(getString(R.string.recharge_record));
        babyInfoModel = DataSupport.where("tokenid= ? ", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, "")).findFirst(BabyInfoModel.class);
        setRechargeUI();

        mPresenter.getWebPayItemList();

        setPosition(0);

        gvRecharge.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               if(pagePosition==0){
                   Intent intent=new Intent(RechargeActivity.this,PayOrderActivity.class);
                   intent.putExtra("bean",happylist.get(i));
                   intent.putExtra("action","New");
                   startActivity(intent);
               }
            }
        });
    }

    private void setPosition(int i) {
        resetPosition();
        switch (i){
            case 0:
                pagePosition=0;
                tvHappyMeal.setSelected(true);
                mAdapter = new RechargeAdapter(this, R.layout.activity_recharge_grid_item_layout, happylist);
                gvRecharge.setAdapter(mAdapter);
                break;
            case 1:
                pagePosition=1;
                tvFunctionMeal.setSelected(true);
                mAdapter = new RechargeAdapter(this, R.layout.activity_recharge_grid_item_layout, funlist);
                gvRecharge.setAdapter(mAdapter);
                break;
        }
    }

    private void resetPosition() {
        tvHappyMeal.setSelected(false);
        tvFunctionMeal.setSelected(false);
    }

    private void setRechargeUI() {
        Glide.with(this).load(babyInfoModel.getPhoto1()).into(civRechargeFace);
        tvRechargeName.setText(babyInfoModel.getPersonName());
        tvRechargeClass.setText(babyInfoModel.getClassDesc());

    }

    @Override
    protected RechargePresenter initPresenter() {
        return new RechargePresenter();
    }

    @OnClick({R.id.iv_title_back, R.id.tv_title_right, R.id.tv_happy_meal, R.id.tv_function_meal, R.id.tv_recharge_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_title_right:
                Intent intent=new Intent(RechargeActivity.this,OrderActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_happy_meal:
                setPosition(0);

                break;
            case R.id.tv_function_meal:
                setPosition(1);

                break;
            case R.id.tv_recharge_more:

                if(tvHappyMeal.isSelected()){
                    WebViewActivity.startWebViewActivity(RechargeActivity.this,"","", Constants.DEFAULTICON,joyPath);
                }else {
                    WebViewActivity.startWebViewActivity(RechargeActivity.this,"","", Constants.DEFAULTICON,funPath);
                }
                break;
        }
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChargeItemSuccess(String haveExtrapackg, String joyPath, String functionPath, String funLastTime, String packgLastTime, List<RechargeItemModel> data) {
        this.joyPath=joyPath;
        funPath=functionPath;
        tvRechargeLastTime.setText(packgLastTime);
        tvFunctionLastTime.setText(funLastTime);
        if(haveExtrapackg.equals("true")){
            tvFunctionMeal.setVisibility(View.VISIBLE);
            tvHappyMeal.setGravity(Gravity.CENTER);
        }else {
            tvFunctionMeal.setVisibility(View.GONE);
            tvHappyMeal.setGravity(Gravity.CENTER_VERTICAL);
        }
        if(data.size()>0){
            for(RechargeItemModel model:data){
                model.setItemCheck(false);
                if(model.getPackgType().equals("欢乐")){
                    happylist.add(model);
                }else {
                    funlist.add(model);
                }
            }
            setPosition(0);
        }

    }


    class RechargeAdapter extends ArrayAdapter<RechargeItemModel> {

        int resourceId;

        public RechargeAdapter(Context context, int resource, List<RechargeItemModel> objects) {
            super(context, resource, objects);
            resourceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            RechargeViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(RechargeActivity.this).inflate(resourceId, null);
                viewHolder = new RechargeViewHolder();
                viewHolder.mtitle = (TextView) convertView.findViewById(R.id.tv_recharge_item_title);
                viewHolder.mPrice = (TextView) convertView.findViewById(R.id.tv_recharge_item_price);
                viewHolder.mOriginalPrice = (TextView) convertView.findViewById(R.id.tv_original_price);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (RechargeViewHolder) convertView.getTag();
            }
            RechargeItemModel datasBean = getItem(position);
            viewHolder.mtitle.setText(datasBean.getPackgTitle());
            viewHolder.mPrice.setText(datasBean.getPackgPrice());
            viewHolder.mOriginalPrice.setText(datasBean.getPackgRemark());
            return convertView;
        }

        class RechargeViewHolder {
            TextView mtitle;
            TextView mPrice;
            TextView mOriginalPrice;
        }
    }
}
