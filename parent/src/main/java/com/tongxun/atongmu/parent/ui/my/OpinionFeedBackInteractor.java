package com.tongxun.atongmu.parent.ui.my;

import android.util.Log;

import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.http.RetrofitHelper;
import com.tongxun.atongmu.parent.util.RxPartMapUtils;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Anro on 2017/7/21.
 */

public class OpinionFeedBackInteractor implements IOpinionFeedBackContract.Interactor {
    @Override
    public void sendToAtom(String commentText, String phoneModel, String phoneVersion, String appVersion, List<String> mlist, onFinishListener listener) {
        String url= Constants.restPutSuggestParent_v2;
        Map<String, RequestBody> map=new HashMap<>();
        map.put("tokenId", RxPartMapUtils.toRequestBodyOfDateText(SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,"")));
        map.put("commentText", RxPartMapUtils.toRequestBodyOfDateText(commentText));
        map.put("phoneVersion", RxPartMapUtils.toRequestBodyOfDateText(phoneVersion));
        map.put("appVersion", RxPartMapUtils.toRequestBodyOfDateText(appVersion));
        map.put("phoneModel", RxPartMapUtils.toRequestBodyOfDateText(phoneModel));
        for(int i=1;i<mlist.size();i++){
            map.put("sourceImage"+i, RxPartMapUtils.toRequestBodyOfDateImage(new File(mlist.get(i))));

        }


        RetrofitHelper.getInstance().getServer().postFeedBack(url,map).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TGA", "onResponse: ");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TGA", "onResponse: ");
            }
        });

    }
}
