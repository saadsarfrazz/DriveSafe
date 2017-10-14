package com.barcelona.hackupc.drivesafe.model;

import android.location.Location;

/**
 * Created by curosity on 14.10.17.
 */

public class UIData {

    private Location location;

    private boolean gpsStatus = false;


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isGpsEnable() {
        return gpsStatus;
    }

    public void setGpsStatus(boolean gpsStatus) {
        this.gpsStatus = gpsStatus;
    }
}
