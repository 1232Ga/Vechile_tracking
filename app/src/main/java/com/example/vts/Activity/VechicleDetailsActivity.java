package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.vts.Activity.UserDelete.UserDeletegetset;
import com.example.vts.Activity.VechicleOwnerdelete.VecheldeletApi;
import com.example.vts.Activity.VechicleOwnerdelete.VechicleDeleteInterface;
import com.example.vts.R;
import com.example.vts.VechoeownerPack.Vechicleownergetset;
import com.example.vts.VechoeownerPack.VechileInterCaeDetails;
import com.example.vts.VechoeownerPack.VehicleOwnerApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VechicleDetailsActivity extends AppCompatActivity {
TextView transportname,Emaill,mobilenumber,address,vehcilenumber,ownername;;
Context context;
String Vechileid;
private ImageView submenu;
    ImageView menuimage1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vechicle_details);
        //vecholename=findViewById(R.id.vecholename);
        transportname=findViewById(R.id.transportname);
        Emaill=findViewById(R.id.Emaill);
        submenu = findViewById(R.id.submenu);
        mobilenumber=findViewById(R.id.mobilenumber);
        address=findViewById(R.id.address);
        vehcilenumber=findViewById(R.id.vehcilenumber);
        ownername=findViewById(R.id.ownername);
        context=VechicleDetailsActivity.this;
        Vechileid=getIntent().getStringExtra("Vechileid");
        System.out.println("jhkhjhj"+Vechileid);
        menuimage1=findViewById(R.id.menuimage1);
        VehicleOwnerApi.getRetrofitInstance(context).create(VechileInterCaeDetails.class).registration(Vechileid).enqueue(new Callback<Vechicleownergetset>() {
            @Override
            public void onResponse(Call<Vechicleownergetset> call, Response<Vechicleownergetset> response) {

                //vecholename.setText(response.body().getOwnerName());
                ownername.setText(response.body().getOwnerName());
                transportname.setText(response.body().getTransportName());
                Emaill.setText(response.body().getEmail());
                mobilenumber.setText(response.body().getMobile());
                address.setText(response.body().getAddress());
                vehcilenumber.setText(response.body().getTotalNoOfVehicle());
                if(!response.body().getProfileImagePath().equalsIgnoreCase("")){
                    Glide.with(context)
                            .load(response.body().getProfileImagePath())
                            .error(R.drawable.dumyimgae)
                            .into(menuimage1);
                }
            }

            @Override
            public void onFailure(Call<Vechicleownergetset> call, Throwable t) {

            }
        });
        submenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(VechicleDetailsActivity.this, submenu);
                popup.getMenuInflater().inflate(R.menu.menu_example, popup.getMenu());
                popup.setForceShowIcon(true);
                //   popup.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.deletesite) {
                            BackPressed(Vechileid);
                            //Toast.makeText(getApplicationContext(), "Delete clicked", Toast.LENGTH_SHORT).show();
                        } else if (item.getItemId() == R.id.editsite) {
                            Intent intent = new Intent(VechicleDetailsActivity.this, EditOwnerActivity.class);
                            intent.putExtra("Vechileid",Vechileid);
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

    private void Delete(String position) {

        final ProgressDialog progressDialog = new ProgressDialog(VechicleDetailsActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();

        VecheldeletApi.getRetrofitInstance(context).create(VechicleDeleteInterface.class).registration(position).enqueue(new Callback<UserDeletegetset>() {
            @Override
            public void onResponse(Call<UserDeletegetset> call, Response<UserDeletegetset> response) {
                if(response.body().getIsSuccess().equalsIgnoreCase("true")){
                    progressDialog.dismiss();
                    Intent intent=new Intent(VechicleDetailsActivity.this,VehicleOwnerActivity.class);
                    startActivity(intent);
                    Toast.makeText(VechicleDetailsActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(VechicleDetailsActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserDeletegetset> call, Throwable t) {

            }
        });
    }


    public void back(View view) {
        Intent intent=new Intent(VechicleDetailsActivity.this,VehicleOwnerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}