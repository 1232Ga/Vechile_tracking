package com.example.vts.RoleListPack;


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
import android.widget.Filter;
import android.widget.Filterable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vts.Activity.RoleDetailsActivity;
import com.example.vts.Activity.SiteDeteilsActivity;
import com.example.vts.DevicePack.DeviceListAdapter;
import com.example.vts.R;
import com.example.vts.SitePackage.SiteGetset;

import java.util.ArrayList;
import java.util.List;

public abstract class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> implements Filterable{

    Context context;
    List<RoleListgetset> userListResponseData;
    List<RoleListgetset> FullList;
    public UsersAdapter(Context context, List<RoleListgetset> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
        FullList = new ArrayList<>(userListResponseData);
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.roleslist, parent, false);
        UsersAdapter.UsersViewHolder viewHolder = new UsersAdapter.UsersViewHolder(listItem);
        return viewHolder;


//        View view = LayoutInflater.from(context).inflate(R.layout.roleslist, null);
//        UsersViewHolder usersViewHolder = new UsersViewHolder(view);
//        return usersViewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder,int position) {
        // set the data
        System.out.println("hjggjghggjg"+userListResponseData.get(position).getUserRoleName());
        holder.tirlr.setText( userListResponseData.get(position).getUserRoleName());
        holder.desc.setText( userListResponseData.get(position).getNotes());

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
                Intent intent=new Intent(context, RoleDetailsActivity.class);
                intent.putExtra("Siteid",userListResponseData.get(position).getUserRoleId());
                context.startActivity(intent);
                // display a toast with user name
                ///delete(userListResponseData.get(position).getId());
                //BackPressed(position,userListResponseData.get(position).getUserRoleId());
            }
        });

//        holder.edit_item1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("jikjkhjjkhkhj");
//             // edit(userListResponseData.get(position).getUserRoleId(),userListResponseData.get(position).getUserRoleName(),userListResponseData.get(position).getOrganizationName());
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
    @Override
    public Filter getFilter() {
        return Searched_Filter;
    }
    private  Filter Searched_Filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<RoleListgetset> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(FullList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (RoleListgetset item : FullList) {
                    if (item.getUserRoleName().toLowerCase().contains(filterPattern)||item.getOrganizationName().toLowerCase().contains(filterPattern)) {
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
        TextView tirlr, desc;
        //ImageButton edit_item1;
        ImageView delete_item;
        LinearLayout lll;
        public UsersViewHolder(View itemView) {
            super(itemView);
            tirlr = (TextView) itemView.findViewById(R.id.tirlr);
            desc = (TextView) itemView.findViewById(R.id.desc);
            //edit_item1 = (ImageButton) itemView.findViewById(R.id.edit_item1);
            delete_item = (ImageView) itemView.findViewById(R.id.delete_item);
            lll = (LinearLayout) itemView.findViewById(R.id.lll);
        }
    }
}
