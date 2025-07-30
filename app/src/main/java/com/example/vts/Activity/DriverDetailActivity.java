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

import com.bumptech.glide.Glide;
import com.example.vts.Activity.DeleteDriver.DriverDeleteApi;
import com.example.vts.Activity.DeleteDriver.DriverDeleteInterface;
import com.example.vts.Activity.UserDelete.UserDeletegetset;
import com.example.vts.DriverPack.DriverApi;
import com.example.vts.DriverPack.DriverIDetilalnterface;
import com.example.vts.DriverPack.Drivergetset;
import com.example.vts.R;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverDetailActivity extends AppCompatActivity {
    String Driverid;
    Context context;
    TextView mobilenumber,address,vehcilenumber,ownername;;
    private ImageView submenu;
    ImageView menuimage1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_detail);
        context=DriverDetailActivity.this;
        Driverid=getIntent().getStringExtra("Driverid");
        mobilenumber=findViewById(R.id.mobilenumber);
        address=findViewById(R.id.address);
        submenu = findViewById(R.id.submenu);
        vehcilenumber=findViewById(R.id.vehcilenumber);
        ownername=findViewById(R.id.ownername);
        menuimage1=findViewById(R.id.menuimage1);
        DriverApi.getRetrofitInstance(context).create(DriverIDetilalnterface.class).registration2(Driverid).enqueue(new Callback<Drivergetset>() {
            @Override
            public void onResponse(Call<Drivergetset> call, Response<Drivergetset> response) {
                System.out.println("jkhkjhjkhjk"+new Gson().toJson(response.body()));
                ownername.setText(response.body().getDriverName());
                mobilenumber.setText(response.body().getPhoneNo());
                address.setText(response.body().getAddress());
                vehcilenumber.setText(response.body().getIdentityNo());
                if(!response.body().getProofImage().equalsIgnoreCase("")){
                    Glide.with(context)
                            .load(response.body().getProofImage())
                            .error(R.drawable.dumyimgae)
                            .into(menuimage1);
                }
            }

            @Override
            public void onFailure(Call<Drivergetset> call, Throwable t) {

            }
        });
        submenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(DriverDetailActivity.this, submenu);
                popup.getMenuInflater().inflate(R.menu.menu_example, popup.getMenu());
                popup.setForceShowIcon(true);
                //   popup.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.deletesite) {
                            BackPressed(Driverid);
                            //Toast.makeText(getApplicationContext(), "Delete clicked", Toast.LENGTH_SHORT).show();
                        } else if (item.getItemId() == R.id.editsite) {
                            Intent intent = new Intent(DriverDetailActivity.this, EditDriverActivity.class);
                            intent.putExtra("Driverid",getIntent().getStringExtra("Driverid"));
                            startActivity(intent);
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

    private void Delete( String position) {

        final ProgressDialog progressDialog = new ProgressDialog(DriverDetailActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        DriverDeleteApi.getRetrofitInstance(context).create(DriverDeleteInterface.class).registration(position).enqueue(new Callback<UserDeletegetset>() {
            @Override
            public void onResponse(Call<UserDeletegetset> call, Response<UserDeletegetset> response) {
                if(response.body().getIsSuccess().equalsIgnoreCase("true")){
                    progressDialog.dismiss();
                    Intent intent=new Intent(DriverDetailActivity.this,DriverManagementActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(DriverDetailActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(DriverDetailActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserDeletegetset> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
    public void back(View view) {
        Intent intent=new Intent(DriverDetailActivity.this,DriverManagementActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}