package com.example.baopengjian.ray_dailywork.third;

/**
 * Created by PF0ZYM2B on 2018/3/16.
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.Gravity;


public class DrawTextUtility {
    private static Rect rect = new Rect();
    private static Paint textPaint = new Paint();

    public DrawTextUtility() {
    }

    public static void drawText(String text, RectF rectF, Canvas canvas, float textSize, int gravity, Paint paint) {
        drawText(text, rectF.left, rectF.top, rectF.right, rectF.bottom, canvas, textSize, gravity, paint);
    }

    public static void drawText(String text, float startX, float startY, float endX, float endY, Canvas canvas, float textSize, int gravity, Paint paint) {
        if(paint == null) {
            textPaint.reset();
            textPaint.setFlags(1);
            textPaint.setColor(Color.parseColor("#FAFAFB"));
            textPaint.setTextSize(textSize);
            drawSimpleText(startX, startY, endX, endY, canvas, textSize, textPaint, text, gravity);
        } else {
            drawSimpleText(startX, startY, endX, endY, canvas, textSize, paint, text, gravity);
        }
    }

    public static void drawSimpleText(float startX, float startY, float endX, float endY, Canvas canvas, float textSize, Paint paint, String text, int gravity) {
        if(text != null) {
            paint.setTextSize(textSize);
            paint.getTextBounds(text, 0, text.length(), rect);
            float tempWidth = endX - startX;
            float tempHeight = endY - startY;

            while((float)rect.width() > tempWidth || (float)rect.height() > tempHeight) {
                --textSize;
                if(textSize <= 0.0F) {
                    break;
                }

                paint.setTextSize(textSize);
                paint.getTextBounds(text, 0, text.length(), rect);
            }

            if(gravity == Gravity.RIGHT) {
                paint.setTextAlign(Align.RIGHT);
                canvas.drawText(text, endX, startY + ((tempHeight + rect.height()) / 2.0F) - rect.bottom, paint);
            } else if(gravity == Gravity.CENTER) {
                paint.setTextAlign(Align.CENTER);
                canvas.drawText(text, startX + tempWidth / 2.0F, startY + ((tempHeight + rect.height()) / 2.0F) - rect.bottom, paint);
            } else if(gravity == Gravity.LEFT) {
                paint.setTextAlign(Align.LEFT);
                canvas.drawText(text, startX, startY + ((tempHeight + rect.height()) / 2.0F) - rect.bottom, paint);
            }

        }
    }
}
