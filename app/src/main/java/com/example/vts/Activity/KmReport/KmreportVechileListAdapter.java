package com.example.vts.Activity.KmReport;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vts.Activity.DasboradAllVechicleActivity.GetAllActivitiesByClientLoginId;
import com.example.vts.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public  class KmreportVechileListAdapter extends RecyclerView.Adapter<KmreportVechileListAdapter.UsersViewHolder> {

    Context context;
    List<GetKmSummary> userListResponseData;

    public KmreportVechileListAdapter(Context context, List<GetKmSummary> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.allvechicleactivity, parent, false);
        KmreportVechileListAdapter.UsersViewHolder viewHolder = new KmreportVechileListAdapter.UsersViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        GetKmSummary userlist = userListResponseData.get(position);
        holder.Vechicle.setText( userlist.getVehicleNumber());
        holder.fromdate.setText( userlist.getTotaldist());
        holder.todate.setText( userlist.getMonth());

    }

    @Override
    public int getItemCount() {
        return userListResponseData.size(); // size of the list items
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView Vechicle, fromdate,todate;

        public UsersViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            Vechicle = (TextView) itemView.findViewById(R.id.tirlr);
            fromdate = (TextView) itemView.findViewById(R.id.desc);
            todate = (TextView) itemView.findViewById(R.id.stsus);

        }
    }
}
