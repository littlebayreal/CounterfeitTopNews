package com.sziti.counterfeittopnews.data;

import android.view.View;

import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseItemData;

import java.util.List;

public class ReportDialogData extends BaseItemData {

    private String showOption;
    private String showInfo;
    private List<ReportDialogItemData> showSubOption;
    public String getShowOption() {
        return showOption;
    }

    public void setShowOption(String showOption) {
        this.showOption = showOption;
    }

    public String getShowInfo() {
        return showInfo;
    }

    public void setShowInfo(String showInfo) {
        this.showInfo = showInfo;
    }

    public List<ReportDialogItemData> getShowSubOption() {
        return showSubOption;
    }

    public void setShowSubOption(List<ReportDialogItemData> showSubOption) {
        this.showSubOption = showSubOption;
    }

    public class ReportDialogItemData extends BaseItemData{
        public static final int TYPE_TITLE = 0;
        public static final int TYPE_CONTENT = 1;
        private int type;
        private String showSubOption;
        private View.OnClickListener onClickListener;
        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getShowSubOption() {
            return showSubOption;
        }

        public void setShowSubOption(String showSubOption) {
            this.showSubOption = showSubOption;
        }

        public View.OnClickListener getOnClickListener() {
            return onClickListener;
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }
    }
}
