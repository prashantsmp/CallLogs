package com.call.logs.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.call.logs.R;
import com.call.logs.app.AppController;
import com.call.logs.models.CallLogs;
import com.call.logs.database.CallLogsTable;
import com.master.permissionhelper.PermissionHelper;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private PermissionHelper permissionHelper;
    private String TAG = MainActivity.this.getClass().getName();
    private TextView callLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callLogs = findViewById(R.id.call_logs);
        checkAppPermission();
    }

    private void checkAppPermission() {
        permissionHelper = new PermissionHelper(this, new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.PROCESS_OUTGOING_CALLS}, 100);
        permissionHelper.request(new PermissionHelper.PermissionCallback() {
            @Override
            public void onPermissionGranted() {
                Timber.tag(TAG).d("onPermissionGranted() called");
                getCallLogs();
            }

            @Override
            public void onIndividualPermissionGranted(@NotNull String[] grantedPermission) {
                Timber.tag(TAG).d("onIndividualPermissionGranted() called with: grantedPermission = [" + TextUtils.join(",", grantedPermission) + "]");
            }

            @Override
            public void onPermissionDenied() {
                Timber.tag(TAG).d("onPermissionDenied() called");
            }

            @Override
            public void onPermissionDeniedBySystem() {
                Timber.tag(TAG).d("onPermissionDeniedBySystem() called");
            }
        });
    }

    private void getCallLogs() {
        CallLogsTable callLogsTable = new CallLogsTable(AppController.getInstance());
        List<CallLogs> callLogsList = callLogsTable.getCallLogs();
        if (!callLogsList.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            Collections.reverse(callLogsList);
            for (CallLogs callLogs : callLogsList) {
                builder.append(callLogs.getMobileNumber()).append("\t").append(callLogs.getDateTime()).append("\n");
            }
            if (callLogs != null)
                callLogs.setText(builder.toString());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionHelper != null) {
            permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
