package com.essam.microprocess.dressamdaher.MainModle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.essam.microprocess.dressamdaher.Contracts.ResultContract;
import com.essam.microprocess.dressamdaher.Enums.DataBase_Refrences;
import com.essam.microprocess.dressamdaher.JsonModel.Result_Pojo;
import com.essam.microprocess.dressamdaher.JsonModel.WorngQestion;
import com.essam.microprocess.dressamdaher.SqLite.SQlHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

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

    @Override
    public void getWrongQestions(SQLiteDatabase db, String tableName) {
        ArrayList<WorngQestion> worngQestions = new ArrayList<>();
        String [] Cols = {SQlHelper.ID_Qestion,SQlHelper.question,SQlHelper.correctAnswer,SQlHelper.Student_Answer};
        Cursor Pointer = db.query(tableName,Cols,SQlHelper.correctAnswer+" != "+SQlHelper.Student_Answer, null,null,null,null);

        while (Pointer.moveToNext()){

            worngQestions.add(new WorngQestion(Pointer.getString(0),Pointer.getString(1),Pointer.getString(2),Pointer.getString(3)));

        }
        presenter.WrongQuestions(worngQestions);
    }

    @Override
    public void UploadResult(String examID, String uid, String examDate, String examName,
                             String finalDegree, String total, ArrayList<WorngQestion> wrongQestions) {

        DatabaseReference reference = FirebaseDatabase
                .getInstance().getReference(DataBase_Refrences.RESULT.getRef()).child(examID).child(uid);
        Result_Pojo result_pojo = new Result_Pojo(examID,uid,examDate,examName,finalDegree,total,wrongQestions);

        reference.setValue(result_pojo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                presenter.UploadSuccessFull("تم رفع نتيجة اختبارك");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                presenter.ResultUploadFaild(e.toString());

            }
        });



    }
}
