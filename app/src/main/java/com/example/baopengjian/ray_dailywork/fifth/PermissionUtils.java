package com.example.baopengjian.ray_dailywork.fifth;

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
 * Created by Ray on 2019/1/17.
 */
public class PermissionUtils {

    public final static int CODE_PERMISSION_REQUEST = 99;
    public final static int CODE_PERMISSION_SETTING = 100;

    public final static int PERMISSION_DENIED = -1;
    public final static int PERMISSION_GRANTED = 0;
    public final static int PERMISSION_SHOW_REQUEST = 1;
    /**
     * 弹出对话框请求权限
     */
    public static void requestPermissions(Activity activity, String[] permissions, int requestCode){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        }
    }

    /**
     * 返回缺失的权限
     * @return 返回缺少的权限，null 意味着没有缺少权限
     */
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



    public static String getPermissionDes(String[] permissions){
        Set<String> set = new LinkedHashSet<>();
        for(int i = 0; i < permissions.length; i++){
            if(!TextUtils.isEmpty(permissions[i])){
                if(permissions[i].equals(Manifest.permission.READ_PHONE_STATE)){
                    set.add("读取手机状态");
                }if(permissions[i].equals(Manifest.permission.SEND_SMS)){
                    set.add("发送短信");
                }else if(permissions[i].equals(Manifest.permission.RECORD_AUDIO)) {
                    set.add("录音");
                }else if(permissions[i].equals(Manifest.permission.MODIFY_AUDIO_SETTINGS)) {
                    set.add("录音");
                } else if(permissions[i].equals(Manifest.permission.CAMERA)) {
                    set.add("拍照");
                }else if(permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    set.add("访问照片");
                }else if(permissions[i].equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    set.add("访问照片");
                }else if(permissions[i].equals(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    set.add("读取位置信息");
                }else if(permissions[i].equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    set.add("读取位置信息");
                }
            }
        }
        if(set != null && !set.isEmpty()){
            StringBuilder res = new StringBuilder();
            Iterator<String> it = set.iterator();
            while(it.hasNext())
                res.append(it.next()).append("、");

            return res.substring(0, res.length() - 1).toString();
        }
        return "";
    }
}
