package com.call.logs.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.call.logs.app.AppController;

public class SQLDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "logs.db";
    private static SQLDatabase sqlDatabase;

    public SQLDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static SQLDatabase getInstance(Context context) {
        if (sqlDatabase == null)
            return getSQLDatabaseInstance(context);
        return sqlDatabase;
    }

    private static SQLDatabase getSQLDatabaseInstance(Context context) {
        return sqlDatabase = context != null ? new SQLDatabase(context) : new SQLDatabase(AppController.getInstance());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //tbl_call_logs
        db.execSQL("CREATE TABLE if not exists tbl_CallLogs(mobileNumber, dateTime)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_CallLogs");
        onCreate(db);
    }

    void closeDB(SQLiteDatabase sqLiteDatabase) {
        if (sqLiteDatabase != null)
            sqLiteDatabase.close();
    }

    void closeCursor(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
}
