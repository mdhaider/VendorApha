package com.instafinancials.vendoralpha.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.vision.text.Text
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
import com.google.android.material.snackbar.Snackbar
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.shared.Const
import com.instafinancials.vendoralpha.ui.activities.camera.*
import com.instafinancials.vendoralpha.shared.GSTChecksumUtil
import java.io.IOException
import kotlin.properties.Delegates


class CameraActivity : AppCompatActivity() {
    private val autoFocus = Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO
    private val useFlash = null; // Camera.Parameters.FLASH_MODE_TORCH
    lateinit var mCameraSource : CameraSource
    lateinit var preview : CameraSourcePreview
    lateinit var tv_result : TextView
    private var textRecognizer by Delegates.notNull<TextRecognizer>()
   // private lateinit var textRecognizer : GSTTextRecognizer
    private lateinit var graphicOverlay: GraphicOverlay<OcrGraphic>

    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private lateinit var gestureDetector : GestureDetector
    private val TAG = "CameraAvtivity"
    // Intent request code to handle updating play services if needed.
    private val RC_HANDLE_GMS = 9001

    // Permission request codes need to be < 256
    private val RC_HANDLE_CAMERA_PERM = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        preview = findViewById(R.id.preview);
        graphicOverlay = findViewById(R.id.graphicOverlay);
        tv_result = findViewById(R.id.tv_result)

