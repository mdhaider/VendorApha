package com.instafinancials.vendoralpha.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
import com.instafinancials.vendoralpha.AppPreferences
import com.instafinancials.vendoralpha.R
import kotlinx.android.synthetic.main.activity_camera.*
import kotlin.properties.Delegates

class CameraActivity : AppCompatActivity() {
    private var mCameraSource by Delegates.notNull<CameraSource>()
    private var textRecognizer by Delegates.notNull<TextRecognizer>()

    private val PERMISSION_REQUEST_CAMERA = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        startCameraSource()
    }

    private fun startCameraSource() {

        //  Create text Recognizer
        textRecognizer = TextRecognizer.Builder(this).build()

        if (!textRecognizer.isOperational) {
            Log.d("HomeFragment", "Dependencies are downloading....try after few moment")
            return
        }

        //  Init camera source to use high resolution and auto focus
        mCameraSource = CameraSource.Builder(applicationContext, textRecognizer)
            .setFacing(CameraSource.CAMERA_FACING_BACK)
            .setRequestedPreviewSize(1280, 1024)
            .setAutoFocusEnabled(true)
            .setRequestedFps(2.0f)
            .build()

        surface_camera_preview.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {

            }

            override fun surfaceDestroyed(p0: SurfaceHolder?) {
                mCameraSource.stop()
            }

            @SuppressLint("MissingPermission")
            override fun surfaceCreated(p0: SurfaceHolder?) {
                try {
                    if (isCameraPermissionGranted()) {
                        mCameraSource.start(surface_camera_preview.holder)
                    } else {
                        requestForPermission()
                    }
                } catch (e: Exception) {
                    // toast("Error:  ${e.message}")
                }
            }
        })

        textRecognizer.setProcessor(object : Detector.Processor<TextBlock> {
            override fun release() {}

            override fun receiveDetections(detections: Detector.Detections<TextBlock>) {
                val items = detections.detectedItems

                if (items.size() <= 0) {
                    return
                }

                tv_result.post {
                    for (i in 0 until items.size()) {
                        val item = items.valueAt(i)
                        if (item.value.length > 15) {
                            var substrings = item.value.split(" ")
                            for (i in 0 until substrings.count()) {
                                if(substrings[i].length == 15) {
                                    tv_result.text = substrings[i]
                                    AppPreferences.gstNum = substrings[i]
                                }
                            }

                           // finish()
                        }
                    }
                }
            }
        })
    }

    fun isCameraPermissionGranted(): Boolean {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED
    }

    private fun requestForPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            PERMISSION_REQUEST_CAMERA
        )
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode != PERMISSION_REQUEST_CAMERA) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (isCameraPermissionGranted()) {
                mCameraSource.start(surface_camera_preview.holder)
            } else {
                //  toast("Permission need to grant")
                finish()
            }
        }
    }
}