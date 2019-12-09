package com.sziti.counterfeittopnews.widget.TreeRecyclerView.wrapper;

import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.widget.NewsDetailView.SimpleLoadMoreView;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseRecyclerAdapter;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.LoadMoreView;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;

/**
 * Created by baozi on 2017/4/30.
 */

public class HeaderAndFootWrapper<T> extends BaseWrapper<T> {
	private static final int HEAD_ITEM = 1000;
	private static final int FOOT_ITEM = 2000;
	private static final int LOAD_MORE_ITEM = 4000;
	protected SparseArray<View> mHeaderViews = new SparseArray<>();
	protected SparseArray<View> mFootViews = new SparseArray<>();
	private LoadMoreView mLoadMoreView = null;
	private boolean headShow = true;
	private boolean footShow = true;

	public HeaderAndFootWrapper(BaseRecyclerAdapter<T> adapter) {
		super(adapter);
		mAdapter.addCheckItemInterfaces(new CheckItemInterface() {
			@Override
			public int checkPosition(int position) {
				return position - getHeadersCount();
			}
		});
	}
	@Override
	public T getData(int position) {
		return mAdapter.getData(position);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (mHeaderViews.get(viewType) != null) {
			return ViewHolder.createViewHolder(mHeaderViews.get(viewType));
		} else if (mFootViews.get(viewType) != null) {
			return ViewHolder.createViewHolder(mFootViews.get(viewType));
		}else if (mLoadMoreView != null){
			return ViewHolder.createViewHolder(parent,mLoadMoreView.getLayoutId());
		}
		return mAdapter.onCreateViewHolder(parent, viewType);
	}

	@Override
	public void onBindViewHolderClick(ViewHolder holder, View view) {
		int layoutPosition = holder.getLayoutPosition();
		if ((isHeaderViewPos(layoutPosition) || isFooterViewPos(layoutPosition))) {
			return;
		}
		super.onBindViewHolderClick(holder, view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		if (getItemViewType(position) == LOAD_MORE_ITEM) {
//			mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_DEFAULT);
			mLoadMoreView.convert(holder);
			return;
		}
		if (isHeaderViewPos(position) || isFooterViewPos(position)) {
			return;
		}
		mAdapter.onBindViewHolder(holder, position - getHeadersCount());
	}

	@Override
	public int getItemCount() {
		return getHeadersCount() + getFootersCount() + mAdapter.getItemCount() + (mLoadMoreView == null ? 0 : 1);
	}

	public int getItemSpanSize(int position) {
		if (isHeaderViewPos(position)) {
			return 0;
		}
		return mAdapter.getItemSpanSize(position);
	}

	@Override
	public int getItemViewType(int position) {
		if (isHeaderViewPos(position)) {
			return mHeaderViews.keyAt(position);
		} else if (isFooterViewPos(position)) {
			return mFootViews.keyAt(position - getHeadersCount() - mAdapter.getItemCount());
		} else if (position == getItemCount() - 1 && mLoadMoreView != null){
			return LOAD_MORE_ITEM;
		}
		return mAdapter.getItemViewType(position - getHeadersCount());
	}

	/**
	 * 添加加载更多view
	 *
	 * @param loadMoreView
	 * @param viewGroup
	 */
	public void addLoadMoreView(LoadMoreView loadMoreView, ViewGroup viewGroup) {
		this.mLoadMoreView = loadMoreView;
	}
	public void setLoadMoreSuccess(){
		if (mLoadMoreView != null) {
			mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_END);
			//刷新最后一条 即LoadMoreView所在位置
			notifyItemInserted(getItemCount() - 1);
		}
	}
	public void setLoadMoreFail(){
		if (mLoadMoreView != null){
			mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_FAIL);
			notifyItemInserted(getItemCount() - 1);
		}
	}
	public void setLoadMoreLoading(){
		if (mLoadMoreView != null){
			mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_LOADING);
			notifyItemChanged(getItemCount() - 1);
		}
	}

	public void addHeaderView(View view) {
		mHeaderViews.put(HEAD_ITEM + mHeaderViews.size(), view);
	}

	public void addFootView(View view) {
		mFootViews.put(FOOT_ITEM + mFootViews.size(), view);
	}

	private boolean isHeaderViewPos(int position) {
		return position < getHeadersCount();
	}

	/**
	 * 判断位置是否在footer范围中
	 * @param position
	 * @return
	 */
	private boolean isFooterViewPos(int position) {
		return position >= getHeadersCount() + mAdapter.getItemCount() && position < getItemCount() - 1;
	}

	public void setShowHeadView(boolean show) {
		this.headShow = show;
	}

	public int getHeadersCount() {
		if (!headShow) {
			return 0;
		}
		return mHeaderViews.size();
	}

	public int getFootersCount() {
		return mFootViews.size();
	}

}
