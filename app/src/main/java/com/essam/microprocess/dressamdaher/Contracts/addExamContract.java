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


    }

    interface presenter {

        void Problem(String Result);
        void ConfigRecyclerview(List<Questions_Form> Questions);
        void CallgetQestionsToRecycleView();
        void ClearList();
        void passQestionSizeToView(int i);
        void refreshAdapter();
    }

    interface view{
        void ConfigRecyclerview(List<Questions_Form> Questions);
        void Problem(String Result);
        void   Update_Questions_size(int lengh);
        void refreshAdapter();
    }
}
