package com.sziti.counterfeittopnews.data;

import android.graphics.drawable.Drawable;

import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseItemData;

import java.util.List;

public class FocusContentData extends BaseItemData {
    public static final int VIDEO = 0;
    public static final int IMAGE = 1;
    public static final int ARTICLE = 2;
    //主要用在转载的文案中
    public static final int HEADER = 3;

    public static final int REPRINT_VIDEO = 4;
    public static final int REPRINT_IMAGE = 5;
    private int type;
    //文字介绍
    private String info;
    //视频 图片集合 文章 内容地址
    private String videoUrl;
    private List<String> imgUrl;
    private String article;

    private List<String> showImage;
    private List<Drawable> showImageDrawable;
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

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<String> getShowImage() {
        return showImage;
    }

    public void setShowImage(List<String> showImage) {
        this.showImage = showImage;
    }

    public List<Drawable> getShowImageDrawable() {
        return showImageDrawable;
    }

    public void setShowImageDrawable(List<Drawable> showImageDrawable) {
        this.showImageDrawable = showImageDrawable;
    }
}
