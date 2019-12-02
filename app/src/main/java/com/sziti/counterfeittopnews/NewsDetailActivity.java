package com.sziti.counterfeittopnews;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sziti.counterfeittopnews.base.BaseActivity;

public class NewsDetailActivity extends BaseActivity{
    public static final String DETAIL_URL = "detail_url";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
    }
}
