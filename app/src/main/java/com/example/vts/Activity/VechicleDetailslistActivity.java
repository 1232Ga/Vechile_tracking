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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vts.Activity.DeleteVechicleList.DeleteVechicleApi;
import com.example.vts.Activity.DeleteVechicleList.DeletevechicleInterface;
import com.example.vts.Activity.UserDelete.UserDeletegetset;
import com.example.vts.AllVechdetails.AllVechidetilaInterface;
import com.example.vts.AllVechdetails.AllVehicledaetialApi;
import com.example.vts.AllVechdetails.Allvechiclownergetset;
import com.example.vts.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VechicleDetailslistActivity extends AppCompatActivity {
    TextView vechiname,vechinunber,vechicletype,vechiclemodel,Rfid,Deviceid,vechicleowner,drivername,driverphone;
    Context context;
    String Vechileid;
    private ImageView submenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vechicle_detailslist);
        context=VechicleDetailslistActivity.this;
        Vechileid=getIntent().getStringExtra("Vechileid");
        System.out.println("jhkhjhj"+Vechileid);
        submenu = findViewById(R.id.submenu);
        vechiname=findViewById(R.id.vechiname);
        vechinunber=findViewById(R.id.vechinunber);
        vechicletype=findViewById(R.id.vechicletype);
        vechiclemodel=findViewById(R.id.vechiclemodel);
        Rfid=findViewById(R.id.Rfid);
        vechicleowner=findViewById(R.id.vechicleowner);
        drivername=findViewById(R.id.drivername);
        Deviceid=findViewById(R.id.Deviceid);
        driverphone=findViewById(R.id.driverphone);

//        Speed=findViewById(R.id.spedd);
//        avgSpeed=findViewById(R.id.avgspppp);
        Vechileid=getIntent().getStringExtra("Vechileid");
        System.out.println("jhkhjhj"+Vechileid);

        AllVehicledaetialApi.getRetrofitInstance(context).create(AllVechidetilaInterface.class).registration2(Vechileid).enqueue(new Callback<Allvechiclownergetset>() {
            @Override
            public void onResponse(Call<Allvechiclownergetset> call, Response<Allvechiclownergetset> response) {
                System.out.println("jkhkjhjhj"+response.body().getVehicleName());
                vechiname.setText(response.body().getVehicleName());
                vechinunber.setText(response.body().getVehicleNo());
                vechicletype.setText(response.body().getVehicleType());
                vechiclemodel.setText(response.body().getVehicleModel());
                Rfid.setText(response.body().getRFId());
                Deviceid.setText(response.body().getDeviceId());
                vechicleowner.setText(response.body().getVehicleOwnerName());
                drivername.setText(response.body().getDriverName());

//               Speed.setText(response.body().getSpeed());
//                avgSpeed.setText(response.body().getAvgSpeed());
            }

            @Override
            public void onFailure(Call<Allvechiclownergetset> call, Throwable t) {

            }
        });
        submenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(VechicleDetailslistActivity.this, submenu);
                popup.getMenuInflater().inflate(R.menu.menu_example, popup.getMenu());
                popup.setForceShowIcon(true);
                //   popup.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.deletesite) {
                            BackPressed(Vechileid);
                            //Toast.makeText(getApplicationContext(), "Delete clicked", Toast.LENGTH_SHORT).show();
                        } else if (item.getItemId() == R.id.editsite) {
                            Intent intent = new Intent(VechicleDetailslistActivity.this, EditVehicleActivity.class);
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

    private void Delete(String id) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        DeleteVechicleApi.getRetrofitInstance(context).create(DeletevechicleInterface.class).registration(id).enqueue(new Callback<UserDeletegetset>() {
            @Override
            public void onResponse(Call<UserDeletegetset> call, Response<UserDeletegetset> response) {
                if(response.body().getIsSuccess().equalsIgnoreCase("true")){
                    progressDialog.dismiss();

                    Intent intent=new Intent(VechicleDetailslistActivity.this,VechicleListActivity.class);
                    startActivity(intent);
                    Toast.makeText(context, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(context, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserDeletegetset> call, Throwable t) {

            }
        });

    }
    public void back(View view) {
        Intent intent=new Intent(VechicleDetailslistActivity.this,VechicleListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}