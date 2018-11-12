package com.sziti.counterfeittopnews.data;

import android.graphics.drawable.Drawable;

import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseItemData;

public class FocusContentData extends BaseItemData {
    public static final int VIDEO = 0;
    public static final int IMAGE = 1;
    public static final int ARTICLE = 2;
    //主要用在转载的文案中
    public static final int Header = 3;
    private int type;
    //文字介绍
    private String info;
    //视频 图片 文章 内容地址
    private String videoUrl;
    private String imgUrl;
    private String article;

    private String showImage;
    private Drawable showImageDrawable;
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getShowImage() {
        return showImage;
    }

    public void setShowImage(String showImage) {
        this.showImage = showImage;
    }

    public Drawable getShowImageDrawable() {
        return showImageDrawable;
    }

    public void setShowImageDrawable(Drawable showImageDrawable) {
        this.showImageDrawable = showImageDrawable;
    }
}
