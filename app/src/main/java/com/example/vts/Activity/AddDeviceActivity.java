package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vts.Activity.AddDevice.AddDeviceApi;
import com.example.vts.Activity.AddDevice.AddDeviceInterface;
import com.example.vts.Activity.AddDevice.AddDeviceParameter;
import com.example.vts.Activity.AddDevice.AddDevicegetset;
import com.example.vts.R;
import com.example.vts.RolePack.RoleParameter;
import com.example.vts.base.CommonUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDeviceActivity extends AppCompatActivity {
    EditText rolename,description;
    Button Login;
    Context context;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        Login=findViewById(R.id.submit_btn);
        rolename=findViewById(R.id.rolename);
        context=AddDeviceActivity.this;
        description=findViewById(R.id.description);
        sharedPreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rolename.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(AddDeviceActivity.this, "Please Enter Device Name", Toast.LENGTH_SHORT).show();
                }else {
                    category();
                }
            }
        });
    }

    private void category() {
        final ProgressDialog progressDialog = new ProgressDialog(AddDeviceActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        AddDeviceParameter prameter= new AddDeviceParameter();
        prameter.DeviceName=rolename.getText().toString();
        prameter.Notes=description.getText().toString();
        prameter.IsActive="true";
        prameter.IsDeleted="false";
        prameter.OrganizationId=sharedPreferences.getString(CommonUtils.shared_ORG_ID,"");
        AddDeviceApi.getRetrofitInstance(context).create(AddDeviceInterface.class).registration2(prameter).enqueue(new Callback<AddDevicegetset>() {
            @Override
            public void onResponse(Call<AddDevicegetset> call, Response<AddDevicegetset> response) {
                progressDialog.dismiss();
                if(response.body().getSuccess()==false){
                    Toast.makeText(AddDeviceActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }else if( response.body().getSuccess()==true){
                    Intent intent=new Intent(AddDeviceActivity.this,DeviceActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(AddDeviceActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddDevicegetset> call, Throwable t) {

            }
        });
    }

    public void back(View view) {
        Intent intent=new Intent(AddDeviceActivity.this,DeviceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}