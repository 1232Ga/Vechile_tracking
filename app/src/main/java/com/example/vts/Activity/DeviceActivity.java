package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.vts.Activity.DeleteDevice.DeviceDeleteApi;
import com.example.vts.Activity.DeleteDevice.DeviceDeleteInterface;
import com.example.vts.Activity.RoleDetepack.DeleteApi;
import com.example.vts.Activity.RoleDetepack.DeleteInterface;
import com.example.vts.Activity.RoleDetepack.Deletegetset;
import com.example.vts.Activity.UserDelete.UserDeletegetset;
import com.example.vts.DevicePack.Deveicegetset;
import com.example.vts.DevicePack.DeviceApi;
import com.example.vts.DevicePack.DeviceInterface;
import com.example.vts.DevicePack.DeviceListAdapter;
import com.example.vts.R;
import com.example.vts.RoleListPack.UsersAdapter;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceActivity extends AppCompatActivity {
    Context context;
    RecyclerView rolesrecycleview;
    DeviceListAdapter usersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        findViewById(R.id.addrole).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DeviceActivity.this,AddDeviceActivity.class);
                startActivity(intent);
            }
        });
        rolesrecycleview=findViewById(R.id.rolesrecycleview);
        context=DeviceActivity.this;
        DeviceApi.getRetrofitInstance(context).create(DeviceInterface.class).registration2().enqueue(new Callback<List<Deveicegetset>>() {
            @Override
            public void onResponse(Call<List<Deveicegetset>> call, Response<List<Deveicegetset>> response) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DeviceActivity.this);
                rolesrecycleview.setLayoutManager(linearLayoutManager);
                usersAdapter   = new DeviceListAdapter(DeviceActivity.this, response.body()) {
                    @Override
                    public void delete(int id,String position) {
                        Delete(id,position);
                    }
                };
                rolesrecycleview.setAdapter(usersAdapter);
            }

            @Override
            public void onFailure(Call<List<Deveicegetset>> call, Throwable t) {

            }
        });

    }





    private void Delete(int id,String position) {
        final ProgressDialog progressDialog = new ProgressDialog(DeviceActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        DeviceDeleteApi.getRetrofitInstance(context).create(DeviceDeleteInterface.class).registration(position).enqueue(new Callback<UserDeletegetset>() {
            @Override
            public void onResponse(Call<UserDeletegetset> call, Response<UserDeletegetset> response) {
                if(response.body().getIsSuccess().equalsIgnoreCase("true")){
                    progressDialog.dismiss();
                    usersAdapter.removeAt(id);
                    Intent intent=getIntent();
                    startActivity(intent);
                    Toast.makeText(DeviceActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(DeviceActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserDeletegetset> call, Throwable t) {

            }
        });

    }



    public void back(View view) {
        Intent intent=new Intent(DeviceActivity.this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}