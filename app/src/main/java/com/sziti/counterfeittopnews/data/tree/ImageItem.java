package com.sziti.counterfeittopnews.data.tree;
import android.widget.ImageView;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.data.ImageData;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;

public class ImageItem extends TreeItem<ImageData>{
    @Override
    protected int initLayoutId() {
        return R.layout.image;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        ((ImageView)viewHolder.getView(R.id.image_iv)).setImageDrawable(getData().getShowImage());
    }
}
