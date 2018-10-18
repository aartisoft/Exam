package com.essam.microprocess.dressamdaher.MainModle;


import android.support.annotation.NonNull;

import com.essam.microprocess.dressamdaher.Contracts.MyResultContract;
import com.essam.microprocess.dressamdaher.Enums.DataBase_Refrences;
import com.essam.microprocess.dressamdaher.JsonModel.Result_Pojo;
import com.essam.microprocess.dressamdaher.Views.Result;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by microprocess on 2018-10-18.
 */

public class MyResultModel implements MyResultContract.model {

    MyResultContract.presenter presenter;
    List<Result_Pojo> Result;
    public MyResultModel(MyResultContract.presenter presenter) {
        this.presenter = presenter;
        Result = new ArrayList<>();
    }

    @Override
    public void getMyResults() {

        Query query = FirebaseDatabase.getInstance().getReference(DataBase_Refrences.RESULT.getRef()).orderByChild("uid")
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                            Result_Pojo pojo = snapshot.getValue(Result_Pojo.class);
                            Result.add(pojo);

                        }
                        presenter.ConfigRecycler(Result);
                    }
                    else {

                        presenter.Problem("لا توجد نتائج سابقة . ");
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


}
