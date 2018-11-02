package com.sziti.counterfeittopnews.widget.pullableview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class PullableRecyclerView extends RecyclerView implements Pullable {
    public PullableRecyclerView(Context context) {
        super(context);
    }

    public PullableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canPullDown() {
        if (getAdapter() == null) return true;
        if (getAdapter().getItemCount() == 0) {
            // 没有item的时候也可以下拉刷新
            return true;
        } else if (((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition() == 0
                && getChildAt(0).getTop() >= 0) {
            // 滑到ListView的顶部了
            return true;
        } else
            return false;
    }

    @Override
    public boolean canPullUp() {
        if (getAdapter() == null) return true;
        if (getAdapter().getItemCount() == 0) {
            // 没有item的时候也可以上拉加载
            return true;
        } else if (((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition() == (getAdapter().getItemCount() - 1)) {
            // 滑到底部了
            if (getChildAt(((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition() - ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition()) != null
                    && getChildAt(((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition()
                            - ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition()).getBottom() <= getMeasuredHeight())
                return true;
        }
        return false;
    }
}
