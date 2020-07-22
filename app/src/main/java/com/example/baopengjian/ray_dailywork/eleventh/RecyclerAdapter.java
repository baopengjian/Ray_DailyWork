package com.example.baopengjian.ray_dailywork.eleventh;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.baopengjian.ray_dailywork.R;
import com.example.baopengjian.ray_dailywork.util.UtilsDensity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ray on 2020-1-2.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Menu> mData;
    private Context mContext;
    private int num;
    private int mStep;

    private ItemTouchHelper itemTouchHelper;

    public RecyclerAdapter(int num, Context context, List<Menu> list) {
        mData = list;
        mContext = context;
        this.num = num;
        mStep = getStep(context, num);
    }

    public RecyclerAdapter(int num, Context context, List<Menu> list,boolean isMove,RecyclerView rv) {
        mData = list;
        mContext = context;
        this.num = num;
        mStep = getStep(context, num);
        if(isMove){
            initTouchHelper(rv);
        }

    }

    private void initTouchHelper(RecyclerView rv) {
         itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                Log.e("hsjkkk", "getMovementFlags()");
//                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                /*} else {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }*/
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Log.e("hsjkkk", "onMove()");
                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(mData, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(mData, i, i - 1);
                    }
                }
                notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                Toast.makeText(MainActivity.this, "拖拽完成 方向" + direction, Toast.LENGTH_SHORT).show();
                Log.e("hsjkkk", "拖拽完成 方向" + direction);

            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                Log.e("hsjkkk", "onSelectedChanged()");
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE)
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                Log.e("hsjkkk", "clearView()");
                viewHolder.itemView.setBackgroundColor(0);

            }

            //重写拖拽不可用
            @Override
            public boolean isLongPressDragEnabled() {
                Log.e("hsjkkk", "isLongPressDragEnabled()");
                return false;
            }

        });
        itemTouchHelper.attachToRecyclerView(rv);
    }


    public static int getStep(Context context, int num) {
        int padding = UtilsDensity.dip2px(15);
        return (UtilsDensity.getScreenWidth(context) - 2 * padding) / num;
    }

    public static  int getColumns(int count,int num) {
        if (count <= num) {
            return 1;
        } else if (count % num == 0) {
            return count / num;
        } else {
            return count / num + 1;
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eleventh_item, parent, false);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = mStep;
        params.height = mStep;
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int position) {
        myViewHolder.textView.setText(mData.get(position).getName());
        if(itemTouchHelper != null){
            myViewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    itemTouchHelper.startDrag(myViewHolder);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyViewHolder(View itemview) {
            super(itemview);
            textView = (TextView) itemview.findViewById(R.id.tv);

        }
    }
}

