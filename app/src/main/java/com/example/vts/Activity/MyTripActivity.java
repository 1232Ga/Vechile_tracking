package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.vts.Activity.DashboardApi.Country;
import com.example.vts.Activity.EravanaIdPAck.MyAdapter;
import com.example.vts.R;
import com.example.vts.SitePackage.SiteGetset;
import com.example.vts.SitePackage.SiteInterface;
import com.example.vts.SitePackage.SiteListApi;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTripActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Spinner Sitelistspin;
    Context context;
    String SiteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trip2);
        tabLayout = findViewById(R.id.tabLayout);
        Sitelistspin=findViewById(R.id.Sitelistspin);
        viewPager = findViewById(R.id.viewPager);
        context=MyTripActivity.this;

        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Running"));
        tabLayout.addTab(tabLayout.newTab().setText("Completed"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        MyAdapter adapter = new MyAdapter(MyTripActivity.this,getSupportFragmentManager(),tabLayout.getTabCount(),SiteId);
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

        SiteListApi.getRetrofitInstance(context).create(SiteInterface.class).registration2().enqueue(new Callback<List<SiteGetset>>() {
            @Override
            public void onResponse(Call<List<SiteGetset>> call, Response<List<SiteGetset>> response) {
                ArrayList<Country> countryList = new ArrayList<>();
               // countryList.add(new Country("Choose Site","Choose Site"));
                for (SiteGetset data:response.body()){
                    countryList.add(new Country(data.getSiteId(), data.getName()));
                    ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(context, android.R.layout.simple_spinner_dropdown_item, countryList);
                    Sitelistspin.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<SiteGetset>> call, Throwable t) {

            }
        });
        Sitelistspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country country = (Country) parent.getSelectedItem();
                SiteId=country.getId();
                System.out.println("gfhfghfhgf"+SiteId);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        System.out.println("gfgfghghgj"+SiteId);



    }
    public void back(View view) {
        onBackPressed();
    }


}