package com.essam.microprocess.dressamdaher.Contracts;

import com.essam.microprocess.dressamdaher.JsonModel.FullRegisterForm;
import com.essam.microprocess.dressamdaher.JsonModel.Questions_Form;

import java.util.List;

/**
 * Created by microprocess on 2018-10-05.
 */

public interface QuestionsBankContract {
    interface model {
        void getQuestionData();
        void addQuestionToAddTestRecycler(String questionID);

    }
    interface presenter{

        void callQuestionData();
        void SendListToView(List<Questions_Form> Result );
        void problem(String problem);
        void sentSuccessfully(String Result);
        void addQuestionToAddTestRecycler(String questionID);
    }
    interface view{
        void RecyclerConfig(List<Questions_Form> Result );
        void problem(String problem);
        void sentSuccessfully(String Result);

        void updateFragbyValuesTogoEditFrag(String questionID);
    }
}
