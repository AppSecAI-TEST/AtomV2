package com.tongxun.atongmu.parent.util;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Anro on 2017/7/25.
 */

public class RxPartMapUtils {
    public static RequestBody toRequestBodyOfText (String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body ;
    }

    public static RequestBody toRequestBodyOfImage(File pFile){

        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), pFile);
        return fileBody;
    }

    public static RequestBody toRequestBodyOfDateImage(File pFile){

        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), pFile);
        return fileBody;
    }
    public static RequestBody toRequestBodyOfDateText (String value) {
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), value);
        return body ;
    }

}
