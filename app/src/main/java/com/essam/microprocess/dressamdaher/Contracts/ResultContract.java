package com.essam.microprocess.dressamdaher.Contracts;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by microprocess on 2018-10-16.
 */

public interface ResultContract {
    interface view {

        void TotalDegrees(int total);
    }
    interface presenter {

        void CountDegree(SQLiteDatabase db, String tableName);

        void TotalDegree(int total);
    }
    interface model {

        void CountDegree(SQLiteDatabase db, String tableName);
    }
}
