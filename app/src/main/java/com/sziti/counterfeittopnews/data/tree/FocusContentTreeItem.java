package com.sziti.counterfeittopnews.data.tree;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.data.FocusContentData;
import com.sziti.counterfeittopnews.data.ImageData;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerAdapter;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerType;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.factory.ItemHelperFactory;

import java.util.ArrayList;
import java.util.List;

public class FocusContentTreeItem extends TreeItem<FocusContentData> {
    TreeRecyclerAdapter treeRecyclerAdapter = null;

    @Override
    protected int initLayoutId() {
        switch (getData().getType()) {
            case FocusContentData.VIDEO:
                break;
            case FocusContentData.ARTICLE:
                return R.layout.item_focus_content_artical;
            case FocusContentData.IMAGE:
                return R.layout.item_focus_content_image;
            case FocusContentData.HEADER:
                return R.layout.item_focus_content_header;
            case FocusContentData.REPRINT_VIDEO:
                break;
            case FocusContentData.REPRINT_IMAGE:
                break;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        switch (getData().getType()) {
            case FocusContentData.HEADER:
                ((TextView) viewHolder.getView(R.id.item_focus_content_header_tv)).setText(getData().getInfo());
                break;
            case FocusContentData.VIDEO:
                break;
            case FocusContentData.ARTICLE:
                ((TextView) viewHolder.getView(R.id.item_focus_content_article_left)).setText(getData().getInfo());
                ((ImageView) viewHolder.getView(R.id.item_focus_content_article_right)).setImageDrawable(getData().getShowImageDrawable().get(0));
                break;
            case FocusContentData.IMAGE:
                RecyclerView rv = viewHolder.getView(R.id.item_focus_content_image_rv);
                rv.setLayoutManager(new GridLayoutManager(viewHolder.itemView.getContext(), 3));
                treeRecyclerAdapter = new TreeRecyclerAdapter(TreeRecyclerType.SHOW_ALL);
                rv.setAdapter(treeRecyclerAdapter);

                List<ImageData> list = new ArrayList<>();
                for (int i = 0; i < getData().getShowImageDrawable().size(); i++) {
                    ImageData imageData = new ImageData();
                    imageData.setShowImage(getData().getShowImageDrawable().get(i));
                    imageData.setSpanSize(1);
                    list.add(imageData);
                }
                treeRecyclerAdapter.setDatas(ItemHelperFactory.createTreeItemList(list, ImageItem.class, null));
                treeRecyclerAdapter.notifyDataSetChanged();
                break;
        }
    }
}
