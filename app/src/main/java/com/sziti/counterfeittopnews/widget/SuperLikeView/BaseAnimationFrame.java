package com.sziti.counterfeittopnews.widget.SuperLikeView;

import java.util.List;

/**
 * Created by Sen on 2018/3/9.
 */

public abstract class BaseAnimationFrame implements AnimationFrame{
    //本帧动画 所有子元素的统一起点
    private int x;
    private int y;
    private double currentTime;
    private boolean isRunnable;
    List<Element> elements;
    private AnimationEndListener animationEndListener;
    long duration;

    public BaseAnimationFrame(long duration){
        this.duration = duration;
    }

    @Override
    public boolean isRunning() {
        return isRunnable;
    }

    @Override
    public List<Element> nextFrame(long interval) {
        currentTime += interval;
        //如果本次动画的持续时间超过了总执行时间
        if(currentTime >= duration){
            isRunnable = false;
            if(animationEndListener != null){
                animationEndListener.onAnimationEnd(this);
            }
        }else{//动态实时计算本次动画所有元素的位置
            for(Element element : elements){
                element.evaluate(x, y, currentTime);
            }
        }
        return elements;
    }

    @Override
    public void setAnimationEndListener(AnimationEndListener animationEndListener) {
        this.animationEndListener = animationEndListener;
    }

    @Override
    public void reset() {
        currentTime = 0;
        if(elements != null){
            elements.clear();
        }
    }

    protected abstract List<Element> generatedElements(int x, int y, BitmapProvider.Provider bitmapProvider);

    protected void setStartPoint(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean onlyOne() {
        return false;
    }
}
