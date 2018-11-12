package com.sziti.counterfeittopnews.data.tree;

import android.widget.TextView;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.data.FocusTitleData;
import com.sziti.counterfeittopnews.widget.CircleImageView;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;

public class FocusTitleTreeItem extends TreeItem<FocusTitleData> {
    @Override
    protected int initLayoutId() {
        return R.layout.item_focus_title;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        ((CircleImageView)viewHolder.getView(R.id.item_focus_title_header)).setImageDrawable(getData().getHeadDrawable());
        ((TextView)viewHolder.getView(R.id.item_focus_title_author)).setText(getData().getAuthor());
        ((TextView)viewHolder.getView(R.id.item_focus_title_base_info)).setText(getData().getBaseInfo());
        ((TextView)viewHolder.getView(R.id.item_focus_title_illustration)).setText(getData().getIllustration());
    }
}
