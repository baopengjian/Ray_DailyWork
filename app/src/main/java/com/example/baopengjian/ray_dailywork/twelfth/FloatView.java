package com.example.baopengjian.ray_dailywork.twelfth;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.baopengjian.ray_dailywork.MyApplication;

public class FloatView extends View {
    private float mTouchStartX;
    private float mTouchStartY;
    private float x;
    private float y;

    private WindowManager wm=(WindowManager)getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
    private WindowManager.LayoutParams wmParams = ((MyApplication)getContext().getApplicationContext()).getMywmParams();



    public FloatView(Context context) {
        super(context);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //获取相对屏幕的坐标，即以屏幕左上角为原点
        x = event.getRawX();
        y = event.getRawY()-25;   //25是系统状态栏的高度
        Log.i("currP", "currX"+x+"====currY"+y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //获取相对View的坐标，即以此View左上角为原点
                mTouchStartX =  event.getX();
                mTouchStartY =  event.getY();

                Log.i("startP", "startX"+mTouchStartX+"====startY"+mTouchStartY);

                break;
            case MotionEvent.ACTION_MOVE:
                updateViewPosition();
                break;

            case MotionEvent.ACTION_UP:
                updateViewPosition();
                mTouchStartX=mTouchStartY=0;
                break;
        }
        return true;
    }

    private void updateViewPosition(){
        //更新浮动窗口位置参数
        wmParams.x=(int)( x-mTouchStartX);
        wmParams.y=(int) (y-mTouchStartY);
        wm.updateViewLayout(this, wmParams);
    }

}