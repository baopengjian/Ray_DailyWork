package com.example.baopengjian.ray_dailywork.sixth;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.provider.CalendarContract;
import android.text.TextUtils;
import android.util.Log;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Ray on 2019-7-8.
 *
 */
public class CalendarUtil {

    private static String CALENDER_URL = "content://com.android.calendar/calendars";
    private static String CALENDER_EVENT_URL = "content://com.android.calendar/events";
    private static String CALENDER_REMINDER_URL = "content://com.android.calendar/reminders";

    private static String CALENDARS_ACCOUNT_NAME = "600198";
    private static String CALENDARS_ACCOUNT_TYPE = "net.dxzq.jgzx";

    /**
     * 检查是否已经添加了日历账户，如果没有添加先添加一个日历账户再查询
     * 获取账户成功返回账户id，否则返回-1
     */
    private static int checkAndAddCalendarAccount(Context context) {
        int oldId = checkCalendarAccount(context);
        if (oldId >= 0) {
            return oldId;
        } else {
            long addId = addCalendarAccount(context);
            if (addId >= 0) {
                return checkCalendarAccount(context);
            } else {
                return -1;
            }
        }
    }

    /**
     * 检查是否存在现有账户，存在则返回账户id，否则返回-1
     */
    private static int checkCalendarAccount(Context context) {
        Cursor userCursor = context.getContentResolver().query(Uri.parse(CALENDER_URL), null, null, null, null);
        try {
            if (userCursor == null) { //查询返回空值
                return -1;
            }
            int count = userCursor.getCount();
            if (count > 0) { //存在现有账户，取第一个账户的id返回
                for (userCursor.moveToFirst(); !userCursor.isAfterLast(); userCursor.moveToNext()) {
              /*      String accountName = userCursor.getString(userCursor.getColumnIndex(CalendarContract.Calendars.ACCOUNT_NAME));
                    if (CALENDARS_ACCOUNT_NAME.equals(accountName)) {*/
                    return userCursor.getInt(userCursor.getColumnIndex(CalendarContract.Calendars._ID));
                    //    }
                }
                return -1;
            } else {
                return -1;
            }
        } finally {
            if (userCursor != null) {
                userCursor.close();
            }
        }
    }

    /**
     * 添加日历账户，账户创建成功则返回账户id，否则返回-1
     */

