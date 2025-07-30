package com.example.vts.Activity.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vts.Activity.EravanaIdPAck.EravanaIdApi;
import com.example.vts.Activity.EravanaIdPAck.EravanaIdInterface;
import com.example.vts.Activity.EravanaIdPAck.EravanaListAdapter;
import com.example.vts.Activity.EravanaIdPAck.Eravanaidgetset;
import com.example.vts.Activity.EravanaIdPAck.Eravanpra;
import com.example.vts.LiveTracking.GenerateAuthTokenAPI;
import com.example.vts.LiveTracking.GetAuthApi;
import com.example.vts.LiveTracking.GetAuthGetSet;
import com.example.vts.LiveTracking.ResultData;
import com.example.vts.R;
import com.example.vts.TriplistPack.TripListApi;
import com.example.vts.TriplistPack.TripListInterface;
import com.example.vts.TriplistPack.TripParameter;
import com.example.vts.TriplistPack.TriplistAdapter;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RunningTrip extends Fragment {
    RecyclerView rolesrecycleview;
    Context context;
    String siteid;
    TextView Nodata;
    SharedPreferences sharedPreferences;
    TriplistAdapter usersAdapter;
    public RunningTrip() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_running_trip, container, false);
        context=getContext();
        rolesrecycleview=view.findViewById(R.id.rolesrecycleview);
        Nodata=view.findViewById(R.id.Nodata);
        Nodata.setVisibility(View.GONE);
       // siteid=getActivity().getIntent().getStringExtra("Siteid");
        System.out.println("jkjkjhjhhjhjhgj"+siteid);
        TripParameter parameter=new TripParameter();
        parameter.SiteId=siteid;
        parameter.TripTypesVal="1";
        TripListApi.getRetrofitInstance(context).create(TripListInterface.class).registration(parameter).enqueue(new Callback<List<Eravanaidgetset>>() {
            @Override
            public void onResponse(Call<List<Eravanaidgetset>> call, Response<List<Eravanaidgetset>> response) {
                System.out.println("hiuhjgjhgj"+new Gson().toJson(response.body()));

                if(response.body().isEmpty()){
                    Nodata.setVisibility(View.VISIBLE);
                    rolesrecycleview.setVisibility(View.GONE);
                }
                else {
                    Nodata.setVisibility(View.GONE);
                    rolesrecycleview.setVisibility(View.VISIBLE);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
                    EravanaListAdapter usersAdapter   = new EravanaListAdapter(getContext(), response.body());
                    rolesrecycleview.setAdapter(usersAdapter);
                    rolesrecycleview.setLayoutManager(linearLayoutManager);
                }
            }

            @Override
            public void onFailure(Call<List<Eravanaidgetset>> call, Throwable t) {

            }
        });
        GetAuthApi.getApiService().registration2().enqueue(new Callback<GetAuthGetSet>() {
            @Override
            public void onResponse(Call<GetAuthGetSet> call, Response<GetAuthGetSet> response) {
                System.out.println("jkhkhhjkhkhjk"+response.body().getResultData());
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
        return view;
    }
}