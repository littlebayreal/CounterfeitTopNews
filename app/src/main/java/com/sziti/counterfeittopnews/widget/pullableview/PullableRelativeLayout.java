package com.sziti.counterfeittopnews.widget.pullableview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.sziti.counterfeittopnews.R;

public class PullableRelativeLayout extends RelativeLayout implements Pullable {
    private RecyclerView recyclerView;

    public PullableRelativeLayout(Context context) {
        super(context);
    }

    public PullableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (recyclerView == null)
                recyclerView = findViewById(R.id.fragment_home_focuse_rv);
            }
        });
    }

    public PullableRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean canPullDown() {
        if (recyclerView == null) return true;
        if (recyclerView.getAdapter() == null) return true;
        if (recyclerView.getAdapter().getItemCount() == 0) {
            // 没有item的时候也可以下拉刷新
            return true;
        } else if (((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition() == 0 && recyclerView.getChildAt(0).getTop() >= 0) {
            // 滑到ListView的顶部了
            return true;
        }
        return false;
    }

    @Override
    public boolean canPullUp() {
        if (recyclerView == null) return true;
        if (recyclerView.getAdapter() == null) return true;
        if (recyclerView.getAdapter().getItemCount() == 0) {
            // 没有item的时候也可以上拉加载
            return true;
        }
        else if (((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1) {
            if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
                return true;
        }
        //拒绝上拉
        return false;
    }
}
