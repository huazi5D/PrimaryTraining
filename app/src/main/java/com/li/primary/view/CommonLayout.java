package com.li.primary.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.li.primary.R;

/**
 * Created by liu on 2017/6/20.
 */

public class CommonLayout extends LinearLayout{
    public static final int SHOW_RELOAD = 0;
    public static final int SHOW_STATE = 1;
    private Button mBtnReload;
    private TextView mTvState;
    private View mView;

    public CommonLayout(Context context) {
        this(context, null);
    }

    public CommonLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CommonLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mView = LayoutInflater.from(context).inflate(R.layout.common_layout, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(mView, params);
        mView.setVisibility(View.GONE);
        mBtnReload = (Button) mView.findViewById(R.id.btn_reload);
        mTvState = (TextView) mView.findViewById(R.id.tv_state);
    }

    public void setListener(OnClickListener listener) {
        mBtnReload.setOnClickListener(listener);
    }


    /**
     * 显示数据View 如果没有数据将隐藏
     *
     * @param isData
     */
    public void showData(boolean isData) {
        if (mView == null) {
            return;
        }
        int dataVisibe = isData ? View.VISIBLE : View.GONE;
        int nullDataVisibe = isData ? View.GONE : View.VISIBLE;
        for (int i = 0; i < this.getChildCount(); i++) {
            View item = this.getChildAt(i);
            if (item.equals(mView)) {
                item.setVisibility(nullDataVisibe);
            } else {
                item.setVisibility(dataVisibe);
            }
        }
    }

    public void show(int mark){
        showData(false);
        switch (mark){
            case SHOW_RELOAD:
                mBtnReload.setVisibility(View.VISIBLE);
                mTvState.setVisibility(View.GONE);
                break;

            case SHOW_STATE:
                mBtnReload.setVisibility(View.GONE);
                mTvState.setVisibility(View.VISIBLE);
                break;
        }
    }


}
