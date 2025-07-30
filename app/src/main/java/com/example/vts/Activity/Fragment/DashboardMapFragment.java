package com.example.vts.Activity.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vts.Activity.DashboardActivity;
import com.example.vts.Activity.DashboardApi.DashBoardApi;
import com.example.vts.Activity.DashboardApi.Dashbaordgetset;
import com.example.vts.Activity.DashboardApi.DashboardInterface;
import com.example.vts.Activity.DashboardApi.DashboardParamter;
import com.example.vts.Activity.DashboardApi.RunnungVehicleManagementlst;
import com.example.vts.Activity.DashboardApi.VehicleManagementItem;
import com.example.vts.R;
import com.example.vts.VechoeownerPack.VechicleownerDashboardListAdapter;
import com.example.vts.util.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardMapFragment extends Fragment {
    View chartview, mapsview;
    LinearLayout ll_chart, ll_map;
    private MapView mMapView;
    private GoogleMap googleMap;
    View mapView;
    View v;
    ImageView arrow;
    LinearLayout hiddenView;
    LinearLayout cardView;
    Context context;
    private double[] latLng = new double[2];
    private Marker marker;
    private float start_rotation;
    TextView Runiingcount, schedulecount, violationcount, completedcount;
    com.example.vts.Activity.DashboardApi.ResultData datum;
    List<RunnungVehicleManagementlst> vehicleManagementItem;
    String vecno, lastupadte, cardate, Siteid;

    public DashboardMapFragment(String siteid) {
        Siteid = siteid;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_dashboard_map, container, false);
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        chartview = v.findViewById(R.id.chartview);
        mapsview = v.findViewById(R.id.mapsview);
        ll_chart = v.findViewById(R.id.ll_chart);
        ll_map = v.findViewById(R.id.ll_map);
        chartview.setVisibility(View.GONE);
        context = getActivity();
        System.out.println("huhjghjgjh" + Siteid);
        cardView = v.findViewById(R.id.base_cardview);
        Runiingcount = v.findViewById(R.id.Runiingcount);
        schedulecount = v.findViewById(R.id.schedulecount);
        violationcount = v.findViewById(R.id.violationcount);
        completedcount = v.findViewById(R.id.completedcount);
        arrow = v.findViewById(R.id.arrow_button);
        hiddenView = v.findViewById(R.id.hidden_view);
        chartview.setVisibility(View.GONE);
        mapsview.setVisibility(View.VISIBLE);
//        ll_map.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                chartview.setVisibility(View.GONE);
//                mapsview.setVisibility(View.VISIBLE);
////                Fragment fragment = new DashboardMapFragment();
////                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
////                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////                fragmentTransaction.replace(R.id.frame_nav, fragment);
////                fragmentTransaction.addToBackStack(null);
////                fragmentTransaction.commit();
//
//            }
//        });
        ll_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartview.setVisibility(View.VISIBLE);
                mapsview.setVisibility(View.GONE);
                Fragment fragment = new DashboardChartFragment(Siteid);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_nav, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hiddenView.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    hiddenView.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    //notifyDataSetChanged();
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    hiddenView.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }
            }
        });
