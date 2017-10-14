package com.barcelona.hackupc.drivesafe;

import android.location.Location;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.barcelona.hackupc.drivesafe.Location.UpdateViewContextValue;
import com.google.android.gms.awareness.snapshot.DetectedActivityResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.common.api.Result;

import java.util.concurrent.TimeUnit;

/**
 * Helper class to get the current contextual information and updates the UI using UpdateViewContextValue interface
 * Created by saad on 14.10.17.
 */

public class ContextResultListener implements ResultCallback<DetectedActivityResult> {

    private String TAG = ContextResultListener.class.getName();

    private UpdateViewContextValue updateContextValueView;
    private Location location;

    public ContextResultListener(UpdateViewContextValue updateContextValueView, Location location){
        this.updateContextValueView = updateContextValueView;
        this.location = location;
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
        updateContextValueView.updateTextValues(probableActivity.toString(),location);
        Log.i(TAG, probableActivity.toString());
//        tvActivityValue.setText( (counter++) + probableActivity.toString());
    }


}
