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


import com.example.vts.Activity.SiteDeteilsActivity;

import com.example.vts.DriverPack.Drivergetset;
import com.example.vts.R;


import java.util.ArrayList;
import java.util.List;

public abstract class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.UsersViewHolder> implements Filterable {

    Context context;
    List<SiteGetset> userListResponseData;
    List<SiteGetset> FullList;
    public SiteAdapter(Context context, List<SiteGetset> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
        FullList = new ArrayList<>(userListResponseData);

    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.sitelist, parent, false);
        SiteAdapter.UsersViewHolder viewHolder = new SiteAdapter.UsersViewHolder(listItem);
        return viewHolder;
//        View view = LayoutInflater.from(context).inflate(R.layout.roleslist, null);
//        UsersViewHolder usersViewHolder = new UsersViewHolder(view);
//        return usersViewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder,int position) {


        holder.tirlr.setText( userListResponseData.get(position).getName());
        holder.desc.setText( userListResponseData.get(position).getAddress()+" "+userListResponseData.get(position).getCity()+" "+userListResponseData.get(position).getState());
        holder.lll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(context, MyTripActivity.class);
//                intent.putExtra("Siteid",userListResponseData.get(position).getSiteId());
//                context.startActivity(intent);
                Intent intent=new Intent(context, SiteDeteilsActivity.class);
                intent.putExtra("Siteid",userListResponseData.get(position).getSiteId());
                context.startActivity(intent);

            }
        });
        holder.delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SiteDeteilsActivity.class);
                intent.putExtra("Siteid",userListResponseData.get(position).getSiteId());
                context.startActivity(intent);
                // display a toast with user name
                ///delete(userListResponseData.get(position).getId());
              //  BackPressed(position,userListResponseData.get(position).getSiteId());
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

                delete(id,sid);
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
    public abstract void delete(String id,int position);
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
        TextView tirlr, desc;
        ImageButton edit_item1;
        ImageView delete_item;
        LinearLayout lll;
        public UsersViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            tirlr = (TextView) itemView.findViewById(R.id.tirlr);
            desc = (TextView) itemView.findViewById(R.id.desc);
            edit_item1 = (ImageButton) itemView.findViewById(R.id.edit_item1);
            delete_item = (ImageView) itemView.findViewById(R.id.delete_item);
            lll = (LinearLayout) itemView.findViewById(R.id.lll);
        }
    }
}
