package com.call.logs.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.call.logs.models.CallLogs;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;


public class CallLogsTable extends SQLDatabase {

    private SQLiteDatabase sqLiteDatabase;

    public CallLogsTable(Context context) {
        super(context);

    }

    public void insertCallLog(CallLogs callLog) {
        try {
            sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("mobileNumber", callLog.getMobileNumber());
            contentValues.put("dateTime", callLog.getDateTime());
            sqLiteDatabase.insert("tbl_CallLogs", null, contentValues);
        } catch (Exception ex) {
            Timber.tag("Insert CallLogs").e(ex.toString());
        } finally {
            closeDB(sqLiteDatabase);
        }
    }

    public List<CallLogs> getCallLogs() {
        List<CallLogs> callLogsList = new ArrayList<>();
        Cursor cursor = null;
        try {
            sqLiteDatabase = this.getReadableDatabase();
            cursor = sqLiteDatabase.query("tbl_CallLogs", null, null, null, null, null, null);
            if (cursor.getCount() > 0) {
                for (int i = 0; i < cursor.getCount(); i++) {
                    CallLogs callLogs;
                    cursor.moveToNext();
                    callLogs = new CallLogs();
                    callLogs.setMobileNumber(cursor.getString(0));
                    callLogs.setDateTime(cursor.getString(1));
                    callLogsList.add(callLogs);
                }
            }
        } catch (Exception ex) {
            Timber.tag("Get CallLogs").e(ex.toString());
        } finally {
            closeCursor(cursor);
            closeDB(sqLiteDatabase);
        }
        return callLogsList;
    }

}