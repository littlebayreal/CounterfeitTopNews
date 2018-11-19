package com.sziti.counterfeittopnews.data.tree;

import android.view.View;
import android.widget.TextView;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.data.ReportDialogData;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseItemData;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerType;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.factory.ItemHelperFactory;

import java.util.List;

public class ReportDialogItemTree extends TreeItem<ReportDialogData.ReportDialogItemData> {
    @Override
    protected int initLayoutId() {
        switch (getData().getType()) {
            case ReportDialogData.ReportDialogItemData.TYPE_TITLE:
                return R.layout.sub_title_report_dialog;
            case ReportDialogData.ReportDialogItemData.TYPE_CONTENT:
                return R.layout.sub_item_report_dialog;
        }
        return R.layout.sub_item_report_dialog;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        switch (getData().getType()) {
            case ReportDialogData.ReportDialogItemData.TYPE_TITLE:
                viewHolder.getView(R.id.sub_title_report_dialog_return).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (getData().getOnClickListener() != null)
                            getData().getOnClickListener().onClick(view);
                    }
                });
                ((TextView) viewHolder.getView(R.id.sub_title_report_dialog_tv)).setText(getData().getShowSubOption());
                break;
            case ReportDialogData.ReportDialogItemData.TYPE_CONTENT:
                ((TextView) viewHolder.getView(R.id.sub_item_report_dialog_tv)).setText(getData().getShowSubOption());
                break;
        }
    }
}
