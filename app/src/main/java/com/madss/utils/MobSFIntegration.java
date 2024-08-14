package com.madss.utils;

import android.util.Log;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class MobSFIntegration {

    private static final String TAG = "MobSFIntegration";
    private static final String API_KEY = "YOUR_API_KEY_HERE"; // Replace with your MobSF API key
    private static final String MOBSF_URL = "http://your-mobsf-url/api/v1/";

    private OkHttpClient client;

    public MobSFIntegration() {
        this.client = new OkHttpClient();
    }

    public String uploadApk(String apkFilePath) {
        try {
            // Prepare the file and request
            File apkFile = new File(apkFilePath);
            RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", apkFile.getName(),
                    RequestBody.create(MediaType.parse("application/vnd.android.package-archive"), apkFile))
                .build();

            Request request = new Request.Builder()
                .url(MOBSF_URL + "upload")
                .addHeader("Authorization", API_KEY)
                .post(requestBody)
                .build();

            // Execute the request
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                Log.e(TAG, "MobSF upload failed: " + response.message());
                return null;
            }
        } catch (IOException e) {
            Log.e(TAG, "MobSF upload error", e);
            return null;
        }
    }

    public JSONObject getScanResults(String scanId) {
        try {
            Request request = new Request.Builder()
                .url(MOBSF_URL + "scan/" + scanId + "/")
                .addHeader("Authorization", API_KEY)
                .get()
                .build();

            // Execute the request
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return new JSONObject(response.body().string());
            } else {
                Log.e(TAG, "MobSF scan results failed: " + response.message());
                return null;
            }
        } catch (IOException e) {
            Log.e(TAG, "MobSF scan results error", e);
            return null;
        }
    }
}
