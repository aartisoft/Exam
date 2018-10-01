package com.essam.microprocess.dressamdaher.MainModle;

import android.util.Log;

import com.essam.microprocess.dressamdaher.Contracts.StudentManagementContract;
import com.essam.microprocess.dressamdaher.Enums.DataBase_Refrences;
import com.essam.microprocess.dressamdaher.JsonModel.FullRegisterForm;
import com.essam.microprocess.dressamdaher.MainPresnter.StudentMangementPresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by microprocess on 2018-10-01.
 */

public class StudentManagementModel implements StudentManagementContract.model {

    private StudentManagementContract.presenter presenter;
    private List<FullRegisterForm> StudentsDetails ;


    public StudentManagementModel(StudentMangementPresenter studentMangementPresenter) {

        this.presenter = studentMangementPresenter;
        StudentsDetails = new ArrayList<>();

    }

    @Override
    public void getstudentData() {

        DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child(DataBase_Refrences.USERREF.getRef());

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        FullRegisterForm g1 = data.getValue(FullRegisterForm.class);
                        Log.d("tag2", g1.getPhone());
                        StudentsDetails.add(g1);
                        presenter.SendListToView(StudentsDetails);
                    }
                }
                else {

                    //not exist
                    presenter.problem("No Student");
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
