package com.li.primary.main.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.li.primary.R;
import com.li.primary.base.AppData;
import com.li.primary.base.BaseActivity;
import com.li.primary.base.bean.BaseResult;
import com.li.primary.base.http.HttpService;
import com.li.primary.base.http.RetrofitUtil;

import rx.Subscriber;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

/**
 * Created by liu on 2017/6/12.
 */

public class FeedBackActivity extends BaseActivity implements View.OnClickListener{
    private ImageView mIvBack;
    private EditText mEtContent;
    private Button mBtnSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mEtContent = (EditText) findViewById(R.id.et_content);
        mBtnSubmit = (Button) findViewById(R.id.btn_submit);
        mIvBack.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;

            case R.id.btn_submit:
                String content = mEtContent.getText().toString().trim();
                if(TextUtils.isEmpty(content)){
                    showToast("请输入内容");
                    return;
                }
                feedback(AppData.token, content);
                break;
        }
    }

    private void feedback(String token, String content){
        RetrofitUtil.getInstance().create(HttpService.class).insUsersuggest(token, content).subscribeOn(io()).observeOn(mainThread()).subscribe(new Subscriber<BaseResult>() {

            @Override
            public void onStart() {
                super.onStart();
                showProgressDialog();
            }

            @Override
            public void onCompleted() {
                removeProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                removeProgressDialog();
                e.printStackTrace();
            }

            @Override
            public void onNext(BaseResult result) {
                if(result.isStatus()){
                    showToast("反馈成功");
                    finishAnimator();
                }
            }
        });
    }
}
