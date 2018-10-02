package com.essam.microprocess.dressamdaher.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.essam.microprocess.dressamdaher.JsonModel.FullRegisterForm;
import com.essam.microprocess.dressamdaher.Permissions.Call_permission;
import com.essam.microprocess.dressamdaher.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by microprocess on 2018-10-01.
 */

public class StudentManagementAdapter extends RecyclerView.Adapter<StudentManagementAdapter.ViewHolder> {

    List<FullRegisterForm> items = new ArrayList<>();
    int photosCounter = 0 ;
    Context context;
    public  StudentManagementAdapter (Context context, List<FullRegisterForm> items ){

        this.items = items ;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.students_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {



            holder.Name.setText(items.get(position).getNameStudent());
            holder.Contry.setText(items.get(position).getCountry());
            holder.phone.setText(items.get(position).getPhone());
            holder.email.setText(items.get(position).getEmail());

            //photos changer .
            if (photosCounter == 0 ) {
                holder.circleImageView.setBackgroundResource(R.drawable.ic_student_1);
                photosCounter ++ ;
            }
            else if (photosCounter == 1) {
                holder.circleImageView.setBackgroundResource(R.drawable.ic_student_2);
                photosCounter ++ ;
            }
            else {
                holder.circleImageView.setBackgroundResource(R.drawable.ic_student_3);
                photosCounter = 0;
            }


            //animation
            holder.Press_on_CardView.setScaleX(.9f);
            holder.Press_on_CardView.setScaleY(.9f);
            holder.Press_on_CardView.animate().scaleX(1f).scaleY(1f).setDuration(500);

            // When press on card it should Display Card Down layout .
            holder.Press_on_CardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(holder.CardDownlayout.isShown()){
                        holder.dropDown.setImageResource(R.drawable.ic_dropdown);
                        holder.CardDownlayout.setVisibility(View.GONE);

                    }
                    else {
                        holder.dropDown.setImageResource(R.drawable.ic_dropup);
                        holder.CardDownlayout.setVisibility(View.VISIBLE);
                        holder.CardDownlayout.setScaleX(0.0f);
                        holder.CardDownlayout.setScaleY(0.0f);
                        holder.CardDownlayout.animate().scaleX(1f).scaleY(1f).setDuration(300);
                    }

                }
            });

            //Call the Number
            holder.phone_Linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Call_permission permission = new Call_permission(context);

                    if (!permission.checkPermissionForCamera()) {
                        permission.requestPermissionForCamera();
                    } else {

                        permission.openCaller(context,items.get(position).getPhone()); // Caller
                    }



                }
            });


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

        @BindView(R.id.circleimage)
        CircleImageView circleImageView;

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

        @BindView(R.id.Details_layout)
        RelativeLayout CardDownlayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
