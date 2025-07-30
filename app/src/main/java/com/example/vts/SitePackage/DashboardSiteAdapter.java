package com.example.vts.SitePackage;


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

import com.example.vts.Activity.DasboradAllVechicleActivity.onClickInterface;
import com.example.vts.Activity.SiteDeteilsActivity;
import com.example.vts.DriverPack.Drivergetset;
import com.example.vts.R;

import java.util.ArrayList;
import java.util.List;

public abstract class DashboardSiteAdapter extends RecyclerView.Adapter<DashboardSiteAdapter.UsersViewHolder> implements Filterable {

    Context context;
    List<SiteGetset> userListResponseData;
    List<SiteGetset> FullList;
    private  int selecteditempostion=-1;
    public DashboardSiteAdapter(Context context, List<SiteGetset> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
        FullList = new ArrayList<>(userListResponseData);

    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.dashsitelist, parent, false);
        DashboardSiteAdapter.UsersViewHolder viewHolder = new DashboardSiteAdapter.UsersViewHolder(listItem);
        return viewHolder;
//        View view = LayoutInflater.from(context).inflate(R.layout.roleslist, null);
//        UsersViewHolder usersViewHolder = new UsersViewHolder(view);
//        return usersViewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder,int position) {
        holder.sitenmame.setText( userListResponseData.get(position).getName());
        holder.lll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 selecteditempostion=position;
                 notifyDataSetChanged();
            }
        });
        if(selecteditempostion==position){
            holder.lll.setBackgroundResource(R.drawable.dashbox);
            holder.sitenmame.setTextColor(context.getResources().getColor(R.color.white));
            delete(userListResponseData.get(position).getSiteId());
        }else {
            holder.lll.setBackgroundResource(R.drawable.oe);
            holder.sitenmame.setTextColor(context.getResources().getColor(R.color.black1));
        }
    }

    public void removeAt(int position) {
        userListResponseData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, userListResponseData.size());
    }
    public abstract void delete(String id);
    @Override
    public int getItemCount() {
        return userListResponseData.size(); // size of the list items
    }
    @Override
    public Filter getFilter() {
        return Searched_Filter;
    }
    private  Filter Searched_Filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<SiteGetset> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(FullList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (SiteGetset item : FullList) {
                    if (item.getName().toLowerCase().contains(filterPattern)||item.getCountry().toLowerCase().contains(filterPattern)||item.getAddress().toLowerCase().contains(filterPattern)||item.getState().toLowerCase().contains(filterPattern)) {
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
        // init the item view's
        TextView sitenmame;
        LinearLayout lll;
        public UsersViewHolder(View itemView) {
            super(itemView);
            sitenmame = (TextView) itemView.findViewById(R.id.sitenmame);
            lll = (LinearLayout) itemView.findViewById(R.id.statefilter);
        }
    }
}
