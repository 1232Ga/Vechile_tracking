package com.example.vts.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.vts.Activity.DasboradAllVechicleActivity.onClickInterface;
import com.example.vts.Activity.DashboardApi.Country;
import com.example.vts.Activity.DashboardApi.DashBoardApi;
import com.example.vts.Activity.DashboardApi.Dashbaordgetset;
import com.example.vts.Activity.DashboardApi.DashboardInterface;
import com.example.vts.Activity.DashboardApi.DashboardParamter;
import com.example.vts.Activity.DashboardApi.RunnungVehicleManagementlst;
import com.example.vts.Activity.DashboardApi.VehicleManagementItem;
import com.example.vts.Activity.EravanaIdPAck.EravanaAllRunningVechicleListAdapter;

import com.example.vts.Activity.Fragment.DashboardMapFragment;
import com.example.vts.LiveTracking.GenerateAuthTokenAPI;
import com.example.vts.LiveTracking.GetAuthApi;
import com.example.vts.LiveTracking.GetAuthGetSet;
import com.example.vts.LiveTracking.ResultData;
import com.example.vts.MainActivity;
import com.example.vts.R;
import com.example.vts.SitePackage.DashboardSiteAdapter;
import com.example.vts.SitePackage.SiteGetset;
import com.example.vts.SitePackage.SiteInterface;
import com.example.vts.SitePackage.SiteListApi;
import com.example.vts.TodayRunPack.TodayRunVechicleListAdapter;
import com.example.vts.VechoeownerPack.VechicleownerDashboardListAdapter;
import com.example.vts.base.CommonUtils;
import com.example.vts.util.GPSTracker;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.Locale;

