package com.tongxun.atongmu.parent.ui.my;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.dialog.CommonDialog;
import com.tongxun.atongmu.parent.model.BaseCallBack;
import com.tongxun.atongmu.parent.model.FeedBackRecord;
import com.tongxun.atongmu.parent.util.GlideOption;
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

public class FeedBackRecordActivity extends BaseActivity {


    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.lv_feedback_record)
    ListView lvFeedbackRecord;
    @BindView(R.id.swipe_feedback_record)
    SwipeRefreshLayout swipeFeedbackRecord;

    private List<FeedBackRecord.DatasBean> mlist = new ArrayList<>();

    private FeedBackRecordAdapter mAdapter;

    private CommonDialog commonDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_record);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        tvTitleName.setText(getString(R.string.feedback_record));
        tvTitleRight.setText(getString(R.string.empty));
        swipeFeedbackRecord.setColorSchemeColors(
                Color.parseColor("#ff33b5e5"),
                Color.parseColor("#ffff4444"),
                Color.parseColor("#ffffbb33"),
                Color.parseColor("#ff99cc00")
        );
        mAdapter = new FeedBackRecordAdapter(FeedBackRecordActivity.this, R.layout.item_feedback_record, mlist);
        lvFeedbackRecord.setAdapter(mAdapter);
        swipeFeedbackRecord.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeFeedbackRecord.setRefreshing(true);
                getWebFeedBackRecord();
            }
        });
        getWebFeedBackRecord();
    }

    private void getWebFeedBackRecord() {
        String url= Constants.restGetSuggestParentList_v2;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toasty.error(FeedBackRecordActivity.this, getString(R.string.net_error), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        swipeFeedbackRecord.setRefreshing(false);
                        Gson gson=new Gson();
                        FeedBackRecord callBack= null;
                        try {
                            callBack = gson.fromJson(response,FeedBackRecord.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                if (mlist.size() > 0) {
                                    mlist.clear();
                                }
                                mlist.addAll(callBack.getDatas());
                                mAdapter.notifyDataSetChanged();
                            }else {
                                Toasty.error(FeedBackRecordActivity.this,callBack.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toasty.error(FeedBackRecordActivity.this,getString(R.string.date_error), Toast.LENGTH_SHORT).show();
                        }
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
                commonDialog=new CommonDialog(FeedBackRecordActivity.this,getResources().getString(R.string.confirm_clean_empty),getResources().getString(R.string.confirm),getResources().getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
                    @Override
                    public void go() {
                        EmptyFeedBackRecord();
                        commonDialog.dismiss();
                    }

                    @Override
                    public void cancel() {
                        commonDialog.dismiss();
                    }
                });
                commonDialog.show();
                break;
        }
    }

    private void EmptyFeedBackRecord() {
        String url=Constants.restDeleteSuggestParent_v2;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toasty.error(FeedBackRecordActivity.this, getString(R.string.net_error), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        BaseCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,BaseCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                mlist.clear();
                                mAdapter.notifyDataSetChanged();
                            }else {
                                Toasty.error(FeedBackRecordActivity.this,callBack.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toasty.error(FeedBackRecordActivity.this,getString(R.string.date_error), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private String CreateJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    private class FeedBackRecordAdapter extends ArrayAdapter<FeedBackRecord.DatasBean> {
        public FeedBackRecordAdapter(Context context, int resource, List<FeedBackRecord.DatasBean> objects) {
            super(context, resource, objects);

        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHodler viewHodler = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(FeedBackRecordActivity.this).inflate(R.layout.item_feedback_record, parent, false);
                viewHodler = new ViewHodler();
                viewHodler.tv_Date = (TextView) convertView.findViewById(R.id.tv_feedback_date);
                viewHodler.tv_Content = (TextView) convertView.findViewById(R.id.tv_feedback_content);
                viewHodler.tv_Reply = (TextView) convertView.findViewById(R.id.tv_feedback_reply);
                viewHodler.gv_FeedBack = (GridView) convertView.findViewById(R.id.gv_feedback_record);
                viewHodler.ll_reply= (LinearLayout) convertView.findViewById(R.id.ll_feedback_reply);
                convertView.setTag(viewHodler);
            } else {
                viewHodler = (ViewHodler) convertView.getTag();
            }

            FeedBackRecord.DatasBean datasBean = mlist.get(position);
            viewHodler.tv_Date.setText(datasBean.getSuggestDate()+"");
            viewHodler.tv_Content.setText(datasBean.getSuggestText()+"");
            if(TextUtils.isEmpty(datasBean.getComment())){
                viewHodler.ll_reply.setVisibility(View.GONE);
            }else {
                viewHodler.ll_reply.setVisibility(View.VISIBLE);
                viewHodler.tv_Reply.setText(datasBean.getComment()+"");
            }

            if(datasBean.getPhotos().size()>0){
                viewHodler.gv_FeedBack.setVisibility(View.VISIBLE);
                viewHodler.gv_FeedBack.setAdapter(new ImageGridViewAdapter(FeedBackRecordActivity.this, R.layout.item_circle_photo_layout,datasBean.getPhotos()));
            }else {
                viewHodler.gv_FeedBack.setVisibility(View.GONE);
            }

            viewHodler.gv_FeedBack.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int itemposition, long id) {

                }
            });

            return convertView;
        }

        class ViewHodler {
            TextView tv_Date;
            TextView tv_Reply;
            TextView tv_Content;
            GridView gv_FeedBack;
            LinearLayout ll_reply;
        }
    }

    class ImageGridViewAdapter extends ArrayAdapter<FeedBackRecord.DatasBean.PhotosBean>{
        int ResouceId;
        private List<FeedBackRecord.DatasBean.PhotosBean> photosDataBeanList;

        public ImageGridViewAdapter(Context context, int resource, List<FeedBackRecord.DatasBean.PhotosBean> objects) {
            super(context, resource, objects);
            ResouceId=resource;
            photosDataBeanList=objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            ImgGridViewHolder viewHolder = null;
            if (convertView == null) {
                view = LayoutInflater.from(FeedBackRecordActivity.this).inflate(ResouceId, null);
                viewHolder = new ImgGridViewHolder();
                viewHolder.img = (ImageView) view.findViewById(R.id.iv_item_img);
                viewHolder.mbg = (ImageView) view.findViewById(R.id.iv_item_bg);
                viewHolder.mtext = (TextView) view.findViewById(R.id.tv_item_num);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (ImgGridViewHolder) view.getTag();
            }
            FeedBackRecord.DatasBean.PhotosBean dataBean = getItem(position);
            Glide.with(FeedBackRecordActivity.this)
                    .load(dataBean.getPhoto())
                    .apply(GlideOption.getPHOption())
                    .into(viewHolder.img);
            if (photosDataBeanList.size() > 9) {
                if (position == 8) {
                    viewHolder.mbg.setVisibility(View.VISIBLE);
                    viewHolder.mtext.setVisibility(View.VISIBLE);
                    viewHolder.mtext.setText("+" + (photosDataBeanList.size() - 9));
                }
                if (position < 8) {
                    viewHolder.mbg.setVisibility(View.GONE);
                    viewHolder.mtext.setVisibility(View.GONE);
                }
            } else {
                viewHolder.mbg.setVisibility(View.GONE);
                viewHolder.mtext.setVisibility(View.GONE);
            }
            return view;
        }

        private class ImgGridViewHolder {
            private ImageView img;
            private ImageView mbg;
            private TextView mtext;
        }
    }

}
