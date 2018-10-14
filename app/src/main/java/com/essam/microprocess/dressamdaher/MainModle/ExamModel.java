package com.essam.microprocess.dressamdaher.MainModle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.essam.microprocess.dressamdaher.Contracts.ExamContract;
import com.essam.microprocess.dressamdaher.MainPresnter.ExamPresenter;
import com.essam.microprocess.dressamdaher.SqLite.SQlHelper;

/**
 * Created by microprocess on 2018-10-14.
 */

public class ExamModel implements ExamContract.model {

    ExamContract.presenter presenter;

    public ExamModel(ExamContract.presenter examPresenter) {
        presenter = examPresenter;
    }

    @Override
    public void getQuestion(SQLiteDatabase db ,String sqlTableName ) {

        String [] Cols = {SQlHelper.ID_Qestion,SQlHelper.question,SQlHelper.answerOne,SQlHelper.answerTwo,SQlHelper.answerThree
                ,SQlHelper.answerFour,SQlHelper.correctAnswer,SQlHelper.Student_Answer};

        Cursor Pointer = db.query(sqlTableName,Cols,SQlHelper.Student_Answer+" = ?",new String[]{""},null,null,null);

        if(Pointer.moveToNext()){
            presenter.quetionIs(Pointer.getString(0),Pointer.getString(1),Pointer.getString(2),Pointer.getString(3)
                ,Pointer.getString(4),Pointer.getString(5),Pointer.getString(6));
        }
        else {

            presenter.Problem(" لقد انهيت الاختبار ");

        }



    }

    @Override
    public void insertAnswerInSql(SQLiteDatabase db,  String sqlTableName , String ID_Qestion,String selectAnswer) {

        ContentValues row = new ContentValues();
        row.put(SQlHelper.Student_Answer,selectAnswer);

        int Updated = db.update(sqlTableName,row,SQlHelper.ID_Qestion+" = ?",new String[]{ID_Qestion});
        if (Updated > 0 ){
            presenter.AnswerInserted();
        }
        else {
            presenter.Problem("لم يتم اضافة الاجابة السابقة");
        }

    }
}
