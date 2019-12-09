package com.sziti.counterfeittopnews.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.base.BaseFragment;
import com.sziti.counterfeittopnews.base.BaseFragmentActivity;
import com.sziti.counterfeittopnews.fragment.HomeFragment;
import com.sziti.counterfeittopnews.util.GeometryUtil;
import com.sziti.counterfeittopnews.util.ScreenUtils;
import com.sziti.counterfeittopnews.widget.CustomRadioGroup;
import com.sziti.counterfeittopnews.widget.NoScrollViewPager;
import com.sziti.counterfeittopnews.widget.OutSideListenDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseFragmentActivity {
    private NoScrollViewPager novp;
    private List<BaseFragment> fragments;
    private CustomRadioGroup rgHome;
    private FragmentStatePagerAdapter fragmentStatePagerAdapter;
    private int checked = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main, BANNER_SEARCH);

        initView();
        initData();
        initListener();
    }

    private void initListener() {
        rgHome.setExceptionButton(R.id.rb_send_top);
        rgHome.setOnItemTabClickListener(new CustomRadioGroup.OnItemTabClickListener() {
            @Override
            public void onItemClick(CustomRadioGroup group, int checkedId) {
                if (checkedId != R.id.rb_send_top)
                    checked = checkedId;
                switch (checkedId) {
                    case R.id.rb_home:
                        Log.e("main", "rb_home");
                        novp.setCurrentItem(0, false);
                        break;
                    case R.id.rb_xigua_video:
                        Log.e("main", "rb_photo");
                        novp.setCurrentItem(1, false);
                        break;
                    case R.id.rb_little_video:
                        novp.setCurrentItem(2, false);
                        break;
                    case R.id.rb_personal_center:
                        Log.e("main", "rb_personal_center");
                        novp.setCurrentItem(3, false);
                        break;
                }
            }
        });
        findViewById(R.id.rb_send_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    //初始化并弹出对话框方法
    private void showDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_send_top, null, false);
        final OutSideListenDialog dialog = new OutSideListenDialog(this);
        dialog.setView(view);
        LinearLayout ll = view.findViewById(R.id.dialog_send_top_bg);
        final ImageView button = view.findViewById(R.id.button);
        ll.setTranslationY(ScreenUtils.getScreenHeight(this) / 9 - 100 - 30);
        float viewHeight = (float) (-ScreenUtils.getScreenHeight(this) / 9);
        float l0indeX = (float) (-ScreenUtils.getScreenWidth(this) / 3);
        float l1indeX = (float) (-ScreenUtils.getScreenWidth(this) / 9);
        float r0indeX = (float) (ScreenUtils.getScreenWidth(this) / 9);
        float r1indeX = (float) (ScreenUtils.getScreenWidth(this) / 3);

        final PointF[] points = new PointF[4];
        //变化完成后的坐标
        points[0] = new PointF(l0indeX, viewHeight);
        points[1] = new PointF(l1indeX, viewHeight);
        points[2] = new PointF(r0indeX, viewHeight);
        points[3] = new PointF(r1indeX, viewHeight);
        View view1 = view.findViewById(R.id.send_img_txt);
        View view2 = view.findViewById(R.id.little_video);
        View view3 = view.findViewById(R.id.send_video);
        View view4 = view.findViewById(R.id.question);
        final View[] imgs = new View[]{view1, view2, view3, view4};

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                ValueAnimator anim = ValueAnimator.ofFloat(0f, 100f);
                anim.setDuration(500);
                anim.setInterpolator(new OvershootInterpolator());
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                           @Override
                                           public void onAnimationUpdate(ValueAnimator animation) {
                                               float currentValue = (float) animation.getAnimatedValue();
                                               Log.e("eee", "current:" + currentValue);
                                               button.setRotation((float) (-180 + 45 * (currentValue / 100)));
                                               Log.e("eee", "roration:" + (-180 + 180 * (currentValue / 100)));
                                               openAnimation(imgs[0], currentValue, points[0], 180, 2f);
                                               openAnimation(imgs[1], currentValue, points[1], 180, 2f);
                                               openAnimation(imgs[2], currentValue, points[2], 180, 2f);
                                               openAnimation(imgs[3], currentValue, points[3], 180, 2f);
                                           }
                                       }
                );
                anim.start();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValueAnimator anim = ValueAnimator.ofFloat(0f, 100f);
                anim.setDuration(200);
                anim.setInterpolator(new LinearInterpolator());
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                           @Override
                                           public void onAnimationUpdate(ValueAnimator animation) {
                                               float currentValue = (float) animation.getAnimatedValue();
                                               button.setRotation((float) (45 - 45 * (currentValue / 100)));
                                               closeAnimation(imgs[0], currentValue, points[0], 180, 2f);
                                               closeAnimation(imgs[1], currentValue, points[1], 180, 2f);
                                               closeAnimation(imgs[2], currentValue, points[2], 180, 2f);
                                               closeAnimation(imgs[3], currentValue, points[3], 180, 2f);
                                           }
                                       });
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        dialog.dismiss();
                    }
                });
                anim.start();
            }
        });
        dialog.setOutTouchListener(new OutSideListenDialog.OutTouchListener() {
            @Override
            public void outTouch() {
                Log.e("eee", "消失吧");
                ValueAnimator anim = ValueAnimator.ofFloat(0f, 100f);
                anim.setDuration(200);
                anim.setInterpolator(new LinearInterpolator());
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float currentValue = (float) animation.getAnimatedValue();
                        button.setRotation((float) (45 - 45 * (currentValue / 100)));
                        closeAnimation(imgs[0], currentValue, points[0], 180, 2f);
                        closeAnimation(imgs[1], currentValue, points[1], 180, 2f);
                        closeAnimation(imgs[2], currentValue, points[2], 180, 2f);
                        closeAnimation(imgs[3], currentValue, points[3], 180, 2f);
                    }
                });
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        dialog.dismiss();
                    }
                });
                anim.start();
            }
        });
        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4
        dialog.getWindow().setLayout(ScreenUtils.getScreenWidth(this), ScreenUtils.getScreenHeight(this) / 4);
        dialog.getWindow().setDimAmount(0);
        dialog.getWindow().getAttributes().gravity = Gravity.BOTTOM;
        dialog.getWindow().setBackgroundDrawable(null);
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

        rgHome.check(R.id.rb_home);
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

    /**
     * @param v
     * @param value 进度条 max100
     * @param endP  结束坐标
     * @param angle 旋转度数
     * @param size  放大倍数
     */
    public void openAnimation(View v, float value, PointF endP, float angle, float size) {
        //设置控件旋转
        v.setRotation((float) (-angle + angle * (value / 100)));
        //设置缩放比例
        if (size / 100 * value < size) {
            if (size / 100 * value >= 1) {
                v.setScaleX(size / 100 * value);
                v.setScaleY(size / 100 * value);
            }
        }
        //设置控件的移动
        if (value >= 0) {
            PointF moveP = GeometryUtil.getPointByPercent(new PointF(0, 0), endP, value / 100);
            v.setTranslationX(moveP.x);
            v.setTranslationY(moveP.y);
        }
    }

    public void closeAnimation(View v, float value, PointF endP, float angle, float size) {
        value = 100 - value;
        v.setRotation((float) (-angle + angle * (value / 100)));

        if (size * (value / 100) <= size) {
            v.setScaleX(size * (value / 100));
            v.setScaleY(size * (value / 100));
        }

        if (value < 100 && value > 0f) {
            PointF moveP = GeometryUtil.getPointByPercent(new PointF(0, 0), endP, value / 100);
            v.setTranslationX(moveP.x);
            v.setTranslationY(moveP.y);
        } else if (value < 0) {
            PointF moveP = GeometryUtil.getPointByPercent(new PointF(0, 0), endP, 0);
            v.setTranslationX(moveP.x);
            v.setTranslationY(moveP.y);
        }
    }
}
