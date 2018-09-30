package com.essam.microprocess.dressamdaher.Contracts;


import com.essam.microprocess.dressamdaher.JsonModel.Resister_form;
import com.google.firebase.database.DatabaseReference;

public interface RegisterFragContracts {


    interface ViewRegister{

        void successDataSaved();
        void failedDataNotSaved();
        void updateUiAboutProblemAUTH(String E);
    }



    interface PresnterRegister{
        void authProblem(String E);
        void updatUISuccessfull();
        void updateUIFailed();
        void detailsForuserFromUI(String email , String password, DatabaseReference reference, Resister_form resister_form);

    }

    interface ModelRegister{

        void signUP(RegisterFragContracts.PresnterRegister presnterRegister, String email , String password, DatabaseReference reference, Resister_form resister_form);

    }


}
