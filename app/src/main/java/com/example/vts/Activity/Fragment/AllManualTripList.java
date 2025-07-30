package com.example.vts.Activity.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vts.Activity.DeleteTripManual.DeleteTripInterface;
import com.example.vts.Activity.DeleteTripManual.DeleteTripManualApi;
import com.example.vts.Activity.EravanaIdPAck.Eravanaidgetset;
import com.example.vts.Activity.ManualTripActivity;
import com.example.vts.Activity.UserDelete.UserDeletegetset;
import com.example.vts.R;
import com.example.vts.TriplistPack.AlltripList;
import com.example.vts.TriplistPack.TripListApi;
import com.example.vts.TriplistPack.TripListInterface;
import com.example.vts.TriplistPack.TripParameter;
import com.example.vts.TriplistPack.TriplistAdapter;
import com.example.vts.base.CommonUtils;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllManualTripList extends Fragment {
        RecyclerView rolesrecycleview;
        Context context;
        TextView Nodata;
        SharedPreferences sharedPreferences;
    TriplistAdapter usersAdapter;
    public AllManualTripList() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_all_manual_trip_list, container, false);
        rolesrecycleview=view.findViewById(R.id.rolesrecycleview);
        Nodata=view.findViewById(R.id.Nodata);
        Nodata.setVisibility(View.GONE);
        context=view.getContext();
        sharedPreferences = getActivity().getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);


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
                    usersAdapter= new TriplistAdapter(context, response.body()) {
                        @Override
                        public void delete(int id,String position) {
                            Delete(id,position);
                        }

                        @Override
                        public void edit(String id, String rolename, String orgname) {
//                            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                            edit_item_name.setText(rolename);
//                            edit_item_description.setText(orgname);
//                            edit_item_id.setText(id);
                        }
                    };
                    rolesrecycleview.setAdapter(usersAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Eravanaidgetset>> call, Throwable t) {

            }
        });

        return view ;
    }
    private void Delete(int id, String position) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        DeleteTripManualApi.getRetrofitInstance(context).create(DeleteTripInterface.class).registration(position).enqueue(new Callback<UserDeletegetset>() {
            @Override
            public void onResponse(Call<UserDeletegetset> call, Response<UserDeletegetset> response) {
                if(response.body().getIsSuccess().equalsIgnoreCase("true")){
                    usersAdapter.removeAt(id);
                    progressDialog.dismiss();
                    Intent intent=new Intent(context,ManualTripActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(context, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(context, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserDeletegetset> call, Throwable t) {

            }
        });
}

}