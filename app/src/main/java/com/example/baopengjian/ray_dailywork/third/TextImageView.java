package com.example.baopengjian.ray_dailywork.third;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.baopengjian.ray_dailywork.R;


/**
 * Created by Ray on 2018/6/22.
 * 包含一个TextView和一个ImageView的LinearLayout，显示方向为水平
 * Requirement：
 * 1 TextView显示文本：字体大小、颜色、内容
 * 2 ImageView显示图片：大小、内容
 * 3 可以设置图片、文本顺序，图片在前或者文本在前
 */
public class TextImageView extends LinearLayout implements Checkable{

    public static final int IMAGE_ORDER_START = 0;

    private boolean isChecked = false;

    private ImageView iv;
    private TextView tv;

    private boolean mIsImageFirst = true;

    /**
     *   Image属性
     */
    /** ImageView 宽高*/
    private LayoutParams mImageParams;
    /**ImageView src对应id*/
    private int mImageSrcId;

    /**
     * Text属性
     */
    /** TextView 的内容*/
    private String mTextContent;

    /** TextView 字大小*/
    private float mTextSize = 14;

    /** TextView 字颜色对应id*/
    private int mTextColor = 0;


    /**
     * 位置属性
     */
    /** ImageView 和 TextView 之间的间距*/
    private int mContent_margin = 0;


    private LayoutParams mTextParams;

    public TextImageView(Context context) {
        this(context, null);
    }

    public TextImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs, context);
        init(context);
    }

    private void initAttrs(AttributeSet attrs, Context context) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextImageView);
            mIsImageFirst = IMAGE_ORDER_START == a.getInt(R.styleable.TextImageView_image_order, IMAGE_ORDER_START);

            mImageSrcId = a.getResourceId(R.styleable.TextImageView_image_src, 0);

            Drawable drawable = a.getDrawable(R.styleable.TextImageView_image_src);
         /*   if(drawable instanceof StateListDrawable){
                StateListDrawable listDrawable = (StateListDrawable) drawable;
                listDrawable.
                // Drawable d1   = listDrawable.getStateDrawable(1);
            }*/

            int imageWidth = a.getDimensionPixelSize(R.styleable.TextImageView_image_width, 0);
            int imageHeight = a.getDimensionPixelSize(R.styleable.TextImageView_image_height, 0);
            if (imageWidth != 0 || imageHeight != 0) {
                mImageParams = new LayoutParams(imageWidth, imageHeight);
            }

            mTextContent = a.getString(R.styleable.TextImageView_text);
            int textWidth = a.getDimensionPixelSize(R.styleable.TextImageView_text_width, 0);
            int textHeight = a.getDimensionPixelSize(R.styleable.TextImageView_text_height, 0);
            if (textWidth != 0 || textHeight != 0) {
                mTextParams = new LayoutParams(textWidth, textHeight);
            }
            mTextSize = a.getDimension(R.styleable.TextImageView_text_size,14f);
            mTextColor = a.getColor(R.styleable.TextImageView_text_color, Color.BLACK);


            mContent_margin = a.getDimensionPixelSize(R.styleable.TextImageView_content_margin,0);
            a.recycle();
        }


        if (mImageParams == null) {
            mImageParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        }

        if (mTextParams == null) {
            mTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        }
    }

    private void init(Context context) {
        iv = new ImageView(context);
        iv.setLayoutParams(mImageParams);
        if (mImageSrcId != 0) {
            iv.setImageResource(mImageSrcId);
        }

        tv = new TextView(context);
        tv.setLayoutParams(mTextParams);
        tv.setText(mTextContent);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,mTextSize);
        if(mTextColor != 0){
            tv.setTextColor(mTextColor);
        }

        if (mIsImageFirst) {
            addView(iv);
            addView(tv);
            mTextParams.leftMargin = mContent_margin;
        } else {
            addView(tv);
            addView(iv);
            mImageParams.leftMargin = mContent_margin;
        }

    }

    public TextView getTextView() {
        return tv;
    }

    public ImageView getImageView() {
        return iv;
    }

    @Override
    public void setChecked(boolean checked) {
        if(isChecked != checked){
            isChecked = checked;
            toggle();
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {

      //  tv.setSelected(isChecked);
    }
}
