package com.essam.microprocess.dressamdaher.MainModle;

import android.support.annotation.NonNull;

import com.essam.microprocess.dressamdaher.Contracts.ExamListContract;
import com.essam.microprocess.dressamdaher.Enums.DataBase_Refrences;
import com.essam.microprocess.dressamdaher.MainPresnter.ExamListPresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by microprocess on 2018-10-09.
 */

public class ExamListModel implements ExamListContract.model {

    ExamListContract.presenter presenter ;

    public ExamListModel(ExamListContract.presenter examListPresenter) {
        presenter = examListPresenter;
    }

    @Override
    public void CheckifAdmin(String Uid) {

            DatabaseReference reference = FirebaseDatabase.getInstance()
                    .getReference(DataBase_Refrences.ADMIN.getRef())
                    .child(Uid);



            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){

                        presenter.ShowAdminTools();

                    }
                    else {

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

    }
}
