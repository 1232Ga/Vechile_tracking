package com.example.vts.VechoeownerPack;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vts.Activity.DashboardApi.VehicleManagementItem;
import com.example.vts.Activity.VechicleDetailslistActivity;
import com.example.vts.R;

import java.util.List;

public  class VechicleownerDashboardListAdapter extends RecyclerView.Adapter<VechicleownerDashboardListAdapter.UsersViewHolder> {

    Context context;
    List<VehicleManagementItem> userListResponseData;

    public VechicleownerDashboardListAdapter(Context context, List<VehicleManagementItem> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.dashboardvechiclelist, parent, false);
        VechicleownerDashboardListAdapter.UsersViewHolder viewHolder = new UsersViewHolder(listItem);
        return viewHolder;
//        View view = LayoutInflater.from(context).inflate(R.layout.driver, null);
//        UsersViewHolder usersViewHolder = new UsersViewHolder(view);
//        return usersViewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        // set the data

        for (int i = 0; i < userListResponseData.size(); i++) {
            holder.tirlr.setText( userListResponseData.get(i).getVehicleName());
            holder.desc.setText( userListResponseData.get(i).getVehicleNo());
            //holder.phones.setText( userListResponseData.get(position).getVehicleOwnerName());
            holder.drivername.setText( userListResponseData.get(i).getRFId());
            holder.phones.setText(userListResponseData.get(i).getDeviceId());
            holder.ll_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("hjjhgjjhgjhg");
                    Intent intent=new Intent(context, VechicleDetailslistActivity.class);
                    intent.putExtra("Vechileid",userListResponseData.get(position).getVehicleId());
                    context.startActivity(intent);
                }
            });
        }


    }



    @Override
    public int getItemCount() {
        return userListResponseData.size();
    }

    static class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView tirlr, desc,phones,drivername;
        ImageView view;
        LinearLayout ll_view;

        public UsersViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            tirlr = (TextView) itemView.findViewById(R.id.tirlr);
            desc = (TextView) itemView.findViewById(R.id.desc);
            phones = (TextView) itemView.findViewById(R.id.phones);
            drivername = (TextView) itemView.findViewById(R.id.drivername);
            view = (ImageView) itemView.findViewById(R.id.view);
            ll_view = (LinearLayout) itemView.findViewById(R.id.ll_view);

        }
    }
}
