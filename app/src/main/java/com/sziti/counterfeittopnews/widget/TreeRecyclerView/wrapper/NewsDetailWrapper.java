package com.sziti.counterfeittopnews.widget.TreeRecyclerView.wrapper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseRecyclerAdapter;

/**
 * 为新闻详情页适配的Adapter
 * @param <T>
 */
public class NewsDetailWrapper<T> extends HeaderAndFootWrapper<T>{
	private static final int EMPTY_ITEM = 3000;
	public NewsDetailWrapper(BaseRecyclerAdapter<T> adapter) {
		super(adapter);
	}

	/**
	 * 为界面添加当前评论消息为空
	 * @param emptyRes 布局资源
	 */
	public void addEmptyView(int emptyRes,ViewGroup viewGroup) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(emptyRes, viewGroup, false);
		mHeaderViews.put(EMPTY_ITEM + mHeaderViews.size(), view);
	}
}
