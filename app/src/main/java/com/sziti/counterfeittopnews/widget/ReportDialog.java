package com.sziti.counterfeittopnews.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.util.ScreenUtils;

public class ReportDialog extends Dialog {
    public Context context;
    private int height, width;
    private int x,y;
    private float alpha;
    private int gravity;
    private boolean cancelTouchout;
    private View view;

    private ReportDialog(Builder builder) {
        super(builder.context);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        x = builder.x;
        y = builder.y;
        alpha = builder.alpha;
        gravity = builder.gravity;
        cancelTouchout = builder.cancelTouchout;
        view = builder.view;
    }

    private ReportDialog(Builder builder, int resStyle) {
        super(builder.context, resStyle);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        x = builder.x;
        y = builder.y;
        alpha = builder.alpha;
        gravity = builder.gravity;
        cancelTouchout = builder.cancelTouchout;
        view = builder.view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);
        setCanceledOnTouchOutside(cancelTouchout);
        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = Gravity.CENTER | gravity;
        lp.height = height;
        lp.width = width;
        lp.x = x;
        lp.y = y;
        lp.alpha = alpha;
        win.setAttributes(lp);
    }

    public static final class Builder {
        private Context context;
        private int height, width;
        private int x,y;
        private float alpha = 100;
        private int gravity;
        private boolean cancelTouchout;
        private View view;
        private int resStyle = -1;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder view(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }

        public Builder heightpx(int val) {
            height = val;
            return this;
        }

        public Builder widthpx(int val) {
            width = val;
            return this;
        }
        public Builder offsetpx(View v){
            int[] delete_location = new int[2];
            v.getLocationOnScreen(delete_location);
            int screenHeight = ScreenUtils.getScreenHeight(context);
            int screenWidth = ScreenUtils.getScreenWidth(context);
            int offsetWidth = screenWidth - delete_location[0] - v.getMeasuredWidth();
            int offsetHeight = 0;
            if (delete_location[1] < screenHeight/2){
                offsetHeight = delete_location[1] - ScreenUtils.getStateBar(context) + v.getMeasuredHeight() + 10;
            }else {
                offsetHeight = delete_location[1] - 500 - ScreenUtils.getStateBar(context) - 10;

            }
            gravity = Gravity.TOP;
//            x = offsetWidth;
            y = offsetHeight;

            return this;
        }
        public Builder alphaFloat(float val){
            alpha = val;
            return this;
        }
//        public Builder heightdp(int val) {
//            height = DensityUtil.dip2px(context, val);
//            return this;
//        }
//
//        public Builder widthdp(int val) {
//            width = DensityUtil.dip2px(context, val);
//            return this;
//        }

        public Builder heightDimenRes(int dimenRes) {
            height = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder widthDimenRes(int dimenRes) {
            width = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder style(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Builder cancelTouchout(boolean val) {
            cancelTouchout = val;
            return this;
        }

        public Builder addViewOnclick(int viewRes, View.OnClickListener listener) {
            view.findViewById(viewRes).setOnClickListener(listener);
            return this;
        }

        public ReportDialog build() {
            if (resStyle != -1) {
                return new ReportDialog(this, resStyle);
            } else {
                return new ReportDialog(this);
            }
        }
    }

}
