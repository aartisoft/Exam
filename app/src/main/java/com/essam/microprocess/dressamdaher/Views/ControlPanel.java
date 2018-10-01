package com.essam.microprocess.dressamdaher.Views;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.essam.microprocess.dressamdaher.Contracts.ControlPanelContract;
import com.essam.microprocess.dressamdaher.MainPresnter.ControlpanelPresnter;
import com.essam.microprocess.dressamdaher.R;

public class ControlPanel extends AppCompatActivity
                          implements ControlPanelContract.ControlUI , View.OnClickListener{

    private Toolbar toolbar;
    private ImageView open_nav;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawaer);
        ControlpanelPresnter controlpanelPresnter = new ControlpanelPresnter(this);
        controlpanelPresnter.updateUitoViews();
    }

    @Override
    public void initializeViews() {
        toolbar  = findViewById(R.id.toolbar);
        open_nav = findViewById(R.id.open_nav);
        drawer   = findViewById(R.id.drawer);
        setSupportActionBar(toolbar);
        open_nav.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view == open_nav){
            //  لو عزت تغير الاتجاه من هنا بتاع فتح ال nav
            if (drawer.isDrawerOpen(Gravity.START)){

                drawer.closeDrawer(Gravity.START);

            }else {
                drawer.openDrawer(Gravity.START);
            }

        }

    }
}
