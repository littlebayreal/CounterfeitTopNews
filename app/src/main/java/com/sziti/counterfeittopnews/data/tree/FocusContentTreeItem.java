package com.sziti.counterfeittopnews.data.tree;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.data.FocusContentData;
import com.sziti.counterfeittopnews.data.ImageData;
import com.sziti.counterfeittopnews.video.base.JZDataSource;
import com.sziti.counterfeittopnews.video.base.Jzvd;
import com.sziti.counterfeittopnews.video.base.JzvdStd;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerAdapter;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerType;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.factory.ItemHelperFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FocusContentTreeItem extends TreeItem<FocusContentData> {
    @Override
    protected int initLayoutId() {
        switch (getData().getType()) {
            case FocusContentData.VIDEO:
                return R.layout.item_focus_content_video;
            case FocusContentData.ARTICLE:
                return R.layout.item_focus_content_artical;
            case FocusContentData.IMAGE:
                return R.layout.item_focus_content_image;
            case FocusContentData.HEADER:
                return R.layout.item_focus_content_header;
            case FocusContentData.REPRINT_VIDEO:
                break;
            case FocusContentData.REPRINT_IMAGE:
                return R.layout.item_focus_reprint_image;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        RecyclerView rv = null;
        TreeRecyclerAdapter treeRecyclerAdapter = null;
        switch (getData().getType()) {
            case FocusContentData.HEADER:
                ((TextView) viewHolder.getView(R.id.item_focus_content_header_tv)).setText(getData().getInfo());
                break;
            case FocusContentData.VIDEO:
                //点击播放的视频
                ((JzvdStd)viewHolder.getView(R.id.item_focus_content_jzvdstd)).setUp(getData().getVideoUrl(),getData().getInfo(),Jzvd.SCREEN_WINDOW_LIST);
                ((JzvdStd)viewHolder.getView(R.id.item_focus_content_jzvdstd)).thumbImageView.setImageDrawable(getData().getShowImageDrawable().get(0));
                break;
            case FocusContentData.ARTICLE:
                ((TextView) viewHolder.getView(R.id.item_focus_content_article_left)).setText(getData().getInfo());
                ((ImageView) viewHolder.getView(R.id.item_focus_content_article_right)).setImageDrawable(getData().getShowImageDrawable().get(0));
                break;
            case FocusContentData.IMAGE:
                rv = viewHolder.getView(R.id.item_focus_content_image_rv);
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
                break;
            case FocusContentData.REPRINT_IMAGE:
                rv = viewHolder.getView(R.id.item_focus_reprint_image_rv);
                rv.setLayoutManager(new GridLayoutManager(viewHolder.itemView.getContext(), 3));
                treeRecyclerAdapter = new TreeRecyclerAdapter(TreeRecyclerType.SHOW_ALL);
                rv.setAdapter(treeRecyclerAdapter);

                List<ImageData> imageDataList = new ArrayList<>();
                for (int i = 0; i < getData().getShowImageDrawable().size(); i++) {
                    ImageData imageData = new ImageData();
                    imageData.setShowImage(getData().getShowImageDrawable().get(i));
                    imageData.setSpanSize(1);
                    imageDataList.add(imageData);
                }
                treeRecyclerAdapter.setDatas(ItemHelperFactory.createTreeItemList(imageDataList, ImageItem.class, null));
                break;
        }
    }
}
