package com.sziti.counterfeittopnews.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.sziti.counterfeittopnews.Cons;
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
import com.sziti.counterfeittopnews.http.Response.News;
import com.sziti.counterfeittopnews.http.Response.NewsData;
import com.sziti.counterfeittopnews.http.Response.NewsResponse;
import com.sziti.counterfeittopnews.http.RetrofitClient;
import com.sziti.counterfeittopnews.ui.NewsDetailActivity;
import com.sziti.counterfeittopnews.ui.VideoNewsDetailActivity;
import com.sziti.counterfeittopnews.video.base.Jzvd;
import com.sziti.counterfeittopnews.video.custom.JZMediaIjkplayer;
import com.sziti.counterfeittopnews.widget.SuperLikeView.BitmapProviderFactory;
import com.sziti.counterfeittopnews.widget.SuperLikeView.SuperLikeLayout;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.BaseItemData;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerAdapter;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.TreeRecyclerType;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.factory.ItemConfig;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.factory.ItemHelperFactory;
import com.sziti.counterfeittopnews.widget.Pullableview.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static com.sziti.counterfeittopnews.data.FocusHorizonImageData.FocusHorizonImageItemData.HorizonImageCard;
import static com.sziti.counterfeittopnews.data.FocusHorizonImageData.FocusHorizonImageItemData.HorizonUserCard;

