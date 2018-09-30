package com.essam.microprocess.dressamdaher.MainPresnter;

import android.widget.ProgressBar;

import com.essam.microprocess.dressamdaher.Contracts.RegisterFragContracts;
import com.essam.microprocess.dressamdaher.JsonModel.Resister_form;
import com.essam.microprocess.dressamdaher.MainModle.RegisterMode;
import com.google.firebase.database.DatabaseReference;

public class RegisterPresnter implements RegisterFragContracts.PresnterRegister {

    private RegisterFragContracts.ViewRegister view;
    RegisterMode registerMode ;

    public RegisterPresnter(RegisterFragContracts.ViewRegister view) {

        this.view = view;
        this.registerMode = new RegisterMode();
    }

    @Override
    public void authProblem(String E) {
        view.updateUiAboutProblemAUTH(E);
    }

    @Override
    public void updatUISuccessfull() {
        view.successDataSaved();
    }

    @Override
    public void updateUIFailed() {
        view.failedDataNotSaved();
    }

    @Override
    public void detailsForuserFromUI(String email, String password, DatabaseReference reference, Resister_form resister_form) {

        registerMode.signUP(this,email,password,reference,resister_form);
    }
}
