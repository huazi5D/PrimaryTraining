package com.li.primary.main.identify.view;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.li.primary.R;
import com.li.primary.main.identify.CameraManager;


/**
 * Created by liu on 15/1/29.
 */
public class PicViewfinderView extends View{
    /**
     * 手机屏幕的密度
     */
    private static float density;
    /**
     * 画笔
     */
    private Paint paint;
    /**
     * 遮挡屏的颜色
     */
    private final int maskColor;
    /**
     * 内容框颜色
     */
    private final int resultColor;
    /**
     * 结果图
     */
    private Bitmap resultBitmap;

    public PicViewfinderView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public PicViewfinderView(Context context) {
        this(context, null);
    }

    public PicViewfinderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        density = context.getResources().getDisplayMetrics().density;
        paint = new Paint();
        Resources resources = getResources();
        maskColor = resources.getColor(R.color.viewfinder_mask);
        resultColor = resources.getColor(R.color.result_view);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /**
         * 中间扫描框
         */
        Rect frame = CameraManager.get().getFramingRect();
        if(frame == null)
        {
            return;
        }
        //获取屏幕的宽和高
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        paint.setColor(resultBitmap != null ? resultColor : maskColor);

        //画出扫描框外面的阴影部分，共四个部分，扫描框的上面到屏幕上面，扫描框的下面到屏幕的下面
        //扫描框的左边面到屏幕的左边，扫描框的右边到屏幕的右边
        canvas.drawRect(0, 0, width, frame.top, paint);
        canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
        canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1,
                paint);
        canvas.drawRect(0, frame.bottom + 1, width, height, paint);
    }

    public void drawViewfinder() {
        resultBitmap = null;
        invalidate();
    }

    /**
     * Draw a bitmap with the result points highlighted instead of the live
     * scanning display.
     *
     * @param barcode
     *            An image of the decoded barcode.
     */
    public void drawResultBitmap(Bitmap barcode) {
        resultBitmap = barcode;
        invalidate();
    }
}
