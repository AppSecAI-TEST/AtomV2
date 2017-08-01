package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.FinishWorkModel;
import com.tongxun.atongmu.parent.util.GlideOption;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 2017/8/1.
 */

public class FinishItemAdapter extends RecyclerView.Adapter<FinishItemAdapter.FinishItemViewHolder> {


    private Context mContext;
    private List<FinishWorkModel> mlist = new ArrayList<>();

    public FinishItemAdapter(Context context, List<FinishWorkModel> list) {
        mContext = context;
        mlist = list;
    }

    @Override
    public FinishItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_finish_item_layout, parent, false);
        FinishItemViewHolder viewHolder = new FinishItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FinishItemViewHolder holder, int position) {
        holder.tvHomeworkName.setText(mlist.get(position).getStudentName());
        Glide.with(mContext).load(mlist.get(position).getStudentImage()).apply(GlideOption.getFaceHolderOption()).into(holder.civHomeworkFace);
        holder.tvHomeworkTime.setText(mlist.get(position).getFinishTime());
        if(mlist.get(position).getHaveVoice().equals("true")){
            holder.llAudio.setVisibility(View.VISIBLE);
        }else {
            holder.llAudio.setVisibility(View.GONE);
        }

        if(mlist.get(position).getHaveVideo().equals("true")){
            holder.niceHomeworkVideo.setVisibility(View.VISIBLE);
            holder.niceHomeworkVideo.setPlayerType(NiceVideoPlayer.PLAYER_TYPE_IJK);
            holder.niceHomeworkVideo.setUp(mlist.get(position).getVideoUrl(),null);
            TxVideoPlayerController controller = new TxVideoPlayerController(mContext);
            controller.setTitle("");
            controller.setImage(R.drawable.video_black_shape);
            holder.niceHomeworkVideo.setController(controller);
        }else {
            holder.niceHomeworkVideo.setVisibility(View.GONE);
        }

        if(TextUtils.isEmpty(mlist.get(position).getFinishRemarks())){
            holder.tvHomeworkContent.setVisibility(View.GONE);
        }else {
            holder.tvHomeworkContent.setVisibility(View.VISIBLE);
            holder.tvHomeworkContent.setText(mlist.get(position).getFinishRemarks());
        }
        holder.tvCommentFlower.setText(mlist.get(position).getFlower());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class FinishItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_homework_face)
        CircleImageView civHomeworkFace;
        @BindView(R.id.tv_homework_name)
        TextView tvHomeworkName;
        @BindView(R.id.tv_homework_time)
        TextView tvHomeworkTime;
        @BindView(R.id.iv_share_homework)
        ImageView ivShareHomework;
        @BindView(R.id.iv_delete_homework)
        ImageView ivDeleteHomework;
        @BindView(R.id.iv_voice_anim)
        ImageView ivVoiceAnim;
        @BindView(R.id.tv_audio_time)
        TextView tvAudioTime;
        @BindView(R.id.ll_audio)
        LinearLayout llAudio;
        @BindView(R.id.tv_homework_content)
        TextView tvHomeworkContent;
        @BindView(R.id.nice_homework_video)
        NiceVideoPlayer niceHomeworkVideo;
        @BindView(R.id.rv_homework_photo)
        RecyclerView rvHomeworkPhoto;
        @BindView(R.id.tv_comment_flower)
        TextView tvCommentFlower;
        @BindView(R.id.iv_comment_voice_anim)
        ImageView ivCommentVoiceAnim;
        @BindView(R.id.tv_comment_audio_time)
        TextView tvCommentAudioTime;
        @BindView(R.id.ll_comment_audio)
        LinearLayout llCommentAudio;
        @BindView(R.id.tv_comment_content)
        TextView tvCommentContent;
        public FinishItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
