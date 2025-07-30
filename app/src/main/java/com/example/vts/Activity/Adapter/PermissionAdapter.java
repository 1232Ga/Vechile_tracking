package com.example.vts.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.example.vts.R;
import java.util.List;

public class PermissionAdapter  extends RecyclerView.Adapter<PermissionAdapter.UsersViewHolder> {
    Context context;

    List<PermissionGetset> userListResponseData;
    public PermissionAdapter(Context context, List<PermissionGetset> userListResponseData) {
        this.context = context;
        this.userListResponseData = userListResponseData;
    }

    @NonNull
    @Override
    public PermissionAdapter.UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.rolepermission, parent, false);
        PermissionAdapter.UsersViewHolder viewHolder = new PermissionAdapter.UsersViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PermissionAdapter.UsersViewHolder holder, int position) {
        holder.title.setText(userListResponseData.get(position).tiltle);
        System.out.println("hhghgfhfhfh"+userListResponseData.get(position).isAdd());
        holder.rolecheck.setChecked(userListResponseData.get(position).isAdd());
        holder.roleview.setChecked(userListResponseData.get(position).isView());
        holder.roledelete.setChecked(userListResponseData.get(position).isDelete());


        holder.rolecheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(!holder.rolecheck.isChecked()){
                     System.out.println("hujghgjgjg");
                     holder.rolecheck.setChecked(false);
                     userListResponseData.get(position).Add=false;
                 }
                 else if(holder.rolecheck.isChecked()){
                     System.out.println("sdsfdsfdf");
                     holder.rolecheck.setChecked(true);
                     userListResponseData.get(position).Add=true;
                 }

            }
        });
        holder.roleview.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!holder.roleview.isChecked()){
                    holder.roleview.setChecked(false);
                    userListResponseData.get(position).View=false;
                }
                else if(holder.roleview.isChecked()){
                    holder.roleview.setChecked(true);
                    userListResponseData.get(position).View=true;
                }
            }
        });
        holder.roledelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!holder.roledelete.isChecked()){
                    holder.roledelete.setChecked(false);
                    userListResponseData.get(position).Delete=false;
                }
                else if(holder.roledelete.isChecked()) {
                    holder.roledelete.setChecked(true);
                    userListResponseData.get(position).Delete=true;
                }
            }
        });

        holder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.hiddenView.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(holder.cardView,new AutoTransition());
                    holder.hiddenView.setVisibility(View.GONE);
                    holder.arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    notifyDataSetChanged();
                }
                else {
                    TransitionManager.beginDelayedTransition(  holder. cardView,new AutoTransition());
                    holder. hiddenView.setVisibility(View.VISIBLE);
                    holder.arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return userListResponseData.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        CheckBox rolecheck,roleview,roledelete;
        ImageView arrow;
        LinearLayout hiddenView;
        LinearLayout cardView;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            rolecheck=itemView.findViewById(R.id.rolecheck);
            roleview=itemView.findViewById(R.id.roleview);
            roledelete=itemView.findViewById(R.id.roledelete);
            cardView =itemView.findViewById(R.id.base_cardview);
            arrow = itemView.findViewById(R.id.arrow_button);
            hiddenView =itemView. findViewById(R.id.hidden_view);

        }
    }
}
