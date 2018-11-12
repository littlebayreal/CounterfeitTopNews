package com.sziti.counterfeittopnews.data.tree;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.data.FocusHorizonImageData;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;

public class FocusHorizonCardItem extends TreeItem<FocusHorizonImageData>{
    @Override
    protected int initLayoutId() {
        return R.layout.item_focus_horizon;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {

    }
}
