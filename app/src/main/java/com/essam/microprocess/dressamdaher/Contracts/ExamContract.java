package com.essam.microprocess.dressamdaher.Contracts;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by microprocess on 2018-10-14.
 */

public interface ExamContract {
    interface view {
        void quetionIs(String ID_Qestion, String question, String answerOne, String answerTwo, String answerThree, String answerFour, String correctAnswer);

        void Problem(String s);

        void AnswerInserted();
    }
    interface presenter{

        void getQuestion(SQLiteDatabase db , String sqlTableName);

        void quetionIs(String ID_Qestion, String question, String answerOne, String answerTwo, String answerThree, String answerFour, String correctAnswer);

        void insertAnswerInSql(SQLiteDatabase db, String sqlTableName , String ID_Qestion, String selectAnswer);

        void AnswerInserted();

        void Problem(String s);
    }
    interface model{

        void getQuestion(SQLiteDatabase db , String sqlTableName);

        void insertAnswerInSql(SQLiteDatabase db, String sqlTableName , String ID_Qestion ,String selectAnswer);
    }
}
