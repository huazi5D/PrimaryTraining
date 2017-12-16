package com.li.primary.main.identify;

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
 * Created by liu on 2017/6/26.
 */

public class FaceDialog extends Dialog implements View.OnClickListener{
    private Button mBtnCancle;
    private Button mBtnVerify;
    private View vLine;
    private TextView mTvContent;
    private String content;
    //0：人脸不能离开人脸框，1：人脸对比， 2：人脸动作
    private int type = 0;

    private FaceCallBackListener mListener;


    public FaceDialog(@NonNull Context context) {
        super(context, R.style.CustomDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_face);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setCanceledOnTouchOutside(false);
        initView();
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return true;
            }
        });
    }

    public void setListener(FaceCallBackListener listener) {
        mListener = listener;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(int type) {
        this.type = type;
    }

    private void initView() {
        mBtnCancle = (Button) findViewById(R.id.btn_cancle);
        mBtnCancle.setOnClickListener(this);
        mBtnVerify = (Button) findViewById(R.id.btn_verify);
        mBtnVerify.setOnClickListener(this);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        vLine = findViewById(R.id.v_line);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cancle:
                dismiss();
                if (mListener != null){
                    mListener.cancle();
                }
                break;

            case R.id.btn_verify:
                dismiss();
                if (mListener != null){
                    switch (type){
                        case 0:
                        case 1:
                            mListener.face();
                            break;

                        case 2:

                            mListener.action();
                    }
                }
                break;
        }
    }



    @Override
    public void show() {
        super.show();
        mTvContent.setText(content);
        if(type == 0){
            mBtnVerify.setVisibility(View.GONE);
            vLine.setVisibility(View.GONE);
        }else{
            mBtnCancle.setVisibility(View.VISIBLE);
            mBtnVerify.setVisibility(View.VISIBLE);
            vLine.setVisibility(View.VISIBLE);
        }
    }

    public interface FaceCallBackListener{
        public void cancle();
        public void face();
        public void action();
    }
}