        // Check for the camera permission before accessing the camera.  If the
        // permission is not granted yet, request permission.
        // Check for the camera permission before accessing the camera.  If the
// permission is not granted yet, request permission.
        val rc = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        if (rc == PackageManager.PERMISSION_GRANTED) {
            createCameraSource()
        } else {
            requestCameraPermission()
        }
        gestureDetector =  GestureDetector(this,  CaptureGestureListener());
        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())
    }

    /**
     * Handles the requesting of the camera permission.  This includes
     * showing a "Snackbar" message of why the permission is needed then
     * sending the request.
     */
    private fun requestCameraPermission() {
        Log.w(
            TAG,
            "Camera permission is not granted. Requesting permission"
        )
        val permissions =
            arrayOf(Manifest.permission.CAMERA)
        if (!ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            )
        ) {
            ActivityCompat.requestPermissions(this, permissions, RC_HANDLE_CAMERA_PERM)
            return
        }
        val thisActivity: Activity = this
        val listener: View.OnClickListener = object : View.OnClickListener {
            override fun onClick(view: View?) {
                ActivityCompat.requestPermissions(
                    thisActivity, permissions,
                    RC_HANDLE_CAMERA_PERM
                )
            }
        }
        Snackbar.make(
            graphicOverlay, R.string.permission_camera_rationale,
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(R.string.ok, listener)
            .show()
    }



    /**
     * Creates and starts the camera.  Note that this uses a higher resolution in comparison
     * to other detection examples to enable the ocr detector to detect small text samples
     * at long distances.
     *
     * Suppressing InlinedApi since there is a check that the minimum version is met before using
     * the constant.
     */
    @SuppressLint("InlinedApi")
    private fun createCameraSource() {
        val context = getApplicationContext();

        // A text recognizer is created to find text.  An associated multi-processor instance
        // is set to receive the text recognition results, track the text, and maintain
        // graphics for each text block on screen.  The factory is used by the multi-processor to
        // create a separate tracker instance for each text block.
        textRecognizer =  TextRecognizer.Builder(context).build();
       // textRecognizer =  GSTTextRecognizer(graphicOverlay)
        textRecognizer.setProcessor(OcrDetectorProcessor(graphicOverlay));

        if (!textRecognizer.isOperational()) {
            // Note: The first time that an app using a Vision API is installed on a
            // device, GMS will download a native libraries to the device in order to do detection.
            // Usually this completes before the app is run for the first time.  But if that
            // download has not yet completed, then the above call will not detect any text,
            // barcodes, or faces.
            //
            // isOperational() can be used to check if the required native libraries are currently
            // available.  The detectors will automatically become operational once the library
            // downloads complete on device.
            Log.w(TAG, "Detector dependencies are not yet available.");

            // Check for low storage.  If there is low storage, the native library will not be
            // downloaded, so detection will not become operational.
//            IntentFilter lowstorageFilter = new IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW);
//            boolean hasLowStorage = registerReceiver(null, lowstorageFilter) != null;
//
//            if (hasLowStorage) {
//                Toast.makeText(this, R.string.low_storage_error, Toast.LENGTH_LONG).show();
//                Log.w(TAG, getString(R.string.low_storage_error));
//            }
        }

        //  Init camera source to use high resolution and auto focus
        mCameraSource = CameraSource.Builder(applicationContext, textRecognizer)
            .setFacing(CameraSource.CAMERA_FACING_BACK)
            //.setRequestedPreviewSize(1280, 1024)
            .setRequestedPreviewSize(640, 480)
            .setRequestedFps(2.0f)
            .setFlashMode(useFlash)
            .setFocusMode(autoFocus)
            .build()
    }

    private fun startCameraSource() {

        // check that the device has play services available.
        // check that the device has play services available.
        val code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
            applicationContext
        )
        if (code != ConnectionResult.SUCCESS) {
            val dlg =
                GoogleApiAvailability.getInstance().getErrorDialog(this, code, RC_HANDLE_GMS)
            dlg.show()
        }
        try {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            preview.start(mCameraSource, graphicOverlay)
        } catch (e: IOException) {
            Log.e(TAG, "Unable to start camera source.", e)
            mCameraSource.release()
        }
    }

    fun isCameraPermissionGranted(): Boolean {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED
    }



    /**
     * Restarts the camera.
     */

    override fun onResume() {
        super.onResume();
        startCameraSource();
    }

    /**
     * Stops the camera.
     */
    override fun onPause() {
        super.onPause();
        preview.stop()
    }

    /**
     * Releases the resources associated with the camera source, the associated detectors, and the
     * rest of the processing pipeline.
     */
    override fun onDestroy() {
        super.onDestroy();
        preview.release()
    }

    override fun onRequestPermissionsResult(requestCode : Int,
                                            permissions : Array<String>,
                                           grantResults: IntArray) {
        if (requestCode != RC_HANDLE_CAMERA_PERM) {
            Log.d(TAG, "Got unexpected permission result: " + requestCode);
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }

        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Camera permission granted - initialize the camera source");
            // we have permission, so create the camerasource
            createCameraSource();
            return;
        }

       /* Log.e(TAG, "Permission not granted: results len = " + grantResults.size +
                " Result code = " + ((grantResults.size > 0)? grantResults.get(0) : "(empty)"));*/

        var listener =  DialogInterface.OnClickListener() { dialogInterface: DialogInterface, i: Int ->
            fun onClick(dialog : DialogInterface, id: Int) {
                finish();
            }
        };

        var builder =  AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name)
                .setMessage(R.string.no_camera_permission)
                .setPositiveButton(R.string.ok, listener)
                .show();
    }

    inner class CaptureGestureListener : SimpleOnGestureListener() {
        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            return onTap(e.rawX, e.rawY) || super.onSingleTapConfirmed(e)
        }
    }

    inner class ScaleListener : ScaleGestureDetector.OnScaleGestureListener {

        /**
         * Responds to scaling events for a gesture in progress.
         * Reported by pointer motion.
         *
         * @param detector The detector reporting the event - use this to
         *                 retrieve extended info about event state.
         * @return Whether or not the detector should consider this event
         * as handled. If an event was not handled, the detector
         * will continue to accumulate movement until an event is
         * handled. This can be useful if an application, for example,
         * only wants to update scaling factors if the change is
         * greater than 0.01.
         */
        override fun onScale(detector : ScaleGestureDetector ) : Boolean {
            return false;
        }

        /**
         * Responds to the beginning of a scaling gesture. Reported by
         * new pointers going down.
         *
         * @param detector The detector reporting the event - use this to
         *                 retrieve extended info about event state.
         * @return Whether or not the detector should continue recognizing
         * this gesture. For example, if a gesture is beginning
         * with a focal point outside of a region where it makes
         * sense, onScaleBegin() may return false to ignore the
         * rest of the gesture.
         */
        override fun onScaleBegin(detector : ScaleGestureDetector ) : Boolean {
            return true;
        }

        /**
         * Responds to the end of a scale gesture. Reported by existing
         * pointers going up.
         * <p/>
         * Once a scale has ended, {@link ScaleGestureDetector#getFocusX()}
         * and {@link ScaleGestureDetector#getFocusY()} will return focal point
         * of the pointers remaining on the screen.
         *
         * @param detector The detector reporting the event - use this to
         *                 retrieve extended info about event state.
         */
        override fun onScaleEnd(detector : ScaleGestureDetector ) {
            mCameraSource.doZoom(detector.getScaleFactor())
        }
    }

    private fun onTap(rawX: Float, rawY: Float): Boolean {
        val graphic = graphicOverlay.getGraphicAtLocation(rawX, rawY)
        var text: Text? = null
        if (graphic != null) {
            text = graphic.textBlock
            if (text != null && text.value != null) {
                Log.d(TAG, "text data is being spoken! " + text.value)
                // Speak the string.
                //tts.speak(text.value, TextToSpeech.QUEUE_ADD, null, "DEFAULT")
                checkValue(text.value)
            } else {
                Log.d(TAG, "text data is null")
            }
        } else {
            Log.d(TAG, "no text detected")
        }
        return text != null
    }

    override fun onTouchEvent(e : MotionEvent) : Boolean {
        var b = scaleGestureDetector.onTouchEvent(e);

        var c = gestureDetector.onTouchEvent(e);

        return b || c || super.onTouchEvent(e);
    }


    private fun checkValue(value : String) {
      //  if (value.length > 15) {
             //   var substrings = value.split(" ")
            //    for (i in 0 until substrings.count()) {
                    if (value.length == 15) {
                        tv_result.post() {
                            tv_result.text = "veryfying GST for $value}"
                        }
                        Log.d(TAG, "GSTChecksum for ${value}")
                        if(GSTChecksumUtil().checValidGST(value)) {
                            Log.d(TAG, "GSTChecksum for ${value} is Valid")
                            finishAndSendResult(value)
                            return
                        }
                    }
               // }
    //    }
    }

    private fun finishAndSendResult(gst : String) {
        val intent = getIntent()
         intent.putExtra(Const.SCAN_DATA, gst)
         setResult(Activity.RESULT_OK, intent)
            finish()
    }
}