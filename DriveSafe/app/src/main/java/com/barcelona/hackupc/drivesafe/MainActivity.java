package com.barcelona.hackupc.drivesafe;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.barcelona.hackupc.drivesafe.Location.UpdateViewContextValue;
import com.barcelona.hackupc.drivesafe.Location.UserLocationListener;
import com.barcelona.hackupc.drivesafe.model.UIData;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

/**
 * Created by saad
 */

public class MainActivity extends Activity implements UpdateViewContextValue {

    private static final String TAG = "MainActivity";

    public GoogleApiClient mGoogleApiClient;

    //Text view for user acivity
    private TextView tvActivityValue;

    //Textvies for coords
    private TextView tvLonValue;
    private TextView tvLatValue;
    private TextView tvGPSStatusValue;


    //Location manager
    protected LocationManager locationManager =null;
    private UserLocationListener userLocationListener;
    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 10; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1 * 1000; // in Milliseconds (per second)
    private static final long FASTEST_TIME_BETWEEN_UPDATES = 1 * 500; // in Milliseconds (per second)

    // for testing
    private static int counter = 0;
    private static final String NOT_AVAILABLE = "N/A";

    //Contexgt api results
//    private PendingResult<DetectedActivityResult> detectedActivityResultPendingResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init views
        tvActivityValue = findViewById(R.id.tvActivityValue);
        tvLonValue = findViewById(R.id.tvLonValue);
        tvLatValue = findViewById(R.id.tvLatValue);
        tvGPSStatusValue = findViewById(R.id.gpsStatusValue);

        //create user google api client
        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .addApi(Awareness.API)
                .build();
        mGoogleApiClient.connect();

//        //init location listener
        userLocationListener = new UserLocationListener(mGoogleApiClient,this);
        //Setting Location Listeners
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d(TAG,"Permissions failed 1");
            Toast.makeText(getApplicationContext(),"Permissions failed 1", Toast.LENGTH_LONG);
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d(TAG,"Permissions failed 2");
            Toast.makeText(getApplicationContext(),"Permissions failed 2", Toast.LENGTH_LONG);
            return;
        }

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATES,
                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                userLocationListener
        );
/*
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(MINIMUM_TIME_BETWEEN_UPDATES);
        mLocationRequest.setFastestInterval(FASTEST_TIME_BETWEEN_UPDATES);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        //current location settings
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);

        //settings meet the criteria ??
        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {

            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

//                detectedActivityResultPendingResult = Awareness.SnapshotApi.getDetectedActivity(mGoogleApiClient);
//                detectedActivityResultPendingResult.setResultCallback(new ContextResultListener());


            }
        });

        task.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                int statusCode = ((ApiException) e).getStatusCode();
                switch (statusCode) {
                    case CommonStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied, but this can be fixed
                        // by showing the user a dialog.
//                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
//                            ResolvableApiException resolvable = (ResolvableApiException) e;
//                            resolvable.startResolutionForResult(MainActivity.this,
//                                    REQUEST_CHECK_SETTINGS);
                        Log.i(TAG, ""+counter++);
//                            tvActivityValue.setText("Please correct settings");
//                        } catch (IntentSender.SendIntentException sendEx) {
//                            // Ignore the error.
//                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way
                        // to fix the settings so we won't show the dialog.
                        tvActivityValue.setText("SETTINGS_CHANGE_UNAVAILABLE");
                        break;
                }
            }
        });

*/

    }


    @Override
    public void updateTextValues(String currentActivity, UIData uiData) {

        if(currentActivity != null ){
            tvActivityValue.setText(currentActivity);
            updateLocationTextViews(uiData);

        }else{//context not read
            tvActivityValue.setText(NOT_AVAILABLE);
            updateLocationTextViews(uiData);


        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(userLocationListener!=null){
            locationManager.removeUpdates(userLocationListener);
            locationManager=null;
        }

    }

    @Override
    protected void onResume(){
        super.onResume();
        if(userLocationListener == null){
            userLocationListener = new UserLocationListener(mGoogleApiClient,this);
        }
    }

    private void updateLocationTextViews(UIData uiData){

        if(uiData.getLocation()!=null){ //everything is good
            tvLatValue.setText(Double.toString(uiData.getLocation().getLatitude()));
            tvLonValue.setText(Double.toString(uiData.getLocation().getLongitude()));
            tvGPSStatusValue.setText("GPS Enabled");
        }else if(uiData.getLocation()==null && uiData.isGpsEnable()){ //gps on but location not available yet
            tvLatValue.setText("Getting Data...");
            tvLonValue.setText("Getting Data...");
            tvGPSStatusValue.setText("GPS Enabled");
        }else{  //
            tvLatValue.setText(NOT_AVAILABLE);
            tvLonValue.setText(NOT_AVAILABLE);
            tvGPSStatusValue.setText("GPS Disabled");
        }
    }
}
