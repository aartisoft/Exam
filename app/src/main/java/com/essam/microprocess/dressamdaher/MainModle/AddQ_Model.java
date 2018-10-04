package com.essam.microprocess.dressamdaher.MainModle;

import android.support.annotation.NonNull;

import com.essam.microprocess.dressamdaher.Contracts.AddQuestionContract;
import com.essam.microprocess.dressamdaher.JsonModel.Questions_Form;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

public class AddQ_Model implements AddQuestionContract.AddQModel {

    String QKey;

    @Override
    public void upload_Questions_toServer(DatabaseReference reference, final AddQuestionContract.AddQPresnter addQPresnter, Questions_Form questions_form) {

        reference.child(questions_form.getQuestionID()).setValue(questions_form).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                if (task.isSuccessful()){

                    addQPresnter.updateUIDataSavedSuccessfull();


                }
                else {

                    addQPresnter.updatUiDataNotSaved("Error");

                }


            }
        });


    }
}
