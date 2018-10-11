package com.essam.microprocess.dressamdaher.MainModle;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.essam.microprocess.dressamdaher.ApiRetrofit.ApiMethod;
import com.essam.microprocess.dressamdaher.ApiRetrofit.Retrofit_Body;
import com.essam.microprocess.dressamdaher.Contracts.addExamContract;
import com.essam.microprocess.dressamdaher.Enums.DataBase_Refrences;
import com.essam.microprocess.dressamdaher.Fragment.addExam;
import com.essam.microprocess.dressamdaher.JsonModel.AddExam_pojo;
import com.essam.microprocess.dressamdaher.JsonModel.All_Country_Details;
import com.essam.microprocess.dressamdaher.JsonModel.FullRegisterForm;
import com.essam.microprocess.dressamdaher.JsonModel.Questions_Form;
import com.essam.microprocess.dressamdaher.JsonModel.Zone;
import com.essam.microprocess.dressamdaher.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by microprocess on 2018-10-05.
 */

public class addExamModel implements addExamContract.model {
    addExamContract.presenter presenter;
    ArrayList<String> QestionID ;
    List<Questions_Form> QestionsList ;

    public addExamModel (addExamContract.presenter presenter){
        this.presenter = presenter;
        QestionID = new ArrayList<>();
        QestionsList = new ArrayList<>();
    }

    @Override
    public void getQestionsToRecycleView() {

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.CHOSENQUESTIONID.getRef());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot1) {
                if (dataSnapshot1.exists()){
                    int x = 0;
                    for (final DataSnapshot snapshot : dataSnapshot1.getChildren()){
                        x++ ;
                        DatabaseReference reference2 = FirebaseDatabase.getInstance()
                                .getReference(DataBase_Refrences.BANKQUESTIONS.getRef())
                                .child(snapshot.getKey());


                        final int finalX = x;
                        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()){
                                    Questions_Form questions_form = dataSnapshot.getValue(Questions_Form.class);
                                    QestionsList.add(questions_form);

                                    //send final result just once . when finished storing .
                                    if (dataSnapshot1.getChildrenCount() == finalX) {

                                        presenter.ConfigRecyclerview(QestionsList);
                                    }

                                }
                                else {
                                    snapshot.getRef().removeValue();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                presenter.Problem(databaseError.toString());
                            }
                        });




                    }


                }
                else {

                    presenter.Problem("لم تقم باختيار اسئلة من بنك الاسئلة .");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                presenter.Problem(databaseError.toString());
            }
        });
    }

    @Override
    public void ClearList() {





        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.CHOSENQUESTIONID.getRef());
        reference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                QestionsList.clear();
                presenter.ConfigRecyclerview(QestionsList);
                presenter.refreshAdapter();
            }
        });



    }




    @Override
    public void storeExaminDatabase(int hour, int minute, int second, String oneQestionDegree, String NumberofQestion, String final_degree, List<Questions_Form> questions, String ExamName, String currentDateandTime) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.EXAMS.getRef()).push();
        AddExam_pojo Exam = new AddExam_pojo(hour,minute,second,reference.getKey(),oneQestionDegree,NumberofQestion,final_degree,questions,ExamName,currentDateandTime);
        reference.setValue(Exam).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                presenter.Successful_Storing();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                presenter.Problem(e.toString());
            }
        });
    }

    @Override
    public void getDateAndTime(Map<String , String> map) {

        ApiMethod apiMethod =  Retrofit_Body.getRetrofit().create(ApiMethod.class);
        Call<All_Country_Details> connection = apiMethod.getTiming(map);
        connection.enqueue(new Callback<All_Country_Details>() {
            @Override
            public void onResponse(@NonNull Call<All_Country_Details> call, @NonNull Response<All_Country_Details> response) {

                if (response.isSuccessful()){

                   Zone zone = Objects.requireNonNull(response.body()).getZones().get(105);

                }



            }

            @Override
            public void onFailure(@NonNull Call<All_Country_Details> call, @NonNull Throwable t) {

            }
        });


    }


}