import java.util.TimeZone;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity  {
    public static ImageView iv_nav;
    Resources mResources;
    Menu mainMenu;
    SharedPreferences sharedpreferences;
    String user_name,Email;
    private GoogleMap googleMapHomeFrag;
    LinearLayout ll_neworderdash,ll_serach,ll_opeserachoff;
    View mapView;
    TextView textallactivity,textdefaultactivity,tv_header_msg,tv_header_email;
    VechicleownerDashboardListAdapter usersAdapter;
    private float start_rotation;
    private double[] latLng = new double[2];
    PieChartView pieChartView;
    BarChart barChart;
    BarData barData;
    String cou,over,vilo,runc;
    BarDataSet barDataSet;
    ArrayList barEntries;
    BottomNavigationView bottomNavigationView;
    TextView todayrunvechiclecount,todatevlue,Allruningvechiclecount,setdatevlue,overspeedcount,routevilotion,fromdatevlue;
    com.github.mikephil.charting.charts.RadarChart RadarChart;
    RadarData radarData;
    RadarDataSet radarDataSet;
    ArrayList radarEntries;
    String todaydatevalue,siteid;
    BubbleChart bubbleChart;
    BubbleData bubbleData;
    BubbleDataSet bubbleDataSet;
    ArrayList bubbleEntries;
    private Marker marker;
    ScatterChart scatterChart;
    ScatterData scatterData;

    ScatterDataSet scatterDataSet;
    ArrayList scatterEntries;
    Spinner packagelistspin;
    RecyclerView rolesrecycleview,allvechiclerecycleview;
    Context context;
    ImageView datechoose,searchopen,searchhide;
    List<RunnungVehicleManagementlst>runnungVehicleManagementlsts;
    List<VehicleManagementItem> vehicleManagementItem;
    com.example.vts.Activity.DashboardApi.ResultData datum;
    String vecno,cardate,lastupadte,sourcename,destiname;
    RecyclerView siterecycleview;
    DashboardSiteAdapter adpter;
    AutoCompleteTextView text;

    String selectedFragment = "";
    FragmentManager fm;
    DashboardMapFragment f1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        iv_nav = findViewById(R.id.iv_nav);
        ll_neworderdash = findViewById(R.id.ll_neworderdash);
        mResources = getResources();
        rolesrecycleview=findViewById(R.id.rolesrecycleview);
        allvechiclerecycleview=findViewById(R.id.allvechiclerecycleview);
        packagelistspin=findViewById(R.id.packagelistspin);
        datechoose=findViewById(R.id.datechoose);
        textallactivity=findViewById(R.id.textallactivity);
        textdefaultactivity=findViewById(R.id.textdefaultactivity);

        textallactivity.setVisibility(View.GONE);
        textdefaultactivity.setVisibility(View.GONE);
        setdatevlue=findViewById(R.id.setdatevlue);
        siterecycleview=findViewById(R.id.siterecycleview);

        fromdatevlue=findViewById(R.id.fromdatevlue);
        todatevlue=findViewById(R.id.todatevlue);
        searchopen=findViewById(R.id.searchopen);
        searchhide=findViewById(R.id.searchhide);
        ll_serach=findViewById(R.id.ll_serach);
        ll_opeserachoff=findViewById(R.id.ll_opeserachoff);
        ll_serach.setVisibility(View.GONE);
        searchopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_opeserachoff.setVisibility(View.GONE);
                ll_serach.setVisibility(View.VISIBLE);
            }
        });
        searchhide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_opeserachoff.setVisibility(View.VISIBLE);
                ll_serach.setVisibility(View.GONE);
            }
        });





        todayrunvechiclecount=findViewById(R.id.todayrunvechiclecount);
        Allruningvechiclecount=findViewById(R.id.Allruningvechiclecount);
        overspeedcount=findViewById(R.id.overspeedcount);
        routevilotion=findViewById(R.id.routevilotion);
        context=DashboardActivity.this;

        GetAuthApi.getApiService().registration2().enqueue(new Callback<GetAuthGetSet>() {
            @Override
            public void onResponse(Call<GetAuthGetSet> call, Response<GetAuthGetSet> response) {
                System.out.println("gffdgfhfh"+response.body().getResultData());
                ResultData data=response.body().getResultData();
                System.out.println("hjggjggjg"+data);
                GenerateAuthTokenAPI authTokenAPI=data.getGenerateAuthTokenAPI();
                System.out.println("kjkhjhjj"+authTokenAPI);
                String token=authTokenAPI.getToken();
                System.out.println("jkhkhjkhj"+token);
            }
            @Override
            public void onFailure(Call<GetAuthGetSet> call, Throwable t) {

            }
        });
        text=(AutoCompleteTextView)findViewById(R.id.editText1);


        SiteListApi.getRetrofitInstance(context).create(SiteInterface.class).registration2().enqueue(new Callback<List<SiteGetset>>() {
            @Override
            public void onResponse(Call<List<SiteGetset>> call, Response<List<SiteGetset>> response) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DashboardActivity.this,LinearLayoutManager.HORIZONTAL,false);
                siterecycleview.setLayoutManager(linearLayoutManager);
                ArrayList<Country> countryList = new ArrayList<>();
                for (SiteGetset data:response.body()) {
                    countryList.add(new Country(data.getSiteId(), data.getName()));
                    ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(context, R.layout.spinnerdropdownitem, countryList);
                    text.setAdapter(adapter);
                    text.setThreshold(2);

                    text.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            System.out.println("hjhghgh"+id);
                            System.out.println("bhhfhfhg"+adapter.getItem(position).getName());
                            String s=adapter.getItem(position).getId();
                            f1 = new DashboardMapFragment(s);
                            loadFrag(f1, "home", fm);
                        }
                    });
                }

                adpter= new DashboardSiteAdapter(DashboardActivity.this, response.body()) {
                    @Override
                    public void delete(String id) {
                        System.out.println("hjhjhjgjhg"+id);

                    }
                };
                siterecycleview.setAdapter(adpter);

            }

            @Override
            public void onFailure(Call<List<SiteGetset>> call, Throwable t) {

            }
        });




        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        System.out.println("xcdffhhh"+dateFormat.format(date));
        todaydatevalue=dateFormat.format(date);
        setdatevlue.setText(todaydatevalue);

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));

        DateFormat inputFormat1 = new SimpleDateFormat("d/M/yyyy");//2021-02-23
        DateFormat outputFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        String ss=getDate(cal);
        String inputDateStr1=ss.trim();
        Date date1 = null;
        try {
            date1 = inputFormat1.parse(inputDateStr1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr1= outputFormat1.format(date1);
        fromdatevlue.setText(outputDateStr1);

        cal.add(Calendar.DATE, 7);
        DateFormat inputFormat11 = new SimpleDateFormat("d/M/yyyy");//2021-02-23
        DateFormat outputFormat11 = new SimpleDateFormat("dd/MM/yyyy");
        String sss=getDate(cal);
        String inputDateStr11=sss.trim();
        Date date12 = null;
        try {
            date12 = inputFormat11.parse(inputDateStr11);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr11= outputFormat11.format(date12);
        todatevlue.setText(outputDateStr11);
        dash(fromdatevlue.getText().toString(),todatevlue.getText().toString());

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigationbar);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);








        fromdatevlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
               int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(DashboardActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormat = "dd/MM/yyyy"; //Change as you need

                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                        System.out.println("jhughjgjhgh"+sdf.format(myCalendar.getTime()));
                        DateFormat inputFormat1 = new SimpleDateFormat("d/M/yyyy");//2021-02-23
                        DateFormat outputFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                        String ss=sdf.format(myCalendar.getTime());
                        String inputDateStr1=ss.trim();
                        Date date1 = null;
                        try {
                            date1 = inputFormat1.parse(inputDateStr1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String outputDateStr1= outputFormat1.format(date1);

                        fromdatevlue.setText(outputDateStr1);
                        myCalendar.add(Calendar.DATE, 7);

                        DateFormat inputFormat11 = new SimpleDateFormat("d/M/yyyy");//2021-02-23
                        DateFormat outputFormat11 = new SimpleDateFormat("dd/MM/yyyy");
                        String sss=getDate(myCalendar);
                        String inputDateStr11=sss.trim();
                        Date date12 = null;
                        try {
                            date12 = inputFormat11.parse(inputDateStr11);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String outputDateStr11= outputFormat11.format(date12);
                        todatevlue.setText(outputDateStr11);
                        dash(fromdatevlue.getText().toString(),todatevlue.getText().toString());

                    }
                }, mYear, mMonth, mDay);

                mDatePicker.show();
            }
        });

        iv_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        initMap();
        findViewById(R.id.mapzoom).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(DashboardActivity.this, PaidMapdrawlineActivity.class);
                        intent.putExtra("Value","1");
                        startActivity(intent);
                    }
                });
        sharedpreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        user_name=sharedpreferences.getString(CommonUtils.shared_User_Name, "");
        Email=sharedpreferences.getString(CommonUtils.shared_EmailId, "");
        mainMenu = navigationView.getMenu();
        tv_header_msg = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_header_msg);
        tv_header_email = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_header_email);

        tv_header_email.setText(Email);
        tv_header_msg.setText(user_name);
        findViewById(R.id.notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("jkhkhhjh");
                addNotification();
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home :
                        Intent intent111114 = new Intent(DashboardActivity.this, DashboardActivity.class);
                        startActivity(intent111114);
                        break;
                    case R.id.device :
                        Intent intent1111 = new Intent(DashboardActivity.this, MyTripActivity.class);
                        startActivity(intent1111);
                        break;
                    case R.id.histore :
                        Intent intent11111 = new Intent(DashboardActivity.this, VehicleHistoryActivity.class);
                        startActivity(intent11111);
                        break;
                    case R.id.profile :
                        Intent intent=new Intent(DashboardActivity.this,UserProfileActivity.class);
                        startActivity(intent);
                        break;
                }

                return true;
            }
        });
        SiteListApi.getRetrofitInstance(context).create(SiteInterface.class).registration2().enqueue(new Callback<List<SiteGetset>>() {
            @Override
            public void onResponse(Call<List<SiteGetset>> call, Response<List<SiteGetset>> response) {
                System.out.println("hjhjgjgj"+new Gson().toJson(response.body()));
                ArrayList<Country> countryList = new ArrayList<>();
                countryList.add(new Country("","All"));
                for (SiteGetset data:response.body()) {
                    countryList.add(new Country(data.getSiteId(), data.getName()));
                    ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(context, android.R.layout.simple_spinner_dropdown_item, countryList);
                    packagelistspin.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<SiteGetset>> call, Throwable t) {

            }
        });
        packagelistspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country country = (Country) parent.getSelectedItem();
                siteid=country.getId();
               // Toast.makeText(context, "Country ID:"+country.getId()+",  Country Name"+country.getName(), Toast.LENGTH_LONG).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        if (isNetworkConnectionAvailable()) {
            dashboradfilter("");
            f1 = new DashboardMapFragment("");
            loadFrag(f1, "home", fm);

          ll_neworderdash.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
        if(cou.equalsIgnoreCase("0")){
            Toast.makeText(DashboardActivity.this, "No Data", Toast.LENGTH_SHORT).show();
        }else {
           // showCustomDialog();
        }

               }
               });




            findViewById(R.id.lltodayrun).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               System.out.println("ghghfhfhfh"+todaydatevalue);
               if(runc.equalsIgnoreCase("0")){
                   Toast.makeText(DashboardActivity.this, "No Data", Toast.LENGTH_SHORT).show();
               }else {
                  // showCustomDialog1(todaydatevalue);
               }


           }
       });
            datechoose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datepick();
                }
            });
            findViewById(R.id.llroutevoilation).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(vilo.equalsIgnoreCase("0")){
                        Toast.makeText(DashboardActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                    }else {
                        //showCustomDialog1(todaydatevalue);
                    }

                }
            });
            findViewById(R.id.ll_vilotion).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(over.equalsIgnoreCase("0")){
                        Toast.makeText(DashboardActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                    }else {
                       // showCustomDialog1(todaydatevalue);
                    }
                    //showCustomDialog1(todaydatevalue);
                }
            });



        }
             else {
            this.registerReceiver(this.mConnReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
            Toast.makeText(DashboardActivity.this, "Network Not Available", Toast.LENGTH_LONG).show();

        }


        pieChartView = findViewById(R.id.chart);
        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(15, Color.BLUE).setLabel("$10"));
        pieData.add(new SliceValue(25, Color.GRAY).setLabel("$4"));
        pieData.add(new SliceValue(10, Color.RED).setLabel("$18"));
        pieData.add(new SliceValue(60, Color.MAGENTA).setLabel("$28"));
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setHasCenterCircle(true).setCenterText1("Report").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);
        barChart = findViewById(R.id.BarChart);
        getEntries();
        barDataSet = new BarDataSet(barEntries, "");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);


        RadarChart = findViewById(R.id.RadarChart);
        getEntries1();
        radarDataSet = new RadarDataSet(radarEntries, "");
        radarData = new RadarData(radarDataSet);
        RadarChart.setData(radarData);
        radarDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        radarDataSet.setValueTextColor(Color.BLACK);
        radarDataSet.setValueTextSize(18f);

        scatterChart = findViewById(R.id.scatterChart);
        getEntries4();
        scatterDataSet = new ScatterDataSet(scatterEntries, "");
        scatterData = new ScatterData(scatterDataSet);
        scatterChart.setData(scatterData);
        scatterDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        scatterDataSet.setValueTextColor(Color.BLACK);
        scatterDataSet.setValueTextSize(18f);

        bubbleChart = findViewById(R.id.BubbleChart);
        getEntries2();
        bubbleDataSet = new BubbleDataSet(bubbleEntries, "");
        bubbleData = new BubbleData(bubbleDataSet);
        bubbleChart.setData(bubbleData);
        bubbleDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        bubbleDataSet.setValueTextColor(Color.BLACK);
        bubbleDataSet.setValueTextSize(18f);
        GraphView linegraph = (GraphView) findViewById(R.id.line_graph);
        LineGraphSeries<DataPoint> lineSeries = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(2, 4),
                new DataPoint(3, 9),
                new DataPoint(4, 6),
                new DataPoint(5, 3),
                new DataPoint(6, 6),
                new DataPoint(7, 1),
                new DataPoint(8, 2)
        });
       lineSeries.setThickness(8);
        linegraph.addSeries(lineSeries);
        GraphView linegraph1 = (GraphView) findViewById(R.id.line_graph1);
        LineGraphSeries<DataPoint> lineSeries1 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        lineSeries1.setColor(Color.GREEN);
        lineSeries1.setThickness(8);
        linegraph1.addSeries(lineSeries1);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint({"NonConstantResourceId", "RtlHardcoded"})
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.scan:
//                        Intent intent = new Intent(DashboardActivity.this, MyTripActivity.class);
//                        startActivity(intent);
                        drawer.closeDrawer(Gravity.LEFT);
                        break;
                        case R.id.roles:
                        Intent intent1 = new Intent(DashboardActivity.this, RolesActivity.class);
                        startActivity(intent1);
                        drawer.closeDrawer(Gravity.LEFT);
                        break;
                        case R.id.user:
                        Intent intent11 = new Intent(DashboardActivity.this, UserActivity.class);
                        startActivity(intent11);
                        drawer.closeDrawer(Gravity.LEFT);
                        break;
                        case R.id.changepassword:
                        Intent intent111 = new Intent(DashboardActivity.this, SettingActivity.class);
                        startActivity(intent111);
                        drawer.closeDrawer(Gravity.LEFT);
                        break;

                    case R.id.devicemanagement:
                        Intent intent1111 = new Intent(DashboardActivity.this, DeviceActivity.class);
                        startActivity(intent1111);
                        drawer.closeDrawer(Gravity.LEFT);
                        break;
                        case R.id.vechiclehistory:
                        Intent intent11111 = new Intent(DashboardActivity.this, VehicleHistoryActivity.class);
                        startActivity(intent11111);
                        drawer.closeDrawer(Gravity.LEFT);
                        break;

                        case R.id.vechicltripss:
                        Intent intent111114 = new Intent(DashboardActivity.this, MyTripActivity.class);
                        startActivity(intent111114);
                        drawer.closeDrawer(Gravity.LEFT);
                        break;

                        case R.id.vechicleownwrmanagement:
                        Intent intent111111 = new Intent(DashboardActivity.this, VehicleOwnerActivity.class);
                        startActivity(intent111111);
                        drawer.closeDrawer(Gravity.LEFT);
                        break;
                        case R.id.vechiclelist:
                        Intent intent1111112 = new Intent(DashboardActivity.this, VechicleListActivity.class);
                        startActivity(intent1111112);
                        drawer.closeDrawer(Gravity.LEFT);
                        break;

                    case R.id.drivermanagement:
                        Intent intent1111111111 = new Intent(DashboardActivity.this, DriverManagementActivity.class);
                        startActivity(intent1111111111);
                        drawer.closeDrawer(Gravity.LEFT);
                        break;

                        case R.id.triplist:
                        Intent intent11111111114 = new Intent(DashboardActivity.this, TripListActivity.class);
                        startActivity(intent11111111114);
                        drawer.closeDrawer(Gravity.LEFT);
                        break;

                        case R.id.Logout_button:
                        log();
                        drawer.closeDrawer(Gravity.LEFT);
                        break;

                        case R.id.vechiclreposrts:
                            Intent intent11111111112 = new Intent(DashboardActivity.this, VehicleSummaryReportActivity.class);
                            startActivity(intent11111111112);
                        drawer.closeDrawer(Gravity.LEFT);
                        break;

                        case R.id.sites:
                            Intent intent1115 = new Intent(DashboardActivity.this, SiteActivity.class);
                            startActivity(intent1115);
                        drawer.closeDrawer(Gravity.LEFT);
                        break;

                    default:
                        return true;
                }
                return true;

            }
        });

    }
    public static String getDate(Calendar cal){
        return "" + cal.get(Calendar.DATE) +"/" +
                (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.YEAR);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }


    public void dashboradfilter(String e){
        DashboardParamter dashboardParamter=new DashboardParamter();
        dashboardParamter.SiteId=e;
        dashboardParamter.EravanaDate=todaydatevalue;
        System.out.println("apicalledhere" + todaydatevalue + siteid);
        DashBoardApi.getRetrofitInstance(context).create(DashboardInterface.class).registration(dashboardParamter).enqueue(new Callback<Dashbaordgetset>() {
            @Override
            public void onResponse(Call<Dashbaordgetset> call, Response<Dashbaordgetset> response) {
                System.out.println("fdgdfgfgfgf"+new Gson().toJson(response.body()));
                cou= String.valueOf(response.body().getResultData().getTotalCount());
                runc= String.valueOf(response.body().getResultData().getRunningCount());
                over= String.valueOf(response.body().getResultData().getOverSpeedCount());
                vilo= String.valueOf(response.body().getResultData().getViolationCount());
                System.out.println("hghjghghgg"+cou);
                MarkerOptions place2;
                if(response.body().getResultData().getTotalCount()==0){
                    datum=response.body().getResultData();
                    System.out.println("jhgggjjg"+datum.getIdlingcount());
                    int s=datum.getTotalCount();
                    int s1=datum.getRunningCount();
                    int ov=datum.getOverSpeedCount();
                    int ro=datum.getViolationCount();
                    String sq= String.valueOf(s);
                    String sqq= String.valueOf(s1);
                    String oversp= String.valueOf(ov);
                    String riut= String.valueOf(ro);
                    System.out.println("hgjgjhgjhghjg"+sq);
                    todayrunvechiclecount.setText(sq);
                    Allruningvechiclecount.setText(sqq);
                    overspeedcount.setText(oversp);
                    routevilotion.setText(riut);
                    runnungVehicleManagementlsts=null;
                    vehicleManagementItem=null;
                    rolesrecycleview.setVisibility(View.GONE);
                    textdefaultactivity.setVisibility(View.VISIBLE);
                    GPSTracker gpsTracker=new GPSTracker(context);
                    double lat=gpsTracker.getLatitude();
                    double lon= gpsTracker.getLongitude();
                    System.out.println("yghghgf"+lat+"   "+lon);
                    googleMapHomeFrag.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lon), 15.0f));
                    place2= new MarkerOptions().position(new LatLng(lat,lon));
                    googleMapHomeFrag.addMarker(place2);

                }else {
                    rolesrecycleview.setVisibility(View.VISIBLE);
                    textdefaultactivity.setVisibility(View.GONE);
                    datum=response.body().getResultData();
                    int s=datum.getOverSpeedCount();
                    int s1=datum.getRunningCount();
                    int ov=datum.getOverSpeedCount();
                    int ro=datum.getViolationCount();
                    String sq= String.valueOf(s);
                    String sqq= String.valueOf(s1);
                    String oversp= String.valueOf(ov);
                    String riut= String.valueOf(ro);
                    System.out.println("fgghghghgg"+sq);
                    todayrunvechiclecount.setText(sq);
                    Allruningvechiclecount.setText(sqq);
                    overspeedcount.setText(oversp);
                    routevilotion.setText(riut);
                    vehicleManagementItem=datum.getVehicleManagementItems();
                    for (int i = 0; i <vehicleManagementItem.size() ; i++) {
                        vecno=vehicleManagementItem.get(i).getVehicleNo();
                        cardate=vehicleManagementItem.get(i).getVehicleModel();
                        sourcename=vehicleManagementItem.get(i).getSourceName();
                        destiname=vehicleManagementItem.get(i).getDestinationName();
                        lastupadte=vehicleManagementItem.get(i).getRFId();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DashboardActivity.this);
                        rolesrecycleview.setLayoutManager(linearLayoutManager);
                        usersAdapter= new VechicleownerDashboardListAdapter(DashboardActivity.this, vehicleManagementItem);
                        rolesrecycleview.setAdapter(usersAdapter);
                        double lat=vehicleManagementItem.get(i).getLatitude();;
                        double lon= vehicleManagementItem.get(i).getLongitude();;
                        LatLng l =new LatLng(lat,lon);
                        place2 = new MarkerOptions().position(l).icon(BitmapFromVector(context,R.drawable.redcar));
                        googleMapHomeFrag.addMarker(place2);
                        googleMapHomeFrag.moveCamera(CameraUpdateFactory.newLatLngZoom(l, 15.0f));
                        Bikeloac(lat,lon);
                    }


                }


            }

            @Override
            public void onFailure(Call<Dashbaordgetset> call, Throwable t) {

            }
        });
    }
    private onClickInterface onclickInterface;
    public void dash(String fromdate,String todatye){
        final ProgressDialog progressDialog = new ProgressDialog(DashboardActivity.this);
//        progressDialog.setCancelable(false); // set cancelable to false
//        progressDialog.setMessage("Please Wait"); // set message
//        progressDialog.show();
//
//        DashboardAllvechicleApi.getRetrofitInstance(context).create(DashboardAllvechicleInterface.class).registration(7868,fromdate,todatye).enqueue(new Callback<DashbaordAllgetset>() {
//            @Override
//            public void onResponse(Call<DashbaordAllgetset> call, Response<DashbaordAllgetset> response) {
//               // progressDialog.dismiss();
//                System.out.println("fsdfdfdgfgd"+new Gson().toJson(response.body()));
//                 com.example.vts.Activity.DasboradAllVechicleActivity.ResultData datum=response.body().getResultData();
//                  List<GetAllActivitiesByClientLoginId>getAllActivitiesByClientLoginIds=datum.getGetAllActivitiesByClientLoginId();
//                System.out.println("gjhghghgh"+getAllActivitiesByClientLoginIds.size());
//                if(getAllActivitiesByClientLoginIds.size()==0){
//                    textallactivity.setVisibility(View.VISIBLE);
//                    allvechiclerecycleview.setVisibility(View.GONE);
//                   // Toast.makeText(DashboardActivity.this, "No Vehicle Found", Toast.LENGTH_SHORT).show();
//                }else {
//                    textallactivity.setVisibility(View.GONE);
//                    allvechiclerecycleview.setVisibility(View.VISIBLE);
//                    for (int i = 0; i <getAllActivitiesByClientLoginIds.size() ; i++) {
//                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DashboardActivity.this);
//                        allvechiclerecycleview.setLayoutManager(linearLayoutManager);
//                        DasAllVechileListAdapter usersAdapter= new DasAllVechileListAdapter(DashboardActivity.this, getAllActivitiesByClientLoginIds, onclickInterface);
//                        allvechiclerecycleview.setAdapter(usersAdapter);
//                        onclickInterface = new onClickInterface() {
//                            @Override
//                            public void setClick(int abc) {
//                                Intent intent=new Intent(DashboardActivity.this, AllVechicleDetailActivity.class);
//                                intent.putExtra("fromdate",fromdate);
//                                intent.putExtra("todate",todatye);
//                                startActivity(intent);
//                                //Toast.makeText(DashboardActivity.this,"Position is"+abc,Toast.LENGTH_LONG).show();
//                            }
//                        };
//                }
//
//                   }
//            }
//
//            @Override
//            public void onFailure(Call<DashbaordAllgetset> call, Throwable t) {
//
//            }
//        });
    }
    public void loadFrag(Fragment f1, String name, FragmentManager fm) {
        selectedFragment = name;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.frame_nav, f1, name);
        ft.commit();

    }
    private void initMap() {
        System.out.println("jhhjhgghgghj");
        SupportMapFragment mSupportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapView=mSupportMapFragment.getView();
        mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap googleMap) {
                if (googleMap != null) {
                    System.out.println("hjjhkjgjhhjgj");
                    googleMapHomeFrag = googleMap;
                    //googleMapHomeFrag.getUiSettings().setAllGesturesEnabled(true);
                    //googleMapHomeFrag.getUiSettings().setScrollGesturesEnabled(true);
                    //googleMapHomeFrag.getUiSettings().setCompassEnabled(false);
                    //googleMapHomeFrag.getUiSettings().setMapToolbarEnabled(false);
                   // googleMapHomeFrag.setMapType(GoogleMap.Map);
                   googleMapHomeFrag.setMapStyle(MapStyleOptions.loadRawResourceStyle(DashboardActivity.this, R.raw.lumap));


                    googleMapHomeFrag.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(@NonNull Marker marker) {
                            LatLng myLatLng = new LatLng(marker.getPosition().latitude,marker.getPosition().longitude);
                            if(vecno==null){
                                Toast.makeText(DashboardActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                            }else {
                                for (VehicleManagementItem vehicleManagementItem :datum.getVehicleManagementItems()) {
                                    if(vehicleManagementItem.getLatitude()==marker.getPosition().latitude){
                                        showCustomDialog7();
                                    }
                                }

                            }
                            return false;
                        }
                    });



                    View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
                    RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
                    rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
                    rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);rlp.setMargins(0,0,30,30);










                }
            }
        });
    }
    TextView vechilenumbers,cardates,sourcecar,destinationcar,lastupadet,vechilenumber2;
    LinearLayout viewdetails;
    private void showCustomDialog7() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.showdetailsvechicle, viewGroup, false);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(true);
        alertDialog.show();
        alertDialog.getWindow().setGravity(Gravity.CENTER);

        vechilenumbers = (TextView) dialogView.findViewById(R.id.vechilenumber);
        viewdetails = (LinearLayout) dialogView.findViewById(R.id.viewdetails);
        //vechilenumber2 = (TextView) dialogView.findViewById(R.id.vechilenumber1);
        cardates = (TextView) dialogView.findViewById(R.id.cardate);
