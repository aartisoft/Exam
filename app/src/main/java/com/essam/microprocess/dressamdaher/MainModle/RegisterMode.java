package com.essam.microprocess.dressamdaher.MainModle;

import android.support.annotation.NonNull;
import android.widget.ProgressBar;

import com.essam.microprocess.dressamdaher.Contracts.RegisterFragContracts;
import com.essam.microprocess.dressamdaher.JsonModel.Resister_form;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterMode implements RegisterFragContracts.ModelRegister {

    private FirebaseAuth auth ;
    String UID;

    @Override
    public void signUP(final RegisterFragContracts.PresnterRegister presnterRegister , String Email , String passord, final DatabaseReference reference, final Resister_form resister_form, final ProgressBar progressBar) {

        auth             = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(Email,passord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()){

                    // added to authentication  //
                    UID              =  auth.getUid();
                    assert UID != null;
                    reference.child(UID).setValue(resister_form).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){

                                presnterRegister.updatUISuccessfull(progressBar);


                            }else {
                                presnterRegister.updateUIFailed(progressBar);
                            }

                        }
                    });



                }
                else {

                    presnterRegister.authProblem(progressBar);

                    // not added to authentication //

                }


            }
        });


    }
}
