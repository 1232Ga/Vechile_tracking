package com.example.vts.TriplistPack;


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

import com.example.vts.Activity.EravanaIdPAck.Eravanaidgetset;
import com.example.vts.Activity.EravanaIdPAck.VehicleManagementItem;
import com.example.vts.Activity.PaidMapdrawlineActivity;
import com.example.vts.R;
import com.example.vts.RoleListPack.RoleListgetset;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class TriplistAdapter extends RecyclerView.Adapter<TriplistAdapter.UsersViewHolder> {

    Context context;
    List<Eravanaidgetset> userListResponseData;

    public TriplistAdapter(Context context, List<Eravanaidgetset> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.manualtriplist, parent, false);
        TriplistAdapter.UsersViewHolder viewHolder = new TriplistAdapter.UsersViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder,int position) {
        // set the data
//        System.out.println("hjggjghggjg"+userListResponseData.get(position).getUserRoleName());
        holder.tripname.setText( userListResponseData.get(position).getName());
        if( userListResponseData.get(position).getStatus().equalsIgnoreCase("Running")){
            holder.Dates.setTextColor(context.getResources().getColor(R.color.active));
        }else if(userListResponseData.get(position).getStatus().equalsIgnoreCase("Proposed")) {
            holder.Dates.setTextColor(context.getResources().getColor(R.color.proposed));
        }else {
            holder.Dates.setTextColor(context.getResources().getColor(R.color.inactive));
        }
        holder.Dates.setText( userListResponseData.get(position).getStatus());
        VehicleManagementItem v=userListResponseData.get(position).getVehicleManagementItem();
        holder.Rfid.setText("("+userListResponseData.get(position).getVehicleManagementItem().getVehicleNo()+")");
        String date = userListResponseData.get(position).getGrossDate();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;
        try {
            parsedDate = inputFormat.parse(date);
            String formattedDate = outputFormat.format(parsedDate);
           // holder.Rfid.setText(formattedDate);
            System.out.println("hjhjhjghjgj"+formattedDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }




        holder.sourcename.setText( userListResponseData.get(position).getSourceName()+"\n"+userListResponseData.get(position).getDestinationName());
        //holder.destname.setText( userListResponseData.get(position).getDestinationName());

//        holder.email.setText("Email: " + userListResponseData.get(position).getEmail());
//        // implement setONCLickListtener on itemView
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // display a toast with user name
//                Toast.makeText(context, userListResponseData.get(position).getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
        holder.delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with user name
                ///delete(userListResponseData.get(position).getId());
                BackPressed(position,userListResponseData.get(position).getEravanaId());
            }
        });
        holder.lll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( userListResponseData.get(position).getStatus().equalsIgnoreCase("Running")){
                    Intent intent=new Intent(context, PaidMapdrawlineActivity.class);
                    intent.putExtra("siteid",userListResponseData.get(position).getEravanaId());
                    context.startActivity(intent);
                }else if(userListResponseData.get(position).getStatus().equalsIgnoreCase("Proposed")){
                    Intent intent=new Intent(context, PaidMapdrawlineActivity.class);
                    intent.putExtra("siteid",userListResponseData.get(position).getEravanaId());
                    context.startActivity(intent);
                }else {
                    Toast.makeText(context, "Trip Completed", Toast.LENGTH_SHORT).show();
                }


            }
        });
//
//        holder.edit_item1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("jikjkhjjkhkhj");
//              edit(userListResponseData.get(position).getUserRoleId(),userListResponseData.get(position).getUserRoleName(),userListResponseData.get(position).getOrganizationName());
//            }
//        });
    }
    public void BackPressed(int sid,String id) {

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setMessage("Do you want to Delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                delete(sid,id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
    }
    public void removeAt(int position) {
        userListResponseData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, userListResponseData.size());
    }
    public abstract void delete(int id,String position);
    public abstract void edit(String id,String rolename,String orgname);
    @Override
    public int getItemCount() {
        return userListResponseData.size(); // size of the list items
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView tripname, Dates,sourcename,Rfid;
        ImageButton edit_item1;
        ImageView delete_item;
        LinearLayout lll;
        public UsersViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            tripname = (TextView) itemView.findViewById(R.id.tripname);
            Dates = (TextView) itemView.findViewById(R.id.Date);
            sourcename = (TextView) itemView.findViewById(R.id.sourcename);
            Rfid = (TextView) itemView.findViewById(R.id.Rfid);
            //destname = (TextView) itemView.findViewById(R.id.destname);
          //  edit_item1 = (ImageButton) itemView.findViewById(R.id.edit_item1);
            delete_item = (ImageView) itemView.findViewById(R.id.delete_item);
            lll = (LinearLayout) itemView.findViewById(R.id.lll);
        }
    }
}