//        sourcecar = (TextView) dialogView.findViewById(R.id.sourcecar);
//        destinationcar = (TextView) dialogView.findViewById(R.id.destinationcar);
        lastupadet = (TextView) dialogView.findViewById(R.id.lastupadet);
     //   ImageView removestaff = (ImageView) dialogView.findViewById(R.id.cancel_bottomsheet_item);
//        removestaff.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
        viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialogall();
            }
        });
        vechilenumbers.setText(vecno);
        //vechilenumber2.setText(vecno);
       // sourcecar.setText(sourcename);
        //destinationcar.setText(destiname);
        lastupadet.setText("RFID:-"+lastupadte);
        cardates.setText(cardate);


    }


    private void showCustomDialogall() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.alldetails, viewGroup, false);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(true);
        alertDialog.show();
        alertDialog.getWindow().setGravity(Gravity.CENTER);

        vechilenumbers = (TextView) dialogView.findViewById(R.id.vechilenumber);
        viewdetails = (LinearLayout) dialogView.findViewById(R.id.viewdetails);
        //vechilenumber2 = (TextView) dialogView.findViewById(R.id.vechilenumber1);
        cardates = (TextView) dialogView.findViewById(R.id.cardate);
//        sourcecar = (TextView) dialogView.findViewById(R.id.sourcecar);
//        destinationcar = (TextView) dialogView.findViewById(R.id.destinationcar);
        lastupadet = (TextView) dialogView.findViewById(R.id.lastupadet);
           ImageView removestaff = (ImageView) dialogView.findViewById(R.id.cancel_bottomsheet_item);
        removestaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        vechilenumbers.setText(vecno);
        //vechilenumber2.setText(vecno);
        // sourcecar.setText(sourcename);
        //destinationcar.setText(destiname);
        lastupadet.setText("RFID:-"+lastupadte);
        cardates.setText(cardate);


    }



    LatLng firstselected,secondselected;
    List<LatLng> locationsecondarray = new ArrayList<>();
    private void Bikeloac(double lati,double lo) {
        locationsecondarray.add(new LatLng(lati,lo));
        latLng[0] = lati;
        latLng[1] = lo;
        System.out.println("jhjjgjh"+latLng[0]+"    "+latLng[1]);
        if(marker==null){
            marker = googleMapHomeFrag.addMarker(new MarkerOptions()
                    .position(new LatLng(latLng[0], latLng[1]))
                    .title("My Car")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.redcar)));
            googleMapHomeFrag.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng[0], latLng[1]), 12.0f));
        }

        if ((latLng[0] != -1 && latLng[0] != 0) && (latLng[1] != -1 && latLng[1] != 0)) {
            if (marker != null) {
                Location temp = new Location(LocationManager.GPS_PROVIDER);
                temp.setLatitude(lati);
                temp.setLongitude(lo);
                System.out.println("kjhhjjj"+temp.getBearing());
                moveVehicle(marker, temp);
                if(locationsecondarray.size() ==2){
                    System.out.println("kjhhjjj"+Float.parseFloat(String.valueOf( bearingBetweenLocations(locationsecondarray.get(0),locationsecondarray.get(1)))));
                    rotateMarker(marker,Float.parseFloat(String.valueOf( bearingBetweenLocations(locationsecondarray.get(0),locationsecondarray.get(1)))), start_rotation);
                }
                //rotateMarker(marker, temp.getBearing(), start_rotation);

            }

            if(locationsecondarray.size() ==2){
                firstselected = locationsecondarray.get(0);
                secondselected = locationsecondarray.get(1);
                locationsecondarray.clear();
                locationsecondarray.add(secondselected);
            }
        } else {
            Toast.makeText(this, "Location Not Found", Toast.LENGTH_LONG).show();
        }
    }
    private double bearingBetweenLocations(LatLng latLng1,LatLng latLng2) {

        double PI = 3.14159;
        double lat1 = latLng1.latitude * PI / 180;
        double long1 = latLng1.longitude * PI / 180;
        double lat2 = latLng2.latitude * PI / 180;
        double long2 = latLng2.longitude * PI / 180;

        double dLon = (long2 - long1);

        double y = Math.sin(dLon) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1)
                * Math.cos(lat2) * Math.cos(dLon);

        double brng = Math.atan2(y, x);

        brng = Math.toDegrees(brng);
        brng = (brng + 360) % 360;

        return brng;
    }





    public void moveVehicle(final Marker myMarker, final Location finalPosition) {

        final LatLng startPosition = myMarker.getPosition();

        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final android.view.animation.Interpolator interpolator = new AccelerateDecelerateInterpolator();
        final float durationInMs = 3000;
        final boolean hideMarker = false;

        handler.post(new Runnable() {
            long elapsed;
            float t;
            float v;

            @Override
            public void run() {
                elapsed = SystemClock.uptimeMillis() - start;
                t = elapsed / durationInMs;
                v = interpolator.getInterpolation(t);
                LatLng currentPosition = new LatLng(
                        startPosition.latitude * (1 - t) + (finalPosition.getLatitude()) * t,
                        startPosition.longitude * (1 - t) + (finalPosition.getLongitude()) * t);
                myMarker.setPosition(currentPosition);
                googleMapHomeFrag.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 18.0f));
                if (t < 1) {
                    handler.postDelayed(this, 16);
                } else {
                    if (hideMarker) {
                        myMarker.setVisible(false);
                    } else {
                        myMarker.setVisible(true);
                    }
                }
            }
        });


    }
    public void rotateMarker(final Marker marker, final float toRotation, final float st) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final float startRotation = marker.getRotation();
        final long duration = 1000;
        final Interpolator interpolator = new LinearInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed / duration);
                float rot = t * toRotation + (1 - t) * startRotation;
                marker.setRotation(-rot > 180 ? rot / 2 : rot);
                start_rotation = -rot > 180 ? rot / 2 : rot;
                if (t < 1.0) {
                    handler.postDelayed(this, 10);
                }
            }
        });
    }




