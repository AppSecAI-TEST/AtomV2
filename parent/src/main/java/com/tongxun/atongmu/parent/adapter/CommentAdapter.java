package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hyphenate.easeui.widget.EmojiTextView;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.FriendCircleCommentModel;

import java.util.List;

/**
 * Created by Anro on 2016/6/6.
 */
public class CommentAdapter extends ArrayAdapter<FriendCircleCommentModel> {
    int resourceId;
    private onCommentNameLinstener linstener;
    public CommentAdapter(Context context, int resource, List<FriendCircleCommentModel> objects) {
        super(context, resource, objects);
        resourceId = resource;
        this.linstener=linstener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        FriendCircleCommentModel commentPerson = getItem(position);
        View view = null;
        ViewHolder viewHolder = null;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.commentPersonA = (TextView) view.findViewById(R.id.comment_item_personA);
            viewHolder.commentPersonB = (TextView) view.findViewById(R.id.comment_item_personB);
            viewHolder.colon = (TextView) view.findViewById(R.id.comment_item_colon);
            viewHolder.reply = (TextView) view.findViewById(R.id.comment_item_reply);
            viewHolder.commentContent = (EmojiTextView) view.findViewById(R.id.comment_item_content);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if(commentPerson.getCommentType()!=null && commentPerson.getCommentType().equals("回复")){
            viewHolder.commentPersonB.setVisibility(View.VISIBLE);
            viewHolder.reply.setVisibility(View.VISIBLE);
            viewHolder.commentPersonB.setText(commentPerson.getCommentSourcePersonName());
        }else {
            viewHolder.commentPersonB.setVisibility(View.GONE);
            viewHolder.reply.setVisibility(View.GONE);
        }
        viewHolder.commentPersonA.setText(commentPerson.getCommentPersonName());
        String str = null;
        try {
            str = unicode2String(commentPerson.getCommentText());
        } catch (Exception e) {
            e.printStackTrace();
            str = "";
        }
        viewHolder.commentPersonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linstener.onCommentNameClick(position);
            }
        });
        viewHolder.commentContent.setText(str);
        return view;
    }


    /**
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            if (hex[i].equals("20")) {
                string.append(" ");
            } else {
                // 转换出每一个代码点
                int data = Integer.parseInt(hex[i], 16);
                // 追加成string
                string.append((char) data);
            }

        }

        return string.toString();
    }


    private class ViewHolder {
        TextView commentPersonA;//评论人
        EmojiTextView commentContent;//评论内容
        TextView commentPersonB;//用户
        TextView colon;//冒号
        TextView reply;//回复
    }

    public interface onCommentNameLinstener{
        void onCommentNameClick(int position);
    }
}
