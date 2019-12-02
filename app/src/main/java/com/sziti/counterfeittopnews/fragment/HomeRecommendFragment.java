package com.sziti.counterfeittopnews.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.sziti.counterfeittopnews.Cons;
import com.sziti.counterfeittopnews.NewsDetailActivity;
import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.base.BaseSubFragment;
import com.sziti.counterfeittopnews.data.FocusBottomData;
import com.sziti.counterfeittopnews.data.FocusContentData;
import com.sziti.counterfeittopnews.data.FocusTitleData;
import com.sziti.counterfeittopnews.data.tree.FocusBottomTreeItem;
import com.sziti.counterfeittopnews.data.tree.FocusContentTreeItem;
import com.sziti.counterfeittopnews.data.tree.FocusHorizonCardItem;
import com.sziti.counterfeittopnews.data.tree.FocusTitleTreeItem;
import com.sziti.counterfeittopnews.http.Response.News;
import com.sziti.counterfeittopnews.http.Response.NewsData;
import com.sziti.counterfeittopnews.http.Response.NewsResponse;
import com.sziti.counterfeittopnews.http.RetrofitClient;
import com.sziti.counterfeittopnews.video.base.Jzvd;
import com.sziti.counterfeittopnews.video.custom.JZMediaIjkplayer;
import com.sziti.counterfeittopnews.widget.SuperLikeView.BitmapProviderFactory;
import com.sziti.counterfeittopnews.widget.SuperLikeView.SuperLikeLayout;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseItemData;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseRecyclerAdapter;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerAdapter;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerType;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.factory.ItemConfig;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.factory.ItemHelperFactory;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HomeRecommendFragment extends BaseSubFragment {
    private RecyclerView rv;
    private SuperLikeLayout superLikeLayout;
    private TreeRecyclerAdapter treeRecyclerAdapter;

    @Override
    public View onCreateSubView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Jzvd.setMediaInterface(new JZMediaIjkplayer());
        View v = inflater.inflate(R.layout.fragment_home_focuse, container, false);

        ItemConfig.addTreeHolderType(100, FocusTitleTreeItem.class);
        ItemConfig.addTreeHolderType(101, FocusContentTreeItem.class);
        ItemConfig.addTreeHolderType(102, FocusBottomTreeItem.class);
        ItemConfig.addTreeHolderType(103, FocusHorizonCardItem.class);
//        ItemConfig.addTreeHolderType(104, ItemHorizonCard.class);
        rv = v.findViewById(R.id.fragment_home_focuse_rv);
        rv.setItemViewCacheSize(10);
        rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        treeRecyclerAdapter = new TreeRecyclerAdapter(TreeRecyclerType.SHOW_ALL);
        rv.setAdapter(treeRecyclerAdapter);

        superLikeLayout = v.findViewById(R.id.fragment_home_focuse_superLike);
        superLikeLayout.setProvider(BitmapProviderFactory.getHDProvider(getContext()));
        return v;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        //在fragment不可见时释放资源
        if (isVisible){
           initData();
           initListener();
        }
//            Jzvd.releaseAllVideos();
    }

    private void initListener() {
        treeRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewHolder viewHolder, int position) {
                News news = (News) treeRecyclerAdapter.getDatas().get(position).getData();
                String itemId = news.getItem_id();
                StringBuffer urlSb = new StringBuffer("http://m.toutiao.com/i");
                urlSb.append(itemId).append("/info/");
                String url = urlSb.toString();//http://m.toutiao.com/i6412427713050575361/info/
                Intent intent = null;
                intent.putExtra(NewsDetailActivity.DETAIL_URL, url);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        RetrofitClient.getInstance().getApiService().getNewsList("",System.currentTimeMillis()- 60000,System.currentTimeMillis())
                .subscribeOn(Schedulers.io())
                .map(new Func1<NewsResponse, NewsResponse>() {
                    @Override
                    public NewsResponse call(NewsResponse newsResponse) {
                        List<News> news = new ArrayList<>();
                        for (NewsData newsData : newsResponse.getData()){
                            News n =  new Gson().fromJson(newsData.content, News.class);
                            news.add(n);
                        }
                        newsResponse.setNews(news);
                        return newsResponse;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewsResponse newsResponse) {
                      refresh(newsResponse);
                    }
                });
    }
    private void refresh(NewsResponse newsResponse){
        List<BaseItemData> list = new ArrayList<>();
        //在这里进行判断文章的类型 页面的初始化 设置相同的itemid 在删除时进行统一删除
        Log.e("vvv","拿到了多少数据:"+ newsResponse.getNews().size());
        for (int i = 0;i<newsResponse.getNews().size();i++){
            News news = newsResponse.getNews().get(i);
            //            文章title
            FocusTitleData focusTitleData = new FocusTitleData();
            focusTitleData.setData(news);
            focusTitleData.setHeadUrl(news.getUser_info().getAvatar_url());
            focusTitleData.setViewItemType(100);
            focusTitleData.setAuthor(news.getUser_info().getName());
            focusTitleData.setBaseInfo(news.getUser_info().getDescription());
            FocusTitleData.DeleteOption deleteOption = focusTitleData.new DeleteOption();
            deleteOption.setShowOption(Cons.DELETE_TWO);
            deleteOption.setShowInfo(Cons.DELETE_TWO_INFO);
            deleteOption.setShowSubOption(Cons.DELETE_TWO_SUB_ITEM);
            focusTitleData.setDeleteOption(deleteOption);
            list.add(focusTitleData);
            //            文章content
            FocusContentData focusContentData = new FocusContentData();
            focusContentData.setData(news);
            focusContentData.setViewItemType(101);
            focusContentData.setType(FocusContentData.ARTICLE);
            focusContentData.setInfo(news.getTitle());
            List<Drawable> ll = new ArrayList<>();
            ll.add(getResources().getDrawable(R.drawable.demo_venom));
            focusContentData.setShowImageDrawable(ll);
            focusTitleData.setFocusContentData(focusContentData);
            //            文章bottom
            FocusBottomData focusBottomData = new FocusBottomData();
            focusBottomData.setData(news);
            focusBottomData.setViewItemType(102);
            focusBottomData.setReprintTotal(6);
            focusBottomData.setMessageTotal(10);
            focusBottomData.setComplimentTotal(24);
            focusBottomData.setLikeListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int[] itemPosition = new int[2];
                    int[] superLikePosition = new int[2];
                    v.getLocationOnScreen(itemPosition);
                    superLikeLayout.getLocationOnScreen(superLikePosition);
                    int x = itemPosition[0];
                    int y = (itemPosition[1] - superLikePosition[1]) + v.getHeight() / 2;
                    //发动点击效果动画
                    superLikeLayout.launch(x, y);
                }
            });
            focusTitleData.setFocusBottomData(focusBottomData);
        }
        List<TreeItem> focusTreeItems = ItemHelperFactory.createFileTreeItemList(list, null);
        treeRecyclerAdapter.setDatas(focusTreeItems);
        treeRecyclerAdapter.notifyDataSetChanged();
    }
}
