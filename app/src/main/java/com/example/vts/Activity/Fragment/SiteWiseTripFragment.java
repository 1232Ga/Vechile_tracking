package com.example.vts.Activity.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vts.Activity.AddTripPack.WithSiteAdapter;
import com.example.vts.Activity.AddTripPack.WithoutSiteAdapter;
import com.example.vts.Activity.EravanaIdPAck.Eravanaidgetset;
import com.example.vts.R;
import com.example.vts.TriplistPack.AlltripList;
import com.example.vts.TriplistPack.TripListApi;
import com.example.vts.TriplistPack.TripListInterface;
import com.example.vts.TriplistPack.TripParameter;
import com.example.vts.base.CommonUtils;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SiteWiseTripFragment extends Fragment {
    RecyclerView rolesrecycleview;
    Context context;
    SharedPreferences sharedPreferences;
    WithSiteAdapter usersAdapter;
    TextView Nodata;
    EditText Search;
    public SiteWiseTripFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_site_wise_trip, container, false);
        rolesrecycleview=view.findViewById(R.id.rolesrecycleview);
        Nodata=view.findViewById(R.id.Nodata);
        Nodata.setVisibility(View.GONE);
        context=view.getContext();
        Search=view.findViewById(R.id.Search);
        Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                usersAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        sharedPreferences = getActivity().getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
//        TripParameter parameter=new TripParameter();
//        parameter.SiteId=null;
//        parameter.TripTypesVal="0";
        TripListApi.getRetrofitInstance(context).create(AlltripList.class).registration().enqueue(new Callback<List<Eravanaidgetset>>() {
            @Override
            public void onResponse(Call<List<Eravanaidgetset>> call, Response<List<Eravanaidgetset>> response) {
                System.out.println("hiuhjgjhgj"+new Gson().toJson(response.body()));
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                if(response.body().isEmpty()){
                    Nodata.setVisibility(View.VISIBLE);
                    rolesrecycleview.setVisibility(View.GONE);
                }
                else {
                    Nodata.setVisibility(View.GONE);
                    rolesrecycleview.setVisibility(View.VISIBLE);
                    rolesrecycleview.setLayoutManager(linearLayoutManager);
                    usersAdapter= new WithSiteAdapter(context, response.body());
                    rolesrecycleview.setAdapter(usersAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Eravanaidgetset>> call, Throwable t) {

            }
        });
        return view;
    }
}