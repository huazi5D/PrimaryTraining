package com.li.primary.main.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.li.primary.R;
import com.li.primary.base.BaseActivity;
import com.li.primary.base.bean.BaseResult;
import com.li.primary.base.http.HttpService;
import com.li.primary.base.http.RetrofitUtil;
import com.li.primary.util.UtilMD5Encryption;

import rx.Subscriber;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

/**
 * Created by liu on 2017/6/12.
 */

public class ModifyActivity extends BaseActivity implements View.OnClickListener{
    private ImageView mIvBack;
    private EditText mEtId;
    private EditText mEtNewPassword;
    private EditText mEtNewRePassword;
    private Button mBtnSubmit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        initView();
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mEtId = (EditText) findViewById(R.id.et_id);
        mEtNewPassword = (EditText) findViewById(R.id.et_new_password);
        mEtNewRePassword = (EditText) findViewById(R.id.et_new_repassword);
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
                String id = mEtId.getText().toString().trim();
                if(TextUtils.isEmpty(id)){
                    showToast("请输入身份证号");
                    return;
                }
                String password = mEtNewPassword.getText().toString().trim();
                String repassword = mEtNewRePassword.getText().toString().trim();
                if(TextUtils.isEmpty(password)){
                    showToast("请输入密码");
                    return;
                }
                if(TextUtils.isEmpty(repassword)){
                    showToast("请再次输入密码");
                    return;
                }
                if(!password.equals(repassword)){
                    showToast("两次输入的密码不一致");
                    return;
                }
                if(password.length() < 6 || repassword.length() < 6){
                    showToast("密码不能少于6位");
                    return;
                }
                RetrofitUtil.getInstance().create(HttpService.class).resetPasswd(id, UtilMD5Encryption.getMd5Value(password)).subscribeOn(io()).observeOn(mainThread()).subscribe(new Subscriber<BaseResult>() {

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
                        showToast("修改失败");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(BaseResult result) {
                        if(result.isStatus()){
                            finishAnimator();
                        }else{
                            showToast(result.getMessage());
                        }
                    }
                });
                break;
        }
    }
}
