package com.example.baopengjian.ray_dailywork.may.bar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;

import com.example.baopengjian.ray_dailywork.R;
import com.example.baopengjian.ray_dailywork.may.UtilsDensity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ray on 2018/5/22.
 * 柱状图
 *  从上往下： 上部内容区 topHeight  + 底部标注区 bottomHeight
 *  从左往右：间宽*1/2 + 柱状图宽*count + 间宽*(count-1) + 间宽/2;
 *
 *  数据设置 柱状图List<Bar>  顶部标题（leftTitle + rightTitle）
 *  Bar(每个柱子 desc 描述 + data 数值)
 */

public class BarChartView extends View {

    public static int TEXT_MARGINING = 5;

    public static int BOTTOM_TEXT_MARGINNG = 20;

    public static int[] BAR_COLORS = {R.color.bar_color_1,R.color.bar_color_2};


    private int mTopHeight,mButtomHeight,mTitlePadding;
    private int mBarWidth,mBarSpace;

    /** 最高柱状图距离middleArea顶部值*/
    private int mPaddingTop;
    private int mHeight,mWidth;

    private int mLineWidth;

    private String mLeftTitle,mRightTilte;
    private List<Bar> mBars;
    private int[] mBarColors;

    private Paint mTextPaint;
    private int mTextColor;

    public BarChartView(Context context) {
        this(context,null);

    }

