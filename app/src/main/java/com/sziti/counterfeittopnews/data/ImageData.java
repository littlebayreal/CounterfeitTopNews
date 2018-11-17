package com.sziti.counterfeittopnews.data;

import android.graphics.drawable.Drawable;

import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseItemData;

public class ImageData extends BaseItemData {
    private String imageUrl;
    private Drawable showImage;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Drawable getShowImage() {
        return showImage;
    }

    public void setShowImage(Drawable showImage) {
        this.showImage = showImage;
    }
}
