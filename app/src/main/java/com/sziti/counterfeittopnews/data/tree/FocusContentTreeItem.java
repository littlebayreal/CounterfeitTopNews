package com.sziti.counterfeittopnews.data.tree;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.data.FocusContentData;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;

public class FocusContentTreeItem extends TreeItem<FocusContentData> {
    @Override
    protected int initLayoutId() {
        switch (getData().getType()) {
            case FocusContentData.VIDEO:
                break;
            case FocusContentData.ARTICAL:
                return R.layout.item_focus_content_artical;
            case FocusContentData.IMAGE:
                break;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {

    }
}
