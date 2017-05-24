package com.example.samsung.p1291_mediarecorderaudio;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by samsung on 24.05.2017.
 */

public class Messager {

    private static final String LOG_TAG = "myLogs";

    public static final void sendToAllRecipients(final Context ctx, final String msg) {
        Log.d(LOG_TAG, msg);
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    public static final void sendToOnlyLog(final String msg) {
        Log.d(LOG_TAG, msg);
    }
}
