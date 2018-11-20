package com.sziti.counterfeittopnews.data.tree;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.data.FocusTitleData;
import com.sziti.counterfeittopnews.data.ReportDialogData;
import com.sziti.counterfeittopnews.util.ScreenUtils;
import com.sziti.counterfeittopnews.widget.CircleImageView;
import com.sziti.counterfeittopnews.widget.ReportDialog;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseRecyclerAdapter;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerAdapter;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerType;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItemGroup;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.factory.ItemHelperFactory;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.manager.ItemManager;

import java.util.ArrayList;
import java.util.List;

public class FocusTitleTreeItem extends TreeItemGroup<FocusTitleData> {
    private ReportDialogItemGroup old;
    private List<TreeItem> ll;
    @Override
    protected List<TreeItem> initChildList(FocusTitleData data) {
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
    public void onBindViewHolder(final ViewHolder viewHolder) {
        ((CircleImageView) viewHolder.getView(R.id.item_focus_title_header)).setImageDrawable(getData().getHeadDrawable());
        ((TextView) viewHolder.getView(R.id.item_focus_title_author)).setText(getData().getAuthor());
        ((TextView) viewHolder.getView(R.id.item_focus_title_base_info)).setText(getData().getBaseInfo());
        ((TextView) viewHolder.getView(R.id.item_focus_title_illustration)).setText(getData().getIllustration());


        (viewHolder.getView(R.id.item_focus_title_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int screenWidth = ScreenUtils.getScreenWidth(viewHolder.itemView.getContext());
                int heightpx = ScreenUtils.dp2px(viewHolder.itemView.getContext(),getData().getDeleteOption().getShowOption().length * 60);
                final ReportDialog reportDialog = new ReportDialog.Builder(viewHolder.itemView.getContext())
                        .view(R.layout.dialog_report).style(R.style.report_dialog)
                        .offsetpx(viewHolder.getView(R.id.item_focus_title_delete))
                        .heightpx(heightpx)
                        .widthpx(screenWidth - (screenWidth - view.getRight()))
                        .cancelTouchout(true)
                        .build();
                reportDialog.show();

                RecyclerView rv = reportDialog.getView(R.id.dialog_report_rv);
                rv.setLayoutManager(new LinearLayoutManager(viewHolder.itemView.getContext()));
                final TreeRecyclerAdapter treeRecyclerAdapter = new TreeRecyclerAdapter();
                rv.setAdapter(treeRecyclerAdapter);
                final List<ReportDialogData> list = new ArrayList<>();
                //初始化删除选项
                for (int i = 0; i < getData().getDeleteOption().getShowOption().length;i++) {
                    ReportDialogData dialogData = new ReportDialogData();
                    dialogData.setShowOption(getData().getDeleteOption().getShowOption()[i]);
                    dialogData.setShowInfo(getData().getDeleteOption().getShowInfo()[i]);
                    if (getData().getDeleteOption().getShowSubOption()[i] != null && getData().getDeleteOption().getShowSubOption()[i].length > 0) {
                        List<ReportDialogData.ReportDialogItemData> dataList = new ArrayList<>();
                        ReportDialogData.ReportDialogItemData reportDialogItemData = dialogData.new ReportDialogItemData();
                        reportDialogItemData.setShowSubOption(dialogData.getShowOption());
                        reportDialogItemData.setType(ReportDialogData.ReportDialogItemData.TYPE_TITLE);
                        //返回按钮
                        reportDialogItemData.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ll = ItemHelperFactory.createTreeItemList(list, ReportDialogItemGroup.class, null);
                                treeRecyclerAdapter.setDatas(ll);
                                treeRecyclerAdapter.notifyDataSetChanged();
                                int heightpx = ScreenUtils.dp2px(viewHolder.itemView.getContext(), ll.size() * 60);
                                reportDialog.setDialogHeight(heightpx);
                            }
                        });
                        dataList.add(reportDialogItemData);
                        for (String s : getData().getDeleteOption().getShowSubOption()[i]) {
                            ReportDialogData.ReportDialogItemData red = dialogData.new ReportDialogItemData();
                            red.setShowSubOption(s);
                            red.setType(ReportDialogData.ReportDialogItemData.TYPE_CONTENT);
                            dataList.add(red);
                        }
                        dialogData.setShowSubOption(dataList);
                    }
                    list.add(dialogData);
                }
                treeRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(ViewHolder viewHolder, int position) {
                        if (ll.get(position) instanceof TreeItemGroup) {
                            if (list.get(position).getShowSubOption() == null) return;
                            ll = ItemHelperFactory.getChildItemsWithType((TreeItemGroup) ll.get(position), TreeRecyclerType.SHOW_ALL);
                            int heightpx = ScreenUtils.dp2px(viewHolder.itemView.getContext(), list.get(position).getShowSubOption().size() * 40);
                            reportDialog.setDialogHeight(heightpx);
                        }
                    }
                });
                ll = ItemHelperFactory.createTreeItemList(list,ReportDialogItemGroup.class,null);
                treeRecyclerAdapter.setDatas(ll);
            }
        });
    }
}
