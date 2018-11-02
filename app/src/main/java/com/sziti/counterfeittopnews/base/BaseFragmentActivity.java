package com.sziti.counterfeittopnews.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sziti.counterfeittopnews.R;

public class BaseFragmentActivity extends FragmentActivity {
    private final String TAG = this.getClass().getSimpleName();
    public static final int BANNER_COMMON = 0;
    public static final int BANNER_SEARCH = 1;
    private int bannerType;
//    private OwnActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    public void setContentView(int layoutResID,int bannerType){
        this.bannerType = bannerType;
        setContentView(layoutResID);
    }
    @Override
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        View layout = null;
        switch (bannerType){
            case BANNER_COMMON:
                //带标题页面的大布局
                layout = View.inflate(this, R.layout.fragment_activity_base_fs, null);
                break;
            case BANNER_SEARCH:
                //带标题页面的大布局
                layout = View.inflate(this, R.layout.fragment_activity_base_search, null);
                break;
        }

//        mActionBar = new OwnActionBar();
//        mActionBar.buttonLeft = (ImageButton) layout.findViewById(R.id.header_btn_left);
//        mActionBar.buttonRight = (ImageButton) layout.findViewById(R.id.header_btn_right);
//        mActionBar.title = (TextView) layout.findViewById(R.id.header_title);
//        mActionBar.mTvNoDataCover = (TextView) layout.findViewById(R.id.base_nodata);
//        mActionBar.swipeRefreshLayout = (VerticalSwipeRefreshLayout) layout.findViewById(R.id.base_refresh);
//        mActionBar.swipeRefreshLayout.setEnabled(false);
//        mActionBar.mBg = (RelativeLayout)layout.findViewById(R.id.header_bg);

        FrameLayout container = (FrameLayout) layout.findViewById(R.id.base_container);
        //添加主页的布局文件
        container.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        // 默认监听器
//        mActionBar.buttonLeft.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        super.setContentView(layout, params);
    }
    /**
     * 获取自定义的ActionBar
     * @return
     */
//    public OwnActionBar getOwnActionBar() {
//        return mActionBar;
//    }
//    public class OwnActionBar {
////        private ImageButton buttonLeft;
//        private ImageButton buttonRight;
//        private TextView title;
//        private TextView mTvNoDataCover;
//        private RelativeLayout mBg;
//        //刷新控件
////        private VerticalSwipeRefreshLayout swipeRefreshLayout;
////        public ImageButton getButtonLeft() {
////            return buttonLeft;
////        }
//        public ImageButton getButtonRight() {
//            return buttonRight;
//        }
//        public TextView getTitle() {
//            return title;
//        }
//        public RelativeLayout getBg() {
//            return mBg;
//        }
//
////        public VerticalSwipeRefreshLayout getSwipeRefreshLayout() {
////            return swipeRefreshLayout;
////        }
//        /**
//         * 设置标题（使用资源编号）
//         * @param strId
//         */
//        public void setTitle(int strId) {
//            setTitle(getString(strId));
//        }
//
//        /**
//         * 设置标题（使用字符串）
//         * @param title
//         */
//        public void setTitle(CharSequence title) {
//            if(getTitle() != null) {
//                getTitle().setCompoundDrawables(null, null, null, null);
//                getTitle().setText(title);
//            }
//        }
//
//        /**
//         * 打开、关闭“暂无数据”
//         * @param visiable
//         */
//        public void showNoDataCover(boolean visiable) {
//            if(visiable) {
//                mTvNoDataCover.setVisibility(View.VISIBLE);
//            } else {
//                mTvNoDataCover.setVisibility(View.GONE);
//            }
//        }
//    }
}
