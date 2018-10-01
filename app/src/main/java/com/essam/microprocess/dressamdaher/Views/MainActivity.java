package com.essam.microprocess.dressamdaher.Views;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.essam.microprocess.dressamdaher.Contracts.MainActivityContract;
import com.essam.microprocess.dressamdaher.Fragment.First_Fragment;
import com.essam.microprocess.dressamdaher.Fragment.Register_Fragment;
import com.essam.microprocess.dressamdaher.Fragment.Signin_Fragment;
import com.essam.microprocess.dressamdaher.R;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.Main_fragment,new First_Fragment(),"firestFrag")
                .commit();

    }

    @Override
    public void showFragmentRegister() {
        // showing register fragment from Firt Fragment ..............//


        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Main_fragment,new Register_Fragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showLoginFragment() {

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.Main_fragment,new Signin_Fragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void openControlPanel(String email , String password) {
        //  لما المستخدم يسجل ويدخل علي الصفحه بتاعته
        Intent intent = new Intent(this,ControlPanel.class);
        intent.putExtra("email",email);
        intent.putExtra("pass",password);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {


            super.onBackPressed();



    }
}
