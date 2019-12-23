package com.instafinancials.vendoralpha.ui.activities.camera;

import android.graphics.Color;
import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.instafinancials.vendoralpha.shared.Const;

public class OcrDetectorProcessor implements Detector.Processor<TextBlock> {

    private GraphicOverlay<OcrGraphic> graphicOverlay;

    public OcrDetectorProcessor(GraphicOverlay<OcrGraphic> ocrGraphicOverlay) {
        graphicOverlay = ocrGraphicOverlay;
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
            TextBlock item = items.valueAt(i);
            if (item != null && item.getValue() != null) {
                Log.d("OcrDetectorProcessor", "Text detected! " + item.getValue());
                for(Text currentText : item.getComponents()) {
                   if(currentText.getValue().trim().length() >= 15) {
                       String[] substrings = currentText.getValue().split(" ");
                       for (int j =0; j < substrings.length; j++) {
                        if (substrings[j].length() == 15) {
                            OcrGraphic graphic = new OcrGraphic(graphicOverlay, item, Color.GREEN);
                            graphicOverlay.add(graphic);
                        } else {
                            OcrGraphic graphic = new OcrGraphic(graphicOverlay, item, Color.YELLOW);
                            graphicOverlay.add(graphic);
                        }
                    }
                   }
                }

//                if (item.getValue().trim().length() >= 15) {
//                    String[] substrings = item.getValue().split(" ");
//                    for (int j =0; j < substrings.length; j++) {
//                        if (substrings[j].length() == 15) {
//                            OcrGraphic graphic = new OcrGraphic(graphicOverlay, item);
//                            graphicOverlay.add(graphic);
//                        }
//                    }
//                }
            }
        }
    }

    /**
     * Frees the resources associated with this detection processor.
     */
    @Override
    public void release() {
        graphicOverlay.clear();
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
}
