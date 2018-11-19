package com.sziti.counterfeittopnews.data.tree;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.data.FocusHorizonImageData;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Base.ViewHolder;
import com.sziti.counterfeittopnews.widget.TreeRecyclerView.Item.TreeItem;

public class ItemHorizonCard extends TreeItem<FocusHorizonImageData.FocusHorizonImageItemData> {
    @Override
    protected int initLayoutId() {
        switch (getData().getHorizontType()){
            case FocusHorizonImageData.FocusHorizonImageItemData.HorizonImageCard:
                Log.e("cvb","重新出刷imageCard");
                return R.layout.item_horizon_card;
            case FocusHorizonImageData.FocusHorizonImageItemData.HorizonUserCard:
                Log.e("cvb","重新出刷user");
                return R.layout.item_horizon_user_card;
        }
        return R.layout.item_horizon_card;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        switch (getData().getHorizontType()){
            case FocusHorizonImageData.FocusHorizonImageItemData.HorizonImageCard:
                ((ImageView)viewHolder.getView(R.id.item_horizon_card_iv)).setImageDrawable(getData().getHeaderDrawable());
                break;
            case FocusHorizonImageData.FocusHorizonImageItemData.HorizonUserCard:
                ((ImageView)viewHolder.getView(R.id.item_horizon_user_card_iv)).setImageDrawable(getData().getHeaderDrawable());
                ((TextView)viewHolder.getView(R.id.item_horizon_user_card_tv)).setText(getData().getUserName());
                break;
        }

    }
}
