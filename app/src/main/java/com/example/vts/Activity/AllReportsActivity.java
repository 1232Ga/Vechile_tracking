package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.vts.R;

public class AllReportsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reports);
       findViewById(R.id.ll_kmreport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AllReportsActivity.this, KmReportActivity.class);
                startActivity(intent);
            }
        });
       findViewById(R.id.ll_overspeed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AllReportsActivity.this, OverSppedReportActivity.class);
                startActivity(intent);
            }
        });
       findViewById(R.id.consolidate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AllReportsActivity.this, ConsolidateReportActivity.class);
                startActivity(intent);
            }
        });
       findViewById(R.id.ll_trackingreport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AllReportsActivity.this, TrackingReportActivity.class);
                startActivity(intent);
            }
        });
       findViewById(R.id.ll_daywise).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AllReportsActivity.this, DayWiseReportActivity.class);
                startActivity(intent);
            }
        });
       findViewById(R.id.currentsum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AllReportsActivity.this, CurentsummaryReportActivity.class);
                startActivity(intent);
            }
        });
       findViewById(R.id.ll_idlereport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AllReportsActivity.this, IdleVehiceleReportActivity.class);
                startActivity(intent);
            }
        });

       findViewById(R.id.haltvehicle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AllReportsActivity.this, HaltReportActivity.class);
                startActivity(intent);
            }
        });
       findViewById(R.id.ll_ignition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AllReportsActivity.this, IgnitiononoffActivity.class);
                startActivity(intent);
            }
        });
    }

    public void back(View view) {
        Intent intent=new Intent(AllReportsActivity.this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}