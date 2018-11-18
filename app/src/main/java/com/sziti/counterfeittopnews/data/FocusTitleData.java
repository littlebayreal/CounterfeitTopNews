package com.sziti.counterfeittopnews.data;

import android.graphics.drawable.Drawable;

import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseItemData;

import java.util.List;

public class FocusTitleData extends BaseItemData {
    private String headUrl;
    private Drawable headDrawable;
    private String author;
    private String baseInfo;
    //内容的简介
    private String illustration;
    private FocusContentData focusContentData;
    private FocusBottomData focusBottomData;
    private DeleteOption deleteOption;

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

    public FocusContentData getFocusContentData() {
        return focusContentData;
    }

    public void setFocusContentData(FocusContentData focusContentData) {
        this.focusContentData = focusContentData;
    }

    public FocusBottomData getFocusBottomData() {
        return focusBottomData;
    }

    public void setFocusBottomData(FocusBottomData focusBottomData) {
        this.focusBottomData = focusBottomData;
    }

    public DeleteOption getDeleteOption() {
        return deleteOption;
    }

    public void setDeleteOption(DeleteOption deleteOption) {
        this.deleteOption = deleteOption;
    }

    //删除按钮的选项
    public class DeleteOption {
        //不感兴趣
        public static final int DELETE_NO_INTEREST = 0;
        //低俗色情
        public static final int DELETE_RESPONSE_SEX = 0;
        //标题党
        public static final int DELETE_RESPONSE_TITLE = 0;
        //内容不实
        public static final int DELETE_RESPONSE_FAKER = 0;
        //旧闻重复
        public static final int DELETE_RESPONSE_REPEAT = 0;
        //垃圾内容
        public static final int DELETE_RESPONSE_TRUSH = 0;
        //显示的删除条目
        private String[] showOption;
        //显示删除条目的详细说明
        private String[] showInfo;
        //显示二级菜单
        private String[][] showSubOption;

        public void setShowOption(String[] showOption) {
            this.showOption = showOption;
        }

        public void setShowInfo(String[] showInfo) {
            this.showInfo = showInfo;
        }

        public String[] getShowOption() {
            return showOption;
        }

        public String[] getShowInfo() {
            return showInfo;
        }

        public String[][] getShowSubOption() {
            return showSubOption;
        }

        public void setShowSubOption(String[][] showSubOption) {
            this.showSubOption = showSubOption;
        }
    }
}
