package com.instafinancials.vendoralpha.ui.activities.camera;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.instafinancials.vendoralpha.shared.Const;
import com.instafinancials.vendoralpha.shared.GSTChecksumUtil;

public class OcrDetectorProcessor implements Detector.Processor<TextBlock> {

    private GraphicOverlay<OcrGraphic> graphicOverlay;
    private GSTChecksumHandlerThread workerThread = null;
    private Handler mainHandler;

    public OcrDetectorProcessor(GraphicOverlay<OcrGraphic> ocrGraphicOverlay, Handler handler) {
        this.mainHandler = handler;
        graphicOverlay = ocrGraphicOverlay;
        // Create and start the worker thread.
        workerThread = new GSTChecksumHandlerThread();
        workerThread.start();
    }

    /**
     * Called by the detector to deliver detection results.
     * If your application called for it, this could be a place to check for
     * equivalent detections by tracking TextBlocks that are similar in location and content from
     * previous frames, or reduce noise by eliminating TextBlocks that have not persisted through
     * multiple detections.
     */
    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        graphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);  // complete block
            if (item != null && item.getValue() != null) {
                Log.d("OcrDetectorProcessor", "Text detected! " + item.getValue());
                for(Text currentText : item.getComponents()) { // lines
                   if(currentText.getValue().length() >= 15) {
                       for (Text word : currentText.getComponents()) {
                           if (word.getValue().length() == 15) {
                               OcrGraphic graphic = new OcrGraphic(graphicOverlay, word, Color.GREEN);
                               graphicOverlay.add(graphic);
                               sendMsgToProcessChecksum(word.getValue());
                           } else if(word.getValue().length() >= 15 && word.getValue().length() < 25){
                               OcrGraphic graphic = new OcrGraphic(graphicOverlay, word, Color.YELLOW);
                               graphicOverlay.add(graphic);
                           }
                       }
                    }
                }
            }
        }
    }

    private void sendMsgToProcessChecksum(String gstin) {
        Message msg = Message.obtain();
                msg.what = Const.GSTIN_SCAN_DATA;
                msg.obj = gstin;
                workerThread.handler.sendMessage(msg);
    }
    /**
     * Frees the resources associated with this detection processor.
     */
    @Override
    public void release() {
        graphicOverlay.clear();
        workerThread.handler.getLooper().quit();
    }


    private void checkValue(String value ) {
        if (value.length() > 15) {
            String[] substrings = value.split(" ");
            for (int i =0; i < substrings.length; i++) {
                if (substrings[i].length() == 15) {

                }
            }
        }
    }

    class GSTChecksumHandlerThread extends Thread {

        Handler handler;
        @Override
        public void run() {
            // Prepare child thread Lopper object.
            Looper.prepare();

            // Create child thread Handler.
            handler = new Handler(Looper.myLooper()) {
                @Override
                public void handleMessage(Message msg) { // When child thread handler get message from child thread message queue.
                    if(msg.what == Const.GSTIN_SCAN_DATA) {
                        if(new GSTChecksumUtil().checValidGST((String) msg.obj)) {
                            Message msgToUI = Message.obtain();
                            msgToUI.what = msg.what;
                            msgToUI.obj = msg.obj;

                            mainHandler.sendMessage(msgToUI);
                            handler.getLooper().quit();
                        }
                    }
                }
            };
            // Loop the child thread message queue.
            Looper.loop();

            // The code after Looper.loop() will not be executed until you call workerThreadHandler.getLooper().quit()

        }
    }
}
