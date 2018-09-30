package com.essam.microprocess.dressamdaher.MainModle;

import android.support.annotation.NonNull;
import android.widget.EditText;

import com.essam.microprocess.dressamdaher.Contracts.SigninContract;
import com.essam.microprocess.dressamdaher.Utils.ViewsEmpty;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by microprocess on 2018-09-30.
 */

public class SigninModel implements SigninContract.model {
    SigninContract.presenter presenter;
    ViewsEmpty empty ;
    FirebaseAuth auth ;
    public SigninModel(SigninContract.presenter presenter) {
        this.presenter = presenter ;
        empty = new ViewsEmpty();
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public String CheckisEmpty(EditText et_email, EditText et_password) {

        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        if (email.isEmpty()){
            et_email.setError("الرجاء كتابة الاميل");
            return "الرجاء كتابة الاميل";
        }
        else if (!isEmailValid(et_email.getText().toString())){
            et_email.setError("الرجاء كتابة الاميل بطريقة صحيحة");
            return "الرجاء كتابة الاميل بطريقة صحيحة";
        }
        else if (password.isEmpty()|| password.length() < 8 ) {
            if (password.isEmpty()) {

                et_password.setError("الرجاء كتابة كلمة السر");
                return "الرجاء كتابة كلمة السر";
            } else {
                et_password.setError("كلمة السر يجب ان يكون اكبر من 8 حروف ");
                return "كلمة السر يجب ان يكون اكبر من 8 حروف ";
            }
        }
        else {

            return "Successful";
        }



    }

    @Override
    public void logIn(String email, String password) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {

                    // successful
                    presenter.updatelogInResult("Successful");


                }
                else {


                    presenter.updatelogInResult("Failure");
                }

            }
        });
    }

    public boolean isEmailValid(CharSequence email) {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches(); //return false if not ok //return true if ok
    }

}
