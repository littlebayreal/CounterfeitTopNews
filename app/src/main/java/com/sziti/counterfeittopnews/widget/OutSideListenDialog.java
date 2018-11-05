package com.sziti.counterfeittopnews.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

public class OutSideListenDialog extends AlertDialog {
    private OutTouchListener outTouchListener;
    public OutSideListenDialog(Context context) {
        super(context);
    }

    protected OutSideListenDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected OutSideListenDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
    public void setOutTouchListener(OutTouchListener outTouchListener){
        this.outTouchListener = outTouchListener;
    }
    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (isOutOfBounds(getContext(), event)) {
            if (outTouchListener != null && event.getAction() == MotionEvent.ACTION_DOWN)
                outTouchListener.outTouch();
            return true;
        }
        return super.onTouchEvent(event);
    }

    private boolean isOutOfBounds(Context context, MotionEvent event) {
        final int x = (int) event.getX();
        final int y = (int) event.getY();
        final int slop = ViewConfiguration.get(context).getScaledWindowTouchSlop();
        final View decorView = getWindow().getDecorView();
        return (x < -slop) || (y < -slop) || (x > (decorView.getWidth() + slop)) || (y > (decorView.getHeight() + slop));
    }
    public interface OutTouchListener{
        void outTouch();
    }
}