DatePickerDialog datePickerDialog;
    private void datepick (){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        System.out.println("hytdsstdratsta"+mYear+"hffdz"+mMonth+"gytfdsdf"+mDay);
        datePickerDialog = new DatePickerDialog(DashboardActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String s=((monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);
                DateFormat inputFormat1 = new SimpleDateFormat("M-dd-yyyy");//2021-02-23
                DateFormat outputFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                String inputDateStr1=s.trim();
                Date date1 = null;
                try {
                    date1 = inputFormat1.parse(inputDateStr1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String outputDateStr1= outputFormat1.format(date1);
                System.out.println("hgtyrdrfaghbhjhfdrtg"+outputDateStr1);
                String dateset=outputDateStr1;
                todaydatevalue=dateset;
                setdatevlue.setText(todaydatevalue);

                DashboardParamter dashboardParamter=new DashboardParamter();
                dashboardParamter.SiteId=siteid;
                dashboardParamter.EravanaDate=todaydatevalue;
                System.out.println("dkhkjhgjkdjkd" + todaydatevalue + siteid);
                DashBoardApi.getRetrofitInstance(context).create(DashboardInterface.class).registration(dashboardParamter).enqueue(new Callback<Dashbaordgetset>() {
                    @Override
                    public void onResponse(Call<Dashbaordgetset> call, Response<Dashbaordgetset> response) {
                        System.out.println("fdsfdgfdgfggffg"+new Gson().toJson(response.body()));
                        cou= String.valueOf(response.body().getResultData().getTotalCount());
                        runc= String.valueOf(response.body().getResultData().getRunningCount());
                        over= String.valueOf(response.body().getResultData().getOverSpeedCount());
                        vilo= String.valueOf(response.body().getResultData().getViolationCount());
                        MarkerOptions place2;
                        if(response.body().getResultData().getTotalCount()==0){
                            datum=response.body().getResultData();
                            int s=datum.getTotalCount();
                            int s1=datum.getRunningCount();
                            int ov=datum.getOverSpeedCount();
                            int ro=datum.getViolationCount();
                            String sq= String.valueOf(s);
                            String sqq= String.valueOf(s1);
                            String oversp= String.valueOf(ov);
                            String riut= String.valueOf(ro);
                            System.out.println("fcgfgfhfghg"+sq);
                            todayrunvechiclecount.setText(sq);
                            Allruningvechiclecount.setText(sqq);
                            overspeedcount.setText(oversp);
                            routevilotion.setText(riut);
                            rolesrecycleview.setVisibility(View.GONE);
                            textdefaultactivity.setVisibility(View.VISIBLE);
                            runnungVehicleManagementlsts=null;
                            vehicleManagementItem=null;
                            GPSTracker gpsTracker=new GPSTracker(context);
                            double lat=gpsTracker.getLatitude();
                            double lon= gpsTracker.getLongitude();
                            googleMapHomeFrag.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lon), 15.0f));
                           place2 = new MarkerOptions().position(new LatLng(lat,lon));
                            googleMapHomeFrag.addMarker(place2);
                        }
                        else {
                            rolesrecycleview.setVisibility(View.VISIBLE);
                            textdefaultactivity.setVisibility(View.GONE);
                            datum=response.body().getResultData();
                            int s=datum.getOverSpeedCount();
                            int s1=datum.getRunningCount();
                            int ov=datum.getOverSpeedCount();
                            int ro=datum.getViolationCount();
                            String sq= String.valueOf(s);
                            String sqq= String.valueOf(s1);
                            String oversp= String.valueOf(ov);
                            String riut= String.valueOf(ro);
                            System.out.println("fgcvcvbcbvb"+sq);
                            todayrunvechiclecount.setText(sq);
                            Allruningvechiclecount.setText(sqq);
                            overspeedcount.setText(oversp);
                            routevilotion.setText(riut);
                            vehicleManagementItem=datum.getVehicleManagementItems();
                            for (int i = 0; i <vehicleManagementItem.size() ; i++) {
                                vecno=vehicleManagementItem.get(i).getVehicleNo();
                                cardate=vehicleManagementItem.get(i).getVehicleModel();
                                sourcename=vehicleManagementItem.get(i).getSourceName();
                                destiname=vehicleManagementItem.get(i).getDestinationName();
                                lastupadte=vehicleManagementItem.get(i).getRFId();
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DashboardActivity.this);
                                rolesrecycleview.setLayoutManager(linearLayoutManager);
                                usersAdapter= new VechicleownerDashboardListAdapter(DashboardActivity.this, vehicleManagementItem);
                                rolesrecycleview.setAdapter(usersAdapter);

                                double lat=vehicleManagementItem.get(i).getLatitude();
                                double lon= vehicleManagementItem.get(i).getLongitude();
                                LatLng l =new LatLng(lat,lon);
                                place2= new MarkerOptions().position(l).icon(BitmapFromVector(context,R.drawable.redcar));
                                googleMapHomeFrag.addMarker(place2);
                                googleMapHomeFrag.moveCamera(CameraUpdateFactory.newLatLngZoom(l, 15.0f));
                                Bikeloac(lat,lon);
                            }


                        }


                    }

                    @Override
                    public void onFailure(Call<Dashbaordgetset> call, Throwable t) {

                    }
                });
            }
        }, mYear, mMonth, mDay);
       // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
    private void getEntries1() {
        radarEntries = new ArrayList<>();
        radarEntries.add(new RadarEntry(0, 0.21f));
        radarEntries.add(new RadarEntry(1, 0.12f));
        radarEntries.add(new RadarEntry(2, 0.20f));
        radarEntries.add(new RadarEntry(2, 0.52f));
        radarEntries.add(new RadarEntry(3, 0.29f));
        radarEntries.add(new RadarEntry(4, 0.62f));
    }
    private void getEntries2() {
        bubbleEntries = new ArrayList<>();
        bubbleEntries.add(new BubbleEntry(0, 1,0.21f));
        bubbleEntries.add(new BubbleEntry(1, 2,0.12f));
        bubbleEntries.add(new BubbleEntry(2, 3,0.20f));
        bubbleEntries.add(new BubbleEntry(2,4, 0.22f));
        bubbleEntries.add(new BubbleEntry(3, 5,0.29f));
        bubbleEntries.add(new BubbleEntry(4, 6,0.12f));
    }
    private void getEntries() {
        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(2f, 0));
        barEntries.add(new BarEntry(4f, 1));
        barEntries.add(new BarEntry(6f, 1));
        barEntries.add(new BarEntry(8f, 3));
        barEntries.add(new BarEntry(7f, 4));
        barEntries.add(new BarEntry(3f, 3));
    }

    private void getEntries4() {
        scatterEntries = new ArrayList<>();
        scatterEntries.add(new BarEntry(2f, 0));
        scatterEntries.add(new BarEntry(4f, 1));
        scatterEntries.add(new BarEntry(6f, 1));
        scatterEntries.add(new BarEntry(8f, 3));
        scatterEntries.add(new BarEntry(7f, 4));
        scatterEntries.add(new BarEntry(3f, 3));
    }
    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.notificationlist, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 30);
        alertDialog.getWindow().setBackgroundDrawable(inset);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, 650);
         ImageView back_button=dialogView.findViewById(R.id.back_button);
        todayrunvechiclerecycleview=dialogView.findViewById(R.id.todayrunvechiclerecycleview);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context);
        todayrunvechiclerecycleview.setLayoutManager(linearLayoutManager1);
        EravanaAllRunningVechicleListAdapter usersAdapter1   = new EravanaAllRunningVechicleListAdapter(context,vehicleManagementItem);
        todayrunvechiclerecycleview.setAdapter(usersAdapter1);


         back_button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        alertDialog.dismiss();
    }


         });




    }
    RecyclerView todayrunvechiclerecycleview;



    private void showCustomDialog1(String da) {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.notification1, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 30);
        alertDialog.getWindow().setBackgroundDrawable(inset);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, 650);
        ImageView back_button=dialogView.findViewById(R.id.back_button);
        todayrunvechiclerecycleview=dialogView.findViewById(R.id.todayrunvechiclerecycleview);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(DashboardActivity.this,DashboardActivity.class);
