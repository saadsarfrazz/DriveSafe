package com.barcelona.hackupc.drivesafe.Location;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.barcelona.hackupc.drivesafe.ContextResultListener;
import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.snapshot.DetectedActivityResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

/**
 * Created by saad on 14.10.17.
 */

public class UserLocationListener implements LocationListener {


    String TAG = "UserLocationListener";

    private PendingResult<DetectedActivityResult> detectedActivityResultPendingResult;
    private UpdateViewContextValue updateContextValueView;

    private GoogleApiClient mGoogleApiClient;

    public UserLocationListener(GoogleApiClient mGoogleApiClient,UpdateViewContextValue updateContextValueView){
        this.mGoogleApiClient = mGoogleApiClient;
        this.updateContextValueView = updateContextValueView;
    }
    
    
    /**
     * This method run each time when there is change in location after mentioned frequency
     * @param location
     */
    public void onLocationChanged(Location location) {

        Log.d(TAG," Location changed");

        detectedActivityResultPendingResult = Awareness.SnapshotApi.getDetectedActivity(mGoogleApiClient);
        //will call parametrized objects Result method when results are obtained
        ContextResultListener contextResultListener = new ContextResultListener(updateContextValueView,location);
        detectedActivityResultPendingResult.setResultCallback(contextResultListener);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.d(TAG,"Status Changed");
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d(TAG,"Provider Disabled");
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d(TAG,"Provider Enabled");
    }
}