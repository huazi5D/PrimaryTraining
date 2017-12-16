package com.li.primary.main.identify;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;


import com.li.primary.main.identify.listener.AutoFocusCallback;
import com.li.primary.main.identify.listener.PreviewCallback;

import java.io.IOException;


/**
 * Created by liu on 15/1/29.
 */
public final class CameraManager {

    private static final String TAG = CameraManager.class.getSimpleName();

    private static final int MIN_FRAME_WIDTH = 400;
    private static final int MIN_FRAME_HEIGHT = 400;
    private static final int MAX_FRAME_WIDTH = 1000;
    private static final int MAX_FRAME_HEIGHT = 1000;
    private static CameraManager cameraManager;

    private Context context;
    private final CameraConfigurationManager configManager;

    private final boolean useOneShotPreviewCallback;

    private Camera camera;
    //是否初始化
    private boolean initialized = false;

    //是否预览中
    private boolean previewing;

    /**
     * Preview frames are delivered here, which we pass on to the registered handler. Make sure to
     * clear the handler so it will only receive one message.
     */
    private final PreviewCallback previewCallback;
    /** Autofocus callbacks arrive here, and are dispatched to the Handler which requested them. */
    private final AutoFocusCallback autoFocusCallback;

    /**
     * 中间扫描框
     */
    private Rect framingRect;
    private Rect framingRectInPreview;

    static final int SDK_INT; // Later we can use Build.VERSION.SDK_INT
    static {
        int sdkInt;
        try {
            sdkInt = Build.VERSION.SDK_INT;
        } catch (NumberFormatException nfe) {
            // Just to be safe
            sdkInt = 10000;
        }
        SDK_INT = sdkInt;
    }

    public static void init(Context context)
    {
        if(cameraManager == null)
        {
            cameraManager = new CameraManager(context);
        }
    }

    public static CameraManager get()
    {
        return cameraManager;
    }

    private CameraManager(Context context)
    {
        this.context = context;
        this.configManager = new CameraConfigurationManager(context);

        useOneShotPreviewCallback = Build.VERSION.SDK_INT > 3;
        previewCallback = new PreviewCallback(configManager, useOneShotPreviewCallback);
        autoFocusCallback = new AutoFocusCallback();
    }


    /**
     * Opens the camera driver and initializes the hardware parameters.
     *
     * @param holder The surface object which the camera will draw preview frames into.
     * @throws IOException Indicates the camera driver failed to open.
     */
    public void openDriver(SurfaceHolder holder) throws IOException {
        if (camera == null) {
            camera = Camera.open();
            if (camera == null) {
                throw new IOException();
            }
            camera.setPreviewDisplay(holder);

            if (!initialized) {
                initialized = true;
                configManager.initFromCameraParameters(camera);
            }
            configManager.setDesiredCameraParameters(camera);

            //FIXME
            //     SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            //�Ƿ�ʹ��ǰ��
//      if (prefs.getBoolean(PreferencesActivity.KEY_FRONT_LIGHT, false)) {
//        FlashlightManager.enableFlashlight();
//      }
//            FlashlightManager.enableFlashlight();
        }
    }

    /**
     * Closes the camera driver if still in use.
     */
    public void closeDriver() {
        if (camera != null) {
//            FlashlightManager.disableFlashlight();
            camera.release();
            camera = null;
        }
    }

    /**
     * Asks the camera hardware to begin drawing preview frames to the screen.
     * 访问相机将图像预览在屏幕上
     */
    public void startPreview() {
        if (camera != null && !previewing) {
            camera.startPreview();
            previewing = true;
        }
    }

    /**
     * Tells the camera to stop drawing preview frames.
     */
    public void stopPreview() {
        if (camera != null && previewing) {
            if (!useOneShotPreviewCallback) {
                camera.setPreviewCallback(null);
            }
            camera.stopPreview();
            previewCallback.setHandler(null, 0);
            autoFocusCallback.setHandler(null, 0);
            previewing = false;
        }
    }

    /**
     * 通过handler机制将图片数据返回
     *
     * @param handler The handler to send the message to.
     * @param message The what field of the message to be sent.
     */
    public void requestPreviewFrame(Handler handler, int message) {
        if (camera != null && previewing) {
            previewCallback.setHandler(handler, message);
            if (useOneShotPreviewCallback) {
                camera.setOneShotPreviewCallback(previewCallback);
            } else {
                camera.setPreviewCallback(previewCallback);
            }
        }
    }

