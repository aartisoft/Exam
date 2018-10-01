package com.essam.microprocess.dressamdaher.Contracts;

import com.essam.microprocess.dressamdaher.JsonModel.FullRegisterForm;

import java.util.List;

/**
 * Created by microprocess on 2018-10-01.
 */

public interface StudentManagementContract {
    interface model{

        void getstudentData();

    }
    interface presenter{
        void callStudentData();
        void SendListToView(List<FullRegisterForm> Result );
    }
    interface view {
        void RecyclerConfig(List<FullRegisterForm> Result );
    }
}
