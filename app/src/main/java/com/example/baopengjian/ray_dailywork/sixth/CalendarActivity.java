package com.example.baopengjian.ray_dailywork.sixth;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.baopengjian.ray_dailywork.R;
import com.example.baopengjian.ray_dailywork.fifth.PermissionActivity;
import com.example.baopengjian.ray_dailywork.util.AppUtils;
import com.example.baopengjian.ray_dailywork.util.DBHelper;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * <uses-permission android:name="android.permission.READ_CALENDAR" />
 * <uses-permission android:name="android.permission.WRITE_CALENDAR" />
 * Created by Ray on 2019-7-8.
 */
public class CalendarActivity extends FragmentActivity {

    private ListView lv;
    private String[] permissions = new String[]{Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public int x = 0, y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);
        checkPermission();

        SharedPreferences sharedPreferences =  getSharedPreferences("aa", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("aaasefa","111" ).commit();
      String path =  getFilesDir().getAbsolutePath();
        Log.i("Cal","path =="+path );
    }

    private boolean checkPermission() {
        String[] perms = AppUtils.getDeniedPermissions(this, permissions);
        if (perms == null || perms.length == 0) {
            return true;
        } else {
            AppUtils.requestPermissions(this, permissions, PermissionActivity.CODE_PERMISSION_REQUEST);
            return false;
        }
    }

    int i = 0;

    public void add(View v) {
        SQLiteDatabase database = DBHelper.getInstance(getApplicationContext()).getWritableDatabase();
        ContentValues local = new ContentValues();
        local.put(CalendarContract.Reminders.EVENT_ID, ++i);
        local.put(CalendarContract.Reminders.TITLE, "标题" + i);


        long insert = database.insert(DBHelper.TABLENAME5, null, local);
        Calendar calendar = Calendar.getInstance();
        CalendarReminderUtils.addCalendarEvent(this, "标题" + x, "到期提醒" + x, calendar.getTimeInMillis(), 0);

        x++;
    }

    public void query(View v) {
        x = 0;
        y = 0;
        lv = (ListView) findViewById(R.id.lv);
        List<Map<String, String>> list = CalendarReminderUtils.query(this);
        lv.setAdapter(new ArrayAdapter<Map<String, String>>(this, android.R.layout.simple_dropdown_item_1line, list));
    }

    public void delete(View v) {
        CalendarReminderUtils.deleteCalendarEvent(this, "标题" + y);
        y++;
    }
}
