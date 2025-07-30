package com.example.vts.Activity.TrackingRepor;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vts.Activity.KmReport.GetKmSummary;
import com.example.vts.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public  class TrackingreportVechileListAdapter extends RecyclerView.Adapter<TrackingreportVechileListAdapter.UsersViewHolder> {

    Context context;
    List<CategoryOneField> userListResponseData;

    public TrackingreportVechileListAdapter(Context context, List<CategoryOneField> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.trackingreport, parent, false);
        TrackingreportVechileListAdapter.UsersViewHolder viewHolder = new TrackingreportVechileListAdapter.UsersViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        CategoryOneField userlist = userListResponseData.get(position);
        holder.Vechicle.setText( userlist.getUniqueid());
        long etMills = Long.parseLong(userlist.getDateTime());
        String date  = new SimpleDateFormat("dd/MM/yyyy").format(new Date(etMills*1000));
        System.out.println("iuuiuiiyiyi"+date);
        holder.fromdate.setText(date);
        holder.Address.setText( userlist.getAddress());

    }

    @Override
    public int getItemCount() {
        return userListResponseData.size(); // size of the list items
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView Vechicle, fromdate,Address;

        public UsersViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            Vechicle = (TextView) itemView.findViewById(R.id.tirlr);
            fromdate = (TextView) itemView.findViewById(R.id.desc);
            Address = (TextView) itemView.findViewById(R.id.stsus);

        }
    }
}
