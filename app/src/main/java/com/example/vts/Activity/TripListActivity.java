package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vts.Activity.AddTripPack.SiteNonSiteTripAdapeter;
import com.example.vts.Activity.EravanaIdPAck.MyAdapter1;
import com.example.vts.R;
import com.example.vts.SitePackage.SiteAdapter;
import com.example.vts.SitePackage.SiteGetset;
import com.example.vts.SitePackage.SiteInterface;
import com.example.vts.SitePackage.SiteListApi;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripListActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
Context context;

    Boolean isAllFabsVisible;
    FloatingActionButton msite, mnonsite,mAddFab;
    TextView addSiteActionText, addNonSiteActionText;
    SiteNonSiteTripAdapeter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);
        context=TripListActivity.this;
//        findViewById(R.id.addrole).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(TripListActivity.this,AddTripActivity.class);
//                startActivity(intent);
//            }
//        });
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Site Trip"));
        tabLayout.addTab(tabLayout.newTab().setText("Non-Site Trip"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter= new SiteNonSiteTripAdapeter(this,getSupportFragmentManager(),tabLayout.getTabCount());
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


        mAddFab = findViewById(R.id.add_fab);
        msite = findViewById(R.id.add_alarm_fab);
        mnonsite = findViewById(R.id.add_person_fab);
        addSiteActionText = findViewById(R.id.add_alarm_action_text);
        addNonSiteActionText = findViewById(R.id.add_person_action_text);

        addSiteActionText.setVisibility(View.GONE);
        addNonSiteActionText.setVisibility(View.GONE);
        mnonsite.setVisibility(View.GONE);
        msite.setVisibility(View.GONE);
        isAllFabsVisible = false;
      //  mAddFab.shrink();

        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAllFabsVisible) {
                    msite.show();
                    mnonsite.show();
                    addSiteActionText.setVisibility(View.VISIBLE);
                    addNonSiteActionText.setVisibility(View.VISIBLE);
                    //mAddFab.extend();
                    isAllFabsVisible = true;
                }else {
                    msite.hide();
                    mnonsite.hide();
                    addNonSiteActionText.setVisibility(View.GONE);
                    addSiteActionText.setVisibility(View.GONE);
                   // mAddFab.shrink();
                    isAllFabsVisible = false;
                }
            }
        });
        mnonsite.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(TripListActivity.this,AddTripActivity.class);
                        intent.putExtra("NonSite","Nonsite");
                          startActivity(intent);
                        //Toast.makeText(TripListActivity.this, "NonSite Added", Toast.LENGTH_SHORT).show();
                    }
                });
        msite.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(TripListActivity.this,AddTripActivity.class);
                        intent.putExtra("NonSite","Site");
                        startActivity(intent);
                        //Toast.makeText(TripListActivity.this, "Site Added", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public void back(View view) {
        Intent intent=new Intent(TripListActivity.this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}