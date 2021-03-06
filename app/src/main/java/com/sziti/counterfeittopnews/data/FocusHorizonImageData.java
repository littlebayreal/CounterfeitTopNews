package com.sziti.counterfeittopnews.data;

import android.graphics.drawable.Drawable;

import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseItemData;

import java.util.ArrayList;
import java.util.List;

public class FocusHorizonImageData extends BaseItemData {
    private List<FocusHorizonImageItemData> list = new ArrayList<>();

    public List<FocusHorizonImageItemData> getList() {
        return list;
    }

    public void setList(List<FocusHorizonImageItemData> list) {
        this.list = list;
    }

    public class FocusHorizonImageItemData extends BaseItemData{
        public static final int HorizonImageCard = 0;
        public static final int HorizonUserCard = 1;
        private String headerUrl;
        private Drawable headerDrawable;
        private String userName;
        private String illustration;
        private int HorizontType;
        public String getHeaderUrl() {
            return headerUrl;
        }

        public void setHeaderUrl(String headerUrl) {
            this.headerUrl = headerUrl;
        }

        public Drawable getHeaderDrawable() {
            return headerDrawable;
        }

        public void setHeaderDrawable(Drawable headerDrawable) {
            this.headerDrawable = headerDrawable;
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

        public int getHorizontType() {
            return HorizontType;
        }

        public void setHorizontType(int horizontType) {
            HorizontType = horizontType;
        }
    }
}
