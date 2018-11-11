package com.sziti.counterfeittopnews.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class HorizonRecyclerView extends RecyclerView {
    private static String TAG = "HorizonRecyclerView";
    public HorizonRecyclerView(Context context) {
        super(context);
    }

    public HorizonRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizonRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return super.onInterceptTouchEvent(e);
    }

    private float lastX, lastY;
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        boolean intercept = super.onTouchEvent(e);
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = e.getX();
                lastY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) Math.abs(e.getX() - lastX);
                int dy = (int) Math.abs(e.getY() - lastY);

                Log.e(TAG, "dx:" + dx + "dy:"+ dy);
                if (dx > dy) {
                    Log.i(TAG, "横划！交给本类处理处理");
                    intercept = true;
                    super.onTouchEvent(e);
                } else if (dy > dx) {
                    Log.i(TAG, "竖划！交给父类处理处理");
                    intercept = false;
                }else{
                    Log.i(TAG, "特殊情况");
                    intercept = true;
                    super.onTouchEvent(e);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                lastX = 0;
                lastY = 0;
                break;
        }
        return intercept;
    }
}
