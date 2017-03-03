package com.boge.library;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

/**
 * @author boge
 * @version 1.0
 * @date 2017/3/2
 * 当设置图片后显示联系人图片，当设置一个字母或者一个汉字时显示字符图。
 */

public class IconDrawable extends Drawable {

    private Paint mTextPaint;
    private String mContentText;
    private int fontSize;

    private int mBgColor = Color.GREEN;
    private int mTextColor = Color.WHITE;

    private Bitmap mBitMap;

    private Path mPath;
    private Matrix mMatrix;

    public IconDrawable() {
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mPath = new Path();
        mMatrix = new Matrix();
    }

    /**
     * 设置文字
     * @param mContentText
     * @return
     */
    public IconDrawable setmContentText(String mContentText) {
        this.mContentText = mContentText;
        this.mBitMap = null;
        invalidateSelf();
        return this;
    }

    /**
     * 设置文字大小
     * @param fontSize
     * @return
     */
    public IconDrawable setFontSize(int fontSize) {
        this.fontSize = fontSize;
        invalidateSelf();
        return this;
    }

    /**
     * 设置图片
     * @param mBitMap
     * @return
     */
    public IconDrawable setmBitMap(Bitmap mBitMap) {
        this.mBitMap = mBitMap;
        this.mContentText = null;
        invalidateSelf();
        return this;
    }

    /**
     * 设置背景颜色
     * @param mBgColor
     * @return
     */
    public IconDrawable setmBgColor(int mBgColor) {
        this.mBgColor = mBgColor;
        invalidateSelf();
        return this;
    }

    /**
     * 设置文字颜色
     * @param mTextColor
     * @return
     */
    public IconDrawable setmTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        invalidateSelf();
        return this;
    }

    @Override
    public void draw(Canvas canvas) {
        Rect rect = getBounds();

        int layer = canvas.saveLayer(new RectF(rect), null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(rect.left, rect.top);
        mPath.reset();
        //添加圆
        mPath.addCircle(rect.width() / 2, rect.height() / 2,
                Math.min(rect.width(), rect.height()) / 2, Path.Direction.CW);
        canvas.clipPath(mPath);

        if(mBitMap == null && !TextUtils.isEmpty(mContentText)){
            mTextPaint.setColor(mBgColor);
            canvas.drawRect(rect, mTextPaint);
            int size = this.fontSize <= 0 ? Math.min(rect.width(), rect.height()) / 2 : this.fontSize;
            mTextPaint.setTextSize(size);
            mTextPaint.setColor(mTextColor);
            canvas.drawText(mContentText, rect.width() / 2,
                    rect.height() / 2 - ((mTextPaint.descent() + mTextPaint.ascent()) / 2) , mTextPaint);
        } else {
            mMatrix.setScale(rect.width() * 1.0f / mBitMap.getWidth(),
                    rect.height() * 1.0f / mBitMap.getHeight());
            Bitmap scaleContentBitmap = Bitmap.createBitmap(mBitMap, 0, 0,
                    mBitMap.getWidth(), mBitMap.getHeight(),
                    mMatrix, true);
            canvas.drawBitmap(scaleContentBitmap, rect, rect, null);
        }
        canvas.restoreToCount(layer);
    }

    @Override
    public void setAlpha(int alpha) {
        mTextPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mTextPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
