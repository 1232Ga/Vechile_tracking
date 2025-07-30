package com.example.vts.Activity.VehicleSummaryReport;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vts.Activity.TripSummaryReportDetailActivity;
import com.example.vts.Activity.VehicleSummaryReportDetailsActivity;
import com.example.vts.R;

import java.util.List;

public  class TripSummaryAdpater extends RecyclerView.Adapter<TripSummaryAdpater.UsersViewHolder>  {
    Context context;
    List<TripSummarygetset> userListResponseData;

    public TripSummaryAdpater(Context context, List<TripSummarygetset> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.tripsummaryreport, parent, false);
        TripSummaryAdpater.UsersViewHolder viewHolder = new TripSummaryAdpater.UsersViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        TripSummarygetset userlist = userListResponseData.get(position);
        EravanaInfoDetail eravanna=userlist.getEravanaInfoDetail();
        VehicleDetail vehicleDetail=userlist.getVehicleDetail();
        DriverManagementItem driverManagementItem=vehicleDetail.getDriverManagementItem();

        holder.vecno.setText(vehicleDetail.getVehicleNo());
        holder.Source.setText(eravanna.getSourceName());
        holder.Destination.setText(eravanna.getDestinationName());
        holder.Tripname.setText(eravanna.getName());
        holder.startdate.setText(eravanna.getEravanaDate());
        holder.enddate.setText(eravanna.getEravanaEndDate());
        holder.avgspee.setText(userlist.getAvgSpeed());
        holder.Netweight.setText(eravanna.getNetWeight());
        holder.Drivername.setText(driverManagementItem.getDriverName());
        System.out.println("bhjjghjghghg"+eravanna.getDistanceCovered()+"   "+vehicleDetail.getAvgSpeed());
        holder.ll_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, TripSummaryReportDetailActivity.class);
                intent.putExtra("Vechiclename",vehicleDetail.getVehicleName());
                intent.putExtra("Vechiclenumber",vehicleDetail.getVehicleNo());
                intent.putExtra("Vechicletype",vehicleDetail.getVehicleType());
                intent.putExtra("Vechiclemodel",vehicleDetail.getVehicleModel());
                intent.putExtra("RFID",vehicleDetail.getRFId());
                intent.putExtra("unique_id",eravanna.getDeviceId());
                intent.putExtra("startaddress",eravanna.getSourceName());
                intent.putExtra("Endaddress",eravanna.getDestinationName());
                intent.putExtra("totaldist",eravanna.getDistanceCovered());
                intent.putExtra("averagespeed",vehicleDetail.getAvgSpeed());
                intent.putExtra("startdatetime",eravanna.getEravanaDate());
                intent.putExtra("enddatetime",eravanna.getEravanaEndDate());
                intent.putExtra("tripname",eravanna.getName());
                intent.putExtra("Netweight",eravanna.getNetWeight());
                intent.putExtra("DriverIdentity",driverManagementItem.getIdentityNo());
                intent.putExtra("DriverName",driverManagementItem.getDriverName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userListResponseData.size();
    }



    class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView Tripname,startdate,enddate,Source,Destination,totaldist,vecno,Drivername,avgspee,Netweight;
        LinearLayout ll_view;
        public UsersViewHolder(View itemView) {
            super(itemView);
            vecno = (TextView) itemView.findViewById(R.id.vecno);
            Tripname = (TextView) itemView.findViewById(R.id.Tripname);
            startdate = (TextView) itemView.findViewById(R.id.startdate);
            totaldist = (TextView) itemView.findViewById(R.id.totaldist);
            enddate = (TextView) itemView.findViewById(R.id.enddate);
            Source = (TextView) itemView.findViewById(R.id.Source);
            avgspee = (TextView) itemView.findViewById(R.id.avgspee);
            Destination = (TextView) itemView.findViewById(R.id.Destination);
            Drivername = (TextView) itemView.findViewById(R.id.Drivername);
            Netweight = (TextView) itemView.findViewById(R.id.Netweight);
            ll_view = (LinearLayout) itemView.findViewById(R.id.ll_view);

        }
    }
}
