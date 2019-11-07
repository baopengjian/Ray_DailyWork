package com.example.baopengjian.ray_dailywork.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.CalendarContract;

/**
 * Created by Ray on 2019-7-23.
 */
public class DBHelper extends SQLiteOpenHelper {


    String sql4 = "CREATE TABLE IF NOT EXISTS " + TABLENAME5 + "(id integer primary key autoincrement, " +
            CalendarContract.Reminders.EVENT_ID + " varchar(60)," +
            CalendarContract.Events.TITLE + "  varchar(60)," +
            "updateTime timestamp)";//修改时间


    public static final String TABLENAME5 = "calendar";//理财日历

    private static DBHelper instance;

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    public DBHelper(Context context) {
        super(context, "daily.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
