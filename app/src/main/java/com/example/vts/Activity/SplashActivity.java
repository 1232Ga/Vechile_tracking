package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.vts.R;
import com.example.vts.base.CommonUtils;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    private String Tokens;
    private SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler=new Handler();
        sharedpreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
       // Tokens = sharedpreferences.getString(CommonUtils., "");
        System.out.println("jkjhkjh"+Tokens);
        new Handler().postDelayed(() -> {

            Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
            startActivity( intent);
//            if (Tokens.equalsIgnoreCase("")) {
//
//            }else {
//                Intent intent=new Intent(SplashActivity.this, DashboardActivity.class);
//                startActivity( intent);
//            }
//            Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
//            startActivity( intent);


        }, 2000);
    }
}