package com.example.vts.Activity.DasboradAllVechicleActivity;


import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vts.Activity.DashboardActivity;
import com.example.vts.R;
import com.example.vts.Vechilelistpack.Vechiclegetset;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public  class DasAllVechileListAdapter extends RecyclerView.Adapter<DasAllVechileListAdapter.UsersViewHolder> {

    Context context;
    List<GetAllActivitiesByClientLoginId> userListResponseData;
    onClickInterface onClickInterface;

    public DasAllVechileListAdapter(Context context, List<GetAllActivitiesByClientLoginId> userListResponseData, com.example.vts.Activity.DasboradAllVechicleActivity.onClickInterface onClickInterface) {
        this.context = context;
        this.userListResponseData = userListResponseData;
        this.onClickInterface = onClickInterface;
    }


    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.allvechicleactivity, parent, false);
        DasAllVechileListAdapter.UsersViewHolder viewHolder = new DasAllVechileListAdapter.UsersViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        GetAllActivitiesByClientLoginId userlist = userListResponseData.get(position);



        // set the data
        long etMills = Long.parseLong(userlist.getFromTs());
        long etMills1 = Long.parseLong(userlist.getToTs());

        String date  = new SimpleDateFormat("dd/MM/yyyy").format(new Date(etMills*1000));
        System.out.println("iuuiuiiyiyi"+date);
        String date1  = new SimpleDateFormat("dd/MM/yyyy").format(new Date(etMills1*1000));
        System.out.println("iuuiuiiyiyi"+date);

        holder.Vechicle.setText( userlist.getVehicleNumber());
        holder.fromdate.setText( date);
        holder.todate.setText( date1);
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickInterface.setClick(position);
            }
        });
//        holder.stsus.setText( userlist.getVehicleOwnerName());
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
//        holder.delete_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                BackPressed(position,userListResponseData.get(position).getVehicleId());
//            }
//        });
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
        TextView Vechicle, fromdate,todate;
LinearLayout ll;
        public UsersViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            Vechicle = (TextView) itemView.findViewById(R.id.tirlr);
            fromdate = (TextView) itemView.findViewById(R.id.desc);
            todate = (TextView) itemView.findViewById(R.id.stsus);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);

        }
    }
}
