package com.madss.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.madss.utils.MobSFIntegration;
import com.madss.utils.FridaIntegration;
import com.madss.utils.QARKIntegration;

public class BackgroundScanService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(() -> {
            MobSFIntegration.runAnalysis();
            FridaIntegration.runAnalysis();
            QARKIntegration.runAnalysis();
            stopSelf();
        }).start();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
