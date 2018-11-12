package com.sziti.counterfeittopnews.data;

import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseItemData;

public class FocusContentData extends BaseItemData {
    public static final int VIDEO = 0;
    public static final int IMAGE = 1;
    public static final int ARTICAL = 2;
    private int type;
    private String videoUrl;
    private String imgUrl;
    private String ArTICAL;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getArTICAL() {
        return ArTICAL;
    }

    public void setArTICAL(String arTICAL) {
        ArTICAL = arTICAL;
    }
}
