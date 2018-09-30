package com.essam.microprocess.dressamdaher.MainModle;

import android.support.annotation.NonNull;
import android.widget.ProgressBar;

import com.essam.microprocess.dressamdaher.Contracts.RegisterFragContracts;
import com.essam.microprocess.dressamdaher.JsonModel.FullRegisterForm;
import com.essam.microprocess.dressamdaher.JsonModel.Resister_form;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterMode implements RegisterFragContracts.ModelRegister {

    private FirebaseAuth auth ;
    private String UID;

    @Override
    public void signUP(final RegisterFragContracts.PresnterRegister presnterRegister , String Email , String passord, final DatabaseReference reference, final Resister_form resister_form) {

        auth             = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(Email,passord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()){

                    // added to authentication  //
                    UID              =  auth.getUid();
                    assert UID != null;
                    FullRegisterForm fullRegisterForm = new FullRegisterForm(resister_form.getNameStudent(),resister_form.getEmail(),resister_form.getPhone(),UID,resister_form.getCountry());
                    reference.child(UID).setValue(fullRegisterForm).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){

                                presnterRegister.updatUISuccessfull();


                            }else {
                                presnterRegister.updateUIFailed();
                            }

                        }
                    });



                }
                else {

                    presnterRegister.authProblem();

                    // not added to authentication //

                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }
}
