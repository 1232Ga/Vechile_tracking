package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vts.Passwordchangepack.ChangePasswordgetset;
import com.example.vts.Passwordchangepack.ChangepasswordApi;
import com.example.vts.Passwordchangepack.ChangepasswordInterface;
import com.example.vts.Passwordchangepack.PasswordParam;
import com.example.vts.R;
import com.example.vts.base.CommonUtils;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
EditText currentpassword,newpassword,confirmpassword;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor prefEditor;
    String Userid;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        currentpassword=findViewById(R.id.currenpaasword);
        newpassword=findViewById(R.id.newpaasword);
        confirmpassword=findViewById(R.id.confirmpaasword);
        context=ChangePasswordActivity.this;
        sharedpreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        prefEditor = sharedpreferences.edit();
        Userid=sharedpreferences.getString(CommonUtils.shared_USER_ID,"");
        System.out.println("jkkhkhjjh"+Userid);
        findViewById(R.id.cancelbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChangePasswordActivity.this,DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentpassword.getText().toString().equalsIgnoreCase("")){
                    currentpassword.setError("Current Password");
                    Toast.makeText(ChangePasswordActivity.this, "Please Enter Current Password", Toast.LENGTH_SHORT).show();
                }else if(newpassword.getText().toString().equalsIgnoreCase("")){
                    newpassword.setError("New Password");
                    Toast.makeText(ChangePasswordActivity.this, "Please Enter New Password", Toast.LENGTH_SHORT).show();
                }else if(confirmpassword.getText().toString().equalsIgnoreCase("")){
                    confirmpassword.setError("confirm Password");
                    Toast.makeText(ChangePasswordActivity.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                }else {
                    category();
                }

            }
        });

    }

    private void category() {
        final ProgressDialog progressDialog = new ProgressDialog(ChangePasswordActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        PasswordParam passwordParam=new PasswordParam();
        passwordParam.ConfirmPassword=confirmpassword.getText().toString();
        passwordParam.NewPassword=newpassword.getText().toString();
        passwordParam.CurrentPassword=currentpassword.getText().toString();
        passwordParam.Userid=Userid;

      ChangepasswordApi.getRetrofitInstance(context).create(ChangepasswordInterface.class).registration(passwordParam).enqueue(new Callback<ChangePasswordgetset>() {
         @Override
         public void onResponse(Call<ChangePasswordgetset> call, Response<ChangePasswordgetset> response) {
             System.out.println("jhghjgjgh"+new Gson().toJson(response.body()));
             progressDialog.dismiss();
             if(response.body().getSuccess()==true){
                 confirmpassword.setText("");
                 newpassword.setText("");
                 currentpassword.setText("");
                 Toast.makeText(ChangePasswordActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_LONG).show();
             }else {
                 Toast.makeText(ChangePasswordActivity.this, response.body().getErrorMessage(), Toast.LENGTH_LONG).show();
             }
         }

         @Override
         public void onFailure(Call<ChangePasswordgetset> call, Throwable t) {

         }
     });

    }

    public void back(View view) {
        Intent intent=new Intent(ChangePasswordActivity.this,SettingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}