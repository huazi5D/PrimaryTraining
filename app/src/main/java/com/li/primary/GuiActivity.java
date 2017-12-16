package com.li.primary;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.li.primary.base.AppConfig;
import com.li.primary.base.BaseActivity;
import com.li.primary.base.common.PermissionDialog;
import com.li.primary.util.AfterPermissionGranted;
import com.li.primary.util.UtilIntent;
import com.li.primary.util.UtilPermission;

import java.util.List;

/**
 * Created by liu on 2017/6/16.
 */

public class GuiActivity extends BaseActivity implements UtilPermission.PermissionCallbacks{
    private PermissionDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui);
        if(!UtilPermission.hasPermissions(GuiActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})){
            UtilPermission.requestPermissions(GuiActivity.this, AppConfig.PERMISSION_CODE, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA});
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(UtilPermission.hasPermissions(GuiActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})){
            mHandler.sendEmptyMessageDelayed(0,2000);
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            UtilIntent.intentDIYLeftToRight(GuiActivity.this, MainActivity.class);
            finish();
        }
    };

    @Override
    public void onBackPressed() {
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
        if(dialog == null) {
            dialog = new PermissionDialog(GuiActivity.this);
            dialog.setCanCancle(false);
            dialog.setContent("当前应用需要SD卡存储、拍照权限，请打开所需权限");
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
                        UtilPermission.executePermissionsRequest(GuiActivity.this, perms.toArray(new String[perms.size()]), AppConfig.PERMISSION_CODE);
                    }
                }
            });
        }
        if(!dialog.isShowing()) {
            dialog.show();
        }
    }

    @AfterPermissionGranted(AppConfig.PERMISSION_CODE)
    public void toMain(){
        mHandler.sendEmptyMessageDelayed(0,2000);
    }

}
