package com.sziti.counterfeittopnews.widget.SuperLikeView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.sziti.counterfeittopnews.R;

import java.util.ArrayList;
import java.util.List;

/*点赞红手升起动画*/
public class PraisedAnimationFrame extends BaseAnimationFrame {
    public static final int TYPE = 3;
    private long lastClickTimeMillis;

    //    private int likeCount;
    public PraisedAnimationFrame(long duration) {
        super(duration);
    }

    //本动画只有在第一次点击时出现  combo重置后第一次点击出现
    private boolean isFirstClickInDuration() {
        if (System.currentTimeMillis() - lastClickTimeMillis < 1000) {
            lastClickTimeMillis = System.currentTimeMillis();
            return false;
        } else {
            lastClickTimeMillis = System.currentTimeMillis();
            return true;
        }
    }

    //整个动画池中只能有一个该动画
    @Override
    public boolean onlyOne() {
        return true;
    }

    @Override
    protected List<Element> generatedElements(int x, int y, BitmapProvider.Provider bitmapProvider) {
        List<Element> elements = new ArrayList<>();
        //如果是combo开始
        if (isFirstClickInDuration()) {
            Log.e("ggg", "升起大拇指");
            Element element = new PraisedElement(bitmapProvider.getSpecialBitmap(R.mipmap.praised), x);
            elements.add(element);
            return elements;
        }
        Log.e("ggg", "不做任何反应");
        return elements;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public void prepare(int x, int y, BitmapProvider.Provider bitmapProvider) {
        reset();
        setStartPoint(x, y);
        elements = generatedElements(x, y, bitmapProvider);
    }

    public static class PraisedElement implements Element {
        private int x;
        private int y;
        private float scale = 1.0f;
        private Bitmap bitmap;

        public PraisedElement(Bitmap bitmap, int x) {
            this.bitmap = bitmap;
            this.x = x;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }

        @Override
        public Bitmap getBitmap() {
            return bitmap;
        }

        public float getScale() {
            return scale;
        }

        @Override
        public void evaluate(int start_x, int start_y, double time) {
            Log.e("hhh", "time:" + time);
            scale = (float) (1.5 * (time / 300));
            x = (int) (start_x - (bitmap.getWidth()/3 * scale));
            y = (int) (start_y - (bitmap.getHeight()/1.2 * scale));
        }
    }
}
