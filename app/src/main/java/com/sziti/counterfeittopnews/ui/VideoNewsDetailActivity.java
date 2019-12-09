package com.sziti.counterfeittopnews.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.nukc.stateview.StateView;
import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.widget.NewsDetailView.NewsDetailHeaderView;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerAdapter;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.wrapper.HeaderAndFootWrapper;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.wrapper.NewsDetailWrapper;

public class VideoNewsDetailActivity extends AppCompatActivity {
	private NewsDetailHeaderView mNewsDetailHeaderView;
	private RecyclerView mRecyclerView;
	private NewsDetailWrapper<TreeItem> mHeaderAndFootWrapper;
	private StateView mStateView;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_detail);

		initView();
		initData();
	}

	private void initData() {
		mHeaderAndFootWrapper = new NewsDetailWrapper<>(new TreeRecyclerAdapter());
		mRecyclerView.setLayoutManager(new GridLayoutManager(VideoNewsDetailActivity.this,1));
		mHeaderAndFootWrapper.addHeaderView(mNewsDetailHeaderView);
		mHeaderAndFootWrapper.addEmptyView(R.layout.pager_no_comment,mRecyclerView);
	}

	private void initView() {
		mStateView = StateView.inject(findViewById(R.id.fl_content));
		mNewsDetailHeaderView = new NewsDetailHeaderView(this);
        mRecyclerView = findViewById(R.id.rv_comment);
	}
}