    public BarChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BarChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mLineWidth = 1;
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.GRAY);
        mTextPaint.setStrokeWidth(1);
        mTextPaint.setTextSize(20);
        mTextColor = R.color.text_color;
        mTextPaint.setColor(getResources().getColor(mTextColor));

        mBarColors = BAR_COLORS;
    }



    private void initWeight() {
        mButtomHeight = UtilsDensity.dp2px(getContext(),30);
        mTopHeight = mHeight - mButtomHeight;
        mPaddingTop = UtilsDensity.dp2px(getContext(),30);
        mTitlePadding =  UtilsDensity.dp2px(getContext(),5);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mHeight = getHeight();
        mWidth = getWidth();

        initWeight();
        drawBackGroundLine(canvas);
        drawBars(canvas);
        drawLeftTitle(canvas);
        drawRightTitle(canvas);
    }

    private void drawRightTitle(Canvas canvas) {
        if(mRightTilte == null || TextUtils.isEmpty(mRightTilte)){
            return;
        }

        Rect rect = new Rect();
        mTextPaint.getTextBounds(mRightTilte,0,mRightTilte.length(),rect);
        int startX = mWidth - mTitlePadding - rect.width();
        canvas.drawText(mRightTilte,startX,mTitlePadding+rect.height(),mTextPaint);
    }


    private void drawLeftTitle(Canvas canvas) {
        if(mLeftTitle == null || TextUtils.isEmpty(mLeftTitle)){
            return;
        }
        Rect rect = new Rect();
        mTextPaint.getTextBounds(mLeftTitle,0,mLeftTitle.length(),rect);
        canvas.drawText(mLeftTitle,mTitlePadding,mTitlePadding+rect.height(),mTextPaint);
    }

    private void drawBars(Canvas canvas) {
        if (mBars == null || mBars.isEmpty()) {
            return;
        }

        if (mBarSpace == 0) {
            mBarWidth = mWidth / (2 * mBars.size());
            mBarSpace = mBarWidth;
        }

        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(mBarWidth);
        paint.setTextSize(20);

        float maxY = getMaxY();
        float rateY = 0;
        if(maxY > 0){
             rateY = (mTopHeight - mPaddingTop)/maxY;
        }


        int x;
        float stopY;
        int startY = mTopHeight,middleX;
        Bar bar;
        int maxDescLength = mBarWidth + mBarSpace/4;
        for (int i = 0; i < mBars.size(); i++) {
            bar = mBars.get(i);

            x = mBarSpace/2 + (mBarWidth + mBarSpace)*i + mBarWidth/2;
            stopY = startY - bar.getDataFloat()*rateY;
            int color = getResources().getColor(mBarColors[i%mBarColors.length]);
            paint.setColor(color);
            canvas.drawLine(x,startY,x,stopY,paint);
            middleX = mBarSpace/2 + (mBarWidth + mBarSpace)*i + mBarWidth/2;
            drawHint(canvas,middleX,bar.getData(),stopY,paint);
            drawDesc(canvas,middleX,bar.getDesc(),startY,maxDescLength);
        }
    }

    private void drawHint(Canvas canvas, int middleX, String data,float stopY, Paint paint) {
        Rect bound = new Rect();
        mTextPaint.getTextBounds(data,0,data.length(),bound);
        int width = bound.width();
        canvas.drawText(data,middleX- width/2,stopY-TEXT_MARGINING,paint);
    }

    private void drawDesc(Canvas canvas, int middleX, String desc, int startY, int maxDescLength){
        Rect bound2 = new Rect();
        mTextPaint.getTextBounds(desc,0,desc.length(),bound2);
        canvas.drawText(desc,middleX - bound2.width()/2,startY+BOTTOM_TEXT_MARGINNG+bound2.height(),mTextPaint);
    }



    private void drawBackGroundLine(Canvas canvas) {

        Paint paint = new Paint();
        paint.setStrokeWidth(mLineWidth);
        paint.setColor(Color.GRAY);


      /*
        竖直方向线
        int verticalLineNumber = 2;
        int verticalSpace = mWidth/(verticalLineNumber -1);

        for(int i = 0;i < verticalLineNumber;i++){
            drawVerticalLine(canvas,paint,verticalSpace,i,verticalLineNumber -1);
        }*/


        int horizontalLineNumber = 5;
        int horizontalSpace = mTopHeight/(horizontalLineNumber - 1);
        float[] dash = new float[]{1,5};
        paint.setStyle ( Paint.Style.STROKE ) ;
        paint.setPathEffect(new DashPathEffect(dash,0));
        for(int i = 1;i < horizontalLineNumber;i++){
            drawHorizontalLine(canvas,paint,horizontalSpace,i,horizontalLineNumber -1);
        }
    }

    private void drawVerticalLine(Canvas canvas, Paint paint, int verticalSpace, int index, int maxIndex) {
        int startY = 0;
        int stopY = mTopHeight;
        int x;
        if(index == 0){
            x = 0;
        }else if(index == maxIndex){
            x = mWidth - mLineWidth;
        }else{
            x = verticalSpace*index;
        }
        canvas.drawLine(x,startY,x,stopY,paint);
    }


    private void drawHorizontalLine(Canvas canvas, Paint paint, int horizontalSpace, int index, int maxIndex){
        int startX = 0;
        int stopX = mWidth;

        int Y;
        if(index == 0){
            Y = 0;
        }else if(index == maxIndex){
            Y = mTopHeight;
        }else {
            Y = horizontalSpace*index;
        }
        canvas.drawLine(startX,Y,stopX,Y,paint);
    }

    public float getMaxY() {
        float max = mBars.get(0).getDataFloat();
        for(Bar bar:mBars){
            if(bar.getDataFloat() > max){
                max = bar.getDataFloat();
            }
        }
        return max;
    }

    public void setBarColors(int[] colors){
        if(colors == null || colors.length == 0){
            return;
        }
        mBarColors = colors;
    }

    public void setBars(List<Bar> list){
        if(list == null || list.isEmpty()){
            return;
        }
        try{
            if(list.get(0).getDataFloat()< 0){
                for(Bar bar:list){
                    if(bar.getData() != null && !TextUtils.isEmpty(bar.getData())){
                        bar.setDataFloat(Float.parseFloat(bar.getData()));
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        mBars = list;
        postInvalidate();
    }

    public void setTitles(String leftTitle,String rightTitle){
        mLeftTitle = leftTitle;
        mRightTilte = rightTitle;
        postInvalidate();
    }

    public static class Bar implements Serializable {

        private String desc;

        private String data;

        private float dataFloat = -1;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public float getDataFloat() {
            return dataFloat;
        }

        public void setDataFloat(float dataFloat) {
            this.dataFloat = dataFloat;
        }

        @Override
        public String toString() {
            return "Bar{" +
                    "desc='" + desc + '\'' +
                    ", data=" + data +
                    '}';
        }
    }
}
