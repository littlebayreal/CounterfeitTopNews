package com.sziti.counterfeittopnews.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;

public class VerticalRcyclerView extends RecyclerView {
    private static String TAG = "VerticalSlideLayout";

    public VerticalRcyclerView(Context context) {
        super(context);
    }

    public VerticalRcyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalRcyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private float lastX, lastY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        boolean intercept = false;
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "新第一次点击");
                lastX = e.getX();
                lastY = e.getY();
                super.onInterceptTouchEvent(e);
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) Math.abs(e.getX() - lastX);
                int dy = (int) Math.abs(e.getY() - lastY);

                Log.e(TAG, "dx:" + dx + "dy:" + dy);
                if (dx > dy * 0.5) {
                    Log.i(TAG, "横划！交给子控件处理");
                    intercept = false;
                } else if (dy * 0.5 > dx) {
                    Log.i(TAG, "竖划！本控件直接拦截处理");
                    intercept = true;
                } else {
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
//                setParentInterceptTouchEvent(false);
//                isInterceptHere = false;
                lastX = 0;
                lastY = 0;
                break;
        }
        return intercept;
    }
}
