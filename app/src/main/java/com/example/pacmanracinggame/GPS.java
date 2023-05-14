package com.example.pacmanracinggame;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import androidx.core.app.ActivityCompat;

public class GPS implements LocationListener {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private double lat = 31.771959;
    private double lon = 35.217018;
    private static final String TAG = "GPS";
    private Location lastLocation;
    private static LocationManager locationManager;

    public GPS(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastLocation != null) {
            onLocationChanged(lastLocation);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: " + location.toString());
        lastLocation = location;
        lat = location.getLatitude();
        lon = location.getLongitude();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG, "onProviderEnabled: " + provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG, "onProviderDisabled: " + provider);
    }

    public static LocationManager getLocationManager() {
        return locationManager;
    }

    public double getLat() {
        return lat;
    }

    public double getLag() {
        return lon;
    }

    private void stopLocationTrack() {
        locationManager.removeUpdates(this);
    }
}