    private static long addCalendarAccount(Context context) {

        TimeZone timeZone = TimeZone.getDefault();
        ContentValues value = new ContentValues();
        value.put(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE);
        value.put(CalendarContract.Calendars.VISIBLE, 1);
        value.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.BLUE);
        value.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
        value.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
        value.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, timeZone.getID());
        value.put(CalendarContract.Calendars.OWNER_ACCOUNT, CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 0);

        Uri calendarUri = Uri.parse(CALENDER_URL);
        calendarUri = calendarUri.buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME)
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE)
                .build();

        Uri result = context.getContentResolver().insert(calendarUri, value);
        long id = result == null ? -1 : ContentUris.parseId(result);
        return id;
    }


    /**
     * 添加理财日历事件
     */
    public static long addCalendarEvent(Context context, CalendarItem item) {
        if (context == null || item == null) {
            return -1;
        }
        List<CalendarItem> list = queryCalendarEvents(context);
        for (CalendarItem item1 : list) {
            if ((item.getRq() + "").equals(item1.getRq()) && (item.getCpdm() + "").equals(item1.getCpdm()) && (item.getCpmc() + "").equals(item1.getCpmc())) {
                return Long.parseLong(item1.getEventId());
            }
        }
        int calId = checkAndAddCalendarAccount(context); //获取日历账户的id
        if (calId < 0) { //获取账户id失败直接返回，添加日历事件失败
            return -1;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
        //添加日历事件
        Calendar mCalendar = Calendar.getInstance();

        try {
            Date date = sdf.parse(item.getRq() + " " + item.getTxkssj());
            mCalendar.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long start = mCalendar.getTime().getTime();
        try {
            Date date = sdf.parse(item.getRq() + " " + item.getTxjssj());
            mCalendar.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置终止时间
        long end = mCalendar.getTime().getTime();
        ContentValues event = new ContentValues();
        event.put(CalendarContract.Events.TITLE, item.getCpmc() + item.getCpdm());
        event.put(CalendarContract.Events.DESCRIPTION, item.getCpsysm());
        event.put(CalendarContract.Events.CALENDAR_ID, calId); //插入账户的id
        event.put(CalendarContract.Events.DTSTART, start);
        event.put(CalendarContract.Events.DTEND, end);
        event.put(CalendarContract.Events.HAS_ALARM, 1);//设置有闹钟提醒
        event.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());//这个是时区，必须有
        Uri newEvent = context.getContentResolver().insert(Uri.parse(CALENDER_EVENT_URL), event); //添加事件
        if (newEvent == null) { //添加日历事件失败直接返回
            return -1;
        }

        //事件提醒的设定
        ContentValues values = new ContentValues();
        long _id = ContentUris.parseId(newEvent);
        Log.i("Calendar", "<<< addCalendarEvent _id =" + _id);
        values.put(CalendarContract.Reminders.EVENT_ID, _id);
        values.put(CalendarContract.Reminders.MINUTES, 0);// 提前previousDate天有提醒
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        Uri uri = context.getContentResolver().insert(Uri.parse(CALENDER_REMINDER_URL), values);
        //uri == null 添加事件提醒失败直接返回


        ContentValues local = new ContentValues();
        local.put(CalendarContract.Reminders.EVENT_ID, _id);
        local.put(CalendarItem.CPMC, item.getCpmc());
        local.put(CalendarItem.CPDM, item.getCpdm());
        local.put(CalendarItem.QGJE, item.getQgje());
        local.put(CalendarItem.CPLX, item.getCplx());
        local.put(CalendarItem.CPLXSM, item.getCplxsm());
        local.put(CalendarItem.ZDSY, item.getZdsy());
        local.put(CalendarItem.CPQX, item.getCpqx());
        local.put(CalendarItem.ZGSY, item.getZgsy());
        local.put(CalendarItem.FXJB, item.getFxjb());
        local.put(CalendarItem.CPSYSM, item.getCpsysm());
        local.put(CalendarItem.XSZT, item.getXszt());
        local.put(CalendarItem.WEEK, item.getWeek());
        local.put(CalendarItem.RQ, item.getRq());
        SQLiteDatabase database = DBHelper.getInstanc(context).getWritableDatabase();

        long insert = database.insert(DBHelper.TABLENAME5, null, local);
        Log.i("Calendar", "<<< insert " + insert + "  _id=" + _id);
        return uri == null ? -1 : _id;
    }

    /**
     * 根据日历的事件去筛选数据库中的事件，更新数据库，再将数据库事件返回
     * 耗时操作
     *
     * @param context
     * @return
     */
    public static List<CalendarItem> queryCalendarEvents(Context context) {
        List<CalendarItem> dbList = new ArrayList<>();
        if (context == null) {
            return dbList;
        }
        //本地数据库
        SQLiteDatabase database = DBHelper.getInstanc(context).getReadableDatabase();

        //没有日历账户
        int account = checkCalendarAccount(context);
        if (account < 0) {
            synCalendarDb(dbList, database);
            return dbList;
        }
        //没有日历事件
        Cursor calendarCursor = context.getContentResolver().query(Uri.parse(CALENDER_EVENT_URL), null, null, null, null);
        if (calendarCursor == null || calendarCursor.getCount() == 0) {
            synCalendarDb(dbList, database);
            return dbList;
        }

        Cursor dbCursor = database.query(DBHelper.TABLENAME5, new String[]{CalendarContract.Reminders.EVENT_ID, CalendarItem.CPMC, CalendarItem.CPDM, CalendarItem.QGJE
                        , CalendarItem.CPLX, CalendarItem.CPLXSM, CalendarItem.ZDSY, CalendarItem.CPQX, CalendarItem.ZGSY, CalendarItem.FXJB, CalendarItem.CPSYSM, CalendarItem.XSZT, CalendarItem.WEEK, CalendarItem.RQ},
                null, null, null, null, CalendarItem.RQ + " desc");
        //本地数据库为空，取到的事件获取不到详情内容，无用处
        if (dbCursor == null || dbCursor.getCount() == 0) {
            return dbList;
        }

        //从日历中找到事件id就添加返回结果list，找不到就删除(非本应用内删除)
        for (dbCursor.moveToFirst(); !dbCursor.isAfterLast(); dbCursor.moveToNext()) {
            String eventId = dbCursor.getString(dbCursor.getColumnIndex(CalendarContract.Reminders.EVENT_ID));
            boolean hasEvent = false;
            for (calendarCursor.moveToFirst(); !calendarCursor.isAfterLast(); calendarCursor.moveToNext()) {
                //取得id
                int id = calendarCursor.getInt(calendarCursor.getColumnIndex(CalendarContract.Calendars._ID));
                if (!TextUtils.isEmpty(eventId) && eventId.equals(String.valueOf(id))) {
                    CalendarItem item = new CalendarItem();
                    item.eventId = eventId;
                    item.setCpmc(dbCursor.getString(dbCursor.getColumnIndex(CalendarItem.CPMC)));
                    item.setCpdm(dbCursor.getString(dbCursor.getColumnIndex(CalendarItem.CPDM)));
                    item.setQgje(dbCursor.getString(dbCursor.getColumnIndex(CalendarItem.QGJE)));
                    item.setCplx(dbCursor.getString(dbCursor.getColumnIndex(CalendarItem.CPLX)));
                    item.setCplxsm(dbCursor.getString(dbCursor.getColumnIndex(CalendarItem.CPLXSM)));
                    item.setZdsy(dbCursor.getString(dbCursor.getColumnIndex(CalendarItem.ZDSY)));
                    item.setCpqx(dbCursor.getString(dbCursor.getColumnIndex(CalendarItem.CPQX)));
                    item.setZgsy(dbCursor.getString(dbCursor.getColumnIndex(CalendarItem.ZGSY)));
                    item.setFxjb(dbCursor.getString(dbCursor.getColumnIndex(CalendarItem.FXJB)));
                    item.setCpsysm(dbCursor.getString(dbCursor.getColumnIndex(CalendarItem.CPSYSM)));
                    item.setXszt(dbCursor.getString(dbCursor.getColumnIndex(CalendarItem.XSZT)));
                    item.setWeek(dbCursor.getString(dbCursor.getColumnIndex(CalendarItem.WEEK)));
                    String rq = dbCursor.getString(dbCursor.getColumnIndex(CalendarItem.RQ));
                    if (rq == null) {
                        rq = "";
                    }
                    item.setRq(rq);
                    item.isRemind = true;
                    dbList.add(item);
                    hasEvent = true;
                }
            }

            if (!hasEvent) {
                database.delete(DBHelper.TABLENAME5, CalendarContract.Reminders.EVENT_ID + " = ?", new String[]{eventId});
                Log.i("Calendar", "<<< query 同步 删除了事件 " + eventId);
            }
        }
        return dbList;
    }

    private static void synCalendarDb(List<CalendarItem> list, SQLiteDatabase database) {
        if (list == null) {
            return;
        }
        Cursor dbCursor = database.query(DBHelper.TABLENAME5, new String[]{CalendarContract.Reminders.EVENT_ID}, null, null, null, null, null);
        //同步本地数据库，将日历中没有事件从本地数据库删除：有删除没有不加，因为没有详情内容
        for (dbCursor.moveToFirst(); !dbCursor.isAfterLast(); dbCursor.moveToNext()) {
            String eventId = dbCursor.getString(dbCursor.getColumnIndex(CalendarContract.Reminders.EVENT_ID));
            if (list.isEmpty()) {
                database.delete(DBHelper.TABLENAME5, CalendarContract.Reminders.EVENT_ID + " = ?", new String[]{eventId});
                continue;
            }

            boolean saved = false;
            for (CalendarItem item : list) {
                if (!TextUtils.isEmpty(item.eventId) && item.eventId.equals(String.valueOf(eventId))) {
                    saved = true;
                }
            }
            if (!saved) {
                database.delete(DBHelper.TABLENAME5, CalendarContract.Reminders.EVENT_ID + " = ?", new String[]{eventId});
            }
        }
    }

    /**
     * 防止手机端删除
     *
     * @param context
     */
   /* public static void syncCalendar(Context context) {
        if (context == null) {
            return;
        }
        List<CalendarItem> dbList = query(context);
        if (dbList == null || dbList.isEmpty()) {
            return;
        }
        int account = checkCalendarAccount(context);
        if (account < 0) {
            return;
        }

        Cursor calendarCursor = context.getContentResolver().query(Uri.parse(CALENDER_EVENT_URL), null, null, null, null);
        if (calendarCursor == null || calendarCursor.getCount() == 0) {
            dbList.clear();
            return;
        }
        for (calendarCursor.moveToFirst(); !calendarCursor.isAfterLast(); calendarCursor.moveToNext()) {
            int id = calendarCursor.getInt(calendarCursor.getColumnIndex(CalendarContract.Calendars._ID));//取得id
            for (CalendarItem item : dbList) {
                item.isRemind = (id + "").equals(item.eventId);
            }
        }
        SQLiteDatabase database = DBHelper.getInstanc(context).getReadableDatabase();
        for (CalendarItem item : dbList) {
            if (!item.isRemind) {
                database.delete(DBHelper.TABLENAME5, CalendarContract.Reminders.EVENT_ID + "= ？", new String[]{item.eventId});
            }
        }

    }*/
       /* if (calendarCursor.getCount() > 0) {
            //遍历所有事件，找到title跟需要查询的title一样的项
                    int id = eventCursor.getInt(eventCursor.getColumnIndex(CalendarContract.Calendars._ID));//取得id


        }*/

    /**
     * 删除日历事件
     * _id 在Calendar中是_id 在本地数据库是
     */
    public static int deleteCalendarEvent(Context context, String _id) {
        if (context == null) {
            return -1;
        }
        Cursor eventCursor = context.getContentResolver().query(Uri.parse(CALENDER_EVENT_URL), null, null, null, null);
        SQLiteDatabase db = DBHelper.getInstanc(context).getWritableDatabase();
        int rows = -1;
        try {
            if (eventCursor == null) { //查询返回空值
                return -1;
            }

            if (eventCursor.getCount() > 0) {
                //遍历所有事件，找到title跟需要查询的title一样的项
                for (eventCursor.moveToFirst(); !eventCursor.isAfterLast(); eventCursor.moveToNext()) {
                    int id = eventCursor.getInt(eventCursor.getColumnIndex(CalendarContract.Calendars._ID));//取得id
                    Log.i("Calendar", "<<< query deleteCalendarEvent  id=" + id);
                    if (_id.equals(id + "")) {
                        Uri deleteUri = ContentUris.withAppendedId(Uri.parse(CALENDER_EVENT_URL), id);
                        rows = context.getContentResolver().delete(deleteUri, null, null);
                        Log.i("Calendar", "<<< query deleteCalendarEvent  删除结果" + rows);
                        if (rows > 0) {
                            int rows2 = db.delete(DBHelper.TABLENAME5, CalendarContract.Reminders.EVENT_ID + " = ?", new String[]{_id});
                            Log.i("Calendar", "<<< query deleteCalendarEvent  删除结果rows2" + rows2);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (eventCursor != null) {
                eventCursor.close();
            }
        }

        //-1 事件删除失败
        return rows;
    }
}
