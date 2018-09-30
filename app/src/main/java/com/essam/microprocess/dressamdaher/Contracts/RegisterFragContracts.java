package com.essam.microprocess.dressamdaher.Contracts;

import android.widget.ProgressBar;

import com.essam.microprocess.dressamdaher.JsonModel.Resister_form;
import com.google.firebase.database.DatabaseReference;

public interface RegisterFragContracts {


    interface ViewRegister{

        void successDataSaved(ProgressBar progressBar);
        void failedDataNotSaved(ProgressBar progressBar);
        void updateUiAboutProblemAUTH(ProgressBar progressBar);
    }



    interface PresnterRegister{
        void authProblem(ProgressBar progressBar);
        void updatUISuccessfull(ProgressBar progressBar);
        void updateUIFailed(ProgressBar progressBar);
        void detailsForuserFromUI(String email , String password, DatabaseReference reference, Resister_form resister_form, ProgressBar progressBar);

    }

    interface ModelRegister{

        void signUP(RegisterFragContracts.PresnterRegister presnterRegister, String email , String password, DatabaseReference reference, Resister_form resister_form,ProgressBar progressBar);

    }


}
