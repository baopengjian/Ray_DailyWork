package com.example.baopengjian.ray_dailywork.fifth;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

import com.example.baopengjian.ray_dailywork.R;
import com.example.baopengjian.ray_dailywork.dialog.MyDialog;


/**
 * Created by Ray on 2019/1/17.
 * 动态权限申请
 */
public class DynamicPermissionRequestActivity extends AppCompatActivity {

    private String[] permissions = new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.SEND_SMS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_permission);

        if(checkPermission()){
            init();
        }
    }

    private void init() {
        //请求权限成功后进行应用的初始化操作
    }


    private boolean checkPermission(){
        String[] perms = PermissionUtils.getDeniedPermissions(this, permissions);
        if(perms == null || perms.length == 0){
            return true;
        }else {
            PermissionUtils.requestPermissions(this, permissions, PermissionUtils.CODE_PERMISSION_REQUEST);
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionUtils.CODE_PERMISSION_REQUEST:
                int permissionVal = PermissionUtils.PERMISSION_GRANTED;
                for(int i = 0; i < grantResults.length; i++){
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        permissionVal = PermissionUtils.PERMISSION_DENIED;
                        if(!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])){
                            permissionVal = PermissionUtils.PERMISSION_SHOW_REQUEST;
                            break;
                        }
                    }
                }
                if(permissionVal == PermissionUtils.PERMISSION_SHOW_REQUEST){
                   new MyDialog.Builder(this)
                            .setTitle("提示")
                            .setMessage("为了确保功能服务的正常使用，我们需要您开启"+
                                    PermissionUtils.getPermissionDes(permissions) + "权限，请在设置后重试。", Gravity.LEFT)
                            .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                                    intent.setData(uri);
                                    startActivityForResult(intent, PermissionUtils.CODE_PERMISSION_SETTING);
                                    dialog.dismiss();
                                }
                            })
                            .setCancelable(false)
                            .setCanceledOnTouchOutside(false)
                            .create().show();
                }else if(permissionVal == PermissionUtils.PERMISSION_DENIED){
                    PermissionUtils.requestPermissions(this, permissions, PermissionUtils.CODE_PERMISSION_REQUEST);
                }else {
                    init();
                }
                break;
            case RESULT_CANCELED:
                break;
            default:
                break;

        }
    }




}
