package com.essam.microprocess.dressamdaher.MainModle;

import android.support.annotation.NonNull;

import com.essam.microprocess.dressamdaher.Contracts.ControlPanelContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ControlPanelModel implements ControlPanelContract.ControlModelUI {




    @Override
    public void checking(final ControlPanelContract.ControlPresnterUI controlPresnterUI, FirebaseAuth auth, String email, String password) {


        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {

                    // successful
                    controlPresnterUI.datatrue();

                }
                else {

                    controlPresnterUI.datanotTrue("Error");
                }

            }
        });

    }
}
