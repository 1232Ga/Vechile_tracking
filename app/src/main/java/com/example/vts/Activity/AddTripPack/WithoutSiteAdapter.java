package com.example.vts.Activity.AddTripPack;


import android.content.Context;
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
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vts.Activity.EravanaIdPAck.Eravanaidgetset;
import com.example.vts.Activity.EravanaIdPAck.VehicleManagementItem;
import com.example.vts.Activity.PaidMapdrawlineActivity;
import com.example.vts.Activity.TripDetailsActivity;
import com.example.vts.R;
import com.example.vts.SitePackage.SiteGetset;

import java.util.ArrayList;
import java.util.List;

public  class WithoutSiteAdapter extends RecyclerView.Adapter<WithoutSiteAdapter.UsersViewHolder> implements Filterable {

    Context context;
    List<Eravanaidgetset> userListResponseData;
    List<Eravanaidgetset> FullList;
    public WithoutSiteAdapter(Context context, List<Eravanaidgetset> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
        FullList = new ArrayList<>(userListResponseData);
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.sitewithoutsitetrip, parent, false);
        WithoutSiteAdapter.UsersViewHolder viewHolder = new WithoutSiteAdapter.UsersViewHolder(listItem);
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
        holder.sourcename.setText( userListResponseData.get(position).getSourceName()+"\n"+userListResponseData.get(position).getDestinationName());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ugjgjghhghhgh"+userListResponseData.get(position).getSiteId());

                if(userListResponseData.get(position).getSiteId()==null){
                    Intent intent=new Intent(context, TripDetailsActivity.class);
                    intent.putExtra("Tripid",userListResponseData.get(position).getEravanaId());
                    intent.putExtra("Nonsite","Nonsite");
                    context.startActivity(intent);
                }
                else {
                    Intent intent=new Intent(context, TripDetailsActivity.class);
                    intent.putExtra("Tripid",userListResponseData.get(position).getEravanaId());
                    intent.putExtra("Nonsite","Site");
                    context.startActivity(intent);
                }


            }
        });



    }

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
            ArrayList<Eravanaidgetset> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(FullList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Eravanaidgetset item : FullList) {
                    if (item.getName().toLowerCase().contains(filterPattern)||item.getSourceName().toLowerCase().contains(filterPattern)||item.getDestinationName().toLowerCase().contains(filterPattern)) {
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
