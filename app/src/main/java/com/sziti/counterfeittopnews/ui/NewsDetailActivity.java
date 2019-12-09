package com.sziti.counterfeittopnews.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import com.github.nukc.stateview.StateView;
import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.http.Response.NewsDetail;
import com.sziti.counterfeittopnews.http.Response.ResultResponse;
import com.sziti.counterfeittopnews.http.RetrofitClient;
import com.sziti.counterfeittopnews.widget.NewsDetailView.NewsDetailHeaderView;
import com.sziti.counterfeittopnews.widget.NewsDetailView.SimpleLoadMoreView;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerAdapter;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.wrapper.NewsDetailWrapper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsDetailActivity extends AppCompatActivity {
    public static final String DETAIL_URL = "http://m.toutiao.com/i6412427713050575361/info/";
    //新闻详情页的头部内容 包含新闻主体 通过评论RecyclerView进行绑定显示
    private NewsDetailHeaderView header;
    //评论
    private RecyclerView commentRv;
    //评论适配器
	private TreeRecyclerAdapter mTreeRa;
    //页面状态显示
    private StateView mStateView;
    private EditText mEditText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        initView();
        initData();
        initListener();
        http();
    }

	private void initListener() {
		mEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void afterTextChanged(Editable editable) {
				Log.i("ccc",editable.toString());
			}
		});
	}

	private void http() {
    	mStateView.showLoading();
		RetrofitClient.getInstance().getApiService().getNewsDetail(DETAIL_URL)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Subscriber<ResultResponse<NewsDetail>>() {
				@Override
				public void onCompleted() {

				}

				@Override
				public void onError(Throwable e) {
				}

				@Override
				public void onNext(ResultResponse<NewsDetail> newsDetailResultResponse) {
                     if (newsDetailResultResponse.isSuccess()){
                        header.setDetail(newsDetailResultResponse.getData(), new NewsDetailHeaderView.LoadWebListener() {
							@Override
							public void onLoadFinished() {
								//网页加载成功后隐藏状态  显示页面内容
								mStateView.showContent();

								commentRv.setLayoutManager(new GridLayoutManager(NewsDetailActivity.this,1));
								NewsDetailWrapper<TreeItem> headerAndFootWrapper = new NewsDetailWrapper(mTreeRa);
								headerAndFootWrapper.addHeaderView(header);

								headerAndFootWrapper.addEmptyView(R.layout.pager_no_comment,commentRv);

								headerAndFootWrapper.addLoadMoreView(new SimpleLoadMoreView(),commentRv);
								//使用linearlayoutManager会导致webview不能够完整显示 切记切记
//								commentRv.setLayoutManager(new LinearLayoutManager(NewsDetailActivity.this, LinearLayoutManager.VERTICAL,false));
								commentRv.setAdapter(headerAndFootWrapper);

								headerAndFootWrapper.setLoadMoreSuccess();
							}
						});
					 }else{
                     	mStateView.showRetry();
					 }
				}
			});
	}

	private void initData() {
		mTreeRa = new TreeRecyclerAdapter();
	}

	private void initView() {
		//显示当前网页状态
		mStateView = StateView.inject(findViewById(R.id.fl_content));
		commentRv = findViewById(R.id.activity_news_detail_rv);
		header = new NewsDetailHeaderView(this);

		mEditText = findViewById(R.id.write_comment);
	}
}
