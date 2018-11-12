package com.sziti.counterfeittopnews.data;

import android.graphics.drawable.Drawable;

import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseItemData;

public class FocusTitleData extends BaseItemData {
     private String headUrl;
     private Drawable headDrawable;
     private String author;
     private String baseInfo;
     //内容的简介
     private String illustration;
     public String getHeadUrl() {
         return headUrl;
     }

     public void setHeadUrl(String headUrl) {
         this.headUrl = headUrl;
     }

    public Drawable getHeadDrawable() {
        return headDrawable;
    }

    public void setHeadDrawable(Drawable headDrawable) {
        this.headDrawable = headDrawable;
    }

    public String getAuthor() {
         return author;
     }

     public void setAuthor(String author) {
         this.author = author;
     }

     public String getBaseInfo() {
         return baseInfo;
     }

     public void setBaseInfo(String baseInfo) {
         this.baseInfo = baseInfo;
     }

     public String getIllustration() {
         return illustration;
     }

     public void setIllustration(String illustration) {
         this.illustration = illustration;
     }
 }
