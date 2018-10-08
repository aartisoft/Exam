package com.essam.microprocess.dressamdaher.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.essam.microprocess.dressamdaher.R;

/**
 * Created by microprocess on 2018-10-08.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView ExamName;
    TextView date;
    CardView Cardview;
    public ViewHolder(View itemView) {
        super(itemView);
        ExamName = itemView.findViewById(R.id.ExamName);
        date     = itemView.findViewById(R.id.date);
        Cardview = itemView.findViewById(R.id.card);

    }
}