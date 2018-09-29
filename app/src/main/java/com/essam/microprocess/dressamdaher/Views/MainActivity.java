package com.essam.microprocess.dressamdaher.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.essam.microprocess.dressamdaher.Contracts.MainActivityContract;
import com.essam.microprocess.dressamdaher.Fragment.First_Fragment;
import com.essam.microprocess.dressamdaher.Fragment.Register_Fragment;
import com.essam.microprocess.dressamdaher.R;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{

    FrameLayout frameLayout;
    Animation openFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.Main_fragment);
        openFrag    = AnimationUtils.loadAnimation(this,R.anim.toright);



        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.Main_fragment,new First_Fragment())
                .commit();

    }

    @Override
    public void showFragmentRegister() {
        // showing register fragment from Firt Fragment ..............//
        frameLayout.startAnimation(openFrag);
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
                .replace(R.id.Main_fragment,new Register_Fragment(),"registerFrag")
                .commit();


    }
}
