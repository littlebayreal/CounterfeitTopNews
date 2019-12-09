package com.sziti.counterfeittopnews.widget.NewsDetailView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.LoadMoreView;

/**
 * Created by BlingBling on 2016/10/11.
 */

public final class SimpleLoadMoreView extends LoadMoreView {
	@Override
    public int getLayoutId() {
        return R.layout.brvah_quick_view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
