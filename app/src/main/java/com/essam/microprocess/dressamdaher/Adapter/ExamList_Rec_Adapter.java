package com.essam.microprocess.dressamdaher.Adapter;



import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;

import com.essam.microprocess.dressamdaher.ApiRetrofit.ApiMethod;
import com.essam.microprocess.dressamdaher.ApiRetrofit.Retrofit_Body;
import com.essam.microprocess.dressamdaher.Dialog.AnimatedDialog;
import com.essam.microprocess.dressamdaher.Enums.DataBase_Refrences;
import com.essam.microprocess.dressamdaher.JsonModel.AddExam_pojo;
import com.essam.microprocess.dressamdaher.JsonModel.All_Country_Details;
import com.essam.microprocess.dressamdaher.JsonModel.Zone;
import com.essam.microprocess.dressamdaher.R;
import com.essam.microprocess.dressamdaher.SqLite.SQlHelper;
import com.essam.microprocess.dressamdaher.Views.Exam;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by microprocess on 2018-10-08.
 */

public class ExamList_Rec_Adapter extends FirebaseRecyclerAdapter<AddExam_pojo,ViewHolder> {
    Activity context;
    String ServerDate;
    AnimatedDialog dialog ;
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
        dialog = new AnimatedDialog(context);
    }

    @Override
    protected void populateViewHolder(ViewHolder holder, final AddExam_pojo model, int position) {
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


                getDateAndTime(model);

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
            return "منذ "+Days+" ايام";
        }
        else {
            return "منذ ...";
        }

    }




    public void getDateAndTime(final AddExam_pojo model) {

        Map<String , String> map = new HashMap<>();
        map.put("key", DataBase_Refrences.TimeApiKey.getRef());
        map.put("format",DataBase_Refrences.Format.getRef());
        ApiMethod apiMethod =  Retrofit_Body.getRetrofit().create(ApiMethod.class);
        Call<All_Country_Details> connection = apiMethod.getTiming(map);
        connection.enqueue(new Callback<All_Country_Details>() {
            @Override
            public void onResponse(@NonNull Call<All_Country_Details> call, @NonNull Response<All_Country_Details> response) {

                if (response.isSuccessful()){

                    Zone zone = Objects.requireNonNull(response.body()).getZones().get(DataBase_Refrences.CountryNum.getINTref());
                    realtimehere(zone,model);

                }



            }

            @Override
            public void onFailure(@NonNull Call<All_Country_Details> call, @NonNull Throwable t) {



            }
        });


    }



    public String getDate(long time_stamp_server) {

//        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
//        return formatter.format(time_stamp_server);
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time_stamp_server * 1000L);
        cal.add(Calendar.HOUR , -2);
        return DateFormat.format("dd/MM/yyyy hh:mm:ss", cal).toString();

    }

    public void realtimehere(Zone zone, final AddExam_pojo model) {

        final String startTime  = getDate(zone.getTimestamp());

        //Check if User Logged in Exam Before ? .
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.STARTEDEXAM.getRef())
                .child(model.getExamID()).child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    //I Dont Know .
                    GotoExam("T"+model.getExamID());
                }
                else {

                    reference.setValue(startTime).addOnSuccessListener(new OnSuccessListener<Void>() {


                        @Override
                        public void onSuccess(Void aVoid) {
                            getRandomQestionAndPutitInSQl(model);
                            GotoExam("T"+model.getExamID());
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    void getRandomQestionAndPutitInSQl(AddExam_pojo model){

                //get Random Questions.
                int QuestionsNumber = Integer.parseInt(model.getNumberofQestion());
                int QuestionsSize = Integer.parseInt(model.getAllqestionsize());
                ArrayList<Integer> Random = new ArrayList<Integer>();
                for (int i = 0 ; i < QuestionsSize ; ++i)
                 Random.add(i);
                 Collections.shuffle(Random);




                //then Put it in Sqlite .
                SQlHelper helper = new SQlHelper(context,"T"+model.getExamID()); //substring To clear ( - ) from id.
                SQLiteDatabase db = helper.getWritableDatabase();
                for (int i = 0 ; i < QuestionsNumber ; ++i) {
                    // insert
                    ContentValues row = new ContentValues();
                    row.put(SQlHelper.ID_Qestion,model.getQuestions().get(Random.get(i)).getQuestionID());
                    row.put(SQlHelper.question,model.getQuestions().get(Random.get(i)).getQuestion() );
                    row.put(SQlHelper.answerOne, model.getQuestions().get(Random.get(i)).getAnswerOne());
                    row.put(SQlHelper.answerTwo,  model.getQuestions().get(Random.get(i)).getAnswerTwo());
                    row.put(SQlHelper.answerThree, model.getQuestions().get(Random.get(i)).getAnswerThree());
                    row.put(SQlHelper.answerFour, model.getQuestions().get(Random.get(i)).getAnswerFour());
                    row.put(SQlHelper.correctAnswer, model.getQuestions().get(Random.get(i)).getCorrectAnswer());
                    row.put(SQlHelper.Student_Answer, "");
                   long x =  db.insert("T"+model.getExamID(), null, row);
                    Log.d("ExamID",x+" / Inserted");
                }


//        String [] Cols = {SQlHelper.ID_Qestion,SQlHelper.question,SQlHelper.answerOne,SQlHelper.answerTwo,SQlHelper.answerThree
//                ,SQlHelper.answerFour,SQlHelper.correctAnswer,SQlHelper.Student_Answer};
//        Cursor Pointer = db.query("T"+model.getExamID(),Cols,null,null,null,null,null); // if there are two conditions use "owner=? and price=?"
//        while (Pointer.moveToNext()){
//
//            Log.d("ExamID",Pointer.getString(0)+" / "+Pointer.getString(1)+" / "+Pointer.getString(2)+" / "+Pointer.getString(3)
//                    +" / "+Pointer.getString(4)+" / "+Pointer.getString(5)+" / "+Pointer.getString(6));
//
//        }



    }

    void GotoExam(String SqlTableName){
                Intent intent = new Intent(context,Exam.class);
                intent.putExtra("SqlTableName",SqlTableName);
                //here Take Date ;
                context.startActivity(intent);

    }
}
