package com.sziti.counterfeittopnews.http.Response;

import com.google.gson.Gson;

import java.util.List;

public class NewsResponse {
    private int login_status;
    private int total_number;
    private boolean has_more;
    private String post_content_hint;
    private int show_et_status;
    private int feed_flag;
    private int action_to_last_stick;
    private String message;
    private boolean has_more_to_refresh;
    private TipEntity tips;
    private List<NewsData> data;
    private List<News> news;
    private int getLogin_status() {
        return login_status;
    }

    public void setLogin_status(int login_status) {
        this.login_status = login_status;
    }

    public int getTotal_number() {
        return total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public String getPost_content_hint() {
        return post_content_hint;
    }

    public void setPost_content_hint(String post_content_hint) {
        this.post_content_hint = post_content_hint;
    }

    public int getShow_et_status() {
        return show_et_status;
    }

    public void setShow_et_status(int show_et_status) {
        this.show_et_status = show_et_status;
    }

    public int getFeed_flag() {
        return feed_flag;
    }

    public void setFeed_flag(int feed_flag) {
        this.feed_flag = feed_flag;
    }

    public int getAction_to_last_stick() {
        return action_to_last_stick;
    }

    public void setAction_to_last_stick(int action_to_last_stick) {
        this.action_to_last_stick = action_to_last_stick;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isHas_more_to_refresh() {
        return has_more_to_refresh;
    }

    public void setHas_more_to_refresh(boolean has_more_to_refresh) {
        this.has_more_to_refresh = has_more_to_refresh;
    }

    public TipEntity getTips() {
        return tips;
    }

    public void setTips(TipEntity tips) {
        this.tips = tips;
    }

    public List<NewsData> getData() {
        return data;
    }

    public void setData(List<NewsData> data) {
        this.data = data;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
