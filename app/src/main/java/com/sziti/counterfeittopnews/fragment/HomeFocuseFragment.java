package com.sziti.counterfeittopnews.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.base.BaseSubFragment;
import com.sziti.counterfeittopnews.data.FocusBottomData;
import com.sziti.counterfeittopnews.data.FocusContentData;
import com.sziti.counterfeittopnews.data.FocusHorizonImageData;
import com.sziti.counterfeittopnews.data.FocusTitleData;
import com.sziti.counterfeittopnews.data.tree.FocusBottomTreeItem;
import com.sziti.counterfeittopnews.data.tree.FocusContentTreeItem;
import com.sziti.counterfeittopnews.data.tree.FocusHorizonCardItem;
import com.sziti.counterfeittopnews.data.tree.FocusTitleTreeItem;
import com.sziti.counterfeittopnews.data.tree.ItemHorizonCard;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseItemData;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerAdapter;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerType;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.factory.ItemConfig;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.factory.ItemHelperFactory;
import com.sziti.counterfeittopnews.widget.pullableview.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFocuseFragment extends BaseSubFragment {
    private RecyclerView rv;
    private TreeRecyclerAdapter treeRecyclerAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getSwipeRefreshLayout().refreshFinish(PullToRefreshLayout.SUCCEED, "你关注的人更新了13条消息");
        }
    };

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
    }

    @Override
    public View onCreateSubView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_focuse, container, false);

        ItemConfig.addTreeHolderType(100, FocusTitleTreeItem.class);
        ItemConfig.addTreeHolderType(101, FocusContentTreeItem.class);
        ItemConfig.addTreeHolderType(102, FocusBottomTreeItem.class);
        ItemConfig.addTreeHolderType(103, FocusHorizonCardItem.class);
        ItemConfig.addTreeHolderType(104,ItemHorizonCard.class);
        rv = v.findViewById(R.id.fragment_home_focuse_rv);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        treeRecyclerAdapter = new TreeRecyclerAdapter(TreeRecyclerType.SHOW_ALL);
        rv.setAdapter(treeRecyclerAdapter);
        initData();
        initListener();
        return v;
    }

    private void initData() {
        List<BaseItemData> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            FocusTitleData focusTitleData = new FocusTitleData();
            focusTitleData.setHeadDrawable(getActivity().getResources().getDrawable(R.drawable.demo_head1));
            focusTitleData.setViewItemType(100);
            focusTitleData.setAuthor("野球帝");
            focusTitleData.setBaseInfo("11-11 10:19·深圳克拉托斯体育有限公司品牌经理 优质体育领域创作人");
            list.add(focusTitleData);

            FocusContentData focusContentData = new FocusContentData();
            focusContentData.setViewItemType(101);
            focusContentData.setType(FocusContentData.ARTICLE);
            focusContentData.setInfo("篮下如何小打大？没错儿！就用它！");
            focusContentData.setShowImageDrawable(getResources().getDrawable(R.drawable.demo_venom));
            focusTitleData.setFocusContentData(focusContentData);

            FocusBottomData focusBottomData = new FocusBottomData();
            focusBottomData.setViewItemType(102);
            focusBottomData.setReprintTotal(6);
            focusBottomData.setMessageTotal(10);
            focusBottomData.setComplimentTotal(24);
            focusTitleData.setFocusBottomData(focusBottomData);

            focusTitleData = new FocusTitleData();
            focusTitleData.setHeadDrawable(getActivity().getResources().getDrawable(R.drawable.demo_head1));
            focusTitleData.setViewItemType(100);
            focusTitleData.setAuthor("汽车常识");
            focusTitleData.setBaseInfo("11-07 09:23·汽车达人 汽车领域创作者");
            focusTitleData.setIllustration("给汽车加油时需要注意这几点，你作对了吗？");
            list.add(focusTitleData);

            focusContentData = new FocusContentData();
            focusContentData.setViewItemType(101);
            focusContentData.setType(FocusContentData.IMAGE);
            focusContentData.setShowImageDrawable(getResources().getDrawable(R.drawable.demo_venom));
            focusContentData.setSpanSize(3);
            focusTitleData.setFocusContentData(focusContentData);

            focusBottomData = new FocusBottomData();
            focusBottomData.setViewItemType(102);
            focusBottomData.setReprintTotal(6);
            focusBottomData.setMessageTotal(10);
            focusBottomData.setComplimentTotal(24);
            focusTitleData.setFocusBottomData(focusBottomData);

            FocusHorizonImageData focusHorizonImageData = new FocusHorizonImageData();
            focusHorizonImageData.setViewItemType(103);
            for (int j = 0; j < 10; j++) {
                FocusHorizonImageData.FocusHorizonImageItemData focusHorizonImageItemData = focusHorizonImageData.new FocusHorizonImageItemData();
                focusHorizonImageItemData.setHeaderDrawable(getResources().getDrawable(R.drawable.demo_girl));
                focusHorizonImageItemData.setViewItemType(104);
                focusHorizonImageItemData.setUserName("LiTtleBayReal");
                focusHorizonImageItemData.setIllustration("其实我是一个演员");
                focusHorizonImageData.getList().add(focusHorizonImageItemData);
            }
            list.add(focusHorizonImageData);
        }
        List<TreeItem> focusTreeItems = ItemHelperFactory.createFileTreeItemList(list, null);
        treeRecyclerAdapter.setDatas(focusTreeItems);
        treeRecyclerAdapter.notifyDataSetChanged();
    }

    private void initListener() {
        getSwipeRefreshLayout().setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                handler.sendEmptyMessageDelayed(0, 1000);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        });
    }
}
