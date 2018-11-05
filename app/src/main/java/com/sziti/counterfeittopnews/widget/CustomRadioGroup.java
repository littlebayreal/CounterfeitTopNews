package com.sziti.counterfeittopnews.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.sziti.counterfeittopnews.R;

public class CustomRadioGroup extends LinearLayout {
//    private ArrayList<Integer> tabChildsId = new ArrayList<Integer>();

    private OnItemTabClickListener listener;
    private PassThroughHierarchyChangeListener mPassThroughListener;
    private CheckedStateTracker mChildOnCheckedChangeListener;
    private int[] exceptionId;
    /**
     * 记录当前id，默认id为-1
     */
    private int mCheckedId = -1;

    public CustomRadioGroup(Context context, AttributeSet attrs,
                            int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomRadioGroup(Context context) {
        super(context);
        init();
    }

    private void init() {
        // radiobutton的监听
        mChildOnCheckedChangeListener = new CheckedStateTracker();

        // 层次结构的监听
        mPassThroughListener = new PassThroughHierarchyChangeListener();
        // 设置监听
        super.setOnHierarchyChangeListener(mPassThroughListener);
    }

    /**
     * 设置tab数据
     *
     * @param datas
     */
//    public void setDataOfTabs(List<RechargeNumInfo> datas) {
//        Log.i("TAG2","getChildCount()===>>>"+getChildCount());
//        Log.i("TAG2","datas()===>>>"+datas.size());
//        int count = 0;
//        if (datas.size() >= getChildCount()) {
//            count = getChildCount();
////			return;
//        }else{
//            count = datas.size();
//        }
//        for (int i = 0; i < count; i++) {
//            Log.i("TAG2","data=1111==>>>");
//            RechargeValueLayout view = (RechargeValueLayout) findViewById(tabChildsId.get(i));
//            RechargeNumInfo data = datas.get(i);
//            Log.i("TAG2","data===>>>"+data);
//            view.setMoneyValue(data.chargeNum);
//            view.setCordsValue(data.gzyNum);
//            view.setZengsValue(data.giftNum);
//        }
//
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // checks the appropriate radio button as requested in the XML file
        if (mCheckedId != -1) {
            check(mCheckedId);
        }
    }

    /**
     * 改变item的选择状态
     *
     * @param viewId
     * @param checked true 为选择，false 为不选择
     */
    private void setCheckedStateForView(int viewId, boolean checked) {
        Log.i("TAG2", "setCheckedStateForView====>>>");
        View checkedView = findViewById(viewId);
        Log.i("TAG2", "setCheckedStateForView===checkedView=>>>" + checkedView);
        if (checkedView != null && checkedView instanceof RadioButton) {
            ((RadioButton) checkedView).setChecked(checked);
            Log.i("TAG2", "setCheckedStateForView===checkedView=111>>>"
                    + checkedView);
        }
    }

    public void check(int id) {
        // don't even bother
        Log.i("TAG2", "check=000======>>>" + id);
        Log.i("TAG2", "check=000=11=====>>>" + mCheckedId);
        if (id != -1 && (id == mCheckedId)) {
            Log.i("TAG2", "check=111======>>>" + id);
            return;
        }

        if (mCheckedId != -1) {
            Log.i("TAG2", "check=222======>>>" + id);
            setCheckedStateForView(mCheckedId, false);
        }

        if (id != -1) {
            Log.i("TAG2", "check=333======>>>" + id);
            setCheckedStateForView(id, true);
        }

        setCheckedId(id);
    }

    /**
     * 选择Item后要执行的操作
     *
     * @param id
     */
    private void setCheckedId(int id) {
        // 更新当前选择的Item的id
        mCheckedId = id;
        if (listener != null) {
            listener.onItemClick(this, mCheckedId);
        }
    }

    /**
     * 设置不需要监听的radiobutton
     */
    public void setExceptionButton(int... buttongId) {
        exceptionId = new int[buttongId.length];
        for (int i = 0; i < buttongId.length; i++) {
            exceptionId[i] = buttongId[i];
        }
    }

    /**
     * 设置点击item的监听
     *
     * @param l
     */
    public void setOnItemTabClickListener(OnItemTabClickListener l) {
        this.listener = l;
    }

    /**
     * item选择监听 Title: RechargeValueGroup.java Description:
     *
     * @author Liusong
     * @version V1.0
     * @date 2015-6-26
     */
    public interface OnItemTabClickListener {
        void onItemClick(CustomRadioGroup customRadioGroup, int checkedId);
    }

    /**
     * 图层变换监听 Title: RechargeValueGroup.java Description:
     *
     * @author Liusong
     * @version V1.0
     * @date 2015-6-26
     */
    private class PassThroughHierarchyChangeListener implements
            ViewGroup.OnHierarchyChangeListener {
        private ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;

        /**
         * {@inheritDoc}
         */
        public void onChildViewAdded(View parent, View child) {
            if (parent == CustomRadioGroup.this
                    && child instanceof RadioButton) {
                int id = child.getId();
//				Log.i("TAG2", "onChildViewAdded=111==>>>>" + id);
                // generates an id if it's missing
                if (id == View.NO_ID) {
                    id = View.generateViewId();
                    child.setId(id);
                }
                ((RadioButton) child)
                        .setOnClickListener(mChildOnCheckedChangeListener);
            }

            if (mOnHierarchyChangeListener != null) {
                mOnHierarchyChangeListener.onChildViewAdded(parent, child);
            }
        }

        /**
         * {@inheritDoc}
         */
        public void onChildViewRemoved(View parent, View child) {
            if (parent == CustomRadioGroup.this
                    && child instanceof RadioButton) {
                ((RadioButton) child).setOnClickListener(null);
//                tabChildsId.remove(child.getId());
            }
//			Log.i("TAG2", "onChildViewRemoved=111==>>>>" + child.getId());
            if (mOnHierarchyChangeListener != null) {
                mOnHierarchyChangeListener.onChildViewRemoved(parent, child);
            }
        }
    }

    /**
     * 每一项Item的点击事件 Title: RechargeValueGroup.java Description:
     *
     * @author Liusong
     * @version V1.0
     * @date 2015-6-26
     */
    class CheckedStateTracker implements OnClickListener {

        @Override
        public void onClick(View v) {
//			setCheckedId(v.getId());
            for (int ecid : exceptionId) {
                if (ecid != v.getId())
                    check(v.getId());
                else {
                    setCheckedStateForView(v.getId(),false);
                    break;
                }
            }
        }
    }
}
