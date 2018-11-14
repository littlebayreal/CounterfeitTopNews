package com.sziti.counterfeittopnews.data.tree;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        return R.layout.item_focus_horizon;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        TextView tv = viewHolder.getView(R.id.item_focus_horizon_tv);
        ImageView iv = viewHolder.getImageView(R.id.item_focus_horizon_delete);
        if (getData().getList().get(0).getHorizontType() == FocusHorizonImageData.FocusHorizonImageItemData.HorizonUserCard) {
            tv.setVisibility(View.GONE);
            iv.setVisibility(View.GONE);
        }
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
