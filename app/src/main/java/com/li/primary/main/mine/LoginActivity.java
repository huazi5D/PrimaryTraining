package com.li.primary.main.mine;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.li.primary.R;
import com.li.primary.base.AppConfig;
import com.li.primary.base.AppData;
import com.li.primary.base.BaseActivity;
import com.li.primary.base.bean.LoginResult;
import com.li.primary.base.common.PermissionDialog;
import com.li.primary.base.common.TokenManager;
import com.li.primary.base.http.HttpService;
import com.li.primary.base.http.RetrofitUtil;
import com.li.primary.util.UtilIntent;
import com.li.primary.util.UtilMD5Encryption;
import com.li.primary.util.UtilPermission;
import com.li.primary.util.UtilSPutil;

import java.util.List;

import rx.Subscriber;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

/**
 * Created by liu on 2017/6/12.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, UtilPermission.PermissionCallbacks {
    private ImageView mIvBack;
    private EditText mEtId;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private Button mBtnRegister;
    private TextView mTvForget;
    private TextView mTvPhone;
    private PermissionDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppData.token = null;
        AppData.examYN = null;
        AppData.url = null;
        TokenManager.signout(this);
        initView();
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mEtId = (EditText) findViewById(R.id.et_id);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mTvForget = (TextView) findViewById(R.id.tv_forget);
        mTvPhone = (TextView) findViewById(R.id.tv_phone);
        mIvBack.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
        mTvForget.setOnClickListener(this);
        mTvPhone.setOnClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;

            case R.id.btn_login:
                String id = mEtId.getText().toString();
                String password = mEtPassword.getText().toString();
                if (TextUtils.isEmpty(id)) {
                    showToast("身份证号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    showToast("请输入密码");
                    return;
                }
                login(id, UtilMD5Encryption.getMd5Value(password));
                break;

            case R.id.btn_register:
                UtilIntent.intentDIYLeftToRight(LoginActivity.this, RegisterActivity.class);

                break;

            case R.id.tv_forget:
                UtilIntent.intentDIYLeftToRight(LoginActivity.this, ModifyActivity.class);
                break;

            case R.id.tv_phone:
                if (!UtilPermission.hasPermission(this, Manifest.permission.CALL_PHONE)) {
                    this.requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, AppConfig.PERMISSION_CODE);
                } else {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "4000600359"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                break;
        }
    }


    private void login(String id, String password) {
        RetrofitUtil.getInstance().create(HttpService.class).signin(id, password).subscribeOn(io()).observeOn(mainThread()).subscribe(new Subscriber<LoginResult>() {

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
            public void onNext(LoginResult result) {
                if(result.isStatus()){
                    String token = result.getData().getToken();
                    Boolean examPassed = result.getData().getExamPassed();
                    AppData.token = token;
                    AppData.examYN = result.getData().getExamYN();
                    AppData.url = result.getData().getFacefirsturl();
                    UtilSPutil.getInstance(LoginActivity.this).setString("token", token);
                    UtilSPutil.getInstance(LoginActivity.this).setBoolean("ExamPassed", examPassed);
                    finishAnimator();
                }else{
                    showToast(result.getMessage());
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        UtilPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }

    @Override
    public void onPermissionGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionDenied(int requestCode, final List<String> perms) {
        if (dialog == null) {
            dialog = new PermissionDialog(LoginActivity.this);
            dialog.setCanCancle(false);
            dialog.setContent("当前应用需要电话权限，请打开所需权限");
            dialog.setLeftListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.setRightListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    boolean shouldShowRationale = false;
                    for (int i = 0; i < perms.size(); i++) {
                        shouldShowRationale = shouldShowRequestPermissionRationale(perms.get(i));
                    }
                    if (!shouldShowRationale) {
                        Intent intent = new Intent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        startActivity(intent);
                    } else {
                        // 直接申请权限
                        UtilPermission.executePermissionsRequest(LoginActivity.this, perms.toArray(new String[perms.size()]), AppConfig.PERMISSION_CODE);
                    }
                }
            });
        }
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }
}
