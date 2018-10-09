package com.essam.microprocess.dressamdaher.Contracts;

import com.google.firebase.auth.FirebaseAuth;

public interface ControlPanelContract {

    interface ControlUI{
        void updatefragToTime(int hour , int minite);
        void initializeViews();
        void emailandpasstrueexit();
        void emailandpassnottrue(String E);
        void whenClickFAB_showFrag();
        void CheckifUserBannedResult(String Result);
        void editQuestions(String questionID , String val);
        void editSuccessopenBank();

        void SetUsername(String nameStudent);
        void AdminTools();
    }



    interface ControlPresnterUI{

        void updateUitoViews();
        void checkModel(FirebaseAuth auth , String email , String password);
        void datatrue();
        void datanotTrue(String E);
        void CheckifUserBanned(String Uid);
        void CheckifUserBannedResult(String Result);

        void CheckifAdmin(String uid);

        void HeIsAdmin();

        void getuserName(String uid);

        void SetUsername(String nameStudent);
    }



    interface ControlModelUI{

        void checking(ControlPanelContract.ControlPresnterUI controlPresnterUI,FirebaseAuth auth, String email , String password);
        void CheckifUserBanned(String Uid);

        void CheckifAdmin(String Uid);

        void getuserName(String uid);
    }

}
