package com.sziti.counterfeittopnews.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sziti.counterfeittopnews.R;

public class BaseActivity extends Activity {
    private final String TAG = this.getClass().getSimpleName();

    private OwnActionBar mActionBar;

    public OwnActionBar getmActionBar() {
        return mActionBar;
    }

    public void setmActionBar(OwnActionBar mActionBar) {
        this.mActionBar = mActionBar;
    }

    /**
     * 进度条提示框，冻结屏幕，直到耗时操作完成
     */
    private AlertDialog mpDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        setContentView(view,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        //将初始化操作放到布局生成中
        View layout = View.inflate(this, R.layout.activity_base_fs, null);
        mActionBar = new OwnActionBar();
        //初始化
//        mActionBar.buttonLeft = (ImageButton) layout.findViewById(R.id.header_btn_left);
        mActionBar.buttonRight = (ImageButton) layout.findViewById(R.id.header_btn_right);
        mActionBar.buttonRightText = (Button) layout.findViewById(R.id.header_btn_right_text);
        mActionBar.title = (TextView) layout.findViewById(R.id.header_title);
        mActionBar.mTvNoDataCover = (TextView) layout.findViewById(R.id.base_nodata);
//        mActionBar.swipeRefreshLayout = (VerticalSwipeRefreshLayout) layout.findViewById(R.id.base_refresh);
//        mActionBar.swipeRefreshLayout.setEnabled(false);
        mActionBar.mBg = (RelativeLayout) layout.findViewById(R.id.header_bg);
        mActionBar.container = (FrameLayout) layout.findViewById(R.id.base_container);
        FrameLayout container = (FrameLayout) layout.findViewById(R.id.base_container);
        container.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        mActionBar.buttonLeft.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        initProgressDialog();
        super.setContentView(layout, params);
    }
    /**
     * 初始化进度条提示框
     */
    public void initProgressDialog(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setCancelable(true);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog, null);
        mBuilder.setView(view);
        mpDialog = mBuilder.create();
    }
    /**
     * 显示进度图标
     */
    public void showProgressDialog() {
        if(mpDialog != null && !mpDialog.isShowing()) {
            mpDialog.show();
            WindowManager.LayoutParams layoutParams = mpDialog.getWindow().getAttributes();
            layoutParams.width = getResources().getDisplayMetrics().widthPixels / 2;
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            mpDialog.getWindow().setAttributes(layoutParams);
        }
    }

    /**
     * 隐藏进度图标
     */
    public void hideProgressDialog() {
        if(mpDialog != null && mpDialog.isShowing()) {
            mpDialog.dismiss();
        }
    }
    public class OwnActionBar {
        //回退
//        private ImageButton buttonLeft;
        //右侧功能按钮
        private ImageButton buttonRight;
        //标题
        private TextView title;

        private TextView mTvNoDataCover;
        //标题栏整体
        private RelativeLayout mBg;
        //右侧文字
        private Button buttonRightText;
        //刷新控件
//        private VerticalSwipeRefreshLayout swipeRefreshLayout;
        private FrameLayout container;
//        public ImageButton getButtonLeft() {
//            return buttonLeft;
//        }

        public ImageButton getButtonRight() {
            return buttonRight;
        }

        public Button getButtonRightText() {
            return buttonRightText;
        }

        public void setButtonRightText(Button buttonRightText) {
            this.buttonRightText = buttonRightText;
        }

        public TextView getTitle() {
            return title;
        }

        public RelativeLayout getBg() {
            return mBg;
        }

//        public VerticalSwipeRefreshLayout getSwipeRefreshLayout() {
//            return swipeRefreshLayout;
//        }

        /**
         * 设置标题（使用资源编号）
         *
         * @param strId
         */
        public void setTitle(int strId) {
            setTitle(getString(strId));
        }

        /**
         * 设置标题（使用字符串）
         *
         * @param title
         */
        public void setTitle(CharSequence title) {
            if (getTitle() != null) {
                getTitle().setCompoundDrawables(null, null, null, null);
                getTitle().setText(title);
            }
        }

        /**
         * 打开、关闭“暂无数据”
         *
         * @param visiable
         */
        public void showNoDataCover(boolean visiable) {
            if (visiable) {
                mTvNoDataCover.setVisibility(View.VISIBLE);
            } else {
                mTvNoDataCover.setVisibility(View.GONE);
            }
        }
    }
}
