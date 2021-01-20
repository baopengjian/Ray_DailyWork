package com.example.baopengjian.ray_dailywork.twelfth;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.baopengjian.ray_dailywork.MyApplication;
import com.example.baopengjian.ray_dailywork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 悬浮窗口
 */
public class FloatingWindowActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floating_window_activity);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(REQUEST_CODE == requestCode){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
                } else{
                    showView();
                }
            }
        }
    }

    public void start(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), REQUEST_CODE);
            }else{
                showView();
            }
        }else{
            showView();
        }
    }

    private void addView(){
        // 获取WindowManager服务
        final WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // 新建悬浮窗

        // 控件
        final Button button = new Button(getApplicationContext());
        button.setText("Floating Window");
        button.setBackgroundColor(Color.BLUE);

        // 设置LayoutParam
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.width = 500;
        layoutParams.height = 100;
        layoutParams.x = 300;
        layoutParams.y = 300;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                windowManager.removeView(button);
                //   startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
        // 将悬浮窗控件添加到WindowManager
        windowManager.addView(button, layoutParams);
    }

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams param;
    private List<FloatView> list = new ArrayList<>();

    private void showView(){
        FloatView mLayout=new FloatView(getApplicationContext());
        mLayout.setBackgroundColor(Color.RED);
        //获取WindowManager
        mWindowManager=(WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        //设置LayoutParams(全局变量）相关参数
        param = ((MyApplication)getApplication()).getMywmParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            param.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            param.type = WindowManager.LayoutParams.TYPE_PHONE;
        }

        param.format=1;
        param.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE; // 不能抢占聚焦点
        param.flags = param.flags | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        param.flags = param.flags | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS; // 排版不受限制

        param.alpha = 1.0f;

        param.gravity= Gravity.LEFT|Gravity.TOP;   //调整悬浮窗口至左上角
        //以屏幕左上角为原点，设置x、y初始值
        param.x=0;
        param.y=0;

        //设置悬浮窗口长宽数据
        param.width=140;
        param.height=140;

        //显示myFloatView图像
        mWindowManager.addView(mLayout, param);
        list.add(mLayout);
    }

    @Override
    protected void onDestroy() {
        for(View v:list){
            mWindowManager.removeView(v);
        }
        super.onDestroy();
    }
}
