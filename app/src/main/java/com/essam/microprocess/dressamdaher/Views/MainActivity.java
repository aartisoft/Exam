package com.essam.microprocess.dressamdaher.Views;

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
import com.essam.microprocess.dressamdaher.R;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{


    Animation openFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openFrag    = AnimationUtils.loadAnimation(this,R.anim.toright);



        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.Main_fragment,new First_Fragment(),"firestFrag")
                .commit();

    }

    @Override
    public void showFragmentRegister() {
        // showing register fragment from Firt Fragment ..............//

    }

    @Override
    public void onBackPressed() {


            super.onBackPressed();



    }
}
