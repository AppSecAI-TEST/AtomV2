package com.tongxun.atongmu.parent.ui.my.babydetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.BaseCallBack;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.MediaType;

public class BabyDetailEditActivity extends BaseActivity {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.et_hobby)
    EditText etHobby;
    @BindView(R.id.lv_baby_detail_edit)
    ListView lvBabyDetailEdit;
    private String type;
    private String value;
    private String action;

    private BabyEditAdapter mAdapter;

    private KProgressHUD hud;

    private List<String> mlist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_detail_edit);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorWhite);
        tvTitleName.setText("");
        tvTitleRight.setText(getString(R.string.save));
        Intent intent=getIntent();
        type=intent.getStringExtra("type");
        value=intent.getStringExtra("value");
        action=intent.getStringExtra("action");

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);

        if(type.equals("hobby")){
            etHobby.setVisibility(View.VISIBLE);
            lvBabyDetailEdit.setVisibility(View.GONE);
            if(!TextUtils.isEmpty(value)){
                etHobby.setText(value);
            }

        }else {
            etHobby.setVisibility(View.GONE);
            lvBabyDetailEdit.setVisibility(View.VISIBLE);
            if(type.equals("sex")){
                if(TextUtils.isEmpty(value)){
                    value="男";

                }
                mlist.add("男");
                mlist.add("女");
                mAdapter=new BabyEditAdapter(this,R.layout.item_account_layout,mlist);
                lvBabyDetailEdit.setAdapter(mAdapter);
            }else {
                if(TextUtils.isEmpty(value)){
                    value="O型";

                }
                mlist.add("O型");
                mlist.add("A型");
                mlist.add("B型");
                mlist.add("AB型");
                mAdapter=new BabyEditAdapter(this,R.layout.item_account_layout,mlist);
                lvBabyDetailEdit.setAdapter(mAdapter);
            }
        }

        lvBabyDetailEdit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                value=mlist.get(i);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.iv_title_back, R.id.tv_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_title_right:
                if(action.equals("Self")){
                    hud.show();
                    saveBabyEdit();
                }else {
                    Intent intent=new Intent();
                    intent.putExtra("type",type);
                    intent.putExtra("value",value);
                    setResult(RESULT_OK,intent);
                    finish();
                }

                break;
        }
    }

    private void saveBabyEdit() {
        String url= Constants.restUpdateStudentInfo_v2;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        hud.dismiss();
                        Toasty.error(BabyDetailEditActivity.this, getString(R.string.net_error), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        hud.dismiss();
                        Gson gson=new Gson();
                        BaseCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,BaseCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                Toasty.success(BabyDetailEditActivity.this, getString(R.string.change_success), Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent();
                                intent.putExtra("type",type);
                                intent.putExtra("value",value);
                                setResult(RESULT_OK,intent);
                                finish();
                            }else {
                                Toasty.error(BabyDetailEditActivity.this, callBack.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toasty.error(BabyDetailEditActivity.this, getString(R.string.date_error), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private String CreateJson() {
        JSONObject jsonObject=null;
        try {
            jsonObject=new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            if(type.equals("hobby")){
                value=etHobby.getText().toString();
                jsonObject.put("interest",value);
            }
            if(type.equals("sex")){
                jsonObject.put("sex",value);
            }
            if(type.equals("blood")){
                jsonObject.put("xuexin",value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    class BabyEditAdapter extends ArrayAdapter<String>{

        public BabyEditAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder=null;
            if(convertView==null){
                convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_account_layout,parent,false);
                viewHolder=new ViewHolder();
                viewHolder.mFaceimage= (ImageView) convertView.findViewById(R.id.id_account_item_faceimg);
                viewHolder.mName= (TextView) convertView.findViewById(R.id.id_account_item_text);
                viewHolder.mCheck= (ImageView) convertView.findViewById(R.id.id_account_item_check);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.mName.setText(mlist.get(position));
            viewHolder.mFaceimage.setVisibility(View.GONE);
            if(mlist.get(position).equals(value)){
                viewHolder.mCheck.setVisibility(View.VISIBLE);
            }else {
                viewHolder.mCheck.setVisibility(View.INVISIBLE);
            }
            return convertView;
        }

        class ViewHolder{
            ImageView mFaceimage;
            TextView mName;
            ImageView mCheck;
        }
    }
}
