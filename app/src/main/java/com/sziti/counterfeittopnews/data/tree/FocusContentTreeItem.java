package com.sziti.counterfeittopnews.data.tree;

import android.widget.ImageView;
import android.widget.TextView;

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
            case FocusContentData.ARTICLE:
                return R.layout.item_focus_content_artical;
            case FocusContentData.IMAGE:
                return R.layout.item_focus_content_image;
            case FocusContentData.Header:
                return R.layout.item_focus_content_header;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        switch (getData().getType()) {
            case FocusContentData.Header:
                ((TextView)viewHolder.getView(R.id.item_focus_content_header_tv)).setText(getData().getInfo());
                break;
            case FocusContentData.VIDEO:
                break;
            case FocusContentData.ARTICLE:
                ((TextView) viewHolder.getView(R.id.item_focus_content_article_left)).setText(getData().getInfo());
                ((ImageView) viewHolder.getView(R.id.item_focus_content_article_right)).setImageDrawable(getData().getShowImageDrawable());
                break;
            case FocusContentData.IMAGE:
                ((ImageView) viewHolder.getView(R.id.item_focus_content_image_iv)).setImageDrawable(getData().getShowImageDrawable());
                break;
        }
    }
}
