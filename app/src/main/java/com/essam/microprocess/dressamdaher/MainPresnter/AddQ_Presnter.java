package com.essam.microprocess.dressamdaher.MainPresnter;

import com.essam.microprocess.dressamdaher.Contracts.AddQuestionContract;
import com.essam.microprocess.dressamdaher.JsonModel.Questions_Form;
import com.essam.microprocess.dressamdaher.MainModle.AddQ_Model;
import com.google.firebase.database.DatabaseReference;

public class AddQ_Presnter implements AddQuestionContract.AddQPresnter {


    AddQuestionContract.AddQUI addQUI;
    AddQ_Model addQ_model ;


    public AddQ_Presnter(AddQuestionContract.AddQUI addQUI) {
        this.addQUI = addQUI;
        addQ_model  = new AddQ_Model();
    }

    @Override
    public void updateUIDataSavedSuccessfull() {

        addQUI.dataSaved();
    }

    @Override
    public void updatUiDataNotSaved(String E) {
        addQUI.problem(E);

    }

    @Override
    public void updateModelToSaveData(DatabaseReference reference, Questions_Form questions_form) {

        addQ_model.upload_Questions_toServer(reference,this,questions_form);

    }
}
