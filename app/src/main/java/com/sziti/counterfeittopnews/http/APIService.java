package com.sziti.counterfeittopnews.http;

import com.sziti.counterfeittopnews.http.Response.NewsDetail;
import com.sziti.counterfeittopnews.http.Response.NewsResponse;
import com.sziti.counterfeittopnews.http.Response.ResultResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface APIService {
    String GET_ARTICLE_LIST = "api/news/feed/v62/?refer=1&count=20&loc_mode=4&device_id=34960436458&iid=13136511752";
    String GET_COMMENT_LIST = "article/v2/tab_comments/";
    /**
     * 获取新闻列表
     *
     * @param category 频道
     * @return
     */
    @GET(GET_ARTICLE_LIST)
    Observable<NewsResponse> getNewsList(@Query("category") String category, @Query("min_behot_time") long lastTime, @Query("last_refresh_sub_entrance_interval") long currentTime);
	/**
	 * 获取新闻详情
	 */
	@GET
	Observable<ResultResponse<NewsDetail>> getNewsDetail(@Url String url);
}
