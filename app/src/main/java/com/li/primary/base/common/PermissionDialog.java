package com.li.primary.base.common;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.li.primary.R;

/**
 * Created by liu on 2017/6/16.
 */

public class PermissionDialog extends Dialog{
    private Button mBtnCancle;
    private Button mBtnSetting;
    private TextView mTvContent;
    private View mView;
    private boolean isCanCancle = true;
    private String content;
    private View.OnClickListener mLeftListener;
    private View.OnClickListener mRightListener;

    public PermissionDialog(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_permission);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        initView();
    }

    public void setCanCancle(boolean canCancle) {
        isCanCancle = canCancle;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLeftListener(View.OnClickListener leftListener) {
        mLeftListener = leftListener;
    }

    public void setRightListener(View.OnClickListener rightListener) {
        mRightListener = rightListener;
    }

    private void initView() {
        mBtnCancle = (Button) findViewById(R.id.btn_cancle);
        mBtnCancle.setOnClickListener(mLeftListener);
        mBtnSetting = (Button) findViewById(R.id.btn_setting);
        mBtnSetting.setOnClickListener(mRightListener);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mTvContent.setText(content);
        mView = findViewById(R.id.v_line);
        if(isCanCancle){
            mBtnCancle.setVisibility(View.VISIBLE);
            mBtnCancle.setBackgroundResource(R.drawable.btn_bottom_left_radius);
            mBtnSetting.setVisibility(View.VISIBLE);
            mBtnSetting.setBackgroundResource(R.drawable.btn_bottom_right_radius);
            mView.setVisibility(View.VISIBLE);
            setCanceledOnTouchOutside(true);
        }else{
            mBtnCancle.setVisibility(View.GONE);
            mBtnSetting.setVisibility(View.VISIBLE);
            mBtnSetting.setBackgroundResource(R.drawable.btn_bottom_radius);
            mView.setVisibility(View.GONE);
            setCanceledOnTouchOutside(false);
            setOnKeyListener(new OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    return true;
                }
            });
        }
    }




}