//        if(Siteid.equalsIgnoreCase("")){
//            System.out.println("hgghgfhfh"+Siteid);
//            dashboradfilter("");
//        }else {
//            dashboradfilter(Siteid);
//        }

        dashboradfilter(Siteid);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {
                        LatLng myLatLng = new LatLng(marker.getPosition().latitude,marker.getPosition().longitude);
                        if(vecno==null){
                            Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show();
                        }else {
                            for (VehicleManagementItem vehicleManagementItem :datum.getVehicleManagementItems()) {
                                if(vehicleManagementItem.getLatitude()==marker.getPosition().latitude){
                                    System.out.println("hjghjgjgjhgj"+vehicleManagementItem.getVehicleNo());
                                    showCustomDialog7();
                                }
                            }

                        }
                        return false;
                    }
                });

            }
        });
        return v;
    }
    MarkerOptions place2;
    double lat,lon;
    public void dashboradfilter(String e){
        DashboardParamter dashboardParamter=new DashboardParamter();
        dashboardParamter.SiteId=e;
        dashboardParamter.EravanaDate="";
        System.out.println("hjhjghjghg" + e);
        DashBoardApi.getRetrofitInstance(context).create(DashboardInterface.class).registration(dashboardParamter).enqueue(new Callback<Dashbaordgetset>() {
            @Override
            public void onResponse(Call<Dashbaordgetset> call, Response<Dashbaordgetset> response) {
                System.out.println("fdgdfgfgfgf"+new Gson().toJson(response.body()));
                datum=response.body().getResultData();
                int s=datum.getCompletedCount();
                int s1=datum.getRunningCount();
                int ov=datum.getScheduledCount();
                int ro=datum.getViolationCount();
                String sq= String.valueOf(s);
                String sqq= String.valueOf(s1);
                String oversp= String.valueOf(ov);
                String riut= String.valueOf(ro);
                System.out.println("fgghghghgg"+sq);
                schedulecount.setText(oversp);
                Runiingcount.setText(sqq);
                completedcount.setText(sq);
                violationcount.setText(riut);
                vehicleManagementItem=datum.getRunnungVehicleManagementlst();
                System.out.println("ghghghfhg"+vehicleManagementItem.size());
                for (int i = 0; i <vehicleManagementItem.size() ; i++) {
                    System.out.println("ghgfhfgfhf"+i);
                    vecno=vehicleManagementItem.get(i).getVehicleNo();
                    cardate=vehicleManagementItem.get(i).getVehicleModel();
                    String sourcename=vehicleManagementItem.get(i).getSourceName();
                    String destiname=vehicleManagementItem.get(i).getDestinationName();
                    lastupadte=vehicleManagementItem.get(i).getRFId();
                    lat=vehicleManagementItem.get(i).getLatitude();;
                    lon= vehicleManagementItem.get(i).getLongitude();;
                    System.out.println("hgfhgfgfgfg"+lat+"   "+lon+"     "+vecno);
                }

                place2 = new MarkerOptions().position(new LatLng(lat,lon));
                googleMap.addMarker(place2);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lon), 8));
                Bikeloac(lat,lon);

            }

            @Override
            public void onFailure(Call<Dashbaordgetset> call, Throwable t) {

            }
        });
    }

    LatLng firstselected,secondselected;
    List<LatLng> locationsecondarray = new ArrayList<>();
    private void Bikeloac(double lati,double lo) {
        locationsecondarray.add(new LatLng(lati,lo));
        latLng[0] = lati;
        latLng[1] = lo;
        System.out.println("jhjjgjh"+latLng[0]+"    "+latLng[1]);
        if(marker==null){
            marker = googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latLng[0], latLng[1])));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng[0], latLng[1]), 12.0f));
        }

        if ((latLng[0] != -1 && latLng[0] != 0) && (latLng[1] != -1 && latLng[1] != 0)) {
            if (marker != null) {
                Location temp = new Location(LocationManager.GPS_PROVIDER);
                temp.setLatitude(lati);
                temp.setLongitude(lo);
                System.out.println("kjhhjjj"+temp.getBearing());
                moveVehicle(marker, temp);
                if(locationsecondarray.size() ==2){
                    System.out.println("kjhhjjj"+Float.parseFloat(String.valueOf( bearingBetweenLocations(locationsecondarray.get(0),locationsecondarray.get(1)))));
                    rotateMarker(marker,Float.parseFloat(String.valueOf( bearingBetweenLocations(locationsecondarray.get(0),locationsecondarray.get(1)))), start_rotation);
                }

            }

            if(locationsecondarray.size() ==2){
                firstselected = locationsecondarray.get(0);
                secondselected = locationsecondarray.get(1);
                locationsecondarray.clear();
                locationsecondarray.add(secondselected);
            }
        } else {
            Toast.makeText(getActivity(), "Location Not Found", Toast.LENGTH_LONG).show();
        }
    }
    private double bearingBetweenLocations(LatLng latLng1,LatLng latLng2) {

        double PI = 3.14159;
        double lat1 = latLng1.latitude * PI / 180;
        double long1 = latLng1.longitude * PI / 180;
        double lat2 = latLng2.latitude * PI / 180;
        double long2 = latLng2.longitude * PI / 180;

        double dLon = (long2 - long1);

        double y = Math.sin(dLon) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1)
                * Math.cos(lat2) * Math.cos(dLon);

        double brng = Math.atan2(y, x);

        brng = Math.toDegrees(brng);
        brng = (brng + 360) % 360;

        return brng;
    }
    public void moveVehicle(final Marker myMarker, final Location finalPosition) {

        final LatLng startPosition = myMarker.getPosition();

        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final android.view.animation.Interpolator interpolator = new AccelerateDecelerateInterpolator();
        final float durationInMs = 3000;
        final boolean hideMarker = false;

        handler.post(new Runnable() {
            long elapsed;
            float t;
            float v;

            @Override
            public void run() {
                elapsed = SystemClock.uptimeMillis() - start;
                t = elapsed / durationInMs;
                v = interpolator.getInterpolation(t);
                LatLng currentPosition = new LatLng(
                        startPosition.latitude * (1 - t) + (finalPosition.getLatitude()) * t,
                        startPosition.longitude * (1 - t) + (finalPosition.getLongitude()) * t);
                myMarker.setPosition(currentPosition);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 18.0f));
                if (t < 1) {
                    handler.postDelayed(this, 16);
                } else {
                    if (hideMarker) {
                        myMarker.setVisible(false);
                    } else {
                        myMarker.setVisible(true);
                    }
                }
            }
        });


    }
    public void rotateMarker(final Marker marker, final float toRotation, final float st) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final float startRotation = marker.getRotation();
        final long duration = 1000;
        final Interpolator interpolator = new LinearInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed / duration);
                float rot = t * toRotation + (1 - t) * startRotation;
                marker.setRotation(-rot > 180 ? rot / 2 : rot);
                start_rotation = -rot > 180 ? rot / 2 : rot;
                if (t < 1.0) {
                    handler.postDelayed(this, 10);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    TextView vechilenumbers,cardates,sourcecar,destinationcar,lastupadet,vechilenumber2;
    LinearLayout viewdetails;
    private void showCustomDialog7() {
        ViewGroup viewGroup =v.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.showdetailsvechicle, viewGroup, false);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(true);
        alertDialog.show();
        alertDialog.getWindow().setGravity(Gravity.CENTER);

        vechilenumbers = (TextView) dialogView.findViewById(R.id.vechilenumber);
        viewdetails = (LinearLayout) dialogView.findViewById(R.id.viewdetails);
        //vechilenumber2 = (TextView) dialogView.findViewById(R.id.vechilenumber1);
        cardates = (TextView) dialogView.findViewById(R.id.cardate);
//        sourcecar = (TextView) dialogView.findViewById(R.id.sourcecar);
//        destinationcar = (TextView) dialogView.findViewById(R.id.destinationcar);
        lastupadet = (TextView) dialogView.findViewById(R.id.lastupadet);
        //   ImageView removestaff = (ImageView) dialogView.findViewById(R.id.cancel_bottomsheet_item);
//        removestaff.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
        viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showCustomDialogall();
            }
        });
        vechilenumbers.setText(vecno);
        //vechilenumber2.setText(vecno);
        // sourcecar.setText(sourcename);
        //destinationcar.setText(destiname);
        lastupadet.setText("RFID:-"+lastupadte);
        cardates.setText(cardate);


    }
}