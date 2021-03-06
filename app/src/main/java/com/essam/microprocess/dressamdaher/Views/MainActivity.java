package com.essam.microprocess.dressamdaher.Views;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.crashlytics.android.Crashlytics;
import com.essam.microprocess.dressamdaher.Contracts.MainActivityContract;
import com.essam.microprocess.dressamdaher.Fragment.First_Fragment;
import com.essam.microprocess.dressamdaher.Fragment.Register_Fragment;
import com.essam.microprocess.dressamdaher.Fragment.Signin_Fragment;
import com.essam.microprocess.dressamdaher.R;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{


    FirebaseAuth auth;
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onStart() {
        super.onStart();

        //Enable collection for selected users by initializing Crashlytics from one of your app's activities
        Fabric.with(this, new Crashlytics());
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        if (auth.getCurrentUser()!=null){

            Crashlytics.setUserIdentifier(auth.getUid());
            startActivity(new Intent(this,ControlPanel.class));
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth  = FirebaseAuth.getInstance();
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
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {


            super.onBackPressed();



    }
}
