package com.example.vts.Activity.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vts.Activity.DashboardApi.DashBoardApi;
import com.example.vts.Activity.DashboardApi.Dashbaordgetset;
import com.example.vts.Activity.DashboardApi.DashboardInterface;
import com.example.vts.Activity.DashboardApi.DashboardParamter;
import com.example.vts.Activity.DashboardApi.Dashboardgraphgeset;
import com.example.vts.Activity.DashboardApi.DashboradgraphInterface;
import com.example.vts.Activity.EndTripPack.SiteNameChartListAdapter;
import com.example.vts.Activity.VechicleListActivity;
import com.example.vts.R;
import com.example.vts.Vechilelistpack.VechileListAdapter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.intrusoft.scatter.ChartData;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardChartFragment extends Fragment {
    View chartview, mapsview;
    LinearLayout ll_chart, ll_map;
    private MapView mMapView;
    String Siteid;
    ImageView arrow;
    LinearLayout hiddenView;
    LinearLayout cardView;
    Context context;

    List<ChartData> data, data1;
     String sq,sqq,oversp;
    TextView Runiingcount, schedulecount, violationcount, completedcount,completedtripcount,scheduletripcount,livetripcount;
    com.example.vts.Activity.DashboardApi.ResultData datum;

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntries;
    TextView noload;
    RecyclerView siterecyclview;

    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;
    LineChartView lineChartView;
    List<String> axisData=new ArrayList<>();
    List<Integer>yAxisData =new ArrayList<>();
     public DashboardChartFragment(String siteid) {
        // Required empty public constructor
        Siteid = siteid;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_dashboard_chart, container, false);
        chartview = v.findViewById(R.id.chartview);
        mapsview = v.findViewById(R.id.mapsview);
        ll_chart = v.findViewById(R.id.ll_chart);
        ll_map = v.findViewById(R.id.ll_map);
        chartview.setVisibility(View.GONE);
        context = getActivity();
        System.out.println("jhgjgjhgj" + Siteid);
        cardView = v.findViewById(R.id.base_cardview);
        noload = v.findViewById(R.id.noload);
        siterecyclview = v.findViewById(R.id.siterecyclview);
        noload.setVisibility(View.GONE);
        Runiingcount = v.findViewById(R.id.Runiingcount);
        completedtripcount = v.findViewById(R.id.completedtripcount);
        scheduletripcount = v.findViewById(R.id.scheduletripcount);
        livetripcount = v.findViewById(R.id.livetripcount);
        schedulecount = v.findViewById(R.id.schedulecount);
        violationcount = v.findViewById(R.id.violationcount);
        completedcount = v.findViewById(R.id.completedcount);
        arrow = v.findViewById(R.id.arrow_button);
        hiddenView = v.findViewById(R.id.hidden_view);
        chartview.setVisibility(View.VISIBLE);
        mapsview.setVisibility(View.GONE);
        pieChart=v.findViewById(R.id.piechart);
        lineChartView= v.findViewById(R.id.chart);
        ll_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartview.setVisibility(View.GONE);
                mapsview.setVisibility(View.VISIBLE);
                Fragment fragment = new DashboardMapFragment(Siteid);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_nav, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hiddenView.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    hiddenView.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    //notifyDataSetChanged();
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    hiddenView.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }
            }
        });
        dashboradfilter(Siteid);
        Dashchart(Siteid);
        barChart = v.findViewById(R.id.BarChart);
        return v;
    }



    public void dashboradfilter(String e){
        DashboardParamter dashboardParamter=new DashboardParamter();
        dashboardParamter.SiteId=e;
        dashboardParamter.EravanaDate="";
        System.out.println("hjhjghjghg" + e);
        DashBoardApi.getRetrofitInstance(context).create(DashboardInterface.class).registration(dashboardParamter).enqueue(new Callback<Dashbaordgetset>() {
            @Override
            public void onResponse(Call<Dashbaordgetset> call, Response<Dashbaordgetset> response) {
                System.out.println("fdgdfgfgfgf"+new Gson().toJson(response.body()));
                datum=response.body().getResultData();
                int s=datum.getCompletedCount();
                int tota=datum.getTotalCount();
                int s1=datum.getRunningCount();
                int ov=datum.getScheduledCount();
                int ro=datum.getViolationCount();
                sq= String.valueOf(s);
                 sqq= String.valueOf(s1);
                 oversp= String.valueOf(ov);
                String riut= String.valueOf(ro);
                String totas= String.valueOf(tota);
                System.out.println("fgghghghgg"+sq);
                schedulecount.setText(oversp);
                scheduletripcount.setText(oversp);
                Runiingcount.setText(sqq);
                livetripcount.setText(sqq);
                completedcount.setText(sq);
                completedtripcount.setText(sq);
                violationcount.setText(riut);
                List<String>datcou=new ArrayList<>();
                datcou.add(sqq);
                datcou.add(oversp);
                datcou.add(sq);
                pieEntries = new ArrayList<>();
                pieEntries.add(new PieEntry(Integer.parseInt(sqq), 0));
                pieEntries.add(new PieEntry(Integer.parseInt(oversp), 1));
                pieEntries.add(new PieEntry(Integer.parseInt(sq), 2));


                pieDataSet =new PieDataSet(pieEntries,"");
                pieData =new PieData(pieDataSet);
                pieData.setValueFormatter(new DefaultValueFormatter(0));
                pieChart.setData(pieData);
                pieChart.setCenterTextColor(Color.BLACK);
                pieChart.setCenterTextSize(20f);
                pieChart.setCenterText("Total Trip \n"+totas);
                pieChart.animateXY(5000,5000);
                pieDataSet.setColors(getResources().getColor(R.color.livecolour),getResources().getColor(R.color.schedulecolour),getResources().getColor(R.color.completecolour));
                pieDataSet.setSliceSpace(2f);
                pieDataSet.setValueTextSize(10f);
                pieDataSet.setValueTextColor(Color.WHITE);

            }

            @Override
            public void onFailure(Call<Dashbaordgetset> call, Throwable t) {

            }
        });
    }

    public  void Dashchart(String s){
       DashBoardApi.getRetrofitInstance(context).create(DashboradgraphInterface.class).registration(s).enqueue(new Callback<List<Dashboardgraphgeset>>() {
           @Override
           public void onResponse(Call<List<Dashboardgraphgeset>> call, Response<List<Dashboardgraphgeset>> response) {
               System.out.println("jjgjghjhghjghg"+new Gson().toJson(response.body()));
               if(response.body().isEmpty()){
                   noload.setVisibility(View.VISIBLE);
                   lineChartView.setVisibility(View.GONE);
               }else {
                   noload.setVisibility(View.GONE);
                   lineChartView.setVisibility(View.VISIBLE);
                   LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
                   siterecyclview.setLayoutManager(linearLayoutManager);
                   SiteNameChartListAdapter usersAdapter   = new SiteNameChartListAdapter(getActivity(), response.body());
                   siterecyclview.setAdapter(usersAdapter);
                   axisData.clear();
                   yAxisData.clear();
                   for (int i = 0; i <response.body().size() ; i++) {
                       String netWeight = response.body().get(i).getTotalNetWeight().toString();
                       String netW = netWeight.split("[.]", 0)[0];
                       axisData.add(response.body().get(i).getTotalNetWeight());
                      yAxisData.add(Integer.parseInt(netW));
                   }
                   List yAxisValues = new ArrayList();
                   List axisValues = new ArrayList();
                   System.out.println("hjhjghjgjhg");
                   Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));
                   System.out.println("hjmkn");
                   for (int i = 0; i < axisData.size(); i++) {
                       axisValues.add(i, new AxisValue(i).setLabel(axisData.get(i)));
                   }
                   for (int i = 0; i < yAxisData.size(); i++) {
                       yAxisValues.add(new PointValue(i, yAxisData.get(i)));
                   }
                   System.out.println("gjhjhjjkhjkklj");
                   List lines = new ArrayList();
                   lines.add(line);

                   LineChartData data = new LineChartData();
                   data.setLines(lines);

                   Axis axis = new Axis();
                   axis.setValues(axisValues);
                   axis.setTextSize(12);
                   axis.setTextColor(Color.parseColor("#000000"));
                   data.setAxisXBottom(axis);


                   Axis yAxis = new Axis();
                   yAxis.setName("Load in Kg");
                   yAxis.setTextColor(Color.parseColor("#000000"));
                   yAxis.setTextSize(12);
                   data.setAxisYLeft(yAxis);
                   lineChartView.setLineChartData(data);

               }

           }

           @Override
           public void onFailure(Call<List<Dashboardgraphgeset>> call, Throwable t) {

           }
       });
    }
}