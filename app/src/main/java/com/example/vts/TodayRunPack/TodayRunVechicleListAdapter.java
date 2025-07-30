package com.example.vts.TodayRunPack;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vts.Activity.DashboardApi.RunnungVehicleManagementlst;
import com.example.vts.R;

import java.util.List;

public  class TodayRunVechicleListAdapter extends RecyclerView.Adapter<TodayRunVechicleListAdapter.UsersViewHolder> {

    Context context;
    List<RunnungVehicleManagementlst> userListResponseData;

    public TodayRunVechicleListAdapter(Context context, List<RunnungVehicleManagementlst> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.todayvechilcerun, parent, false);
        TodayRunVechicleListAdapter.UsersViewHolder viewHolder = new TodayRunVechicleListAdapter.UsersViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        RunnungVehicleManagementlst userlist = userListResponseData.get(position);
        // set the data
        holder.vechicenumber.setText( userlist.getVehicleNo());
        holder. Name.setText( userlist.getVehicleName());
        holder.startpoint.setText( userlist.getSourceName());
        holder.destpoint.setText( userlist.getDestinationName());
//        holder.ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context, UserDetillActivity.class);
//                intent.putExtra("Vechileid",userlist.getVehicleId());
//                context.startActivity(intent);
//            }
//        });
//        holder.email.setText("Email: " + userListResponseData.get(position).getEmail());
//        // implement setONCLickListtener on itemView

//        holder.edit_item1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("jikjkhjjkhkhj");
//                edit(userListResponseData.get(position).getId(),userListResponseData.get(position).getFullName(),userListResponseData.get(position).getEmail(),userListResponseData.get(position).getStatusText());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return userListResponseData.size(); // size of the list items
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView vechicenumber,startpoint,Name,destpoint;

        public UsersViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            startpoint = (TextView) itemView.findViewById(R.id.startpoint);
            vechicenumber = (TextView) itemView.findViewById(R.id.vechicenumber);
            Name = (TextView) itemView.findViewById(R.id.Name);
            destpoint = (TextView) itemView.findViewById(R.id.destpoint);

        }
    }
}
