package com.sziti.counterfeittopnews.data.tree;

import android.widget.ImageView;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.data.FocusHorizonImageData;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;

public class ItemHorizonCard extends TreeItem<FocusHorizonImageData.FocusHorizonImageItemData> {
    @Override
    protected int initLayoutId() {
        return R.layout.item_horizon_card;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        ((ImageView)viewHolder.getView(R.id.item_horizon_card_iv)).setImageDrawable(getData().getHeaderDrawable());
    }
}
