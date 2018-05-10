package com.example.baopengjian.ray_dailywork.may;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.ListView;

/**
 * Created by PF0ZYBAJ on 2018/5/10.
 */

public class HSListView extends ListView {
    public HSListView(Context context) {
        super(context);
    }

    public HSListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HSListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private HScrollListViewListener mListener;
    /**
     * 紀錄手指按下時的x座標
     */
    private float xDown;
    /**
     * 紀錄手指按下時的y座標
     */
    private float yDown;
    /**
     * 紀錄手指移動時的x坐標
     */
    private float xMove;
    /**
     * 紀錄手指移動時的y座標
     */
    private float yMove;
    /**
     * 紀錄手指放開時的x座標
     */
    private float xUp;
    /**
     * 紀錄手指放開時的y座標
     */
    private float yUp;
    /**
     * 計算手指滑動的速度
     */
    private VelocityTracker mVelocityTracker;
    /**
     * X軸滑動到這個距離觸發水平滑動，暫停垂直滑動
     * <li>預設30
     */
    private int touchXSlop = 30;
    /**
     * Y軸滑動到這個距離觸發垂直滑動，暫停水平滑動
     * <li>預設30
     */
    private int touchYSlop = 30;
    private int REBOUND_DISTANCE_X = 30; //scroller X軸反彈效果的距離
    private int distanceY;

    private boolean isSliding, isPulling;

    public void setOnListener(HScrollListViewListener listener) {
        mListener = listener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        createVelocityTracker(ev);
        getParent().requestDisallowInterceptTouchEvent(true);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = ev.getRawX();
                yDown = ev.getRawY();
                xMove = xDown;
                yMove = yDown;
                isSliding = false;
                isPulling = false;
                if (mListener != null) {
                    mListener.onTouchDown(ev.getX(), ev.getY());
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //先判斷是否上下滑動，若上下滑動就鎖死左右滑動
                if (!isSliding && Math.abs(yDown - ev.getRawY()) >= touchYSlop) {
                    isSliding = false;
                    isPulling = true;
                }
                //若不是上下滑動，就判斷是否左右滑動，是的話就鎖死上下滑動
                if (!isPulling && Math.abs(xDown - ev.getRawX()) >= touchXSlop) {
                    isSliding = true;
                    isPulling = false;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//		if(isTouchHeaderOrFooter(ev)){
//			getParent().requestDisallowInterceptTouchEvent(false);
//			return super.onInterceptTouchEvent(ev);
//		}
        getParent().requestDisallowInterceptTouchEvent(true);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return super.onInterceptTouchEvent(ev);
            case MotionEvent.ACTION_MOVE:
                if (isSliding && !isPulling) {
                    return true;
                } else {
                    return super.onInterceptTouchEvent(ev);
                }
            case MotionEvent.ACTION_UP:
                if (isSliding && !isPulling) {
                    return true;
                } else {
                    return super.onInterceptTouchEvent(ev);
                }
            default:
                super.onInterceptTouchEvent(ev);
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//		if(isTouchHeaderOrFooter(event)){
//			getParent().requestDisallowInterceptTouchEvent(false);
//			return super.onTouchEvent(event);
//		}
        getParent().requestDisallowInterceptTouchEvent(true);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return super.onTouchEvent(event);
            case MotionEvent.ACTION_MOVE:
                int moveDistanceX = (int) (xMove - event.getRawX());
                distanceY = (int) (yMove - event.getRawY());
                xMove = event.getRawX();
                yMove = event.getRawY();
                if (mListener != null && isSliding && !isPulling && xDown > 0 ) {
                    mListener.onSliding(moveDistanceX);
                    return true;
                } else {
                    return super.onTouchEvent(event);
                }
            case MotionEvent.ACTION_UP:
                xUp = event.getX();
                yUp = event.getY();
                if (mListener != null) {
                    mListener.onTouchUp(xUp, yUp);
                }
              /*  if (xDown < 0 ) {
                    if (isSliding && xUp - xDown > 0) { //在名稱範圍內左向右滑
                        if (mListener != null) {
                            mListener.onOpenLeftMenu();
                        }
                    }
                } else {
                    if (xUp - xDown < 0 && mListener != null  && isSliding) {
                        mListener.onCloseLeftMenu();
                    }
                }*/
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                if (mListener != null && isSliding && xDown > 0 ) {
                    mListener.onFling(0, 0, -getScrollVelocity() * 2 / 3, 0, 0, 0, 0, 0, REBOUND_DISTANCE_X, 0);
                    isSliding = false;
                    isPulling = false;
                    recycleVelocityTracker();
                    return true;
                } else {
                    return super.onTouchEvent(event);
                }
            default:
                super.onTouchEvent(event);
                break;
        }
        return true;
    }


    /**
     * 獲取手指在右邊layout的監聽View上的滑動速度
     * @return 滑動速度，以每秒鐘移動了多少像素為單位
     */
    private int getScrollVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);
        return (int) mVelocityTracker.getXVelocity();
    }

    /**
     * 回收VelocityTracker
     */
    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    private void createVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    public  interface HScrollListViewListener {

        void onSliding(int moveDistanceX);

        void onFling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY, int overX, int overY);

        void onTouchDown(float x, float y);

        void onTouchUp(float x, float y);

/*        void onOpenLeftMenu();

        void onCloseLeftMenu();*/

    }
}
