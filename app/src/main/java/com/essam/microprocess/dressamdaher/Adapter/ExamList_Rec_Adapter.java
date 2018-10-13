package com.essam.microprocess.dressamdaher.Adapter;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.essam.microprocess.dressamdaher.JsonModel.AddExam_pojo;
import com.essam.microprocess.dressamdaher.R;
import com.essam.microprocess.dressamdaher.Views.Exam;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by microprocess on 2018-10-08.
 */

public class ExamList_Rec_Adapter extends FirebaseRecyclerAdapter<AddExam_pojo,ViewHolder> {
    Activity context;
    String ServerDate;
    /**
     * @param modelClass      Firebase will marshall the data at a location into
     *                        an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list.
     *                        You will be responsible for populating an instance of the corresponding
     *                        view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location,
*                        using some combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     * @param ServerDate
     * @param context
     */
    public ExamList_Rec_Adapter(Class<AddExam_pojo> modelClass, int modelLayout, Class<ViewHolder> viewHolderClass, Query ref, String ServerDate, Activity context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.ServerDate = ServerDate;
        this.context = context;
    }

    @Override
    protected void populateViewHolder(ViewHolder holder, AddExam_pojo model, int position) {
        holder.ExamName.setText(model.getExamName());
        holder.Cardview.setScaleX(.9f);
        holder.Cardview.setScaleY(.9f);
        holder.Cardview.animate().scaleX(1f).scaleY(1f).setDuration(500);
        holder.ExamID = model.getExamID();



        //Data
        int dateDifference = (int) SubtractTwoDate(new SimpleDateFormat("dd-MM-yyyy"), model.getCurrentDateandTime() ,ServerDate);
        holder.Date.setText(Chcek(dateDifference));

        //StartExam
        holder.BtnStartExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Exam.class);
                context.startActivity(intent);


            }
        });

    }


    public static long SubtractTwoDate(SimpleDateFormat format, String oldDate, String newDate) {


        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String Chcek (int Days){

        if (Days == 0 ){
            return " منذ اليوم" ;
        }
        else if (Days == 1){
            return " منذ أمس";
        }
        else if (Days == 2){
            return "منذ قبل أمس";
        }
        else if (Days >= 3){
            return " ايام"+Days+"منذ ";
        }
        else {
            return "منذ ...";
        }

    }

}
