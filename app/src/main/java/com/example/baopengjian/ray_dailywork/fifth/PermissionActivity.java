package com.example.baopengjian.ray_dailywork.fifth;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;


public class PermissionActivity extends FragmentActivity {
    public final static int CODE_PERMISSION_REQUEST = 99;
    public final static int CODE_PERMISSION_SETTING = 100;

    public final static String KEY_EXTRAS_PERMISSIONS = "key_extras_permissions";

    public final static int PERMISSION_DENIED = -1;
    public final static int PERMISSION_GRANTED = 0;
    public final static int PERMISSION_SHOW_REQUEST = 1;

    private String routerPath;
    private Bundle routerBundle;
    private String[] permissions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        View view = new View(this);
        setContentView(view);
        super.onCreate(savedInstanceState);

    }

  /*  private void initData(){
        routerPath = getIntent().getStringExtra(Router.KEY_EXTRA_URL);
        routerBundle = getIntent().getBundleExtra(Router.KEY_EXTRA_BUNDLE);
        if(routerBundle != null) {
            permissions = routerBundle.getStringArray(KEY_EXTRAS_PERMISSIONS);
        }else {
            permissions = new String[]{};
        }

        String[] perms = AppUtil.getDeniedPermissions(this, permissions);
        if(perms == null || perms.length == 0){
            Router.getInstance().build(routerPath).build(routerBundle).navigation(this);
            finish();
        }else {
            AppUtil.requestPermissions(this, permissions, CODE_PERMISSION_REQUEST);
        }
    }*/
    
 /*   @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CODE_PERMISSION_REQUEST:
                int permissionVal = PERMISSION_GRANTED;
                for(int i = 0; i < grantResults.length; i++){
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        permissionVal = PERMISSION_DENIED;
                        if(!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])){
                            permissionVal = PERMISSION_SHOW_REQUEST;
                            break;
                        }
                    }
                }
                if(permissionVal == PERMISSION_SHOW_REQUEST){
                    new DialogStyle.Builder(this)
                            .setTitle("提示")
                            .setMessage("为了确保功能服务的正常使用，我们需要您开启"+
                                    AppUtil.getPermissionDes(permissions) + "权限，请在设置后重试。", Gravity.LEFT)
                            .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                                    intent.setData(uri);
                                    startActivityForResult(intent, CODE_PERMISSION_SETTING);
                                    dialog.dismiss();
                                }
                            })
                            .setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    dialog.dismiss();
                                    finish();
                                }
                            }).create().show();
                }else if(permissionVal == PERMISSION_DENIED){
                    AppUtil.requestPermissions(this, permissions, CODE_PERMISSION_REQUEST);
                }else {
                    Router.getInstance().build(routerPath).build(routerBundle).navigation(this);
                    finish();
                }
                break;
            case RESULT_CANCELED:

                break;
            default:

                break;

        */}
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (resultCode){
//            case RESULT_CANCELED:
//                String[] perms = AppUtil.getDeniedPermissions(this, permissions);
//                if(perms == null || perms.length == 0){
//                    Router.getInstance().build(routerPath).build(routerBundle).navigation(this);
//                }
//                finish();
//                break;
//            default:
//                break;
//        }
//    }
