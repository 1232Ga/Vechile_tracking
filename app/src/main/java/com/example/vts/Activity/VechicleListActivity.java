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
import android.widget.EditText;
import android.widget.Toast;

import com.example.vts.Activity.DeleteVechicleList.DeleteVechicleApi;
import com.example.vts.Activity.DeleteVechicleList.DeletevechicleInterface;
import com.example.vts.Activity.UserDelete.UserDeletegetset;
import com.example.vts.R;
import com.example.vts.SitePackage.SiteAdapter;
import com.example.vts.Vechilelistpack.VechicleInterface;
import com.example.vts.Vechilelistpack.Vechiclegetset;
import com.example.vts.Vechilelistpack.VechiclilistApi;
import com.example.vts.Vechilelistpack.VechileListAdapter;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VechicleListActivity extends AppCompatActivity {
    Context context;
    RecyclerView rolesrecycleview;
    VechileListAdapter usersAdapter;
    EditText Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vechicle_list);
        findViewById(R.id.addrole).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VechicleListActivity.this,AddVehicleActivity.class);
                startActivity(intent);
            }
        });
        rolesrecycleview=findViewById(R.id.rolesrecycleview);
        context=VechicleListActivity.this;

        Search=findViewById(R.id.Search);
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
        VechiclilistApi.getRetrofitInstance(context).create(VechicleInterface.class).registration2().enqueue(new Callback<List<Vechiclegetset>>() {
            @Override
            public void onResponse(Call<List<Vechiclegetset>> call, Response<List<Vechiclegetset>> response) {
                System.out.println("kjkhjhjhj"+new Gson().toJson(response.body()));
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                rolesrecycleview.setLayoutManager(linearLayoutManager);
                usersAdapter   = new VechileListAdapter(VechicleListActivity.this, response.body()) {
                    @Override
                    public void edit(String id, String name, String role, String status) {

                    }
                    @Override
                    public void delete(String id,int position) {
                        Delete(id,position);
                    }
                };
                rolesrecycleview.setAdapter(usersAdapter);
            }

            @Override
            public void onFailure(Call<List<Vechiclegetset>> call, Throwable t) {

            }
        });

    }

    private void Delete(String id, int position) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        DeleteVechicleApi.getRetrofitInstance(context).create(DeletevechicleInterface.class).registration(id).enqueue(new Callback<UserDeletegetset>() {
            @Override
            public void onResponse(Call<UserDeletegetset> call, Response<UserDeletegetset> response) {
                if(response.body().getIsSuccess().equalsIgnoreCase("true")){
                    progressDialog.dismiss();
                    usersAdapter.removeAt(position);
                    Intent intent=getIntent();
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
        Intent intent=new Intent(VechicleListActivity.this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}