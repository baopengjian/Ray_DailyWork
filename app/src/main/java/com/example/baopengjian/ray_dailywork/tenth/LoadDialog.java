package com.example.baopengjian.ray_dailywork.tenth;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.baopengjian.ray_dailywork.R;

import java.lang.ref.WeakReference;

/**
 * 加载中Dialog
 */
public class LoadDialog extends Dialog implements OnClickListener {

    private ImageView iv_load;
    private TextView tips_loading_msg, tv_cancel;
    private AnimationDrawable animationDrawable;
    private String msg = "正在加载...";
    private boolean flag = true;
    private Context context;
    boolean cancelShow = true;
    private  int referenceTime = 0;

    public LoadDialog(Context context) {
        super(context);
        this.context = context;
    }

    public Context getBaseContext() {
        return context;
    }

    public LoadDialog(Context context, String message, boolean flag, boolean cancelShow) {
        super(context, R.style.load_dialog);
        this.setCancelable(flag);
        this.setCanceledOnTouchOutside(false);
        this.cancelShow = cancelShow;
        if (null == message) {
            msg = "正在加载...";
        } else {
            this.msg = message;
        }
        referenceTime = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.load_view, null);
        this.setContentView(view);
        tips_loading_msg =  findViewById(R.id.tips_loading_msg);
        iv_load =  findViewById(R.id.iv_load);
        iv_load.setImageResource(R.drawable.load_view_animation);
        animationDrawable = (AnimationDrawable) iv_load.getDrawable();
        tv_cancel =  findViewById(R.id.tv_cancel);
        tv_cancel.setClickable(true);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        if (cancelShow) {
            tv_cancel.setVisibility(View.VISIBLE);
        } else {
            tv_cancel.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.setCancelable(flag);
        if (null != msg) {
            tips_loading_msg.setText(msg);
        }
        if (cancelShow) {
            tv_cancel.setVisibility(View.VISIBLE);
        } else {
            tv_cancel.setVisibility(View.GONE);
        }
        tv_cancel.setOnClickListener(this);
    }

    public static WeakReference<LoadDialog> dialogReference;

    /**
     * 只有最新的dialog会显示，后面的会覆盖前面的，自动释放
     * runOnUiThread中使用
     */
    public static void showDialog(Context context) {
        try {
            LoadDialog loadDialog = getCorrectDialog(context, false);
            loadDialog.referenceTime++;
            if (!loadDialog.isShowing()) {
                loadDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static synchronized LoadDialog  getCorrectDialog(Context context, boolean cancelShow) {
        LoadDialog loadDialog;
        if (null == dialogReference) {
            loadDialog = new LoadDialog(context, null, cancelShow, true);
            dialogReference = new WeakReference<>(loadDialog);
        } else {
            //是不是当前Activity或者Fragment的Dialog
            loadDialog = dialogReference.get();
            if (loadDialog == null) {
                loadDialog = new LoadDialog(context, null, cancelShow, true);
                dialogReference = new WeakReference<>(loadDialog);
            } else if (loadDialog.getBaseContext() != context) {
                //是其他Activity或Fragment的Dialog则释放,然后重新创建
                if (loadDialog.isShowing()) {
                    loadDialog.dismiss();
                }
                loadDialog.release();
                loadDialog = new LoadDialog(context, null, cancelShow, true);
                dialogReference = new WeakReference<>(loadDialog);
            }
        }

        return loadDialog;
    }


    public static synchronized void showDialog(Context context, boolean cancelShow) {
        try {
            LoadDialog loadDialog = getCorrectDialog(context, cancelShow);
            loadDialog.referenceTime++;
            if (!loadDialog.isShowing()) {
                loadDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏最新的dialog
     * runOnUiThread中使用
     */
    public static synchronized void dismissDialog() {
        try {
            if (dialogReference == null || dialogReference.get() == null) {
                return;
            }

            LoadDialog loadDialog = dialogReference.get();
            if (loadDialog.referenceTime > 0) {
                loadDialog.referenceTime--;
            }
            if (loadDialog.referenceTime == 0) {
                if (loadDialog.isShowing()) {
                    loadDialog.dismiss();
                }
                loadDialog.release();
                dialogReference.clear();
            }

        } catch (Exception e) {
             e.printStackTrace();
        }

    }

    /**
     * 清楚当前显示的Dialog
     */
    public static synchronized void clear() {
        if(dialogReference == null || dialogReference.get() == null){
            return;
        }
        LoadDialog loadDialog = dialogReference.get();
        if(loadDialog.isShowing()){
            loadDialog.dismiss();
        }
        loadDialog.referenceTime = 0;
        loadDialog.release();
        dialogReference.clear();
    }

    /**
     * 清除当前Activity或者Fragment对应Dialog
     * @param context
     */
    public static synchronized void clear(Context context) {
        if(dialogReference == null || dialogReference.get() == null){
            return;
        }
        LoadDialog loadDialog = dialogReference.get();
        if (loadDialog.getBaseContext() == context) {
            if (loadDialog.isShowing()) {
                loadDialog.dismiss();
            }
            loadDialog.release();
            dialogReference.clear();
            loadDialog.referenceTime = 0;
        }

    }

    private void release() {
        if (animationDrawable == null) {
            release();
            for (int i = 0; i < animationDrawable.getNumberOfFrames(); i++) {
                Drawable frame = animationDrawable.getFrame(i);
                if (frame instanceof BitmapDrawable) {
                    ((BitmapDrawable) frame).getBitmap().recycle();
                }
                frame.setCallback(null);
            }
            animationDrawable.setCallback(null);
        }

    }


    @Override
    public void show() {
        if (null == msg) {
            msg = "正在加载...";
        }
        super.show();
        animationDrawable.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        referenceTime = 0;
        animationDrawable.stop();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }


}
