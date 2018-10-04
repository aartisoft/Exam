package com.essam.microprocess.dressamdaher.MainModle;

import android.support.annotation.NonNull;

import com.essam.microprocess.dressamdaher.Contracts.QuestionsBankContract;
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

public class Question_BankModel implements QuestionsBankContract.model {

    QuestionsBankContract.presenter presenter;
    List<Questions_Form> StudentsDetails;

    public Question_BankModel(QuestionsBankContract.presenter presenter) {
        this.presenter = presenter;
        StudentsDetails = new ArrayList<>();
    }

    @Override
    public void getQuestionData() {
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child(DataBase_Refrences.BANKQUESTIONS.getRef());

        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Questions_Form g1 = data.getValue(Questions_Form.class);
                        StudentsDetails.add(g1);
                        presenter.SendListToView(StudentsDetails);
                    }
                } else {

                    //not exist
                    presenter.problem("No Questions");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                //problem
                presenter.problem(databaseError.getMessage());


            }
        });
    }
}