package com.example.vts.Activity.VehicleSummaryReport;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vts.Activity.VehicleSummaryReportDetailsActivity;
import com.example.vts.R;
import java.util.List;

public  class VechicleSummaryAdpater extends RecyclerView.Adapter<VechicleSummaryAdpater.UsersViewHolder>  {
    Context context;
    List<VehicleSummaryGetset> userListResponseData;

    public VechicleSummaryAdpater(Context context, List<VehicleSummaryGetset> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.vechiclesummaryreport, parent, false);
        VechicleSummaryAdpater.UsersViewHolder viewHolder = new VechicleSummaryAdpater.UsersViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        VehicleSummaryGetset userlist = userListResponseData.get(position);
        VehicleDetail vehicleDetail=userlist.getVehicleDetail();
        holder.vecno.setText(vehicleDetail.getVehicleNo());
        holder.startaddres.setText(userlist.getStartAddress());
        holder.endaddress.setText(userlist.getEndAddress());
        holder.totaldist.setText(userlist.getTotalDist());
        holder.totalruntime.setText(userlist.getTotalRunningTime());
        holder.halttime.setText(userlist.getTotalHaltTime());
        holder.Ideletime.setText(userlist.getTotalIdlingTime());
        holder.avgspee.setText(userlist.getAvgSpeed());

        holder.ll_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, VehicleSummaryReportDetailsActivity.class);
                intent.putExtra("Vechiclename",vehicleDetail.getVehicleName());
                intent.putExtra("Vechiclenumber",vehicleDetail.getVehicleNo());
                intent.putExtra("Vechicletype",vehicleDetail.getVehicleType());
                intent.putExtra("Vechiclemodel",vehicleDetail.getVehicleModel());
                intent.putExtra("RFID",vehicleDetail.getRFId());
                intent.putExtra("unique_id",userlist.getUniqueid());
                intent.putExtra("startaddress",userlist.getStartAddress());
                intent.putExtra("Endaddress",userlist.getEndAddress());
                intent.putExtra("totaldist",userlist.getTotalDist());
                intent.putExtra("totalrunn",userlist.getTotalRunningTime());
                intent.putExtra("totalhalt",userlist.getTotalHaltTime());
                intent.putExtra("averagespeed",userlist.getAvgSpeed());
                intent.putExtra("totalidle",userlist.getTotalIdlingTime());
                intent.putExtra("startdatetime",userlist.getStartTimeDate());
                intent.putExtra("enddatetime",userlist.getEndTimeDate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userListResponseData.size();
    }



    class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView vecno,startaddres,endaddress,totaldist,totalruntime,halttime,avgspee,Ideletime;
        LinearLayout ll_view;
        public UsersViewHolder(View itemView) {
            super(itemView);
            vecno = (TextView) itemView.findViewById(R.id.vecno);
            startaddres = (TextView) itemView.findViewById(R.id.startaddres);
            endaddress = (TextView) itemView.findViewById(R.id.endaddress);
            totaldist = (TextView) itemView.findViewById(R.id.totaldist);
            totalruntime = (TextView) itemView.findViewById(R.id.totalruntime);
            halttime = (TextView) itemView.findViewById(R.id.halttime);
            avgspee = (TextView) itemView.findViewById(R.id.avgspee);
            Ideletime = (TextView) itemView.findViewById(R.id.Ideletime);
            ll_view = (LinearLayout) itemView.findViewById(R.id.ll_view);

        }
    }
}
