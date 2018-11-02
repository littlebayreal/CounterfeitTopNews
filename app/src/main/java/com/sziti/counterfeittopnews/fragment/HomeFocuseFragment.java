package com.sziti.counterfeittopnews.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.base.BaseFragment;
import com.sziti.counterfeittopnews.widget.pullableview.PullableRecyclerView;

public class HomeFocuseFragment extends BaseFragment {
    private PullableRecyclerView rv;
//    private
    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
    }

    @Override
    public View onCreateSubView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_focuse,container,false);
        rv = v.findViewById(R.id.fragment_home_focuse_rv);

        return v;
    }
}
