package com.essam.microprocess.dressamdaher.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.essam.microprocess.dressamdaher.JsonModel.Questions_Form;
import com.essam.microprocess.dressamdaher.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by microprocess on 2018-10-05.
 */

public class QuestionBankAdapter extends RecyclerView.Adapter<QuestionBankAdapter.ViewHolder> {
    List<Questions_Form> qestions = new ArrayList<>();
    public QuestionBankAdapter(List <Questions_Form> qestions){
        this.qestions = qestions;

    }

    @NonNull
    @Override
    public QuestionBankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.qestion_rec_layout,parent,false);
        return new QuestionBankAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionBankAdapter.ViewHolder holder, int position) {
        holder.Question.setText(qestions.get(position).getQuestion());

        //animation
        holder.Cardview.setScaleX(.9f);
        holder.Cardview.setScaleY(.9f);
        holder.Cardview.animate().scaleX(1f).scaleY(1f).setDuration(500);
    }

    @Override
    public int getItemCount() {
        return qestions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Question ;
        CardView Cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            Question = itemView.findViewById(R.id.Question);
            Cardview = itemView.findViewById(R.id.Cardview);
        }
    }
}
