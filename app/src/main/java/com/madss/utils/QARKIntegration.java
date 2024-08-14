package com.madss.utils;

import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class QARKIntegration {

    private static final String TAG = "QARKIntegration";

    public String runQarkAnalysis(String apkFilePath) {
        try {
            // Run QARK analysis on the APK file
            Process process = Runtime.getRuntime().exec(new String[]{
                "qark",
                "--apk", apkFilePath,
                "--report-type", "json"
            });

            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                output.append(line).append("\n");
            }
            in.close();
            process.waitFor();

            // Return the output of the QARK analysis
            return output.toString();

        } catch (Exception e) {
            Log.e(TAG, "Error running QARK analysis", e);
            return null;
        }
    }
}
