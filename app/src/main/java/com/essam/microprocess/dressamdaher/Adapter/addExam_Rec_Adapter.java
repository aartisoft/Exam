package com.essam.microprocess.dressamdaher.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.essam.microprocess.dressamdaher.JsonModel.Questions_Form;
import com.essam.microprocess.dressamdaher.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by microprocess on 2018-10-05.
 */

public class addExam_Rec_Adapter extends RecyclerView.Adapter<addExam_Rec_Adapter.ViewHolder> {
    List<Questions_Form>  questions ;
    public addExam_Rec_Adapter(List<Questions_Form> questions) {
        this.questions = new ArrayList<>();
        this.questions = questions;
    }

    @NonNull
    @Override
    public addExam_Rec_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chosen_question_rec_layout,parent,false);
        return new addExam_Rec_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull addExam_Rec_Adapter.ViewHolder holder, int position) {
        holder.txQuestion.setText(questions.get(position).getQuestion());

        //animation
        holder.Cardview.setScaleX(.9f);
        holder.Cardview.setScaleY(.9f);
        holder.Cardview.animate().scaleX(1f).scaleY(1f).setDuration(500);

        //animation 2
        holder.background.setScaleX(.9f);
        holder.background.setScaleY(.9f);
        holder.background.animate().scaleX(1f).scaleY(1f).setDuration(500);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView Cardview ;
        TextView txQuestion;
        LinearLayout background;
        public ViewHolder(View itemView) {
            super(itemView);
            Cardview = itemView.findViewById(R.id.Cardview);
            txQuestion = itemView.findViewById(R.id.Question);
            background = itemView.findViewById(R.id.background);
        }
    }
}
