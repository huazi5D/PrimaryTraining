package com.li.primary.main.identify.handler;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.li.primary.R;
import com.li.primary.main.identify.CameraManager;
import com.li.primary.main.identify.IdentifyActivity;
import com.li.primary.main.identify.thread.IdentifyThread;


/**
 * Created by liu on 15/1/29.
 */
public class PicCaptureActivityHandler extends Handler{
    private static final String TAG = PicCaptureActivityHandler.class.getSimpleName();

    private IdentifyActivity activity;

    private final IdentifyThread identifyThread;
    private State state;

    private enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    public PicCaptureActivityHandler(IdentifyActivity activity)
    {
        this.activity = activity;
        this.identifyThread = new IdentifyThread(activity);
        identifyThread.start();
        state = State.SUCCESS;
        // Start ourselves capturing previews and decoding.
        CameraManager.get().startPreview();
        restartPreviewAndDecode();
    }

    @Override
    public void handleMessage(Message message) {
        switch (message.what) {
            case R.id.auto_focus:
                //Log.d(TAG, "Got auto-focus message");
                // When one auto focus pass finishes, start another. This is the closest thing to
                // continuous AF. It does seem to hunt a bit, but I'm not sure what else to do.
                if (state == State.PREVIEW) {
                    CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
                }
                break;
            case R.id.restart_preview:
                Log.d(TAG, "Got restart preview message");
                restartPreviewAndDecode();
                break;
            case R.id.decode_succeeded:
                Log.d(TAG, "Got decode succeeded message");
                state = State.SUCCESS;
                Bundle bundle = message.getData();

                /***********************************************************************/
//                Bitmap barcode = bundle == null ? null :
//                        (Bitmap) bundle.getParcelable(identifyThread.BARCODE_BITMAP);//���ñ����߳�
                    byte[] bytes = bundle.getByteArray(identifyThread.BARCODE_BITMAP);
                int width = bundle.getInt("x");
                int height = bundle.getInt("y");
//                    activity.handleDecode(bytes, width, height);
//                activity.handleDecode(barcode);//���ؽ��?        /***********************************************************************/
                Toast.makeText(activity, "Scan success!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.decode_failed:
                // We're decoding as fast as possible, so when one decode fails, start another.
                state = State.PREVIEW;
                CameraManager.get().requestPreviewFrame(identifyThread.getHandler(), R.id.decode);
                Toast.makeText(activity, "识别失败", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.return_scan_result:
//                Log.d(TAG, "Got return scan result message");
//                activity.setResult(Activity.RESULT_OK, (Intent) message.obj);
//                activity.finish();
//                break;
//            case R.id.launch_product_query:
//                Log.d(TAG, "Got product query message");
//                String url = (String) message.obj;
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//                activity.startActivity(intent);
//                break;
        }
    }

    /**
     * 同步退出
     */
    public void quitSynchronously() {
        state = State.DONE;
        CameraManager.get().stopPreview();
        Message quit = Message.obtain(identifyThread.getHandler(), R.id.quit);
        quit.sendToTarget();
        try {
            identifyThread.join();
        } catch (InterruptedException e) {
            // continue
        }

        // Be absolutely sure we don't send any queued up messages
        removeMessages(R.id.decode_succeeded);
        removeMessages(R.id.decode_failed);
    }

    private void restartPreviewAndDecode() {
        if (state == State.SUCCESS) {
            state = State.PREVIEW;
            CameraManager.get().requestPreviewFrame(identifyThread.getHandler(), R.id.decode);
            CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
//            activity.drawViewfinder();
        }
    }
}
