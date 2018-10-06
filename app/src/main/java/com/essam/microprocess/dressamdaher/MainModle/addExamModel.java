package com.essam.microprocess.dressamdaher.MainModle;

import android.support.annotation.NonNull;

import com.essam.microprocess.dressamdaher.Contracts.addExamContract;
import com.essam.microprocess.dressamdaher.Enums.DataBase_Refrences;
import com.essam.microprocess.dressamdaher.JsonModel.FullRegisterForm;
import com.essam.microprocess.dressamdaher.JsonModel.Questions_Form;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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



}