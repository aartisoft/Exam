package com.essam.microprocess.dressamdaher.MainModle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.essam.microprocess.dressamdaher.Contracts.ResultContract;
import com.essam.microprocess.dressamdaher.SqLite.SQlHelper;

/**
 * Created by microprocess on 2018-10-16.
 */

public class ResultModel implements ResultContract.model {
    ResultContract.presenter presenter ;

    public ResultModel(ResultContract.presenter resultPresenter) {
        presenter  = resultPresenter;
    }

    @Override
    public void CountDegree(SQLiteDatabase db, String tableName) {
        int Total = 0 ;

        String [] Cols = {SQlHelper.Degree};
        Cursor Pointer = db.query(tableName,Cols,null,null,null,null,null);
        while (Pointer.moveToNext()){

            Total += Integer.parseInt(Pointer.getString(0));


        }

        presenter.TotalDegree(Total);
    }
}
