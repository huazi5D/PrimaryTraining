package com.li.primary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

/**
 * Created by liu on 2017/6/12.
 */

public class LfSeekBar extends SeekBar{
    public LfSeekBar(Context context) {
        super(context);
    }

    public LfSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LfSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return true;
    }
}
