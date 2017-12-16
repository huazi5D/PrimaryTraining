package com.li.primary.main.mine;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;


import com.li.primary.R;
import com.li.primary.base.AppData;
import com.li.primary.base.BaseActivity;

import java.io.File;

/**
 * Created by liu on 15/6/23.
 */
public class ChoosePicDialog extends Dialog implements View.OnClickListener{

    private BaseActivity activity;
    private Button mBtnCamera;
    private Button mBtnPhoto;
    private Button mBtnCancle;

    public ChoosePicDialog(BaseActivity activity) {
        super(activity, R.style.ChoosePicDialog);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choose_pic);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
        initView();
    }

    private void initView() {
        mBtnCamera = (Button) findViewById(R.id.btn_camera);
        mBtnPhoto = (Button) findViewById(R.id.btn_photo);
        mBtnCancle = (Button) findViewById(R.id.btn_cancle);
        mBtnCamera.setOnClickListener(this);
        mBtnCancle.setOnClickListener(this);
        mBtnPhoto.setOnClickListener(this);
    }


    private void camera()
    {
        File file = new File(AppData.PATH);
        if(!file.exists()){
            file.mkdirs();
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(AppData.PATH, "touxiang.jpg")));
        activity.startActivityForResult(intent, 0);
        dismiss();
    }

    private void photo()
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        activity.startActivityForResult(intent, 1);
        dismiss();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cancle:
                dismiss();
                break;

            case R.id.btn_camera:
                dismiss();
                camera();
                break;

            case R.id.btn_photo:
                dismiss();
                photo();
                break;
        }
    }
}
