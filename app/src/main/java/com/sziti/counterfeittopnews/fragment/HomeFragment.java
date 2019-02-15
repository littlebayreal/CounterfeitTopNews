package com.sziti.counterfeittopnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.base.BaseFragment;
import com.sziti.counterfeittopnews.base.BaseSubFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private ViewPager vp;
    private TabLayout tabLayout;
    private List<BaseSubFragment> fragmentList;
    private String[] menu = {"关注", "推荐", "苏州", "视频", "热点", "小视频", "图片"};

    @Override
    public View onCreateSubView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        vp = view.findViewById(R.id.fragment_home_vp);
        fragmentList = new ArrayList<>();
        vp = view.findViewById(R.id.fragment_home_vp);
        tabLayout = view.findViewById(R.id.fragment_home_tablelayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setSelectedTabIndicatorHeight(0);
        for (String m : menu) {
            switch (m) {
                case "关注":
                    HomeFocuseFragment homeFocuseFragment = new HomeFocuseFragment();
                    homeFocuseFragment.setTAG(m);
                    fragmentList.add(homeFocuseFragment);
                    break;
                default:
                    CommonFragment commonFragment = new CommonFragment();
                    commonFragment.setTAG(m);
                    fragmentList.add(commonFragment);
                    break;
            }
        }
        vp.setAdapter(new HomeViewPagerAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(vp);

        return view;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible)
            Toast.makeText(getActivity(), "HomeFragment 可见", Toast.LENGTH_SHORT).show();
    }

    class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

        public HomeViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentList.get(position).getTAG();
        }
    }
}
