package com.barcelona.hackupc.drivesafe.context_awareness;

import android.util.Log;

import com.barcelona.hackupc.drivesafe.Location.UpdateViewContextValue;
import com.barcelona.hackupc.drivesafe.model.UIData;
import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.snapshot.DetectedActivityResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

/**
 * Created by curosity on 14.10.17.
 */

public class ContextAwarenessHelper {

    private static final String TAG = ContextAwarenessHelper.class.getName();

//    private  detectedActivityResultPendingResult;
    private UpdateViewContextValue updateContextValueView;

    public  ContextAwarenessHelper( UpdateViewContextValue updateContextValueView ){
        
        this.updateContextValueView = updateContextValueView;
    }

    /**
     * helper method which creates
     * @return
     */
    public void requestAwarenessUpdate(GoogleApiClient mGoogleApiClient, UIData uiData){

        PendingResult<DetectedActivityResult> detectedActivityResultPendingResult = Awareness.SnapshotApi.getDetectedActivity(mGoogleApiClient);
        //listener required to detect pending results
        ContextResultListener contextResultListener = new ContextResultListener(updateContextValueView,uiData);
        detectedActivityResultPendingResult.setResultCallback(contextResultListener);

        Log.d(TAG,"AwarenessRequest requested");
    }



}
