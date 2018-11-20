package com.sziti.counterfeittopnews.data.tree;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.data.ReportDialogData;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItemGroup;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItemReplaceGroup;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.factory.ItemHelperFactory;

import java.util.ArrayList;
import java.util.List;

public class ReportDialogItemGroup extends TreeItemReplaceGroup<ReportDialogData> {
    @Override
    protected List<TreeItem> initChildList(ReportDialogData data) {
        if (data.getShowSubOption() == null || data.getShowSubOption().size() <= 0) {
            this.setCanExpand(false);
            return new ArrayList<>();
        }
        //初始化子菜单
        List<ReportDialogData.ReportDialogItemData> list = new ArrayList();
        for (ReportDialogData.ReportDialogItemData s : data.getShowSubOption()) {
            list.add(s);
        }
        List<TreeItem> treeItems = ItemHelperFactory.createTreeItemObjectList(list, ReportDialogItemTree.class, this);
        this.setCanExpand(true);
        return treeItems;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.item_report_dialog;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        ((TextView) viewHolder.getView(R.id.item_report_dialog_show_option)).setText(getData().getShowOption());
        ((TextView) viewHolder.getView(R.id.item_report_dialog_show_info)).setText(getData().getShowInfo());
        if (TextUtils.isEmpty(getData().getShowInfo())) {
            (viewHolder.getView(R.id.item_report_dialog_show_info)).setVisibility(View.GONE);
        } else
            (viewHolder.getView(R.id.item_report_dialog_show_info)).setVisibility(View.VISIBLE);

        if (getData().getShowSubOption() != null && getData().getShowSubOption().size() > 0)
            (viewHolder.getView(R.id.item_report_dialog_show_right)).setVisibility(View.VISIBLE);
        else
            (viewHolder.getView(R.id.item_report_dialog_show_right)).setVisibility(View.INVISIBLE);
    }
}
