package com.example.vts.VechoeownerPack;


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

import com.bumptech.glide.Glide;
import com.example.vts.Activity.VechicleDetailsActivity;
import com.example.vts.DriverPack.Drivergetset;
import com.example.vts.R;
import com.example.vts.SitePackage.SiteGetset;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public abstract class VechicleownerListAdapter extends RecyclerView.Adapter<VechicleownerListAdapter.UsersViewHolder> implements Filterable{

    Context context;
    List<Vechicleownergetset> userListResponseData;
    List<Vechicleownergetset> FullList;
    public VechicleownerListAdapter(Context context, List<Vechicleownergetset> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
        FullList = new ArrayList<>(userListResponseData);
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.vechicleowner, parent, false);
        VechicleownerListAdapter.UsersViewHolder viewHolder = new VechicleownerListAdapter.UsersViewHolder(listItem);
        return viewHolder;
//        View view = LayoutInflater.from(context).inflate(R.layout.driver, null);
//        UsersViewHolder usersViewHolder = new UsersViewHolder(view);
//        return usersViewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        Vechicleownergetset userlist = userListResponseData.get(position);
        // set the data
        holder.tirlr.setText( userlist.getOwnerName());
        holder.phone.setText( userlist.getMobile());
        System.out.println("hjghghghghghh"+userlist.getProfileImagePath());

        if(!userlist.getProfileImagePath().equalsIgnoreCase("")){
            Glide.with(context)
                    .load(userlist.getProfileImagePath())
                    .error(R.drawable.dumyimgae)
                    .into(holder.menuimage1);
        }
        if(!userlist.getProfileImagePath().equalsIgnoreCase("null")){
            Glide.with(context)
                    .load(userlist.getProfileImagePath())
                    .error(R.drawable.dumyimgae)
                    .into(holder.menuimage1);
        }

        if(!userlist.getProfileImagePath().equalsIgnoreCase(null)){
            Glide.with(context)
                    .load(userlist.getProfileImagePath())
                    .error(R.drawable.dumyimgae)
                    .into(holder.menuimage1);
        }
        holder.delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, VechicleDetailsActivity.class);
                intent.putExtra("Vechileid",userlist.getVehicleOwnerId());
                context.startActivity(intent);
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
    @Override
    public Filter getFilter() {
        return Searched_Filter;
    }
    private  Filter Searched_Filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Vechicleownergetset> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(FullList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Vechicleownergetset item : FullList) {
                    if (item.getOwnerName().toLowerCase().contains(filterPattern)||item.getMobile().toLowerCase().contains(filterPattern)||item.getAddress().toLowerCase().contains(filterPattern)||item.getTransportName().toLowerCase().contains(filterPattern)) {
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
        TextView tirlr, phone;
        ImageView delete_item;
        CircleImageView menuimage1;
        public UsersViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            tirlr = (TextView) itemView.findViewById(R.id.tirlr);
            phone = (TextView) itemView.findViewById(R.id.phone);
            delete_item = (ImageView) itemView.findViewById(R.id.delete_item);
            menuimage1 = (CircleImageView) itemView.findViewById(R.id.menuimage1);
        }
    }
}
