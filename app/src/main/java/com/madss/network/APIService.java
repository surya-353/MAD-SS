package com.madss.network;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class APIService {

    private static final String BASE_URL = "http://your-backend-url.com/";

    public static void uploadAPK(File apkFile, Callback callback) {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", apkFile.getName(), RequestBody.create(apkFile, MediaType.parse("application/octet-stream")))
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "upload")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(callback);
    }
}
