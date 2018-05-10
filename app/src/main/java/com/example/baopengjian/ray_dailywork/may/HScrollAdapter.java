package com.example.baopengjian.ray_dailywork.may;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.baopengjian.ray_dailywork.R;

/**
 * Created by PF0ZYBAJ on 2018/5/10.
 */

public class HScrollAdapter extends BaseAdapter {



    private Context mContext;

    public HScrollAdapter(Context context) {
        mContext = context;
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
      if(convertView == null){
          convertView = View.inflate(mContext, R.layout.hs_item_adapter,null);
          hold = new ViewHold();
          hold.convertView = convertView;
          convertView.setTag(hold);
      }else{
          hold = (ViewHold) convertView.getTag();
      }

        return convertView;
    }

    class ViewHold{
        View convertView;
    }

}
