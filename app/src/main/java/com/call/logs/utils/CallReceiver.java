package com.call.logs.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.call.logs.app.AppController;
import com.call.logs.database.CallLogsTable;
import com.call.logs.models.CallLogs;

import java.util.Date;

public class CallReceiver extends PhoneCallReceiver {
    @Override
    protected void onIncomingCallStarted(Context ctx, String number, Date start) {
        Toast.makeText(ctx, "IncomingCallStarted :" + number, Toast.LENGTH_SHORT).show();
        addToCallLogsTable(number);
    }

    @Override
    protected void onIncomingCallAttended(Context ctx, String number, Date start) {
        Toast.makeText(ctx, "IncomingCallAttended :" + number, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
        Toast.makeText(ctx, "OutgoingCallStarted :" + number, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
        Toast.makeText(ctx, "IncomingCallEnded :" + number, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
        Toast.makeText(ctx, "OutgoingCallEnded :" + number, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {
        Toast.makeText(ctx, "MissedCall :" + number, Toast.LENGTH_SHORT).show();
    }

    private void openApp(final Context context) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //String packageName = AppController.getAppPackageName();
                String packageName = "com.who_cookin";
                if (context != null) {
                    context.startActivity(context.getPackageManager().getLaunchIntentForPackage(packageName));
                }
            }
        }, 2000);
    }

    private void addToCallLogsTable(String number) {
        CallLogsTable callLogsTable = new CallLogsTable(AppController.getInstance());
        callLogsTable.insertCallLog(new CallLogs(number, DateUtils.getIncomingCallTime()));
        if (!AppController.isAppForeGrounded()) {
            openApp(AppController.getInstance());
        }
    }

}
