package com.madss.utils;

import android.util.Log;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FridaIntegration {

    private static final String TAG = "FridaIntegration";
    private String scriptPath;

    public FridaIntegration(String scriptPath) {
        this.scriptPath = scriptPath;
    }

    public String executeFrida(String targetAppPackageName) {
        try {
            // Build the command to run Frida
            String[] cmd = {
                "frida",
                "-U",
                "-f", targetAppPackageName,
                "-l", scriptPath,
                "--no-pause"
            };

            // Execute the Frida command
            Process process = Runtime.getRuntime().exec(cmd);

            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                output.append(line).append("\n");
            }
            in.close();
            process.waitFor();

            // Return the output from Frida execution
            return output.toString();

        } catch (Exception e) {
            Log.e(TAG, "Error running Frida script", e);
            return null;
        }
    }

    public JSONObject parseResults(String fridaOutput) {
        try {
            // Assume Frida script returns JSON formatted string, parse it
            return new JSONObject(fridaOutput);
        } catch (Exception e) {
            Log.e(TAG, "Error parsing Frida output", e);
            return null;
        }
    }
}
