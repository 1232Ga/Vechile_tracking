package com.example.vts.Activity.EravanaIdPAck;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vts.Activity.DashboardApi.VehicleManagementItem;
import com.example.vts.R;

import java.util.List;

public  class EravanaAllRunningVechicleListAdapter extends RecyclerView.Adapter<EravanaAllRunningVechicleListAdapter.UsersViewHolder> {

    Context context;
    List<VehicleManagementItem> userListResponseData;

    public EravanaAllRunningVechicleListAdapter(Context context, List<VehicleManagementItem> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.todayvechilcerun, parent, false);
        EravanaAllRunningVechicleListAdapter.UsersViewHolder viewHolder = new EravanaAllRunningVechicleListAdapter.UsersViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        VehicleManagementItem userlist = userListResponseData.get(position);
        // set the data
        holder.vechicenumber.setText( userlist.getVehicleNo());
        holder. Name.setText( userlist.getVehicleName());
        holder.startpoint.setText( userlist.getSourceName());
        holder.destpoint.setText( userlist.getDestinationName());



    }

    @Override
    public int getItemCount() {
        return userListResponseData.size(); // size of the list items
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView vechicenumber,startpoint,Name,destpoint;
        public UsersViewHolder(View itemView) {
            super(itemView);
            startpoint = (TextView) itemView.findViewById(R.id.startpoint);
            vechicenumber = (TextView) itemView.findViewById(R.id.vechicenumber);
            Name = (TextView) itemView.findViewById(R.id.Name);
            destpoint = (TextView) itemView.findViewById(R.id.destpoint);

        }
    }
}
