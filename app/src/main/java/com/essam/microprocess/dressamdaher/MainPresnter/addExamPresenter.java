package com.essam.microprocess.dressamdaher.MainPresnter;

import com.essam.microprocess.dressamdaher.Contracts.addExamContract;
import com.essam.microprocess.dressamdaher.JsonModel.Questions_Form;
import com.essam.microprocess.dressamdaher.MainModle.addExamModel;

import java.util.List;

/**
 * Created by microprocess on 2018-10-05.
 */

public class addExamPresenter implements addExamContract.presenter {
    addExamContract.model model;
    addExamContract.view view ;
    public addExamPresenter(addExamContract.view view){

        this.view = view ;
        model = new addExamModel(this);

    }

    @Override
    public void Problem(String Result) {
        view.Problem(Result);
    }

    @Override
    public void ConfigRecyclerview(List<Questions_Form> Questions) {
        view.ConfigRecyclerview(Questions);
    }

    @Override
    public void CallgetQestionsToRecycleView() {
        model.getQestionsToRecycleView();
    }
    public void passQestionSizeToView(int i){
        view.Update_Questions_size(i);
    }
}
