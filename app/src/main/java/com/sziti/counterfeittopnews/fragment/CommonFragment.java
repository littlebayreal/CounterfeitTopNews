package com.sziti.counterfeittopnews.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.base.BaseFragment;
import com.sziti.counterfeittopnews.base.BaseSubFragment;

public class CommonFragment extends BaseSubFragment {
    @Override
    public View onCreateSubView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_common,container,false);
        return v;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
    }
}
