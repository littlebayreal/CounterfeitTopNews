package com.sziti.counterfeittopnews.data.tree;

import android.view.View;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.data.FocusBottomData;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;

public class FocusBottomTreeItem extends TreeItem<FocusBottomData> {
    @Override
    protected int initLayoutId() {
        return R.layout.item_focus_bottom;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
       viewHolder.getView(R.id.compliment).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               getData().getLikeListener().onClick(view);
           }
       });
    }
}
