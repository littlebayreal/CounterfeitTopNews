package com.sziti.counterfeittopnews.data.tree;

import android.util.Log;
import android.widget.TextView;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.data.FocusBottomData;
import com.sziti.counterfeittopnews.data.FocusContentData;
import com.sziti.counterfeittopnews.data.FocusTitleData;
import com.sziti.counterfeittopnews.widget.CircleImageView;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItemGroup;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.factory.ItemHelperFactory;

import java.util.ArrayList;
import java.util.List;

public class FocusTitleTreeItem extends TreeItemGroup<FocusTitleData> {
    @Override
    protected List<TreeItem> initChildList(FocusTitleData data) {
        Log.e("bbb","出事了啊");
        //进行子类 内容 以及 底部的数据初始化
        List<Object> list = new ArrayList();
        list.add(data.getFocusContentData());
        List<TreeItem> treeItems = ItemHelperFactory.createFileTreeItemList(list, this);
        list.clear();
        list.add(data.getFocusBottomData());
        treeItems.addAll(ItemHelperFactory.createFileTreeItemList(list, this));
        return treeItems;
    }
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
