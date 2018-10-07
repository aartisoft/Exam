package com.essam.microprocess.dressamdaher.Contracts;

import com.essam.microprocess.dressamdaher.JsonModel.Questions_Form;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by microprocess on 2018-10-05.
 */

public interface addExamContract  {

    interface  model {

        void getQestionsToRecycleView();
        void ClearList();
        void storeExaminDatabase(int hour, int minute, int second,
                                 String oneQestionDegree, String NumberofQestion, String final_degree,
                                 List<Questions_Form> questions, String ExamName, String currentDateandTime);

    }

    interface presenter {

        void Problem(String Result);
        void ConfigRecyclerview(List<Questions_Form> Questions);
        void CallgetQestionsToRecycleView();
        void ClearList();
        void passQestionSizeToView(int i);
        void refreshAdapter();

        void storeExaminDatabase(int hour, int minute, int second,
                                 String oneQestionDegree, String NumberofQestion, String final_degree,
                                 List<Questions_Form> questions, String ExamName, String currentDateandTime);
        void Successful_Storing();
    }

    interface view{
        void ConfigRecyclerview(List<Questions_Form> Questions);
        void Problem(String Result);
        void   Update_Questions_size(int lengh);
        void refreshAdapter();
        void Successful_Storing();
    }
}
