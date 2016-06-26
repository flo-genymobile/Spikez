package com.flo.spikez.monitoring;

import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

public class MonitoringService extends Service implements DeviceMonitoringTools {

    private static final String TAG = MonitoringService.class.getSimpleName();
    private MonitoringServiceHandler serviceHandler;
    private volatile HandlerThread handlerThread;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: MonitoringService created");

        handlerThread = new HandlerThread("MonitoringService.HandlerThread", Process.THREAD_PRIORITY_BACKGROUND);
        handlerThread.start();

        serviceHandler = new MonitoringServiceHandler(handlerThread.getLooper(), this);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: MonitoringService destroyed");
        handlerThread.quit();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        serviceHandler.startMonitoringTimer();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void monitorForegroundApplication() {
        /*ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = activityManager.getRunningAppProcesses();
        Field field = null;
        try {
            field = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState");
        } catch (Exception ignored) {
        }

        for (ActivityManager.RunningAppProcessInfo appInfo: runningApps) {
            if (appInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                    && appInfo.importanceReasonCode == ActivityManager.RunningAppProcessInfo.REASON_UNKNOWN) {
                Integer state = null;
                try {
                    state = field.getInt(appInfo);
                } catch (Exception e) {
                }

                if (state != null && state == 2) {
                    Log.d(TAG, "monitorForegroundApplication: current running app is: " + appInfo.processName);
                    break;
                }
            }
        }*/

        /*List<AndroidAppProcess> processes = ProcessManager.getRunningAppProcesses();
        for (AndroidAppProcess app:processes) {
            if (app.foreground) {
                Log.d(TAG, "monitorForegroundApplication: current running app is: " + app.name);
            }
        }*/
    }
}
