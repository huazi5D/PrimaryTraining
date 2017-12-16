package com.li.primary.main.mine;

import android.app.Dialog;
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
 * Created by liu on 2017/6/15.
 */

public class ScoreDialog extends Dialog implements View.OnClickListener{
    private ExamActivity mActivity;
    private Button mBtnOk;
    private TextView mTvScore;
    private int score = 0;

    public ScoreDialog(@NonNull ExamActivity mActivity) {
        super(mActivity,R.style.CustomDialog);
        this.mActivity = mActivity;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_score);
        setCanceledOnTouchOutside(false);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        initView();
    }

    private void initView() {
        mTvScore = (TextView) findViewById(R.id.tv_score);
        mBtnOk = (Button) findViewById(R.id.btn_ok);
        mBtnOk.setOnClickListener(this);
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ok:
                dismiss();
                mActivity.finishAnimator();
                break;
        }
    }

    @Override
    public void show() {
        super.show();
        mTvScore.setText(String.valueOf(score));
    }
}
