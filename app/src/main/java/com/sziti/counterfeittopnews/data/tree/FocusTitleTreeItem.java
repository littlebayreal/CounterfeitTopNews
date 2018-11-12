package com.sziti.counterfeittopnews.data.tree;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.data.FocusTitleData;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;

public class FocusTitleTreeItem extends TreeItem<FocusTitleData> {
    @Override
    protected int initLayoutId() {
        return R.layout.item_focus_title;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {

    }
}
