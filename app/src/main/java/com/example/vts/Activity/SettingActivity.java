package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.vts.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViewById(R.id.changepass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });
     findViewById(R.id.profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity.this,UserProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void back(View view) {
        Intent intent=new Intent(SettingActivity.this,DashboardActivity.class);
        startActivity(intent);
    }
}