package com.example.baopengjian.ray_dailywork.second;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.baopengjian.ray_dailywork.R;

/**
 * Created by Ray on 2018/5/10.
 */

public class HScrollAdapter extends BaseAdapter {


    private int scrollXPos;
    private Context mContext;
    private Scroller mScrollerCompat;

    public HScrollAdapter(Context context) {
        mContext = context;
    }

    public void setScroller(Scroller scroller) {
        mScrollerCompat = scroller;
    }

    @Override
    public int getCount() {
        return 30;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold hold;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.hs_item_adapter, null);
            hold = new ViewHold();
            hold.convertView = convertView;
            convertView.setTag(hold);
        } else {
            hold = (ViewHold) convertView.getTag();
        }

        TextView tv_title = hold.convertView.findViewById(R.id.tv_title);
        tv_title.setText("固定列" + position);
        tv_title.setBackgroundColor(Color.GRAY);

        TextView tv_title1 = hold.convertView.findViewById(R.id.tv_title1);
        tv_title1.setText("内容一");

        TextView tv_title2 = hold.convertView.findViewById(R.id.tv_title2);
        tv_title2.setText("内容二");

        TextView tv_title3 = hold.convertView.findViewById(R.id.tv_title3);
        tv_title3.setText("内容三");

        TextView tv_title4 = hold.convertView.findViewById(R.id.tv_title4);
        tv_title4.setText("内容四");

        TextView tv_title5 = hold.convertView.findViewById(R.id.tv_title5);
        tv_title5.setText("内容五");

        TextView tv_title6 = hold.convertView.findViewById(R.id.tv_title6);
        tv_title6.setText("内容六");

        TextView tv_title7 = hold.convertView.findViewById(R.id.tv_title7);
        tv_title7.setText("内容七");

        TextView tv_title8 = hold.convertView.findViewById(R.id.tv_title8);
        tv_title8.setText("内容八");

        HScrollLayout hScrollLayout = convertView.findViewById(R.id.hsl);
        if (hScrollLayout != null && mScrollerCompat != null) {
            hScrollLayout.setScroller(mScrollerCompat);
        }
        if (scrollXPos != 0) {
            hScrollLayout.scrollTo(scrollXPos, 0);
        } else {
            if (mScrollerCompat != null) {
                hScrollLayout.scrollTo(mScrollerCompat.getCurrX(), 0);
            }
        }


        return convertView;
    }

    public void setScrollXPos(int pos) {
        this.scrollXPos = pos;
    }

    class ViewHold {
        View convertView;
    }

}
