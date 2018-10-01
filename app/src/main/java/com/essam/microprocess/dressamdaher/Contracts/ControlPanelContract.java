package com.essam.microprocess.dressamdaher.Contracts;

import com.google.firebase.auth.FirebaseAuth;

public interface ControlPanelContract {

    interface ControlUI{

        void initializeViews();
        void emailandpasstrueexit();
        void emailandpassnottrue(String E);


    }



    interface ControlPresnterUI{

        void updateUitoViews();
        void checkModel(FirebaseAuth auth , String email , String password);
        void datatrue();
        void datanotTrue(String E);

    }



    interface ControlModelUI{

        void checking(ControlPanelContract.ControlPresnterUI controlPresnterUI,FirebaseAuth auth, String email , String password);

    }

}
