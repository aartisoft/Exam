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

    }
    interface presenter {
        void Problem(String Result);
        void ConfigRecyclerview(List<Questions_Form> Questions);
        void CallgetQestionsToRecycleView();
    }
    interface view{
    void ConfigRecyclerview(List<Questions_Form> Questions);
    void Problem(String Result);
    }
}
