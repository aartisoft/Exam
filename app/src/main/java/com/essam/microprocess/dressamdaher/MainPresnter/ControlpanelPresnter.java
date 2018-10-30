package com.essam.microprocess.dressamdaher.MainPresnter;

import com.essam.microprocess.dressamdaher.Contracts.ControlPanelContract;
import com.essam.microprocess.dressamdaher.MainModle.ControlPanelModel;
import com.google.firebase.auth.FirebaseAuth;

public class ControlpanelPresnter implements ControlPanelContract.ControlPresnterUI {

    private ControlPanelContract.ControlUI view;
    private ControlPanelContract.ControlModelUI controlPanelModel;

    public ControlpanelPresnter(ControlPanelContract.ControlUI view) {
        this.view = view;
        controlPanelModel = new ControlPanelModel(this);
    }

    @Override
    public void updateUitoViews() {
        view.initializeViews();
    }




    @Override
    public void CheckifUserBanned(String Uid) {
        controlPanelModel.CheckifUserBanned(Uid);
    }

    @Override
    public void CheckifUserBannedResult(String Result) {
        view.CheckifUserBannedResult(Result);
    }

    @Override
    public void CheckifAdmin(String uid) {
        controlPanelModel.CheckifAdmin(uid);
    }

    @Override
    public void HeIsAdmin() {
        view.AdminTools();
    }

    @Override
    public void getuserName(String uid) {
        controlPanelModel.getuserName(uid);
    }

    @Override
    public void SetUsername(String nameStudent) {
        view.SetUsername(nameStudent);
    }
}