public class HomeFocuseFragment extends BaseSubFragment {
	private RecyclerView rv;
	private SuperLikeLayout superLikeLayout;
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
		//在fragment不可见时释放资源
		if (!isVisible)
			Jzvd.releaseAllVideos();
	}

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
		initData();
		initListener();
		return v;
	}

	private void initData() {
		List<BaseItemData> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			/*显示关注用户*/
			if (i == 0) {
				FocusHorizonImageData focusHorizonImageData = new FocusHorizonImageData();
				focusHorizonImageData.setViewItemType(103);
				for (int j = 0; j < 10; j++) {
					FocusHorizonImageData.FocusHorizonImageItemData focusHorizonImageItemData = focusHorizonImageData.new FocusHorizonImageItemData();
					focusHorizonImageItemData.setHeaderDrawable(getResources().getDrawable(R.drawable.demo_head2));
//                    focusHorizonImageItemData.setViewItemType(104);
					focusHorizonImageItemData.setHorizontType(HorizonUserCard);
					focusHorizonImageItemData.setUserName("格斗世界");
					focusHorizonImageData.getList().add(focusHorizonImageItemData);
				}
				list.add(focusHorizonImageData);
			}
			/*模拟文章数据*/
			FocusTitleData focusTitleData = new FocusTitleData();
			focusTitleData.setHeadDrawable(getActivity().getResources().getDrawable(R.drawable.demo_head1));
			focusTitleData.setViewItemType(100);
			focusTitleData.setAuthor("野球帝");
			focusTitleData.setBaseInfo("11-11 10:19·深圳克拉托斯体育有限公司品牌经理 优质体育领域创作人");
			FocusTitleData.DeleteOption deleteOption = focusTitleData.new DeleteOption();
			deleteOption.setShowOption(Cons.DELETE_TWO);
			deleteOption.setShowInfo(Cons.DELETE_TWO_INFO);
			deleteOption.setShowSubOption(Cons.DELETE_TWO_SUB_ITEM);
			focusTitleData.setDeleteOption(deleteOption);
			list.add(focusTitleData);

			FocusContentData focusContentData = new FocusContentData();
			focusContentData.setViewItemType(101);
			focusContentData.setType(FocusContentData.ARTICLE);
			focusContentData.setInfo("篮下如何小打大？没错儿！就用它！");
			List<Drawable> ll = new ArrayList<>();
			ll.add(getResources().getDrawable(R.drawable.demo_venom));
			focusContentData.setShowImageDrawable(ll);
			focusTitleData.setFocusContentData(focusContentData);
			final FocusContentData finalFocusContentData = focusContentData;
			focusContentData.setClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = null;
					switch (finalFocusContentData.getType()) {
						case FocusContentData.VIDEO:
							intent = new Intent(getActivity(), VideoNewsDetailActivity.class);
							break;
						case FocusContentData.IMAGE:
							intent = new Intent(getActivity(), NewsDetailActivity.class);
							break;
						case FocusContentData.ARTICLE:
							intent = new Intent(getActivity(), NewsDetailActivity.class);
							break;
					}
					startActivity(intent);
				}
			});

			FocusBottomData focusBottomData = new FocusBottomData();
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

			/*模拟视频数据*/
			focusTitleData = new FocusTitleData();
			focusTitleData.setHeadDrawable(getActivity().getResources().getDrawable(R.drawable.demo_head4));
			focusTitleData.setViewItemType(100);
			focusTitleData.setAuthor("我是郭杰瑞");
			focusTitleData.setBaseInfo("4小时前·知名搞笑领域创作者");
			focusTitleData.setIllustration("奢侈品牌Tiffany卖下午茶，1000元吃一顿饭值得吗？");
			deleteOption = focusTitleData.new DeleteOption();
			deleteOption.setShowOption(Cons.DELETE_ONE);
			focusTitleData.setDeleteOption(deleteOption);
			list.add(focusTitleData);


			focusContentData = new FocusContentData();
			focusContentData.setViewItemType(101);
			focusContentData.setType(FocusContentData.VIDEO);
			if (i == 0)
				focusContentData.setVideoUrl("http://jzvd.nathen.cn/6ea7357bc3fa4658b29b7933ba575008/fbbba953374248eb913cb1408dc61d85-5287d2089db37e62345123a1be272f8b.mp4");
			if (i == 1)
				focusContentData.setVideoUrl("http://jzvd.nathen.cn/35b3dc97fbc240219961bd1fccc6400b/8d9b76ab5a584bce84a8afce012b72d3-5287d2089db37e62345123a1be272f8b.mp4");
			if (i == 2)
				focusContentData.setVideoUrl("http://jzvd.nathen.cn/df6096e7878541cbbea3f7298683fbed/ef76450342914427beafe9368a4e0397-5287d2089db37e62345123a1be272f8b.mp4");

			ll = new ArrayList<>();
			ll.add(getResources().getDrawable(R.drawable.demo_girl));
			focusContentData.setShowImageDrawable(ll);
			focusTitleData.setFocusContentData(focusContentData);

			focusBottomData = new FocusBottomData();
			focusBottomData.setViewItemType(102);
			focusBottomData.setReprintTotal(6);
			focusBottomData.setMessageTotal(10);
			focusBottomData.setComplimentTotal(24);
			focusTitleData.setFocusBottomData(focusBottomData);


			/*模拟多张图片数据*/
			focusTitleData = new FocusTitleData();
			focusTitleData.setHeadDrawable(getActivity().getResources().getDrawable(R.drawable.demo_head3));
			focusTitleData.setViewItemType(100);
			focusTitleData.setAuthor("汽车常识");
			focusTitleData.setBaseInfo("11-07 09:23·汽车达人 汽车领域创作者");
			focusTitleData.setIllustration("给汽车加油时需要注意这几点，你作对了吗？");
			list.add(focusTitleData);

			focusContentData = new FocusContentData();
			focusContentData.setViewItemType(101);
			focusContentData.setType(FocusContentData.IMAGE);
			ll = new ArrayList<>();
			for (int u = 0; u < 8; u++) {
				ll.add(getResources().getDrawable(R.drawable.demo_venom));
			}
			focusContentData.setShowImageDrawable(ll);
			focusTitleData.setFocusContentData(focusContentData);

			focusBottomData = new FocusBottomData();
			focusBottomData.setViewItemType(102);
			focusBottomData.setReprintTotal(6);
			focusBottomData.setMessageTotal(10);
			focusBottomData.setComplimentTotal(24);
			focusTitleData.setFocusBottomData(focusBottomData);

			/*模拟多张图片转发*/
			focusTitleData = new FocusTitleData();
			focusTitleData.setHeadDrawable(getActivity().getResources().getDrawable(R.drawable.demo_head3));
			focusTitleData.setViewItemType(100);
			focusTitleData.setAuthor("汽车常识");
			focusTitleData.setBaseInfo("11-07 09:23·汽车达人 汽车领域创作者");
			focusTitleData.setIllustration("给汽车加油时需要注意这几点，你作对了吗？");
			list.add(focusTitleData);

			focusContentData = new FocusContentData();
			focusContentData.setViewItemType(101);
			focusContentData.setType(FocusContentData.REPRINT_IMAGE);
			ll = new ArrayList<>();
			for (int u = 0; u < 8; u++) {
				ll.add(getResources().getDrawable(R.drawable.demo_head3));
			}
			focusContentData.setShowImageDrawable(ll);
			focusTitleData.setFocusContentData(focusContentData);

			focusBottomData = new FocusBottomData();
			focusBottomData.setViewItemType(102);
			focusBottomData.setReprintTotal(6);
			focusBottomData.setMessageTotal(10);
			focusBottomData.setComplimentTotal(24);
			focusTitleData.setFocusBottomData(focusBottomData);

			/*模拟他们也在用*/
			FocusHorizonImageData focusHorizonImageData = new FocusHorizonImageData();
			focusHorizonImageData.setViewItemType(103);
			for (int j = 0; j < 10; j++) {
				FocusHorizonImageData.FocusHorizonImageItemData focusHorizonImageItemData = focusHorizonImageData.new FocusHorizonImageItemData();
				focusHorizonImageItemData.setHeaderDrawable(getResources().getDrawable(R.drawable.demo_head4));
//                focusHorizonImageItemData.setViewItemType(104);
				focusHorizonImageItemData.setHorizontType(HorizonImageCard);
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
		rv.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
			@Override
			public void onChildViewAttachedToWindow(View view) {
				Jzvd.onChildViewAttachedToWindow(view, R.id.item_focus_content_jzvdstd);
			}

			@Override
			public void onChildViewDetachedFromWindow(View view) {
				Jzvd.onChildViewDetachedFromWindow(view);
			}
		});
	}
}