//                startActivity(intent);
                alertDialog.dismiss();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        todayrunvechiclerecycleview.setLayoutManager(linearLayoutManager);
        TodayRunVechicleListAdapter usersAdapter   = new TodayRunVechicleListAdapter(context,  runnungVehicleManagementlsts);
        todayrunvechiclerecycleview.setAdapter(usersAdapter);


    }
    private void showCustomDialog2() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.notification2, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 30);
        alertDialog.getWindow().setBackgroundDrawable(inset);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, 650);
        ImageView back_button=dialogView.findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });




    }



    private BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            String reason = intent.getStringExtra(ConnectivityManager.EXTRA_REASON);
            boolean isFailover = intent.getBooleanExtra(ConnectivityManager.EXTRA_IS_FAILOVER, false);

            NetworkInfo currentNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            NetworkInfo otherNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_OTHER_NETWORK_INFO);

            if (currentNetworkInfo.isConnected()) {
                Log.d("=============", "Connected");
                finish();
                startActivity(getIntent());
                Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
            } else {
                Log.d("============", "Not Connected");
                Toast.makeText(getApplicationContext(), "Not Connected",
                        Toast.LENGTH_LONG).show();
            }
        }
    };

    public boolean isNetworkConnectionAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if (isConnected) {
            Log.d("Network", "Connected");
            return true;
        } else {
            checkNetworkConnection();
            Log.d("Network", "Not Connected");
            return false;
        }
    }
    public void checkNetworkConnection() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to continue");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog1, int which) {
                dialog1.dismiss();
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
            }
        });
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Scanreddem.this.finish();
//                System.exit(0);
                finishAffinity();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog1, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog1.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }
    private void addNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("Notifications Example")
                .setContentText("This is a test notification");
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
    public void log() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage("Are You Sure To Logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Logout();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog1, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog1.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    private void Logout() {
        Toast.makeText(DashboardActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
        DashboardActivity.this.getSharedPreferences(CommonUtils.MyPREFERENCES, 0).edit().clear().commit();
        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


}