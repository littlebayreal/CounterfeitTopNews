package com.sziti.counterfeittopnews.widget.SuperLikeView;

import android.graphics.Bitmap;

/**
 * Created by Sen on 2018/3/9.
 */

public interface Element {

    int getX();

    int getY();

    Bitmap getBitmap();
    //根据动画执行的时间 对元素进行实时偏移
    void evaluate(int start_x, int start_y, double time);

}
