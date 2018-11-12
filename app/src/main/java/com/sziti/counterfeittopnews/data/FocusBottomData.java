package com.sziti.counterfeittopnews.data;

import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseItemData;

public class FocusBottomData extends BaseItemData {
    private int reprintTotal;
    private int messageTotal;
    private int complimentTotal;

    public int getReprintTotal() {
        return reprintTotal;
    }

    public void setReprintTotal(int reprintTotal) {
        this.reprintTotal = reprintTotal;
    }

    public int getMessageTotal() {
        return messageTotal;
    }

    public void setMessageTotal(int messageTotal) {
        this.messageTotal = messageTotal;
    }

    public int getComplimentTotal() {
        return complimentTotal;
    }

    public void setComplimentTotal(int complimentTotal) {
        this.complimentTotal = complimentTotal;
    }
}
