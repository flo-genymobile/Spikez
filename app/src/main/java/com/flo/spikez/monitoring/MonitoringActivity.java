package com.flo.spikez.monitoring;

import android.Manifest;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.flo.spikez.R;

import java.util.ArrayList;
import java.util.List;

public class MonitoringActivity extends AppCompatActivity {

    private static final String TAG = MonitoringActivity.class.getCanonicalName();
    private static final int PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS = 0;
    private static final int PERMISSIONS_REQUEST_READ_SMS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager packageManager = getPackageManager();

        List<ApplicationInfo> packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        List<ApplicationInfo> systemApp = new ArrayList<>();
        List<ApplicationInfo> gameApp = new ArrayList<>();

        for (ApplicationInfo packageInfo : packages) {
            Log.d(TAG, "Installed package :" + packageInfo.packageName);
            Log.d(TAG, "Source dir : " + packageInfo.sourceDir);

            //check if game or not
            if ((packageInfo.flags & ApplicationInfo.FLAG_IS_GAME) != 0) {
                gameApp.add(packageInfo);
            }
            //check if app is system
            if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                systemApp.add(packageInfo);
            }
        }

        /*String PICTURE_DIR = "/storage/emulated/0/Pictures";
        String MUSIC_DIR = "/storage/emulated/0/Music";
        String DCIM_DIR = "/storage/emulated/0/DCIM";
        String DOWNLOAD_DIR = "/storage/emulated/0/Download";
        String VIDEO_DIR = "/storage/emulated/0/Movies";
        String APP_DIR = "/storage/emulated/0/Android";*/

        Log.d(TAG, "App installed: " + packages.size());
        Log.d(TAG, "System App installed: " + systemApp.size());
        Log.d(TAG, "User App installed: " + (packages.size() - systemApp.size()));
        Log.d(TAG, "Game App installed: " + gameApp.size());

        if (hasUsageStatPermission()) {
            startMonitoring();
        } else {
            askUserToGrantPermission();
        }

        if (hasReadPermission()) {
            startSmsMonitor();
        } else {
            aksUserToGrandReadPermission();
        }
    }

    private boolean hasUsageStatPermission() {
        AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), getPackageName());
        return mode == AppOpsManager.MODE_ALLOWED;
    }

    private void askUserToGrantPermission() {
        Log.d(TAG, "App doesnt have permission to look for usage stats, asking user to grant it");

        startActivityForResult(
                new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS),
                PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS:
                if (hasUsageStatPermission()){
                    startMonitoring();
                } else {
                    Toast.makeText(this, "Not granting the permission, app cant work", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void startMonitoring() {
        Log.d(TAG, "Start system monitoring");

        Intent startServiceIntent = new Intent(this, MonitoringService.class);
        startService(startServiceIntent);
    }

    private boolean hasReadPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    private void aksUserToGrandReadPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_SMS},
                PERMISSIONS_REQUEST_READ_SMS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "Not granting the permission, app cant work", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    private void startSmsMonitor() {
        SmsMonitor smsMonitor = new SmsMonitor(getApplicationContext());
        smsMonitor.getNumberOfReceivedSms();
    }
}
