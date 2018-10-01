package com.essam.microprocess.dressamdaher.Views;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.essam.microprocess.dressamdaher.Contracts.ControlPanelContract;
import com.essam.microprocess.dressamdaher.Fragment.Register_Fragment;
import com.essam.microprocess.dressamdaher.Fragment.StudentManagement;
import com.essam.microprocess.dressamdaher.MainPresnter.ControlpanelPresnter;
import com.essam.microprocess.dressamdaher.R;

public class ControlPanel extends AppCompatActivity
                          implements ControlPanelContract.ControlUI
                                   , View.OnClickListener
                                   , NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private ImageView open_nav;
    private DrawerLayout drawer;
    private NavigationView navigation;
    private TextView Title ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawaer);
        ControlpanelPresnter controlpanelPresnter = new ControlpanelPresnter(this);
        controlpanelPresnter.updateUitoViews();
    }

    @Override
    public void initializeViews() {
        toolbar    = findViewById(R.id.toolbar);
        open_nav   = findViewById(R.id.open_nav);
        drawer     = findViewById(R.id.drawer);
        navigation = findViewById(R.id.navigation);
        Title      = toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        open_nav.setOnClickListener(this);
        navigation.setNavigationItemSelectedListener(this);


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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.emams:

                Toast.makeText(this, "hhhhhhhhh", Toast.LENGTH_SHORT).show();
                //  هنا اعمل الي انت عاوزه دي قائمه الاختبارات

                break;

            case R.id.studentManger:

                //  اداره الطلاب
                Title.setText(R.string.mangeStudent);
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.Exam_Frame,new StudentManagement())
                        .addToBackStack(null)
                        .commit();

                
                break;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(Gravity.START)){
            drawer.closeDrawer(Gravity.START);
        }else {
            super.onBackPressed();
        }

    }
}
