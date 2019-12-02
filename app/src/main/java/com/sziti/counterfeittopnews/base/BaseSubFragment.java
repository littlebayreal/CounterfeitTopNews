package com.sziti.counterfeittopnews.base;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.widget.Pullableview.PullToRefreshLayout;

public class BaseSubFragment extends Fragment {
    private String TAG;
    private FrameLayout mBaseContainer;
    private TextView mBaseNodata;
    public long lastTime;
    //刷新控件
    private PullToRefreshLayout swipeRefreshLayout;
    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }
    /**
     * rootView是否初始化标志，防止回调函数在rootView为空的时候触发
     */
    private boolean hasCreateView;

    /**
     * 当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
     */
    private boolean isFragmentVisible;

    /**
     * onCreateView()里返回的view，修饰为protected,所以子类继承该类时，在onCreateView里必须对该变量进行初始化
     */
    protected View view;

    /**
     * 进度条提示框，冻结屏幕，直到耗时操作完成
     */
    private AlertDialog mpDialog;
    /**
     * 进度条提示框提示信息TextView
     */
    private TextView mpDialogTextView;

    //    @Override
//    public void onStart() {
//        super.onStart();
//        Log.e("zxb","拽取数据"+ getUserVisibleHint());
//        if (getUserVisibleHint()){
//            Log.e("zxb","拽取数据");
//            pullData();
//        }
//    }
//    public void refreshDataForActivity(VerticalSwipeRefreshLayout refreshLayout) {
//    }

    public void pullData() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("zxb", getTAG() + "setUserVisibleHint() -> isVisibleToUser: " + isVisibleToUser);
        if (view == null) {
            return;
        }
        hasCreateView = true;
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }

    //初始化参数
    private void initVariable() {
        hasCreateView = false;
        isFragmentVisible = false;
    }

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作，因为配合fragment的view复用机制，你不用担心在对控件操作中会报 null 异常
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {
        Log.w("zxb", getTAG() + "onFragmentVisibleChange -> isVisible: " + isVisible);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!hasCreateView && getUserVisibleHint()) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("zxxx", "basefragment oncreateview");
        view = inflater.inflate(R.layout.fragment_base_sub_fs, null);
//        mHeaderBar = (RelativeLayout) view.findViewById(R.id.header_bar);
//        mBaseLeft = (ImageButton) view.findViewById(R.id.header_btn_back);
//        mBaseRight = (ImageButton) view.findViewById(R.id.header_btn_settings);
//        mBaseText = (TextView) view.findViewById(R.id.header_title);
        mBaseContainer = (FrameLayout) view.findViewById(R.id.base_container);
        mBaseNodata = (TextView) view.findViewById(R.id.base_nodata);
//        mBaseRightText = (Button) view.findViewById(R.id.header_btn_right_text);
        swipeRefreshLayout = view.findViewById(R.id.fragment_base_sub_pullToRefresh);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                refreshDataForActivity(swipeRefreshLayout);
//            }
//        });
        View subView = onCreateSubView(inflater, container, savedInstanceState);
        if (subView != null) {
            mBaseContainer.addView(subView);
        }
        initProgressDialog();
        return view;
    }
    /**
     * 子类需要实现的子界面
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateSubView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }
//    /**
//     * 设置标题
//     *
//     * @param title
//     */
//    public void setTitle(String title) {
//        if (mBaseText != null) {
//            mBaseText.setText(title);
//        }
//    }
//
//    public RelativeLayout getmHeaderBar() {
//        return mHeaderBar;
//    }
//    public ImageButton getBaseLeft() {
//        return mBaseLeft;
//    }
//
//    public ImageButton getBaseRight() {
//        return mBaseRight;
//    }
//
//    public Button getBaseRightText() {
//        return mBaseRightText;
//    }

    public PullToRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }
    /**
     * 初始化进度条提示框
     */
    public void initProgressDialog(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        mBuilder.setCancelable(false);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        mBuilder.setView(view);
        mpDialogTextView = (TextView)view.findViewById(R.id.progress_dialog_tv);
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
}
