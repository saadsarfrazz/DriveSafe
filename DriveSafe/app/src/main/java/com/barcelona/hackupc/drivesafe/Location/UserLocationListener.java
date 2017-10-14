package com.barcelona.hackupc.drivesafe.Location;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;


import com.barcelona.hackupc.drivesafe.context_awareness.ContextAwarenessHelper;
import com.barcelona.hackupc.drivesafe.model.UIData;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by saad on 14.10.17.
 */

public class UserLocationListener implements LocationListener {


    String TAG = "UserLocationListener";

    private ContextAwarenessHelper contextAwarenessHelper;

    private GoogleApiClient mGoogleApiClient;

    public UserLocationListener(GoogleApiClient mGoogleApiClient,UpdateViewContextValue updateContextValueView){
        this.mGoogleApiClient = mGoogleApiClient;
        this.contextAwarenessHelper = new ContextAwarenessHelper(updateContextValueView );
    }
    
    
    /**
     * This method run each time when there is change in location after mentioned frequency
     * @param location
     */
    public void onLocationChanged(Location location) {

        Log.d(TAG," Location changed");
        //prepare ui data
        UIData uiData = new UIData();
        uiData.setLocation(location);
        uiData.setGpsStatus(true);

        contextAwarenessHelper.requestAwarenessUpdate(mGoogleApiClient,uiData);

//        detectedActivityResultPendingResult = Awareness.SnapshotApi.getDetectedActivity(mGoogleApiClient);
        //will call parametrized objects Result method when results are obtained


//        ContextResultListener contextResultListener = new ContextResultListener(updateContextValueView,uiData);
//        detectedActivityResultPendingResult.setResultCallback(contextResultListener);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extra) {
        //not available
        if(!provider.equals(LocationProvider.AVAILABLE)){
            UIData uiData = new UIData();
            uiData.setLocation(null);
            uiData.setGpsStatus(false);
            contextAwarenessHelper.requestAwarenessUpdate(mGoogleApiClient,uiData);
        }else{
            UIData uiData = new UIData();
            uiData.setLocation(null);
            uiData.setGpsStatus(true);
            contextAwarenessHelper.requestAwarenessUpdate(mGoogleApiClient,uiData);
        }


        Log.d(TAG,"Status Changed");
    }

    @Override
    public void onProviderDisabled(String provider) {

        if(provider.equals("gps")){
            UIData uiData = new UIData();
            uiData.setLocation(null);
            uiData.setGpsStatus(false);
            contextAwarenessHelper.requestAwarenessUpdate(mGoogleApiClient,uiData);
        }



        Log.d(TAG,"Provider Disabled:" + provider);
    }

    @Override
    public void onProviderEnabled(String provider) {

        if(provider.equals("gps")){
            UIData uiData = new UIData();
            uiData.setLocation(null);
            uiData.setGpsStatus(true);
            contextAwarenessHelper.requestAwarenessUpdate(mGoogleApiClient,uiData);
        }

        Log.d(TAG,"Provider Enabled");
    }
}