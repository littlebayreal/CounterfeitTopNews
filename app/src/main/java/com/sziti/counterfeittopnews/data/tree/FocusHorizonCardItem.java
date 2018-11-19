package com.sziti.counterfeittopnews.data.tree;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.data.FocusHorizonImageData;
import com.sziti.counterfeittopnews.widget.HorizonRecyclerView;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerAdapter;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerType;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.factory.ItemHelperFactory;

import java.util.List;

public class FocusHorizonCardItem extends TreeItem<FocusHorizonImageData> {
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
        if (rv == null) {
            rv = viewHolder.getView(R.id.item_focus_horizon_rv);
            rv.setNestedScrollingEnabled(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(viewHolder.itemView.getContext(), GridLayoutManager.HORIZONTAL, false);
            rv.setLayoutManager(layoutManager);
        }
        if (treeRecyclerAdapter == null) {
            treeRecyclerAdapter = new TreeRecyclerAdapter(TreeRecyclerType.SHOW_ALL);
            rv.setAdapter(treeRecyclerAdapter);
        }
        if (rv != null && treeRecyclerAdapter != null) {
//            if(viewHolder.getLayoutPosition() == 0){
//                treeRecyclerAdapter = new TreeRecyclerAdapter(TreeRecyclerType.SHOW_ALL);
//                rv.setAdapter(treeRecyclerAdapter);
//            }
            Log.e("zoo","position:"+ viewHolder.getLayoutPosition());
//            Log.e("zoo","horizontype:"+ getData().getList().get(0).getHorizontType());
            List<TreeItem> list = ItemHelperFactory.createTreeItemList(getData().getList(), ItemHorizonCard.class, null);
//            Log.e("zoo","horizontype:"+ ((FocusHorizonImageData.FocusHorizonImageItemData)list.get(0).getData()).getHorizontType());
            if (viewHolder.getLayoutPosition() == 0){
                for (TreeItem t:list){
                    ((FocusHorizonImageData.FocusHorizonImageItemData)t.getData()).setHorizontType(1);
                }
            }
            treeRecyclerAdapter.setDatas(list);
            treeRecyclerAdapter.notifyDataSetChanged();
        }
    }
}
