package com.sziti.counterfeittopnews;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.sziti.counterfeittopnews.base.BaseFragment;
import com.sziti.counterfeittopnews.base.BaseFragmentActivity;
import com.sziti.counterfeittopnews.fragment.HomeFragment;
import com.sziti.counterfeittopnews.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseFragmentActivity {
    private NoScrollViewPager novp;
    private List<BaseFragment> fragments;
    private RadioGroup rgHome;
    private FragmentStatePagerAdapter fragmentStatePagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main,BANNER_SEARCH);

        initView();
        initData();
        initListener();
    }

    private void initListener() {
        rgHome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        Log.e("main","rb_home");
                        novp.setCurrentItem(0, false);
                        break;
                    case R.id.rb_xigua_video:
                        Log.e("main","rb_photo");
                        novp.setCurrentItem(1, false);
                        break;
                    case R.id.rb_send_top:
                        Toast.makeText(MainActivity.this, "发送你的头条", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_little_video:
                        novp.setCurrentItem(2, false);
                        break;
                    case R.id.rb_personal_center:
                        Log.e("main","rb_personal_center");
                        novp.setCurrentItem(3, false);
                        break;
                }
            }
        });
    }

    private void initData() {
        fragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setTAG("homeFragment");
        fragments.add(homeFragment);
        novp.setAdapter(new HomeViewPagerAdapter(getSupportFragmentManager()));
    }

    private void initView() {
        novp = findViewById(R.id.activity_main_page);
        novp.setNoScroll(true);
        novp.setOffscreenPageLimit(1);

        rgHome = findViewById(R.id.rg_home);
    }
    class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

        public HomeViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return fragments.get(0);
                case 1:
                    return fragments.get(1);
                case 2:
                    return fragments.get(2);
                case 3:
                    return fragments.get(3);
                default:
                    return new Fragment();
            }
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
