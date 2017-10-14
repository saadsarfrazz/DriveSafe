package com.barcelona.hackupc.drivesafe.context_awareness;

import android.support.annotation.NonNull;
import android.util.Log;

import com.barcelona.hackupc.drivesafe.Location.UpdateViewContextValue;
import com.barcelona.hackupc.drivesafe.model.UIData;
import com.google.android.gms.awareness.snapshot.DetectedActivityResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

/**
 * Helper class to get the current contextual information and updates the UI using UpdateViewContextValue interface
 * Created by saad on 14.10.17.
 */

public class ContextResultListener implements ResultCallback<DetectedActivityResult> {

    private String TAG = ContextResultListener.class.getName();

    private UpdateViewContextValue updateContextValueView;
    private UIData uiData;

    public ContextResultListener(UpdateViewContextValue updateContextValueView, UIData uiData){
        this.updateContextValueView = updateContextValueView;
        this.uiData = uiData;
    }

    @Override
    public void onResult(@NonNull DetectedActivityResult detectedActivityResult) {
        if (!detectedActivityResult.getStatus().isSuccess()) {
            Log.d(TAG, "Could not get the current activity.");
            updateContextValueView.updateTextValues("Could not get the current activity.",null);
//            Toast.makeText(getApplicationContext(),"Could not get the current activity.",Toast.LENGTH_LONG).show();
//            tvActivityValue.setText("Could not get the current activity.");
            return;
        }
        ActivityRecognitionResult ar = detectedActivityResult.getActivityRecognitionResult();
        DetectedActivity probableActivity = ar.getMostProbableActivity();
        updateContextValueView.updateTextValues(probableActivity.toString(), uiData);
        Log.d(TAG, probableActivity.toString());
//        tvActivityValue.setText( (counter++) + probableActivity.toString());
    }


}
