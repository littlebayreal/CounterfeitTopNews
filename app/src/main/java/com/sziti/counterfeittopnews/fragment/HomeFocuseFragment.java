package com.sziti.counterfeittopnews.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.base.BaseFragment;
import com.sziti.counterfeittopnews.base.BaseSubFragment;
import com.sziti.counterfeittopnews.data.FocuseData;
import com.sziti.counterfeittopnews.data.FocuseTreeItem;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerAdapter;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.factory.ItemHelperFactory;
import com.sziti.counterfeittopnews.widget.pullableview.PullToRefreshLayout;
import com.sziti.counterfeittopnews.widget.pullableview.PullableRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        treeRecyclerAdapter = new TreeRecyclerAdapter();
        rv.setAdapter(treeRecyclerAdapter);
        initData();
        initListener();
        return v;
    }

    private void initData() {
        List<FocuseData> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            FocuseData focuseData = new FocuseData();
            focuseData.setName("就是这个味道:"+ i);
            list.add(focuseData);
        }
        List<TreeItem> focuseTreeItems = ItemHelperFactory.createTreeItemList(list, FocuseTreeItem.class,null);
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
