package com.essam.microprocess.dressamdaher.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.essam.microprocess.dressamdaher.R;

/**
 * Created by microprocess on 2018-10-19.
 */

public class ViewHolder2  extends RecyclerView.ViewHolder {

        Button BtnResults;
        TextView ExamName;
        TextView Date;
        CardView Cardview;
        public ViewHolder2(View itemView) {
            super(itemView);
            BtnResults = itemView.findViewById(R.id.ShowResults);
            ExamName   = itemView.findViewById(R.id.ExamName);
            Date       =  itemView.findViewById(R.id.date);
            Cardview = itemView.findViewById(R.id.card);
        }

}
