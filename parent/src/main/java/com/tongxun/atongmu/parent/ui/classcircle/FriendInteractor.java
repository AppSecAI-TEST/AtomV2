package com.tongxun.atongmu.parent.ui.classcircle;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.model.BaseCallBack;
import com.tongxun.atongmu.parent.model.CircleCommetCallBack;
import com.tongxun.atongmu.parent.model.FriendCircleCallBack;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Anro on 2017/7/5.
 */

public class FriendInteractor implements IFriendCircleContract.Interactor {

    /**
     * 获得最新的20条圈子
     */
    @Override
    public void getTopCircle(final onFinishLinstener listener) {
        String url= Constants.restGetCircleTop20_v2;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onError(ParentApplication.getContext().getString(R.string.net_error));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        FriendCircleCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,FriendCircleCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                listener.onSuccess(callBack.getCurrentNickName(),callBack.getDatas());
                            }else {
                                listener.onError(callBack.getMessage());
                            }
                        }else {
                            listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    /**
     * 请求家长能否发布圈子
     */
    @Override
    public void getParentIsCanPutCircle(final onFinishLinstener listener) {
        String url=Constants.restIsCanPutCircle_v2;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateCanPutJson())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onError(ParentApplication.getContext().getString(R.string.net_error));
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
                                listener.onCanPutSuccess();
                            }else {
                                listener.onCanPutFail();
                            }
                        }else {
                            listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    private String CreateCanPutJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId",  SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            //// TODO: 2017/8/6 classId
            jsonObject.put("classcId", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 点赞
     */
    @Override
    public void setItemList(final int position, String sourceId, final onFinishLinstener listener) {
        String url=Constants.restSetCircleVote_v2;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateSourceIdJson(sourceId))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onError(ParentApplication.getContext().getString(R.string.net_error));
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
                                listener.onLikeSuccess(position);
                            }else {
                                listener.onLikeOrRemoveError(ParentApplication.getContext().getResources().getString(R.string.vote_error));
                            }
                        }else {
                            listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }


    /**
     * 取消点赞

     */
    @Override
    public void removeItemList(final int position, String sourceId, final onFinishLinstener listener) {
        String url=Constants.restSetCircleVoteCancel;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateSourceIdJson(sourceId))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onError(ParentApplication.getContext().getString(R.string.net_error));
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
                                listener.onRemoveListSuccess(position);
                            }else {
                                listener.onLikeOrRemoveError(ParentApplication.getContext().getResources().getString(R.string.vote_error));
                            }
                        }else {
                            listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    @Override
    public void upShareCount(String circleId, final onFinishLinstener listener) {
        String url=Constants.restCircleRecordShare;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateShareJson(circleId))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onError(ParentApplication.getContext().getString(R.string.net_error));
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

                            }else {
                                listener.onLikeOrRemoveError(ParentApplication.getContext().getResources().getString(R.string.vote_error));
                            }
                        }else {
                            listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    @Override
    public void postCircleComment(String circleId, String sourcePersonId, final String commentSourceName , final String remarks, final String commentType, final onFinishLinstener listener) {
        String url=Constants.restSetCircleComment;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateCommentJson(circleId,sourcePersonId,remarks,commentType))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onError(ParentApplication.getContext().getString(R.string.net_error));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        CircleCommetCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,CircleCommetCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                listener.onCommentSuccess(commentType,callBack.getCommentId(),commentSourceName,remarks);
                            }else {
                                listener.onLikeOrRemoveError(ParentApplication.getContext().getResources().getString(R.string.vote_error));
                            }
                        }else {
                            listener.onError(ParentApplication.getContext().getString(R.string.date_error));
                        }
                    }
                });
    }

    private String CreateCommentJson(String circleId, String sourcePersonId, String remarks, String commentType) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("sourceId", circleId);
            jsonObject.put("sourcePersonId", sourcePersonId);
            jsonObject.put("remarks", remarks);
            jsonObject.put("commentType", commentType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    private String CreateShareJson(String circleId) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("circleId",circleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
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

    private String CreateSourceIdJson(String sourceId) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("sourceId", sourceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
