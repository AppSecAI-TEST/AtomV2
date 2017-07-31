package com.tongxun.atongmu.parent.util;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCustomSignerCredentialProvider;
import com.tongxun.atongmu.parent.application.ParentApplication;

/**
 * Created by Anro on 2017/7/31.
 */

public class OssUtil {
    private OSS oss;

    private static OssUtil instance=null;


    String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    // 在移动端建议使用STS的方式初始化OSSClient，更多信息参考：[访问控制]
    OSSCredentialProvider credentialProvider = new OSSCustomSignerCredentialProvider() {
        @Override
        public String signContent(String s) {
            //return "OSS "+"LTAIdaD36exSRPDh" +":"+base64(hmac-sha1("C5HNzJkTE7BbRS8Cxb3olALPfADMjV",""));
            return "";
        }
    };


    private OssUtil() {
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        oss = new OSSClient(ParentApplication.getContext(), endpoint, credentialProvider, conf);
    }

    private OSS getOss(){
        return oss;
    }

    public static OSS getInstance(){
        if(instance==null){
            instance=new OssUtil();
        }
        return instance.getOss();
    }


}
