package com.example.baopengjian.ray_dailywork.april;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baopengjian.ray_dailywork.R;

/**
 * Created by Ray on 2018/4/9 .
 * TextView设置文字不同颜色 +部分可以点击
 *
 */

public class PartiallyClickableActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partially_clickable_activity);

        tv = (TextView) findViewById(R.id.tv);

        //step1 设置点击背景色为透明
        tv.setHighlightColor(getResources().getColor(android.R.color.transparent));
        String st1 = "产品成立、计息并进封入闭期。购买成功后1-2个交易日可通过";
        String st2 = "【我的理财】";


        //step3 设置SpannableString
        SpannableString info = new SpannableString(st1 + st2 );
        info.setSpan(new ForegroundColorSpan(Color.BLACK), 0, st1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        info.setSpan(new ForegroundColorSpan(Color.RED), st1.length(), (st1 + st2).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        info.setSpan(new Clickable(clickListener), st1.length(), (st1 + st2).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE | ~Spanned.SPAN_MARK_MARK);

        //设置字体大小（绝对值,单位：像素）,第二个参数boolean dip，如果为true，表示前面的字体大小单位为dip，否则为像素
        info.setSpan(new AbsoluteSizeSpan(14,true),0,st1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        info.setSpan(new AbsoluteSizeSpan(24,true),st1.length(),(st1 + st2).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //step4 TextView设置SpannableString
        tv.setText(info);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(PartiallyClickableActivity.this, "点击成功....【我的理财】", Toast.LENGTH_SHORT).show();
        }
    };

    //step2 定义ClickableSpan
    class Clickable extends ClickableSpan {
        private final View.OnClickListener mListener;

        public Clickable(View.OnClickListener l) {
            mListener = l;
        }


        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            //取消下滑线
            ds.setUnderlineText(false);
//          super.updateDrawState(ds);
        }
    }
}


