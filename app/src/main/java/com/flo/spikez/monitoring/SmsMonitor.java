package com.flo.spikez.monitoring;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.util.Log;

public class SmsMonitor {
    private static final String TAG = SmsMonitor.class.getSimpleName();
    private Context context;

    public SmsMonitor(Context context) {
        this.context = context;
    }

    public void getNumberOfReceivedSms() {
        Uri contentUri = Telephony.Sms.Inbox.CONTENT_URI;

        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(contentUri, null, null, null, null);

        int numberOfMessages = cursor.getCount();
        Log.d(TAG, "getNumberOfReceivedSms: " + numberOfMessages);
    }
}
