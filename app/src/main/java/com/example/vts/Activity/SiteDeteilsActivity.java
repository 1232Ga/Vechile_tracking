package com.example.vts.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.vts.Activity.SiteDelete.SiteDeleteApi;
import com.example.vts.Activity.SiteDelete.SiteDeleteInterface;
import com.example.vts.Activity.UserDelete.UserDeletegetset;
import com.example.vts.R;
import com.example.vts.SitePackage.SiteListApi;
import com.example.vts.SitePackage.Sitedetailgetset;
import com.example.vts.SitePackage.Sitedeteilainterface;
import com.example.vts.base.CommonUtils;
import com.example.vts.util.GPSTracker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiteDeteilsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    String kk,Siteid;
    Context context;
    SharedPreferences sharedPreferences;
    EditText latlong, address, country, stste, District, pincode, ststename,orgnizationname;
    private LinearLayout mBottomSheetLayout;
    private BottomSheetBehavior sheetBehavior;
    private ImageView header_Arrow_Image,submenu;
     double mylat,mylom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_deteils);
        Siteid=getIntent().getStringExtra("Siteid");
        System.out.println("jkhjghjgjg"+Siteid);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        address = findViewById(R.id.address);
        submenu = findViewById(R.id.submenu);
        context=SiteDeteilsActivity.this;
        latlong = findViewById(R.id.latlong);
        mBottomSheetLayout = findViewById(R.id.bottom_sheet_layout);
        sheetBehavior = BottomSheetBehavior.from(mBottomSheetLayout);
        header_Arrow_Image = findViewById(R.id.bottom_sheet_arrow);

        country = findViewById(R.id.country);
        stste = findViewById(R.id.stste);
        orgnizationname = findViewById(R.id.orgnizationname);
        District = findViewById(R.id.District);
        pincode = findViewById(R.id.pincode);
        ststename = findViewById(R.id.ststename);
        sharedPreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        header_Arrow_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

            }
        });

      submenu.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              PopupMenu popup = new PopupMenu(SiteDeteilsActivity.this, submenu);
              popup.getMenuInflater().inflate(R.menu.menu_example, popup.getMenu());
              popup.setForceShowIcon(true);
           //   popup.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

              popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                  public boolean onMenuItemClick(MenuItem item) {
                      if (item.getItemId() == R.id.deletesite) {
                          BackPressed(Siteid);
                          //Toast.makeText(getApplicationContext(), "Delete clicked", Toast.LENGTH_SHORT).show();
                      } else if (item.getItemId() == R.id.editsite) {
                          Intent intent = new Intent(SiteDeteilsActivity.this, EditSiteActivity.class);
                          intent.putExtra("Siteid",Siteid);
                          startActivity(intent);
                          finish();
                         // Toast.makeText(getApplicationContext(), "Edit clicked", Toast.LENGTH_SHORT).show();
                      }

                      return true;
                  }
              });
              popup.show();
          }
      });
    }

    public void BackPressed(String id) {

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setMessage("Do you want to Delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Delete(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
    }

    private void Delete(String id) {
        final ProgressDialog progressDialog = new ProgressDialog(SiteDeteilsActivity.this);
        progressDialog.setCancelable(false); // set cancelable to User
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();

        SiteDeleteApi.getRetrofitInstance(context).create(SiteDeleteInterface.class).registration(id).enqueue(new Callback<UserDeletegetset>() {
            @Override
            public void onResponse(Call<UserDeletegetset> call, Response<UserDeletegetset> response) {
                if(response.body().getIsSuccess().equalsIgnoreCase("true")){
                    progressDialog.dismiss();
                    Intent intent=new Intent(SiteDeteilsActivity.this,SiteActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(SiteDeteilsActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(SiteDeteilsActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserDeletegetset> call, Throwable t) {

            }
        });
    }





    public void back(View view) {
        Intent intent = new Intent(SiteDeteilsActivity.this, SiteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        SiteListApi.getRetrofitInstance(context).create(Sitedeteilainterface.class).registration2(Siteid).enqueue(new Callback<Sitedetailgetset>() {
            @Override
            public void onResponse(Call<Sitedetailgetset> call, Response<Sitedetailgetset> response) {
                String data = response.body().getGeoLocation();
                System.out.println("hjhgjhgjgj"+data);
                String [] tempArray = data.split(",");
                mylom=Double.parseDouble(data.split(",")[1].toString().trim());
                mylat=Double.parseDouble(data.split(",")[0].toString().trim());
                System.out.println("----->"+mylat+","+mylom);
//                mylom = Double.parseDouble(tempArray[0]);
//                mylat = Double.parseDouble(tempArray[1]);
                latlong.setText(mylat+" ,"+mylom);
                address.setText(response.body().getAddress());
                country.setText(response.body().getCountry());
                stste.setText(response.body().getState());
                District.setText(response.body().getDistrict());
                pincode.setText(response.body().getPinCode());
                ststename.setText(response.body().getCity());
                orgnizationname.setText(response.body().getName());
                LatLng coordinate = new LatLng(mylat, mylom);
                MarkerOptions markerOptions=new MarkerOptions().position(coordinate);
                CameraUpdate locations = CameraUpdateFactory.newLatLngZoom(coordinate, 14);
                mMap.animateCamera(locations);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 14));
                String  s= String.valueOf(coordinate);
                String jj=(s.replaceAll("lat/lng:",""));
                kk=(jj.replaceAll("()",""));
                mMap.addMarker(markerOptions);
            }

            @Override
            public void onFailure(Call<Sitedetailgetset> call, Throwable t) {

            }
        });



    }
}