package com.essam.microprocess.dressamdaher.Contracts;


/**
 * Created by microprocess on 2018-10-08.
 */

public interface  ExamListContract  {
    interface view{
        void ShowAdminTools();
    }
    interface presenter{

        void CheckifAdmin(String Uid);
        void ShowAdminTools();

    }
    interface model {
        void CheckifAdmin(String Uid);

    }

}
