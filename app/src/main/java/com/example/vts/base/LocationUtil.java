
package com.example.vts.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;


public class LocationUtil implements ConnectionCallbacks,
        OnConnectionFailedListener,
        LocationListener, ResultCallback<LocationSettingsResult> {
	public static final int REQUEST_CHECK_SETTINGS = 1123;
	public static final long UPDATE_INTERVAL_IN_MILISECONDS = 4000;
	public static final long FASTEST_UPDATE_INTERVAL_IN_MILISECONDS = UPDATE_INTERVAL_IN_MILISECONDS / 2;
	protected GoogleApiClient mGoogleApiClient;
	protected LocationRequest mLocationRequest;
	protected LocationSettingsRequest mLocationSettingsRequest;
	protected Boolean mRequestingLocationUpdates = false;
	private Activity mActivity;
	private GetLocationListener location_listener_service = null;
	private LocationManager lm;
	private GpsStatus.Listener gpsListner;
	public LocationUtil(Activity activity, GetLocationListener getLocationListener) {
		this.location_listener_service = getLocationListener;
		this.mActivity = activity;
		buildGoogleApiClient();
		createLocationRequest();
		buildLocationSettingsRequest();
		addGpsListner();
	}
	protected synchronized void buildGoogleApiClient() {
		mGoogleApiClient = new GoogleApiClient.Builder(mActivity)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(LocationServices.API)
				.build();
		mGoogleApiClient.connect();
	}
	protected void createLocationRequest() {
		mLocationRequest = new LocationRequest();
		mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILISECONDS);
		mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILISECONDS);
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	}
	protected void buildLocationSettingsRequest() {
		LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
		builder.addLocationRequest(mLocationRequest);
		builder.setAlwaysShow(true);    //if it is true, then it shows, alert with yes,no option, and if it is false, then it comes with yes,no and never.
		mLocationSettingsRequest = builder.build();
	}


	public void checkLocationSettings() {
		PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, mLocationSettingsRequest);
		result.setResultCallback(this);
	}
	private void startLocationUpdates() {
		if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			return;
		}
		LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this).setResultCallback(new ResultCallback<Status>() {
			@Override
			public void onResult(@NonNull Status status) {
				mRequestingLocationUpdates = true;
			}
		});
	}
	protected void stopLocationUpdates()
	{
		LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this).setResultCallback(new ResultCallback<Status>() {
			@Override
			public void onResult(@NonNull Status status) {
				mRequestingLocationUpdates = false;
			}
		});
	}

	public void stop_Location_Update()
	{
		if (mGoogleApiClient != null && mGoogleApiClient.isConnected())
		{
			stopLocationUpdates();
		}
	}

	public void restart_location_update()
	{
		if (mGoogleApiClient != null && mGoogleApiClient.isConnected() && mRequestingLocationUpdates)
		{
			startLocationUpdates();
		}
	}

	public void destroy_Location_Update()
	{
		if (mGoogleApiClient != null)
		{
			mGoogleApiClient.disconnect();
		}
	}

	@Override
	public void onConnected(@Nullable Bundle bundle) {
		/**
		 * On Connected doing the location Update.*/
		checkLocationSettings();
	}

	@Override
	public void onConnectionSuspended(int i) {
		location_listener_service.location_Error("Location"+"Connection Suspended");
	}

	@Override
	public void onLocationChanged(Location location) {
		/**
		 * On Connect checking if the location is available or not, if not then again
		 * update the location.
		 */
		if (location == null)
		{
			restart_location_update();
		}
		else
		{
			location_listener_service.updateLocation(location);
		}
	}

	@Override
	public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
		location_listener_service.location_Error("Location"+"Connection failed: ConnectionResult.getErrorCode()=" + connectionResult.getErrorCode());
	}
	@Override
	public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
		final Status status = locationSettingsResult.getStatus();
		switch (status.getStatusCode())
		{
			case LocationSettingsStatusCodes.SUCCESS:
				startLocationUpdates();
				break;
			case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
				try
				{
					status.startResolutionForResult(mActivity, REQUEST_CHECK_SETTINGS);
				}
				catch (IntentSender.SendIntentException e)
				{
					e.printStackTrace();
				}
				break;
			case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
				Toast.makeText(mActivity,"Location settings are inadequate, and cannot be fixed here. Dialog " +
						"not created.",Toast.LENGTH_SHORT).show();
				break;
		}
	}


	public void addGpsListner()
	{
		lm = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);
		if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			return;
		}
		gpsListner=new GpsStatus.Listener() {
			public void onGpsStatusChanged(int event) {

				//Log.d("GPS STATUS", "GPS : " + event);
				//Log.d("GPS GpsStatus", "GPS : " + GpsStatus.GPS_EVENT_FIRST_FIX);

				switch (event) {
					case 1:
						// do your tasks
						Log.d("GPS STATUS", "On");
						break;
					case 3:
						// do your tasks
						// LocationUtil.isShowSettings=false;
						Log.d("GPS STATUS", "On");
						break;
					case 4:
						// do your tasks
						// LocationUtil.isShowSettings=false;
						Log.d("GPS STATUS", "On");
						break;
					case 2:
						// do your tasks
						// LocationUtil.isShowSettings=true;
						checkLocationSettings();
						Log.d("GPS STATUS", "Off");
						break;
				}
			}
		};
		lm.addGpsStatusListener(gpsListner);
	}
	/**
	 * <h2>getLocationListener</h2>
	 * <P>
	 *   Location update listener.
	 * </P>*/
	public interface  GetLocationListener
	{
		void updateLocation(Location location);
		void location_Error(String error);
	}
}