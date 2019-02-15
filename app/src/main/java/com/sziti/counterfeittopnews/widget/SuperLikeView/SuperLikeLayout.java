package com.sziti.counterfeittopnews.widget.SuperLikeView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.sziti.counterfeittopnews.R;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Sen on 2018/2/6.
 */

public class SuperLikeLayout extends View implements AnimationEndListener {

    private static final String TAG = "SuperLikeLayout";

    private static final long INTERVAL = 40;
    //最多可以喷射多少次
    private static final int MAX_FRAME_SIZE = 16;
    //每次最多喷射多少个emoji
    private static final int ERUPTION_ELEMENT_AMOUNT = 4;
    private AnimationFramePool animationFramePool;

    private AnimationHandler animationHandler;
    private BitmapProvider.Provider provider;
    //是否有喷射动画
    private boolean hasEruptionAnimation;
    //是否有文字动画
    private boolean hasTextAnimation;
    //是否有大拇指飞赞动画
    private boolean hasPraisedAnimation;


    public SuperLikeLayout(@NonNull Context context) {
        this(context, null);
    }

    public SuperLikeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperLikeLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        animationHandler = new AnimationHandler(this);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SuperLikeLayout, defStyleAttr, 0);
        int elementAmount = a.getInteger(R.styleable.SuperLikeLayout_eruption_element_amount, ERUPTION_ELEMENT_AMOUNT);
        int maxFrameSize = a.getInteger(R.styleable.SuperLikeLayout_max_eruption_total, MAX_FRAME_SIZE);
        hasEruptionAnimation = a.getBoolean(R.styleable.SuperLikeLayout_show_emoji, true);
        hasTextAnimation = a.getBoolean(R.styleable.SuperLikeLayout_show_text, true);
        hasPraisedAnimation = a.getBoolean(R.styleable.SuperLikeLayout_show_praised, true);
        a.recycle();

        animationFramePool = new AnimationFramePool(maxFrameSize, elementAmount);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (!animationFramePool.hasRunningAnimation())
            return;
        //遍历所有AnimationFrame 并绘制Element
        // note: 需要倒序遍历 nextFrame方法可能会改变runningFrameList Size 导致异常
        List<AnimationFrame> runningFrameList = animationFramePool.getRunningFrameList();
        for (int i = runningFrameList.size() - 1; i >= 0; i--) {
            AnimationFrame animationFrame = runningFrameList.get(i);
            //修改时间间隔参数 实时绘制emoji的新位置
            List<Element> elementList = animationFrame.nextFrame(INTERVAL);
            if (animationFrame.getType() == PraisedAnimationFrame.TYPE) {
                for (Element element : elementList) {
                   drawBitmapPostScale(canvas,element.getBitmap(),element.getX(),element.getY(),((PraisedAnimationFrame.PraisedElement)element).getScale());
                }
            }else {
                //本次所有动画元素一次性绘制到屏幕上
                for (Element element : elementList) {
                    canvas.drawBitmap(element.getBitmap(), element.getX(), element.getY(), null);
                }
            }
        }

    }
    private void drawBitmapPostScale(Canvas canvas,Bitmap bmp,int x,int y,float scale) {
        Log.d("ggg", "bmp"+ scale);
        // 获取图片资源
        // Matrix类进行图片处理（缩小或者旋转）
        Matrix matrix = new Matrix();
        // 缩小一倍
        matrix.postScale(scale, scale);
        // 生成新的图片
        Bitmap dstbmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
                bmp.getHeight(), matrix, true);
        // 添加到canvas
        canvas.drawBitmap(dstbmp, x, y, null);
    }

    public void launch(int x, int y) {
        if (!hasEruptionAnimation && !hasTextAnimation) {
            return;
        }
        // 喷射动画
        if (hasEruptionAnimation) {
            AnimationFrame eruptionAnimationFrame = animationFramePool.obtain(EruptionAnimationFrame.TYPE);
            if (eruptionAnimationFrame != null && !eruptionAnimationFrame.isRunning()) {
                eruptionAnimationFrame.setAnimationEndListener(this);
                eruptionAnimationFrame.prepare(x, y, getProvider());
            }
        }
        // combo动画
        if (hasTextAnimation) {
            AnimationFrame textAnimationFrame = animationFramePool.obtain(TextAnimationFrame.TYPE);
            if (textAnimationFrame != null) {
                textAnimationFrame.setAnimationEndListener(this);
                textAnimationFrame.prepare(x, y, getProvider());
            }
        }
        if (hasPraisedAnimation) {
            AnimationFrame praisedAnimationFrame = animationFramePool.obtain(PraisedAnimationFrame.TYPE);
            if (praisedAnimationFrame != null) {
                praisedAnimationFrame.setAnimationEndListener(this);
                praisedAnimationFrame.prepare(x, y, getProvider());
            }
        }
        animationHandler.removeMessages(AnimationHandler.MESSAGE_CODE_REFRESH_ANIMATION);
        animationHandler.sendEmptyMessageDelayed(AnimationHandler.MESSAGE_CODE_REFRESH_ANIMATION, INTERVAL);

    }

    public boolean hasAnimation() {
        return animationFramePool.hasRunningAnimation();
    }

    public void setProvider(BitmapProvider.Provider provider) {
        this.provider = provider;
    }

    public BitmapProvider.Provider getProvider() {
        if (provider == null) {
            provider = new BitmapProvider.Builder(getContext())
                    .build();
        }
        return provider;
    }


    /**
     * 回收SurpriseView  添加至空闲队列方便下次使用
     */
    private void onRecycle(AnimationFrame animationFrame) {
        Log.v(TAG, "=== AnimationFrame recycle ===");
        //清除elements
        animationFrame.reset();
        //将animatinoFrame放入缓存队列备用
        animationFramePool.recycle(animationFrame);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!hasAnimation())
            return;
        // 回收所有动画 并暂停动画
        animationFramePool.recycleAll();

        animationHandler.removeMessages(AnimationHandler.MESSAGE_CODE_REFRESH_ANIMATION);
    }

    //动画执行完成回收
    @Override
    public void onAnimationEnd(AnimationFrame animationFrame) {
        onRecycle(animationFrame);
    }


    private static final class AnimationHandler extends Handler {
        public static final int MESSAGE_CODE_REFRESH_ANIMATION = 1001;
        private WeakReference<SuperLikeLayout> weakReference;

        public AnimationHandler(SuperLikeLayout superLikeLayout) {
            weakReference = new WeakReference<>(superLikeLayout);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MESSAGE_CODE_REFRESH_ANIMATION && weakReference != null && weakReference.get() != null) {
                weakReference.get().invalidate();
                // 动画还未结束继续刷新  根据animationpool动画池是否还有未执行的动画来决定是否刷新
                if (weakReference.get().hasAnimation()) {
                    sendEmptyMessageDelayed(MESSAGE_CODE_REFRESH_ANIMATION, INTERVAL);
                }
            }

        }
    }

}
