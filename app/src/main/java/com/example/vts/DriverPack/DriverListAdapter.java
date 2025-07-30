package com.example.vts.DriverPack;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vts.Activity.DriverDetailActivity;
import com.example.vts.R;
import com.example.vts.VechoeownerPack.Vechicleownergetset;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public abstract class DriverListAdapter extends RecyclerView.Adapter<DriverListAdapter.UsersViewHolder> implements Filterable {
    Context context;
    List<Drivergetset> userListResponseData;
    List<Drivergetset> FullList;

    public DriverListAdapter(Context context, List<Drivergetset> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
        FullList = new ArrayList<>(userListResponseData);
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.driver, parent, false);
        DriverListAdapter.UsersViewHolder viewHolder = new DriverListAdapter.UsersViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        Drivergetset userlist = userListResponseData.get(position);
        holder.tirlr.setText( userlist.getDriverName());
        holder.phone.setText( userlist.getPhoneNo());
        if(!userlist.getProofImage().equalsIgnoreCase("")){
            Glide.with(context)
                    .load(userlist.getProofImage())
                    .error(R.drawable.dumyimgae)
                    .into(holder.menuimage1);
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DriverDetailActivity.class);
                intent.putExtra("Driverid",userlist.getDriverId());
                context.startActivity(intent);
            }
        });
    }

    public abstract void delete(int id,String position);
    @Override
    public int getItemCount() {
        return userListResponseData.size();
    }
    @Override
    public Filter getFilter() {
        return Searched_Filter;
    }
    private  Filter Searched_Filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Drivergetset> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(FullList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Drivergetset item : FullList) {
                    if (item.getDriverName().toLowerCase().contains(filterPattern)||item.getPhoneNo().toLowerCase().contains(filterPattern)||item.getAddress().toLowerCase().contains(filterPattern)||item.getIdentityNo().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            userListResponseData.clear();
            userListResponseData.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
    class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView tirlr, phone;
       ImageView view;
      CircleImageView menuimage1;
        public UsersViewHolder(View itemView) {
            super(itemView);
            tirlr = (TextView) itemView.findViewById(R.id.tirlr);
            phone = (TextView) itemView.findViewById(R.id.phone);
            view = (ImageView) itemView.findViewById(R.id.view);
            menuimage1 = (CircleImageView) itemView.findViewById(R.id.menuimage1);

        }
    }
}
