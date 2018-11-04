package com.sziti.counterfeittopnews.widget.pullableview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class PullableRelativeLayout extends RelativeLayout implements Pullable {
    public PullableRelativeLayout(Context context) {
        super(context);
    }

    public PullableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean canPullDown() {
        return true;
    }

    @Override
    public boolean canPullUp() {
        return true;
    }
}
