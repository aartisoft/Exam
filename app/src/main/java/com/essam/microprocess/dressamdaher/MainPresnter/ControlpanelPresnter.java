package com.essam.microprocess.dressamdaher.MainPresnter;

import com.essam.microprocess.dressamdaher.Contracts.ControlPanelContract;
import com.essam.microprocess.dressamdaher.MainModle.ControlPanelModel;
import com.google.firebase.auth.FirebaseAuth;

public class ControlpanelPresnter implements ControlPanelContract.ControlPresnterUI {

    private ControlPanelContract.ControlUI view;
    private ControlPanelModel controlPanelModel;

    public ControlpanelPresnter(ControlPanelContract.ControlUI view) {
        this.view = view;
        controlPanelModel = new ControlPanelModel();
    }

    @Override
    public void updateUitoViews() {
        view.initializeViews();
    }

    @Override
    public void checkModel(FirebaseAuth auth,String email , String password) {
        controlPanelModel.checking(this,auth,email,password);
    }

    @Override
    public void datatrue() {
        view.emailandpasstrueexit();
    }

    @Override
    public void datanotTrue(String E) {
        view.emailandpassnottrue(E);
    }
}
