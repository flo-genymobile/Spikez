package com.flo.spikez.monitoring;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MonitoringServiceHandler extends Handler {
    private static final String TAG = MonitoringServiceHandler.class.getSimpleName();

    private DeviceMonitoringTools deviceMonitoringTools;

    public MonitoringServiceHandler(Looper looper, DeviceMonitoringTools deviceMonitoringTools) {
        super(looper);
        this.deviceMonitoringTools = deviceMonitoringTools;
    }

    @Override
    public void handleMessage(Message message) {
        Log.d(TAG, "check system status....");

        deviceMonitoringTools.monitorForegroundApplication();
        startMonitoringTimer();
    }

    public void startMonitoringTimer() {
        sendEmptyMessageDelayed(0, 5000);
    }
}
