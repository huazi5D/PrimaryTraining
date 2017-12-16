package com.li.primary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.li.primary.util.UtilIntent;
import com.li.primary.view.MyToast;

/**
 * Created by liu on 2017/6/3.
 */

public class BaseActivity extends AppCompatActivity{
    /**
     * 全局的加载框对象，已经完成初始化.
     */
    public LoadDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onBackPressed() {
        UtilIntent.finishDIY(this);
    }


    /**
     * 显示加载框
     */
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new LoadDialog(this);
        }
        //如果加载框不显示， 那么就显示加载框
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * 移除加载框.
     */
    public void removeProgressDialog() {
        if (mProgressDialog != null) {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
    }


    /**
     * 描述：Toast提示文本.
     *
     * @param text 文本
     */
    public void showToast(String text) {
        MyToast toast = new MyToast(this);
        toast.setText(text);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }



    public void finishAnimator(){
        UtilIntent.finishDIY(this);
    }
}
