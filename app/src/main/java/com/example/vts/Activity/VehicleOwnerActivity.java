package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.vts.Activity.UserDelete.UserDeletegetset;
import com.example.vts.Activity.VechicleOwnerdelete.VecheldeletApi;
import com.example.vts.Activity.VechicleOwnerdelete.VechicleDeleteInterface;
import com.example.vts.DevicePack.DeviceListAdapter;
import com.example.vts.R;
import com.example.vts.VechoeownerPack.VechicleInterface;
import com.example.vts.VechoeownerPack.VechicleownerListAdapter;
import com.example.vts.VechoeownerPack.Vechicleownergetset;
import com.example.vts.VechoeownerPack.VehicleOwnerApi;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleOwnerActivity extends AppCompatActivity {
    RecyclerView rolesrecycleview;
    Context context;
    VechicleownerListAdapter usersAdapter;
    EditText Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_owner);
        rolesrecycleview=findViewById(R.id.rolesrecycleview);
        context=VehicleOwnerActivity.this;
        Search=findViewById(R.id.Search);
        findViewById(R.id.addrole).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VehicleOwnerActivity.this,AddOwnerActivity.class);
                startActivity(intent);
            }
        });

        Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                usersAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        VehicleOwnerApi.getRetrofitInstance(context).create(VechicleInterface.class).registration2().enqueue(new Callback<List<Vechicleownergetset>>() {
            @Override
            public void onResponse(Call<List<Vechicleownergetset>> call, Response<List<Vechicleownergetset>> response) {
                System.out.println("ygghhghhghhfghfhghffg"+new Gson().toJson(response.body()));
                if(response.body().isEmpty()){
                    rolesrecycleview.setVisibility(View.GONE);
                }else {
                    rolesrecycleview.setVisibility(View.VISIBLE);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(VehicleOwnerActivity.this);
                    rolesrecycleview.setLayoutManager(linearLayoutManager);

                    usersAdapter   = new VechicleownerListAdapter(VehicleOwnerActivity.this, response.body()) {
                        @Override
                        public void delete(int id,String position) {
                            //Delete(id,position);
                        }
                    };
                    rolesrecycleview.setAdapter(usersAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<Vechicleownergetset>> call, Throwable t) {

            }
        });
    }
    public void back(View view) {
        Intent intent=new Intent(VehicleOwnerActivity.this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}