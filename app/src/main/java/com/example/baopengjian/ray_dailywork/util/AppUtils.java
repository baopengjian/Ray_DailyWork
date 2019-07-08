package com.example.baopengjian.ray_dailywork.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Ray on 2019-7-8.
 */
public class AppUtils {

    public static String[] getDeniedPermissions(Context context, String[] permissions){
        if (Build.VERSION.SDK_INT >= 23) {
            ArrayList<String> deniedPermissionList = new ArrayList<>();
            for(String permission : permissions){
                if(ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
                    deniedPermissionList.add(permission);
                }
            }
            int size = deniedPermissionList.size();
            if(size > 0){
                return deniedPermissionList.toArray(new String[deniedPermissionList.size()]);
            }
        }
        return null;
    }


    public static void requestPermissions(Activity activity, String[] permissions, int requestCode){
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        }
    }
}
