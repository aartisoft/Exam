package com.essam.microprocess.dressamdaher.MainPresnter;

import android.database.sqlite.SQLiteDatabase;

import com.essam.microprocess.dressamdaher.Contracts.ResultContract;
import com.essam.microprocess.dressamdaher.MainModle.ResultModel;

/**
 * Created by microprocess on 2018-10-16.
 */

public class ResultPresenter implements ResultContract.presenter {
    ResultContract.view view;
    ResultContract.model model;
    public ResultPresenter(ResultContract.view view) {
        this.view = view;
        model = new ResultModel(this);
    }

    @Override
    public void CountDegree(SQLiteDatabase db, String tableName) {
        model.CountDegree(db,tableName);
    }

    @Override
    public void TotalDegree(int total) {
        view.TotalDegrees(total);
    }
}
