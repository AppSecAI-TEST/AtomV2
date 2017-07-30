package com.tongxun.atongmu.parent.ui.my.accountchange;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.BabyInfoModel;
import com.tongxun.atongmu.parent.ui.im.ILoginIMInteractor;
import com.tongxun.atongmu.parent.ui.im.LoginIMInteractor;
import com.tongxun.atongmu.parent.util.GlideOption;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountChangeActivity extends BaseActivity implements ILoginIMInteractor.onFinishListener {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.id_account_list)
    ListView idAccountList;

    private List<BabyInfoModel> mlist = new ArrayList<>();

    private AccountAdapter mAdapter;
    private String tokenId;
    private KProgressHUD hud;

    private LoginIMInteractor imInteractor;

    private int mPosition;

    private boolean isUpDate=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_change);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        tvTitleName.setText(getString(R.string.account_change));
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);
        imInteractor = new LoginIMInteractor();
        mlist.addAll(DataSupport.findAll(BabyInfoModel.class));
        tokenId = SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, "");
        mAdapter = new AccountAdapter(this, R.layout.item_account_layout, mlist);
        idAccountList.setAdapter(mAdapter);
        idAccountList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (tokenId.equals(mlist.get(position).getTokenId())) {
                    return;
                }
                hud.show();

                changeIMAccount(position,mlist.get(position).getTokenId());
            }
        });
    }

    private void changeIMAccount(int position, String toId) {
        mPosition=position;
        imInteractor.LoginIM(toId,this);
    }

    @OnClick(R.id.iv_title_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onIMSuccess(String groupId, String imNickName, String imUsername, String imPassword) {
        tokenId=mlist.get(mPosition).getTokenId();
        mAdapter.notifyDataSetChanged();
        loginChatUser(imUsername,imPassword);
        SharedPreferences.Editor edit=SharePreferenceUtil.getEditor();
        edit.putString(SharePreferenceUtil.TOKENID, tokenId);
        edit.putString(SharePreferenceUtil.IMNICKNAME, imNickName);
        edit.putString(SharePreferenceUtil.IMUSERNAME, imUsername);
        edit.putString(SharePreferenceUtil.IMPASSWORD, imPassword);
        edit.putString(SharePreferenceUtil.GROUPID, groupId);
        edit.commit();
    }

    private void loginChatUser(String imUsername, String imPassword) {

        if(EMClient.getInstance().isLoggedInBefore()){
            EMClient.getInstance().logout(true);
            EMClient.getInstance().login(imUsername, imPassword, new EMCallBack() {
                @Override
                public void onSuccess() {
                    hud.dismiss();
                    setResult(RESULT_OK);
                    finish();
                }

                @Override
                public void onError(int i, String s) {
                    hud.dismiss();
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
        }
    }


    class AccountAdapter extends ArrayAdapter<BabyInfoModel> {
        private int resourceId;

        public AccountAdapter(Context context, int resource, List<BabyInfoModel> objects) {
            super(context, resource, objects);
            resourceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            BabyInfoModel babyDataBean = getItem(position);
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.mFaceimage = (ImageView) convertView.findViewById(R.id.id_account_item_faceimg);
                viewHolder.mName = (TextView) convertView.findViewById(R.id.id_account_item_text);
                viewHolder.mCheck = (ImageView) convertView.findViewById(R.id.id_account_item_check);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.mName.setText(babyDataBean.getPersonName());
            Glide.with(AccountChangeActivity.this)
                    .load(babyDataBean.getPhoto1())
                    .apply(GlideOption.getFaceHolderOption())
                    .into(viewHolder.mFaceimage);

            if (babyDataBean.getTokenId().equals(tokenId)) {
                viewHolder.mCheck.setVisibility(View.VISIBLE);
            } else {
                viewHolder.mCheck.setVisibility(View.INVISIBLE);
            }
            return convertView;
        }

        class ViewHolder {
            ImageView mFaceimage;
            TextView mName;
            ImageView mCheck;
        }
    }
}
