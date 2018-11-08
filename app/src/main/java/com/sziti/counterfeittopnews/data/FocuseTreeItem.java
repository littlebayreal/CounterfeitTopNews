package com.sziti.counterfeittopnews.data;

import android.util.Log;
import android.widget.TextView;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;

public class FocuseTreeItem extends TreeItem<FocuseData> {
    @Override
    protected int initLayoutId() {
        Log.e("aaaa","创建布局");
        return R.layout.item_home_focuse;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        ((TextView)viewHolder.getView(R.id.item_home_focuse_txt)).setText(getData().getName());
    }
}
