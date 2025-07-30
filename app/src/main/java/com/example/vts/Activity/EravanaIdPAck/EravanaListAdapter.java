package com.example.vts.Activity.EravanaIdPAck;


import android.content.Context;
import android.content.DialogInterface;
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

import com.example.vts.Activity.PaidMapdrawlineActivity;
import com.example.vts.R;
import com.example.vts.Vechilelistpack.Vechiclegetset;

import java.util.List;

public  class EravanaListAdapter extends RecyclerView.Adapter<EravanaListAdapter.UsersViewHolder> {

    Context context;
    List<Eravanaidgetset> userListResponseData;

    public EravanaListAdapter(Context context, List<Eravanaidgetset> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.triplist, parent, false);
        EravanaListAdapter.UsersViewHolder viewHolder = new EravanaListAdapter.UsersViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        Eravanaidgetset userlist = userListResponseData.get(position);
        // set the data
        holder.tripname.setText( userListResponseData.get(position).getName());
          if( userListResponseData.get(position).getStatus().equalsIgnoreCase("Running")){
            holder.Dates.setTextColor(context.getResources().getColor(R.color.active));
        }else if(userListResponseData.get(position).getStatus().equalsIgnoreCase("Scheduled")) {
            holder.Dates.setTextColor(context.getResources().getColor(R.color.proposed));
        }else {
            holder.Dates.setTextColor(context.getResources().getColor(R.color.inactive));
        }
        holder.Dates.setText( userListResponseData.get(position).getStatus());
        System.out.println("hjhghjghghg"+userListResponseData.get(position).getLastUpdatedAt());
        holder.lastupadte.setText( userListResponseData.get(position).getLastUpdatedAt());
        VehicleManagementItem v=userListResponseData.get(position).getVehicleManagementItem();
         holder.Rfid.setText("("+userListResponseData.get(position).getVehicleManagementItem().getVehicleNo()+")");

              // holder.Rfid.setText("("+userListResponseData.get(position).getVehicleNo()+")");
        holder.sourcename.setText( userListResponseData.get(position).getSourceName()+"\n"+userListResponseData.get(position).getDestinationName());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( userListResponseData.get(position).getStatus().equalsIgnoreCase("Running")){
                    Intent intent=new Intent(context, PaidMapdrawlineActivity.class);
                    intent.putExtra("siteid",userListResponseData.get(position).getEravanaId());
                    context.startActivity(intent);
                }else if(userListResponseData.get(position).getStatus().equalsIgnoreCase("Scheduled")){
                    Toast.makeText(context, "Trip Scheduled For Future", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Trip Completed", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return userListResponseData.size(); // size of the list items
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView tripname, Dates,sourcename,Rfid,lastupadte;
        ImageButton edit_item1;
        ImageView delete_item;
        LinearLayout lll;
        LinearLayout ll;
        public UsersViewHolder(View itemView) {
            super(itemView);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);
            tripname = (TextView) itemView.findViewById(R.id.tripname);
            Dates = (TextView) itemView.findViewById(R.id.Date);
            sourcename = (TextView) itemView.findViewById(R.id.sourcename);
            Rfid = (TextView) itemView.findViewById(R.id.Rfid);
            lastupadte = (TextView) itemView.findViewById(R.id.lastupadte);
            //destname = (TextView) itemView.findViewById(R.id.destname);
            //  edit_item1 = (ImageButton) itemView.findViewById(R.id.edit_item1);
            delete_item = (ImageView) itemView.findViewById(R.id.delete_item);

        }
    }
}
