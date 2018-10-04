package com.essam.microprocess.dressamdaher.Contracts;

import com.essam.microprocess.dressamdaher.JsonModel.Questions_Form;
import com.google.firebase.database.DatabaseReference;

public interface AddQuestionContract {


    interface AddQUI{

        void dataSaved();
        void problem(String E);


    }
    interface AddQPresnter{
        void updateUIDataSavedSuccessfull();
        void updatUiDataNotSaved(String E);
        void updateModelToSaveData(DatabaseReference reference , Questions_Form questions_form);

    }
    interface AddQModel{

        void upload_Questions_toServer(DatabaseReference reference , AddQuestionContract.AddQPresnter addQPresnter, Questions_Form questions_form);

    }

}
