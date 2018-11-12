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
import com.sziti.counterfeittopnews.data.FocusTitleData;
import com.sziti.counterfeittopnews.data.tree.FocusTitleTreeItem;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerAdapter;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.factory.ItemHelperFactory;
import com.sziti.counterfeittopnews.widget.pullableview.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFocuseFragment extends BaseSubFragment {
    private RecyclerView rv;
    private TreeRecyclerAdapter treeRecyclerAdapter;
        private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            getSwipeRefreshLayout().refreshFinish(PullToRefreshLayout.SUCCEED,"你关注的人更新了13条消息");
        }
    };
    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
    }

    @Override
    public View onCreateSubView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_focuse, container, false);
        rv = v.findViewById(R.id.fragment_home_focuse_rv);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        treeRecyclerAdapter = new TreeRecyclerAdapter();
        rv.setAdapter(treeRecyclerAdapter);
        initData();
        initListener();
        return v;
    }

    private void initData() {
        List<FocusTitleData> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            FocusTitleData focusTitleData = new FocusTitleData();
//            focusTitleData.setName("就是这个味道:"+ i);
            list.add(focusTitleData);
        }
        List<TreeItem> focuseTreeItems = ItemHelperFactory.createTreeItemList(list, FocusTitleTreeItem.class,null);
        treeRecyclerAdapter.setDatas(focuseTreeItems);
        treeRecyclerAdapter.notifyDataSetChanged();
    }

    private void initListener() {
        getSwipeRefreshLayout().setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                handler.sendEmptyMessageDelayed(0,1000);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        });
    }
}
