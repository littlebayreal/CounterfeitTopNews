package com.sziti.counterfeittopnews.data;

import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseItemData;

public class FocusHorizonImageData extends BaseItemData {
    private String headerUrl;
    private String userName;
    private String illustration;

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIllustration() {
        return illustration;
    }

    public void setIllustration(String illustration) {
        this.illustration = illustration;
    }
}
