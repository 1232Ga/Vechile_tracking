package com.example.vts.Activity.Ignitiononoff;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.vts.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public  class IgnitionreportVechileListAdapter extends RecyclerView.Adapter<IgnitionreportVechileListAdapter.UsersViewHolder> {

    Context context;
    List<CategoryTwoField> userListResponseData;

    public IgnitionreportVechileListAdapter(Context context, List<CategoryTwoField> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.daywisereport, parent, false);
        IgnitionreportVechileListAdapter.UsersViewHolder viewHolder = new IgnitionreportVechileListAdapter.UsersViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        CategoryTwoField userlist = userListResponseData.get(position);
        holder.uniqueid.setText( userlist.getUniqueid());
        holder.distance.setText( userlist.getTotalDist()+"(Km)");

        if(!(userlist.getDate() ==null)){
            long etMills = Long.parseLong(userlist.getDate());
            String date  = new SimpleDateFormat("dd/MM/yyyy").format(new Date(etMills*1000));
            System.out.println("iuuiuiiyiyi"+date);
            holder.Dat.setText(date);
        }else {
            holder.Dat.setText("NO Date Found");
        }

        holder.Avgspeed.setText( userlist.getAvgSpeed()+"(Km)");
        holder.Maxspeed.setText( userlist.getMaxSpeed()+"(Km)");

    }

    @Override
    public int getItemCount() {
        return userListResponseData.size(); // size of the list items
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView uniqueid,Dat,distance,Avgspeed,Maxspeed;

        public UsersViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            uniqueid = (TextView) itemView.findViewById(R.id.uniqueid);
            Dat = (TextView) itemView.findViewById(R.id.Dat);
            distance = (TextView) itemView.findViewById(R.id.distance);
            Avgspeed = (TextView) itemView.findViewById(R.id.Avgspeed);
            Maxspeed = (TextView) itemView.findViewById(R.id.Maxspeed);

        }
    }
}
