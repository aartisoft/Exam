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
    public void authProblem(ProgressBar progressBar) {
        view.updateUiAboutProblemAUTH(progressBar);
    }

    @Override
    public void updatUISuccessfull(ProgressBar progressBar) {
        view.successDataSaved(progressBar);
    }

    @Override
    public void updateUIFailed(ProgressBar progressBar) {
        view.failedDataNotSaved(progressBar);
    }

    @Override
    public void detailsForuserFromUI(String email, String password, DatabaseReference reference, Resister_form resister_form, ProgressBar progressBar) {

        registerMode.signUP(this,email,password,reference,resister_form,progressBar);
    }
}
