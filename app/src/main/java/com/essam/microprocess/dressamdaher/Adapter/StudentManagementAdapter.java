package com.essam.microprocess.dressamdaher.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.essam.microprocess.dressamdaher.JsonModel.FullRegisterForm;
import com.essam.microprocess.dressamdaher.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by microprocess on 2018-10-01.
 */

public class StudentManagementAdapter extends RecyclerView.Adapter<StudentManagementAdapter.ViewHolder> {

    List<FullRegisterForm> items = new ArrayList<>();

    public  StudentManagementAdapter (List<FullRegisterForm> items ){

        this.items = items ;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.students_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.Name.setText(items.get(position).getNameStudent());
            holder.Contry.setText(items.get(position).getCountry());
            holder.phone.setText(items.get(position).getPhone());
            holder.email.setText(items.get(position).getEmail());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public   class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txNameStudent)
        TextView Name ;

        @BindView(R.id.txcountry)
        TextView Contry;

        @BindView(R.id.image_dropdown)
        ImageView dropDown;

        @BindView(R.id.txphone)
        TextView phone;

        @BindView(R.id.txemail)
        TextView email ;

        @BindView(R.id.phone_Linear)
        LinearLayout phone_Linear;

        @BindView(R.id.email_linear)
        LinearLayout email_linear;

        @BindView(R.id.ban_linear)
        LinearLayout ban_linear;

        @BindView(R.id.delete_linear)
        LinearLayout delete_linear;

        @BindView(R.id.Press_on_CardView)
        CardView Press_on_CardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
