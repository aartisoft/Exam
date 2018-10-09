package com.essam.microprocess.dressamdaher.MainPresnter;

import com.essam.microprocess.dressamdaher.Contracts.ExamListContract;
import com.essam.microprocess.dressamdaher.MainModle.ExamListModel;

/**
 * Created by microprocess on 2018-10-09.
 */

public class ExamListPresenter implements ExamListContract.presenter {
    ExamListContract.view View;
    ExamListContract.model model;
    public ExamListPresenter(ExamListContract.view view) {
        this.View = view;
        model = new ExamListModel(this);
    }

    @Override
    public void CheckifAdmin(String Uid) {

        model.CheckifAdmin(Uid);

    }

    @Override
    public void ShowAdminTools() {

        View.ShowAdminTools();

    }
}
