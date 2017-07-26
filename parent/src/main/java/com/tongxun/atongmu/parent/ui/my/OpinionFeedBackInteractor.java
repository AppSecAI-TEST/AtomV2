package com.tongxun.atongmu.parent.ui.my;

import android.util.Log;

import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.http.RetrofitHelper;
import com.tongxun.atongmu.parent.model.BaseCallBack;
import com.tongxun.atongmu.parent.util.RxPartMapUtils;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
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

        MultipartBody.Builder builder=new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        builder.addFormDataPart("tokenId",SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
        builder.addFormDataPart("commentText",commentText);
        builder.addFormDataPart("phoneVersion",phoneVersion);
        builder.addFormDataPart("appVersion",appVersion);
        builder.addFormDataPart("phoneModel",phoneModel);
        builder.addFormDataPart("ishaveImg","true");
        for(int i=1;i<mlist.size();i++){
            File file=new File(mlist.get(i));
            builder.addFormDataPart(("sourceImage"+i), file.getName(), RxPartMapUtils.toRequestBodyOfDateImage(file));
        }

        List<MultipartBody.Part> parts = builder.build().parts();



        RetrofitHelper.getInstance().getServer().postFeedBack(url,parts).enqueue(new Callback<BaseCallBack>() {
            @Override
            public void onResponse(Call<BaseCallBack> call, Response<BaseCallBack> response) {
                Log.d("TGA", "onResponse: ");
            }

            @Override
            public void onFailure(Call<BaseCallBack> call, Throwable t) {

            }
        });

    }
}
