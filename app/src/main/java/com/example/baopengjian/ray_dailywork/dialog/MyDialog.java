package com.example.baopengjian.ray_dailywork.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.baopengjian.ray_dailywork.R;
import com.example.baopengjian.ray_dailywork.util.UtilsDensity;


/**
 * 项目弹出框
 * 1） 标题 mTitle（默认隐藏，通过setTitle设置时，自动显示）
 * 2） 内容区ll_content（默认有一个TextView:tv_content）:当需要自定义内容区时，可调用getContent获取，然后removeView和addView
 * 3） 按钮区 ll_btn（取消:默认不显示 + 确定）
 * 注意：所有get方法获取的控件，必须要在show方法调用后
 *
 * @author Ray
 */
public class MyDialog extends Dialog {

    public MyDialog(Context context) {
        super(context);
    }

    public MyDialog(Context context, int theme) {
        super(context, theme);
    }

    /**
     * 简单显示一段内容 ,不带标题
     */
    public static MyDialog show(Context context, String msg) {
        Builder builder = new Builder(context);
        MyDialog dialog = builder.setMessage(msg).create();
        dialog.show();
        return dialog;
    }

    /**
     * 简单显示一段内容 ,带标题
     */
    public static MyDialog show(Context context, String msg, String title) {
        Builder builder = new Builder(context);
        MyDialog dialog = builder.setMessage(msg).setTitle(title).create();
        dialog.show();
        return dialog;
    }

    /**
     * 简单显示一段内容 ,带标题,对齐方式
     */
    public static MyDialog show(Context context, String msg, String title, int gravity) {
        Builder builder = new Builder(context);
        MyDialog dialog = builder.setMessage(msg,gravity).setTitle(title).create();
        dialog.show();
        return dialog;
    }
    /**
     * 在show()方法调用后；
     * 获取标题,如果隐藏或显示，为了圆角效果，须修改ll_content的背景同时修改et_content的距离顶部距离
     */
    public TextView getTitle() {
        return (TextView) findViewById(R.id.title);
    }

    /**
     * 在show()方法调用后；
     * 获取默认内容控件
     */
    public TextView getSimpleContent() {
        return (TextView) findViewById(R.id.message);
    }

    /**
     * 在show()方法调用后；
     * 获取整个内容区域
     */
    public LinearLayout getContent() {
        return (LinearLayout) findViewById(R.id.content);
    }

    /**
     * 在show()方法调用后；
     * 获取整个按钮区
     */
    public LinearLayout getBtns() {
        return (LinearLayout) findViewById(R.id.ll_btn);
    }

    /**
     * 获取取消按钮：默认隐藏的
     * 注意：1)如果修改按钮的背景，则要弹框的左下圆角效果被覆盖
     * 2)如果设置显示，分割线也要设置显示
     * 3)在show()方法调用后；
     */
    public TextView getCancleBtn() {
        return (TextView) findViewById(R.id.negativeButton);
    }

    /**
     * 在show()方法调用后；
     * 获取确定按钮：默认显示的
     * 注意：如果修改按钮的背景，则要弹框的右下圆角效果被覆盖
     */
    public TextView getConfirmBtn() {
        return (TextView) findViewById(R.id.positiveButton);
    }

    /**
     * 在show()方法调用后；
     * 按钮分割线
     */
    public View getBtnDivider() {
        return findViewById(R.id.btn_divider);
    }

    public static class Builder {
        private Context context;
        private String message;
        private int gravity = Gravity.CENTER;
        private String positiveButtonText = null;
        private int positiveButtonTextTextColor;
        private String negativeButtonText = null;
        private int negativeButtonTextColor;
        private View contentView;
        private boolean flag = true;
        private boolean outSideCancelable = true;
        private DialogInterface.OnClickListener positiveListener;
        private DialogInterface.OnClickListener negativeListener;
        private DialogInterface.OnDismissListener dismissListener;

        private String title;

        public Builder(Context context) {
            this.context = context;
            dismissListener = null;
        }

        public Builder setMessage(String message, int gravity) {
            this.message = message;
            this.gravity = gravity;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveListener = listener;
            return this;
        }



        public Builder setPositiveButton(int positiveButtonText,int colorid,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveListener = listener;
            this.positiveButtonTextTextColor = colorid;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, int colorid,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveListener = listener;
            this.positiveButtonTextTextColor = colorid;
            return this;
        }



        public Builder setOnDismissListener(
                DialogInterface.OnDismissListener listener) {
            this.dismissListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,int colorid,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeListener = listener;
            this.negativeButtonTextColor = colorid;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, int colorid,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeListener = listener;
            this.negativeButtonTextColor = colorid;
            return this;
        }

        public Builder setCancelable(boolean flag) {
            this.flag = flag;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.outSideCancelable = canceledOnTouchOutside;
            return this;
        }


        public MyDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final MyDialog dialog = new MyDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_normal_layout, null);
            dialog.addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

            TextView mTitle = (TextView) layout.findViewById(R.id.title);
            LinearLayout ll_content = (LinearLayout) layout.findViewById(R.id.content);
            TextView tv_content = (TextView) layout.findViewById(R.id.message);

            View btn_divider = layout.findViewById(R.id.btn_divider);
            TextView confirm = (TextView) layout.findViewById(R.id.positiveButton);
            TextView cancel = (TextView) layout.findViewById(R.id.negativeButton);

            if (!TextUtils.isEmpty(title)) {
                mTitle.setText(title);
                mTitle.setVisibility(View.VISIBLE);
                ll_content.setBackgroundResource(R.color.dialog_bg);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tv_content.getLayoutParams();
                params.topMargin = params.topMargin - UtilsDensity.dip2px(13);
                tv_content.setLayoutParams(params);
            }

            if (dismissListener != null) {
                dialog.setOnDismissListener(dismissListener);
            }
            if (!TextUtils.isEmpty(positiveButtonText)) {
                confirm.setText(positiveButtonText);
            }
            if(positiveButtonTextTextColor != 0){
                confirm.setTextColor(context.getResources().getColor(positiveButtonTextTextColor));
            }
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (positiveListener != null) {
                        positiveListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                    } else {
                        dialog.dismiss();
                    }
                }
            });
            if (!TextUtils.isEmpty(negativeButtonText)) {
                cancel.setVisibility(View.VISIBLE);
                btn_divider.setVisibility(View.VISIBLE);
                cancel.setText(negativeButtonText);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (negativeListener != null) {
                            negativeListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        } else {
                            dialog.dismiss();
                        }
                    }
                });
            }
            if(negativeButtonTextColor != 0){
                cancel.setTextColor(context.getResources().getColor(negativeButtonTextColor));
            }


            if (!TextUtils.isEmpty(message)) {
                tv_content.setText(message);
                tv_content.setGravity(gravity);
                tv_content.setMovementMethod(ScrollingMovementMethod.getInstance());
            } else if (contentView != null) {
                ll_content.removeAllViews();
                ll_content.addView(contentView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            }
            dialog.setContentView(layout);  //自定义内容布局
            dialog.setCancelable(flag);
            dialog.setCanceledOnTouchOutside(outSideCancelable);
            Window dialogWindow = dialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            DisplayMetrics d = context.getResources().getDisplayMetrics();
            lp.width = (int) (d.widthPixels * 0.85); //
            lp.height = LayoutParams.WRAP_CONTENT;
            dialogWindow.setAttributes(lp);
            return dialog;
        }


    }
}  