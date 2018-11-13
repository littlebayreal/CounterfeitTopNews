package com.sziti.counterfeittopnews.data.tree;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.data.FocusHorizonImageData;
import com.sziti.counterfeittopnews.widget.HorizonRecyclerView;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerAdapter;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.factory.ItemHelperFactory;

import java.util.List;

public class FocusHorizonCardItem extends TreeItem<FocusHorizonImageData>{
    private HorizonRecyclerView rv;
    private TreeRecyclerAdapter treeRecyclerAdapter;
    @Override
    protected int initLayoutId() {
        Log.e("vvv","我被夹在了");
        return R.layout.item_focus_horizon;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        rv = viewHolder.getView(R.id.item_focus_horizon_rv);
        rv.setLayoutManager(new LinearLayoutManager(viewHolder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        if (treeRecyclerAdapter == null)
        treeRecyclerAdapter = new TreeRecyclerAdapter();
        rv.setAdapter(treeRecyclerAdapter);
        List<TreeItem> list = ItemHelperFactory.createFileTreeItemList(getData().getList(),null);

        treeRecyclerAdapter.setDatas(list);
        treeRecyclerAdapter.notifyDataSetChanged();
    }
}
