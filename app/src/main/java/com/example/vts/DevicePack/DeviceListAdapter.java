package com.example.vts.DevicePack;


import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vts.R;
import com.example.vts.UserListPack.User;

import java.util.List;

public abstract class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.UsersViewHolder> {

    Context context;
    List<Deveicegetset> userListResponseData;

    public DeviceListAdapter(Context context, List<Deveicegetset> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.device, parent, false);
        DeviceListAdapter.UsersViewHolder viewHolder = new DeviceListAdapter.UsersViewHolder(listItem);
        return viewHolder;
//        View view = LayoutInflater.from(context).inflate(R.layout.device, null);
//        UsersViewHolder usersViewHolder = new UsersViewHolder(view);
//        return usersViewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        Deveicegetset userlist = userListResponseData.get(position);
        // set the data
        holder.tirlr.setText( userlist.getDeviceName());
       holder.desc.setText( userlist.getNotes());
//        holder.stsus.setText( userlist.getStatusText());
//        holder.email.setText("Email: " + userListResponseData.get(position).getEmail());
//        // implement setONCLickListtener on itemView
        holder.delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with user name
               ///delete(userListResponseData.get(position).getId());
                BackPressed(position,userListResponseData.get(position).getDeviceId());
            }
        });
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
    @Override
    public int getItemCount() {
        return userListResponseData.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView tirlr, desc;
        ImageButton edit_item1;
        ImageView delete_item;

        public UsersViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            tirlr = (TextView) itemView.findViewById(R.id.tirlr);
            desc = (TextView) itemView.findViewById(R.id.desc);
            edit_item1 = (ImageButton) itemView.findViewById(R.id.edit_item1);
            delete_item = (ImageView) itemView.findViewById(R.id.delete_item);
        }
    }
}