    /**
     * 进行自动对焦
     *
     * @param handler The Handler to notify when the autofocus completes.
     * @param message The message to deliver.
     */
    public void requestAutoFocus(Handler handler, int message) {
        if (camera != null && previewing) {
            autoFocusCallback.setHandler(handler, message);
            //Log.d(TAG, "Requesting auto-focus callback");
            camera.autoFocus(autoFocusCallback);
        }
    }

    /**
     * Calculates the framing rect which the UI should draw to show the user where to place the
     * barcode. This target helps with alignment as well as forces the user to hold the device
     * far enough away to ensure the image will be in focus.
     *
     * @return The rectangle to draw on screen in window coordinates.
     */
    public Rect getFramingRect() {
        Point screenResolution = configManager.getScreenResolution();
        if (framingRect == null) {
            if (camera == null) {
                return null;
            }
            int width = screenResolution.x * 3 / 4;
            if (width < MIN_FRAME_WIDTH) {
                width = MIN_FRAME_WIDTH;
            } else if (width > MAX_FRAME_WIDTH) {
                width = MAX_FRAME_WIDTH;
            }
            int height = screenResolution.y * 3 / 4;
            if (height < MIN_FRAME_HEIGHT) {
                height = MIN_FRAME_HEIGHT;
            } else if (height > MAX_FRAME_HEIGHT) {
                height = MAX_FRAME_HEIGHT;
            }
            int leftOffset = (screenResolution.x - width) / 2;
            int topOffset = (screenResolution.y - height) / 2;
            framingRect = new Rect(leftOffset, topOffset, leftOffset + width, topOffset + height);
            Log.d(TAG, "Calculated framing rect: " + framingRect);
        }
        return framingRect;
    }

    /**
     * Like {@link #getFramingRect} but coordinates are in terms of the preview frame,
     * not UI / screen.
     */
    public Rect getFramingRectInPreview() {
        if (framingRectInPreview == null) {
            Rect rect = new Rect(getFramingRect());
            Point cameraResolution = configManager.getCameraResolution();
            Point screenResolution = configManager.getScreenResolution();
            //modify here
//      rect.left = rect.left * cameraResolution.x / screenResolution.x;
//      rect.right = rect.right * cameraResolution.x / screenResolution.x;
//      rect.top = rect.top * cameraResolution.y / screenResolution.y;
//      rect.bottom = rect.bottom * cameraResolution.y / screenResolution.y;
            rect.left = rect.left * cameraResolution.y / screenResolution.x;
            rect.right = rect.right * cameraResolution.y / screenResolution.x;
            rect.top = rect.top * cameraResolution.x / screenResolution.y;
            rect.bottom = rect.bottom * cameraResolution.x / screenResolution.y;
            framingRectInPreview = rect;
        }
        return framingRectInPreview;
    }

    /**
     * Converts the result points from still resolution coordinates to screen coordinates.
     *
     * @param points The points returned by the Reader subclass through Result.getResultPoints().
     * @return An array of Points scaled to the size of the framing rect and offset appropriately
     *         so they can be drawn in screen coordinates.
     */
  /*
  public Point[] convertResultPoints(ResultPoint[] points) {
    Rect frame = getFramingRectInPreview();
    int count = points.length;
    Point[] output = new Point[count];
    for (int x = 0; x < count; x++) {
      output[x] = new Point();
      output[x].x = frame.left + (int) (points[x].getX() + 0.5f);
      output[x].y = frame.top + (int) (points[x].getY() + 0.5f);
    }
    return output;
  }
   */


//    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] data, int width, int height) {
//        Rect rect = getFramingRectInPreview();
//        int previewFormat = configManager.getPreviewFormat();
//        String previewFormatString = configManager.getPreviewFormatString();
//        switch (previewFormat) {
//            // This is the standard Android format which all devices are REQUIRED to support.
//            // In theory, it's the only one we should ever care about.
//            case PixelFormat.YCbCr_420_SP:
//                // This format has never been seen in the wild, but is compatible as we only care
//                // about the Y channel, so allow it.
//            case PixelFormat.YCbCr_422_SP:
//                return new PlanarYUVLuminanceSource(data, width, height, rect.left, rect.top,
//                        rect.width(), rect.height());
//            default:
//                // The Samsung Moment incorrectly uses this variant instead of the 'sp' version.
//                // Fortunately, it too has all the Y data up front, so we can read it.
//                if ("yuv420p".equals(previewFormatString)) {
//                    return new PlanarYUVLuminanceSource(data, width, height, rect.left, rect.top,
//                            rect.width(), rect.height());
//                }
//        }
//        throw new IllegalArgumentException("Unsupported picture format: " +
//                previewFormat + '/' + previewFormatString);
//    }

    public Context getContext() {
        return context;
    }
}
