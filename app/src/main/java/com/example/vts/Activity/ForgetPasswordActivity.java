package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vts.Activity.ForgetpasswordPack.ForgetApi;
import com.example.vts.Activity.ForgetpasswordPack.Forgetgetset;
import com.example.vts.Activity.ForgetpasswordPack.Forgetparam;
import com.example.vts.R;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity {
TextInputLayout Textinputpassword;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Textinputpassword=findViewById(R.id.Textinputpassword);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        Textinputpassword.setVisibility(View.GONE);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("ijkjhkhkj"+s+" "+count);
                if(count>1){
                    System.out.println("njjnmjnmnm"+count);
                    Textinputpassword.setVisibility(View.VISIBLE);
                }else {
                    System.out.println("czxcxvxv"+count);
                    Textinputpassword.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(ForgetPasswordActivity.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                }else if(password.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(ForgetPasswordActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }else {
                    category();
                }
            }
        });
    }

    private void category() {
        final ProgressDialog progressDialog = new ProgressDialog(ForgetPasswordActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        Forgetparam forgetparam=new Forgetparam();
        forgetparam.Email=username.getText().toString();
        forgetparam.Password=password.getText().toString();
        System.out.println("kjkhkjhkjk"+forgetparam.Email+"   "+forgetparam.Password);
        ForgetApi.getApiService().registration(forgetparam).enqueue(new Callback<Forgetgetset>() {
            @Override
            public void onResponse(Call<Forgetgetset> call, Response<Forgetgetset> response) {
                System.out.println("hjgjhgjg"+response.body());
//                if(response.body().getSuccess()==false){
//                    progressDialog.dismiss();
//                    Toast.makeText(ForgetPasswordActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
//                }else if( response.body().getSuccess()==true){
//                    progressDialog.dismiss();
//                    Intent intent=new Intent(ForgetPasswordActivity.this,LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                    Toast.makeText(ForgetPasswordActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
//                }
            }
            @Override
            public void onFailure(Call<Forgetgetset> call, Throwable t) {

            }
        });
    }
}