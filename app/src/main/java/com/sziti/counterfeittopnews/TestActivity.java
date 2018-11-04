package com.sziti.counterfeittopnews;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.sziti.counterfeittopnews.util.GeometryUtil;

public class TestActivity extends AppCompatActivity {
    ImageView imageView;
    boolean isOpen = false;
    int width;
    int height;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        WindowManager wm = (WindowManager)
                getSystemService(Context.WINDOW_SERVICE);
        width  = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();

        imageView = (ImageView) findViewById(R.id.button);

        float viewHeight = (float) (-200);
        float lindeX = (float) (-width/5);
        float rindeX = (float) (width/5);


        final PointF[] points = new PointF[2];
        //变化完成后的坐标
        points[0] = new PointF(lindeX,viewHeight);
        points[1] = new PointF(rindeX,viewHeight);

        ImageView leftView = (ImageView) findViewById(R.id.imageView1);
        ImageView rightView = (ImageView) findViewById(R.id.imageView2);
        final ImageView[] imgs = new ImageView[]{leftView,rightView};

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ValueAnimator anim = ValueAnimator.ofFloat(0f, 100f);
                anim.setDuration(500);
                anim.setInterpolator(new OvershootInterpolator());
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        isOpen = !isOpen;
                    }
                });
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                           @Override
                                           public void onAnimationUpdate(ValueAnimator animation) {
                                               float currentValue = (float) animation.getAnimatedValue();
                                               if(!isOpen){
                                                   Log.e("eee","current:"+ currentValue);
                                                   imageView.setRotation((float) (-180+180*(currentValue/100)));
                                                   Log.e("eee","roration:"+  (-180+180*(currentValue/100)));
                                                   openAnimation(imgs[0],currentValue,points[0],180,2f);
                                                   openAnimation(imgs[1],currentValue,points[1],180,2f);
                                               }else{
                                                   imageView.setRotation((float) (0-180*(currentValue/100)));
//                                                   currentValue = 100 -currentValue;
//                                                   imageView.setRotation((float) (-180+(180/100)*currentValue));
                                                   closeAnimation(imgs[0],currentValue, points[0],180,2f);
                                                   closeAnimation(imgs[1],currentValue,points[1],180,2f);
                                               }

                                           }
                                       }
                );
                anim.start();
            }
        });
    }

    /**
     *
     * @param v
     * @param value     进度条 max100
     * @param endP  结束坐标
     * @param angle 旋转度数
     * @param size 放大倍数
     */
    public void openAnimation(View v,float value,PointF endP,float angle,float size){
        //设置控件旋转
        v.setRotation((float) (-angle+angle*(value/100)));
        //设置缩放比例
        if(size/100*value < size ){
            if(size/100*value >=1){
                v.setScaleX(size/100*value);
                v.setScaleY(size/100*value);
            }
        }
        //设置控件的移动
        if(value >=0){
            PointF moveP = GeometryUtil.getPointByPercent(new PointF(0,0), endP, value/100);
            v.setTranslationX(moveP.x);
            v.setTranslationY(moveP.y);
        }
    }
    public void closeAnimation(View v,float value,PointF endP,float angle,float size){
        value = 100 -value;
        v.setRotation((float) (-angle+angle*(value/100)));
        if(size/100*value < size ){
            if(size/100*value >=1){
                v.setScaleX(size/100*value);
                v.setScaleY(size/100*value);
            }else{
                v.setScaleX(1);
                v.setScaleY(1);
            }
        }

        if(value <100 &&value >0f){
            PointF moveP = GeometryUtil.getPointByPercent(new PointF(0,0), endP, value/100);
            v.setTranslationX(moveP.x);
            v.setTranslationY(moveP.y);
        }else if(value<0){
            PointF moveP = GeometryUtil.getPointByPercent(new PointF(0,0), endP, 0);
            v.setTranslationX(moveP.x)      ;
            v.setTranslationY(moveP.y);
        }
    }
}
