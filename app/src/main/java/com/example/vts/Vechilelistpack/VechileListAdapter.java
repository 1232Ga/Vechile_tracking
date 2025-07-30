package com.example.vts.Vechilelistpack;


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

import com.example.vts.Activity.VechicleDetailslistActivity;
import com.example.vts.R;
import com.example.vts.SitePackage.SiteGetset;


import java.util.ArrayList;
import java.util.List;

public abstract class VechileListAdapter extends RecyclerView.Adapter<VechileListAdapter.UsersViewHolder> implements Filterable {

    Context context;
    List<Vechiclegetset> userListResponseData;
    List<Vechiclegetset> FullList;
    public VechileListAdapter(Context context, List<Vechiclegetset> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
        FullList = new ArrayList<>(userListResponseData);
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.vechilelist, parent, false);
        VechileListAdapter.UsersViewHolder viewHolder = new VechileListAdapter.UsersViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, final int position) {
        Vechiclegetset userlist = userListResponseData.get(position);
        // set the data
        holder.tirlr.setText( userlist.getVehicleOwnerName());
        holder.model.setText(userlist.getVehicleName());

        holder.desc.setText( "("+userlist.getVehicleNo()+")");
        //holder.stsus.setText( userlist.getVehicleOwnerName());
        holder.Rfid.setText( userlist.getRFId());

        if(userlist.getIsActive().booleanValue()==true){
            holder.status.setText("Active");
            holder.status.setTextColor(context.getResources().getColor(R.color.active));
        }else {
            holder.status.setText("Inactive");
            holder.status.setTextColor(context.getResources().getColor(R.color.inactive));
        }
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
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, VechicleDetailslistActivity.class);
                intent.putExtra("Vechileid",userListResponseData.get(position).getVehicleId());
                context.startActivity(intent);
                //BackPressed(position,userListResponseData.get(position).getVehicleId());
            }
        });
//        holder.edit_item1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("jikjkhjjkhkhj");
//                edit(userListResponseData.get(position).getId(),userListResponseData.get(position).getFullName(),userListResponseData.get(position).getEmail(),userListResponseData.get(position).getStatusText());
//            }
//        });
    }
    public abstract void edit(String id,String name,String role,String status);
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
            ArrayList<Vechiclegetset> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(FullList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Vechiclegetset item : FullList) {
                    if (item.getVehicleName().toLowerCase().contains(filterPattern)||item.getVehicleOwnerName().toLowerCase().contains(filterPattern)||item.getVehicleNo().toLowerCase().contains(filterPattern)||item.getRFId().toLowerCase().contains(filterPattern)) {
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
        TextView tirlr, desc,Rfid,status,model;
        ImageButton edit_item1;
        ImageView delete_item,view;
         LinearLayout ll;
        public UsersViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            tirlr = (TextView) itemView.findViewById(R.id.tirlr);
            desc = (TextView) itemView.findViewById(R.id.desc);
            Rfid = (TextView) itemView.findViewById(R.id.Rfid);
            model = (TextView) itemView.findViewById(R.id.model);
            status = (TextView) itemView.findViewById(R.id.status);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);
            edit_item1 = (ImageButton) itemView.findViewById(R.id.edit_item1);
            delete_item = (ImageView) itemView.findViewById(R.id.delete_item);
            view = (ImageView) itemView.findViewById(R.id.view);
        }
    }
}
