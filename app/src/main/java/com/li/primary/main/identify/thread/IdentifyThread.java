package com.li.primary.main.identify.thread;

import android.os.Handler;
import android.os.Looper;


import com.li.primary.main.identify.IdentifyActivity;
import com.li.primary.main.identify.handler.DecodeHandler;

import java.util.concurrent.CountDownLatch;


/**
 * Created by liu on 15/1/29.
 */
public class IdentifyThread extends Thread{
    public static final String BARCODE_BITMAP = "barcode_bitmap";
    private IdentifyActivity activity;

    private Handler handler;
    private final CountDownLatch handlerInitLatch;

    public IdentifyThread(IdentifyActivity activity)
    {
        this.activity = activity;
        this.handlerInitLatch = new CountDownLatch(1);
    }

    public Handler getHandler()
    {
        try{
            handlerInitLatch.await();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return handler;
    }

    @Override
    public void run() {
        Looper.prepare();
        handler = new DecodeHandler(activity);
        handlerInitLatch.countDown();
        Looper.loop();
    }
}
