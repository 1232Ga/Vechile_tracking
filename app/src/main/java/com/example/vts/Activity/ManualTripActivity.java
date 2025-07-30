package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.vts.Activity.DeleteTripManual.DeleteTripInterface;
import com.example.vts.Activity.DeleteTripManual.DeleteTripManualApi;
import com.example.vts.Activity.EravanaIdPAck.Eravanaidgetset;
import com.example.vts.Activity.EravanaIdPAck.MyAdapter;
import com.example.vts.Activity.EravanaIdPAck.MyAdapter1;
import com.example.vts.Activity.UserDelete.UserDeletegetset;
import com.example.vts.R;
import com.example.vts.RoleListPack.RoleListgetset;
import com.example.vts.RoleListPack.UsersAdapter;
import com.example.vts.TriplistPack.TripListApi;
import com.example.vts.TriplistPack.TripListGetset;
import com.example.vts.TriplistPack.TripListInterface;
import com.example.vts.TriplistPack.TriplistAdapter;
import com.example.vts.base.CommonUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManualTripActivity extends AppCompatActivity {

TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_trip);
        findViewById(R.id.addrole).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ManualTripActivity.this,AddTripActivity.class);
                startActivity(intent);
            }
        });
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Running"));
        tabLayout.addTab(tabLayout.newTab().setText("Completed"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        MyAdapter1 adapter = new MyAdapter1(this,getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }


    public void back(View view) {
        Intent intent=new Intent(ManualTripActivity.this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}