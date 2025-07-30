package com.example.vts.UserListPack;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vts.Activity.UserDetillActivity;
import com.example.vts.Activity.VechicleDetailsActivity;
import com.example.vts.DevicePack.DeviceListAdapter;
import com.example.vts.R;
import com.example.vts.RoleListPack.RoleListgetset;
import com.example.vts.SitePackage.SiteGetset;

import java.util.ArrayList;
import java.util.List;

public  class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UsersViewHolder>  {
    Context context;
    List<User> userListResponseData;
    List<User> FullList;
    public UsersListAdapter(Context context, List<User> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
        FullList = new ArrayList<>(userListResponseData);
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.users, parent, false);
        UsersListAdapter.UsersViewHolder viewHolder = new UsersListAdapter.UsersViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        User userlist = userListResponseData.get(position);
        holder.tirlr.setText( userlist.getFullName());
        holder.desc.setText("Role: "+userlist.getRole());
        holder.delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, UserDetillActivity.class);
                intent.putExtra("Userid",userlist.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userListResponseData.size(); // size of the list items
    }



    class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView tirlr, desc;
        ImageView delete_item;
        LinearLayout ll;
        public UsersViewHolder(View itemView) {
            super(itemView);
            tirlr = (TextView) itemView.findViewById(R.id.tirlr);
            desc = (TextView) itemView.findViewById(R.id.desc);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);
            delete_item = (ImageView) itemView.findViewById(R.id.delete_item);
        }
    }
}
