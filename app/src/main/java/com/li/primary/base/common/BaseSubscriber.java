package com.li.primary.base.common;

import com.li.primary.base.BaseActivity;
import com.li.primary.base.bean.BaseResult;
import com.li.primary.main.mine.LoginActivity;
import com.li.primary.util.UtilIntent;

import java.lang.ref.WeakReference;

import rx.Subscriber;

/**
 * Created by liu on 2017/6/30.
 */

public abstract class BaseSubscriber<T extends BaseResult> extends Subscriber<T>{
    private WeakReference<BaseActivity> mWeakReference;

    public BaseSubscriber(BaseActivity activity) {
        mWeakReference = new WeakReference<BaseActivity>(activity);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mWeakReference != null && mWeakReference.get() != null){
            mWeakReference.get().showProgressDialog();
        }
    }

    @Override
    public void onCompleted() {
        if(mWeakReference != null && mWeakReference.get() != null){
            mWeakReference.get().removeProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        if(mWeakReference != null && mWeakReference.get() != null){
            mWeakReference.get().removeProgressDialog();
        }
    }

    @Override
    public void onNext(T t) {
        if(t.isStatus()){
            success(t);
        }else{
            if(t.getMessage().equals("99")){
                if(mWeakReference != null && mWeakReference.get() != null) {
                    UtilIntent.intentDIYLeftToRight(mWeakReference.get(), LoginActivity.class);
                }
            }else{
                if(mWeakReference != null && mWeakReference.get() != null){
                    mWeakReference.get().showToast(t.getMessage());
                }
            }
        }
    }

    public abstract void success(T t);

}
