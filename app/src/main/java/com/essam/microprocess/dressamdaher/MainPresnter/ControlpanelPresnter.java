package com.essam.microprocess.dressamdaher.MainPresnter;

import com.essam.microprocess.dressamdaher.Contracts.ControlPanelContract;

public class ControlpanelPresnter implements ControlPanelContract.ControlPresnterUI {

    private ControlPanelContract.ControlUI view;

    public ControlpanelPresnter(ControlPanelContract.ControlUI view) {
        this.view = view;
    }

    @Override
    public void updateUitoViews() {
        view.initializeViews();
    }
}
