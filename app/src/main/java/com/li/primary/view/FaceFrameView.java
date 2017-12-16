package com.li.primary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liu on 2017/6/22.
 */

public class FaceFrameView extends View{
    private Paint mPaint;

    public FaceFrameView(Context context) {
        super(context);
    }

    public FaceFrameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FaceFrameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setColor(0xff000000);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(500, 1000, 300, mPaint);
    }
}
