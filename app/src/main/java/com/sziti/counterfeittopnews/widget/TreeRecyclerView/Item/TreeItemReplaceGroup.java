package com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item;
import android.util.Log;

import java.util.List;

public abstract class TreeItemReplaceGroup<D> extends TreeItemGroup<D>{
    /**
     * 持有的子item集
     */
    private List<TreeItem> child;

    /**
     * 是否展开
     */
    private boolean isExpand;


    public boolean isExpand() {
        return isExpand;
    }

//    /**
//     * 设置为传入
//     *
//     * @param expand 传入true则展开,传入false则折叠
//     */
//    public final void setExpand(boolean expand) {
//        if (!isCanExpand()) {
//            return;
//        }
//        if (expand) {
//            //展开
//            onExpand();
//        } else {
//            //折叠
//            onCollapse();
//        }
//    }

//    /**
//     * 刷新Item的展开状态
//     */
//    @Deprecated
//    public void notifyExpand() {
//        setExpand(isExpand);
//    }
    /**
     * 将展开变成替换
     */
    protected void onExpand() {
        isExpand = true;
        //根据对象查找item在视图中的准确位置
//        int itemPosition = getItemManager().getItemPosition(this);
//        getItemManager().addItems(getItemManager().getAdapter(),itemPosition + 1, getExpandChild());
        Log.e("bbbb","走的这里");
        getItemManager().replaceAllItem(getExpandChild());
    }

//    /**
//     * 折叠
//     */
//    protected void onCollapse() {
//        isExpand = false;
//        getItemManager().removeItems(getExpandChild());
//        getItemManager().notifyDataChanged();
//    }

//    /**
//     * 能否展开折叠
//     *
//     * @return
//     */
//    public boolean isCanExpand() {
//        return true;
//    }
//
//
//    /**
//     * 获得所有childs,包括子item的childs
//     *
//     * @return
//     */
//    @Nullable
//    public List<TreeItem> getExpandChild() {
//        if (getChild() == null) {
//            return null;
//        }
//        return ItemHelperFactory.getChildItemsWithType(this, TreeRecyclerType.SHOW_EXPAND);
//    }
//
//
//    public void setData(D data) {
//        super.setData(data);
//        child = initChildList(data);
//    }
//
//    public void setChild(List<TreeItem> child) {
//        this.child = child;
//    }
//
//    /**
//     * 获得所有childs,包括下下....级item的childs
//     *
//     * @return
//     */
//    @Nullable
//    public List<TreeItem> getAllChilds() {
//        if (getChild() == null) {
//            return null;
//        }
//        return ItemHelperFactory.getChildItemsWithType(this, TreeRecyclerType.SHOW_ALL);
//    }
//
//    /**
//     * 获得自己的childs.
//     *
//     * @return
//     */
//    @Nullable
//    public List<TreeItem> getChild() {
//        return child;
//    }
//
//
//    public int getChildCount() {
//        return child == null ? 0 : child.size();
//    }
//
//    /**
//     * 初始化子集
//     *
//     * @param data
//     * @return
//     */
//    protected abstract List<TreeItem> initChildList(D data);
//
//    /**
//     * 是否消费child的click事件
//     *
//     * @param child 具体click的item
//     * @return 返回true代表消费此次事件，child不会走onclick()，返回false说明不消费此次事件，child依然会走onclick()
//     */
//    public boolean onInterceptClick(TreeItem child) {
//        return false;
//    }
//
////    /**
////     * 相应RecyclerView的点击事件 展开或关闭某节点
////     */
////    public void expandOrCollapse() {
////        //展开,折叠
////        setExpand(!isExpand);
////    }
//
//    @Override
//    public int getSpanSize() {
//        return super.getSpanSize();
//    }
}
