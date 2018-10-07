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

    @Override
    public void ClearList() {
        model.ClearList();
    }

    public void passQestionSizeToView(int i){
        view.Update_Questions_size(i);
    }

    @Override
    public void refreshAdapter() {
        view.refreshAdapter();
    }

    @Override
    public void storeExaminDatabase(int hour, int minute, int second, String oneQestionDegree,
                                    String NumberofQestion, String final_degree, List<Questions_Form> questions,
                                    String ExamName, String currentDateandTime) {

        model.storeExaminDatabase(hour,minute,second,oneQestionDegree,
                NumberofQestion,final_degree,questions,ExamName,currentDateandTime);

    }

    @Override
    public void Successful_Storing() {
        view.Successful_Storing();
    }
}
