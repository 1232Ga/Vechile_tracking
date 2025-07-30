package com.example.vts.Activity.EndTripPack;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vts.Activity.DashboardApi.Dashboardgraphgeset;
import com.example.vts.Activity.EravanaIdPAck.Eravanaidgetset;
import com.example.vts.Activity.EravanaIdPAck.VehicleManagementItem;
import com.example.vts.Activity.PaidMapdrawlineActivity;
import com.example.vts.R;

import java.util.List;

public  class SiteNameChartListAdapter extends RecyclerView.Adapter<SiteNameChartListAdapter.UsersViewHolder> {

    Context context;
    List<Dashboardgraphgeset> userListResponseData;

    public SiteNameChartListAdapter(Context context, List<Dashboardgraphgeset> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.sitename, parent, false);
        SiteNameChartListAdapter.UsersViewHolder viewHolder = new SiteNameChartListAdapter.UsersViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        Dashboardgraphgeset userlist = userListResponseData.get(position);
        holder.tripname.setText( userlist.getName()+"\n"+"("+userlist.getTotalNetWeight()+")");
    }

    @Override
    public int getItemCount() {
        return userListResponseData.size(); // size of the list items
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView tripname;
        public UsersViewHolder(View itemView) {
            super(itemView);
            tripname = (TextView) itemView.findViewById(R.id.tripname);
        }
    }
}
